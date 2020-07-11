package com.andreibarroso.springionic.domain;

import com.andreibarroso.springionic.domain.enums.Perfil;
import com.andreibarroso.springionic.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Cliente implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String nome;


    @Column(unique = true)
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

    @JsonIgnore
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade=CascadeType.ALL)
    private final List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private  final Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private final Set<Integer> perfis =  new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private final List<Pedido> pedidos = new ArrayList<>();

    public Cliente () {

        addPerfil(Perfil.CLIENTE);

    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCod();

        addPerfil(Perfil.CLIENTE);
    }

    public TipoCliente getTipo ()  {
        return TipoCliente.toEnum(tipo);
    }
   public void setTipo (TipoCliente tipo) {
        this.tipo = tipo.getCod ();
   }
    public Set<Perfil> getPerfis(){
        return  perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }


}
