package com.gabriel.xclientapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Fornecedor.
 */
@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "forn_nome", nullable = false)
    private String fornNome;

    @NotNull
    @Column(name = "forn_cnpj", nullable = false)
    private String fornCnpj;

    @Column(name = "forn_celular")
    private String fornCelular;

    @NotNull
    @Column(name = "forn_telefone", nullable = false)
    private String fornTelefone;

    @NotNull
    @Column(name = "forn_email", nullable = false)
    private String fornEmail;

    @NotNull
    @Column(name = "forn_rua", nullable = false)
    private String fornRua;

    @NotNull
    @Column(name = "forn_cod_postal", nullable = false)
    private String fornCodPostal;

    @NotNull
    @Column(name = "forn_cidade", nullable = false)
    private String fornCidade;

    @NotNull
    @Column(name = "forn_estado", nullable = false)
    private String fornEstado;

    @OneToMany(mappedBy = "fornecedor")
    @JsonIgnore
    private Set<Produto> produtos = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFornNome() {
        return fornNome;
    }

    public void setFornNome(String fornNome) {
        this.fornNome = fornNome;
    }

    public String getFornCnpj() {
        return fornCnpj;
    }

    public void setFornCnpj(String fornCnpj) {
        this.fornCnpj = fornCnpj;
    }

    public String getFornCelular() {
        return fornCelular;
    }

    public void setFornCelular(String fornCelular) {
        this.fornCelular = fornCelular;
    }

    public String getFornTelefone() {
        return fornTelefone;
    }

    public void setFornTelefone(String fornTelefone) {
        this.fornTelefone = fornTelefone;
    }

    public String getFornEmail() {
        return fornEmail;
    }

    public void setFornEmail(String fornEmail) {
        this.fornEmail = fornEmail;
    }

    public String getFornRua() {
        return fornRua;
    }

    public void setFornRua(String fornRua) {
        this.fornRua = fornRua;
    }

    public String getFornCodPostal() {
        return fornCodPostal;
    }

    public void setFornCodPostal(String fornCodPostal) {
        this.fornCodPostal = fornCodPostal;
    }

    public String getFornCidade() {
        return fornCidade;
    }

    public void setFornCidade(String fornCidade) {
        this.fornCidade = fornCidade;
    }

    public String getFornEstado() {
        return fornEstado;
    }

    public void setFornEstado(String fornEstado) {
        this.fornEstado = fornEstado;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fornecedor fornecedor = (Fornecedor) o;
        if(fornecedor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, fornecedor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
            "id=" + id +
            ", fornNome='" + fornNome + "'" +
            ", fornCnpj='" + fornCnpj + "'" +
            ", fornCelular='" + fornCelular + "'" +
            ", fornTelefone='" + fornTelefone + "'" +
            ", fornEmail='" + fornEmail + "'" +
            ", fornRua='" + fornRua + "'" +
            ", fornCodPostal='" + fornCodPostal + "'" +
            ", fornCidade='" + fornCidade + "'" +
            ", fornEstado='" + fornEstado + "'" +
            '}';
    }
}
