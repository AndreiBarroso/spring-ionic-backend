package com.andreibarroso.springionic.services;


import com.andreibarroso.springionic.domain.Cidade;
import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.domain.Endereco;
import com.andreibarroso.springionic.domain.enums.Perfil;
import com.andreibarroso.springionic.domain.enums.TipoCliente;
import com.andreibarroso.springionic.dto.ClienteDTO;
import com.andreibarroso.springionic.dto.ClienteNewDTO;
import com.andreibarroso.springionic.exceptions.AuthorizationException;
import com.andreibarroso.springionic.exceptions.DateIntegrityException;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.CidadeRepository;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import com.andreibarroso.springionic.repositories.EnderecoRepository;
import com.andreibarroso.springionic.security.UserSS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    private EnderecoRepository enderecoRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private S3Service s3Service;

    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    public ClienteService (ImageService imageService, S3Service s3Service, BCryptPasswordEncoder bCryptPasswordEncoder,  ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.s3Service = s3Service;
        this.imageService = imageService;
    }

    public Cliente findCliente (Integer id) {
        UserSS user = UserService.authenticated();
        if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw  new AuthorizationException("Acesso negado");
        }


        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public void delete (Integer id) {
        findCliente(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DateIntegrityException("Não é possivel excluir uma Cliente com produtos");
        }
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = findCliente(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }


    public List<Cliente> findAll() {
        return  clienteRepository.findAll();
    }

    /*
    método para paginação
     */

    public Page<Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  clienteRepository.findAll(pageRequest);
    }


    /*
    transformar uma entidade em DTO
     */
    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), bCryptPasswordEncoder.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }



    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture (MultipartFile multipartFile) {

        /*
        verificar se o usuario esta logado
         */
        UserSS user = UserService.authenticated();
        if (user == null ) {
            throw  new AuthorizationException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        String filename = prefix + user.getId() + ".jpg";

        return  s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), filename, "image");

    }

}


