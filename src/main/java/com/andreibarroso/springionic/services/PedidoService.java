package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.domain.*;
import com.andreibarroso.springionic.domain.enums.EstadoPagamento;
import com.andreibarroso.springionic.exceptions.AuthorizationException;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.ItemPedidoRepository;
import com.andreibarroso.springionic.repositories.PagamentoRepository;
import com.andreibarroso.springionic.repositories.PedidoRepository;
import com.andreibarroso.springionic.security.UserSS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    private final BoletoService boletoService;

    private final PedidoRepository pedidoRepository;

    private final ProdutoService produtoService;

    private final PagamentoRepository pagamentoRepository;

    private ClienteService clienteService;

    private EmailService emailService;

    public PedidoService (EmailService emailService, ClienteService clienteService, ItemPedidoRepository itemPedidoRepository, ProdutoService produtoService, BoletoService boletoService, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.boletoService = boletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.produtoService = produtoService;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clienteService = clienteService;
        this.emailService = emailService;
    }

    public Pedido findPedido(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.findCliente(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }

        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);

            ip.setProduto(produtoService.findPedido(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);

        }

        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return  obj;

    }
    public Page<Pedido> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso Negado");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.findCliente(user.getId());
        return  pedidoRepository.findByCliente(cliente, pageRequest);


    }
}



