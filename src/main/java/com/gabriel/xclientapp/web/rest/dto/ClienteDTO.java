package com.gabriel.xclientapp.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Cliente entity.
 */
public class ClienteDTO implements Serializable {

    private Long id;

    @NotNull
    private String clienteNomeCompleto;

    @NotNull
    private String clienteEmail;

    @NotNull
    private String clienteTelefone;

    private String clienteCelular;

    @NotNull
    private String clienteCpfCnpj;

    @NotNull
    private String clienteRua;

    @NotNull
    private String clienteCodPostal;

    @NotNull
    private String clienteCidade;

    @NotNull
    private String clienteEstado;


    private Long userId;
    

    private String userLogin;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClienteDTO clienteDTO = (ClienteDTO) o;

        if ( ! Objects.equals(id, clienteDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
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
