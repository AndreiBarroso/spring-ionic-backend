package com.andreibarroso.springionic;

import com.andreibarroso.springionic.domain.*;
import com.andreibarroso.springionic.domain.enums.EstadoPagamento;
import com.andreibarroso.springionic.domain.enums.TipoCliente;
import com.andreibarroso.springionic.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {


	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;
	private EstadoRepository estadoRepository;
	private CidadeRepository cidadeRepository;
	private ClienteRepository clienteRepository;
	private EnderecoRepository enderecoRepository;
	private PedidoRepository pedidoRepository;
	private PagamentoRepository pagamentoRepository;

	public SpringIonicApplication (PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository,EnderecoRepository enderecoRepository,CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.pedidoRepository = pedidoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "escritorio");
		Categoria cat2 = new Categoria(null, "informatica");

		Produto p1 = new Produto(null, "COMPUTADOR", 200.00);
		Produto p2 = new Produto(null, "IMPRESSORA", 300.00);
		Produto p3 = new Produto(null, "MOUSE", 1400.00);

		Estado estado1 = new Estado(null, "Minas gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "Taubate", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado1);


		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));


		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado1.getCidades().addAll(Arrays.asList(cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade2));


		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Cliente cli1 = new Cliente(null, "maria", "maria@gmail.com", "12345678909", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "938329098"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli1, cidade1);
		Endereco e2 = new Endereco(null, "Rua sargento", "300", "Apto 4023", "Jardim", "38220834", cli1, cidade2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/08/2019"),  cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/08/2019"),  cli1, e2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/08/2019 00:00"), null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

	}
}
