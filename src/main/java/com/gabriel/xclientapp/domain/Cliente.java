package com.gabriel.xclientapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Cliente.
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "cliente_nome_completo", nullable = false)
    private String clienteNomeCompleto;

    @NotNull
    @Column(name = "cliente_email", nullable = false)
    private String clienteEmail;

    @NotNull
    @Column(name = "cliente_telefone", nullable = false)
    private String clienteTelefone;

    @Column(name = "cliente_celular")
    private String clienteCelular;

    @NotNull
    @Column(name = "cliente_cpf_cnpj", nullable = false)
    private String clienteCpfCnpj;

    @NotNull
    @Column(name = "cliente_rua", nullable = false)
    private String clienteRua;

    @NotNull
    @Column(name = "cliente_cod_postal", nullable = false)
    private String clienteCodPostal;

    @NotNull
    @Column(name = "cliente_cidade", nullable = false)
    private String clienteCidade;

    @NotNull
    @Column(name = "cliente_estado", nullable = false)
    private String clienteEstado;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private Set<Venda> compras = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteNomeCompleto() {
        return clienteNomeCompleto;
    }

    public void setClienteNomeCompleto(String clienteNomeCompleto) {
        this.clienteNomeCompleto = clienteNomeCompleto;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    public String getClienteCelular() {
        return clienteCelular;
    }

    public void setClienteCelular(String clienteCelular) {
        this.clienteCelular = clienteCelular;
    }

    public String getClienteCpfCnpj() {
        return clienteCpfCnpj;
    }

    public void setClienteCpfCnpj(String clienteCpfCnpj) {
        this.clienteCpfCnpj = clienteCpfCnpj;
    }

    public String getClienteRua() {
        return clienteRua;
    }

    public void setClienteRua(String clienteRua) {
        this.clienteRua = clienteRua;
    }

    public String getClienteCodPostal() {
        return clienteCodPostal;
    }

    public void setClienteCodPostal(String clienteCodPostal) {
        this.clienteCodPostal = clienteCodPostal;
    }

    public String getClienteCidade() {
        return clienteCidade;
    }

    public void setClienteCidade(String clienteCidade) {
        this.clienteCidade = clienteCidade;
    }

    public String getClienteEstado() {
        return clienteEstado;
    }

    public void setClienteEstado(String clienteEstado) {
        this.clienteEstado = clienteEstado;
    }

    public Set<Venda> getCompras() {
        return compras;
    }

    public void setCompras(Set<Venda> vendas) {
        this.compras = vendas;
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
        Cliente cliente = (Cliente) o;
        if(cliente.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + id +
            ", clienteNomeCompleto='" + clienteNomeCompleto + "'" +
            ", clienteEmail='" + clienteEmail + "'" +
            ", clienteTelefone='" + clienteTelefone + "'" +
            ", clienteCelular='" + clienteCelular + "'" +
            ", clienteCpfCnpj='" + clienteCpfCnpj + "'" +
            ", clienteRua='" + clienteRua + "'" +
            ", clienteCodPostal='" + clienteCodPostal + "'" +
            ", clienteCidade='" + clienteCidade + "'" +
            ", clienteEstado='" + clienteEstado + "'" +
            '}';
    }
}
