package com.gabriel.xclientapp.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Fornecedor entity.
 */
public class FornecedorDTO implements Serializable {

    private Long id;

    @NotNull
    private String fornNome;

    @NotNull
    private String fornCnpj;

    private String fornCelular;

    @NotNull
    private String fornTelefone;

    @NotNull
    private String fornEmail;

    @NotNull
    private String fornRua;

    @NotNull
    private String fornCodPostal;

    @NotNull
    private String fornCidade;

    @NotNull
    private String fornEstado;


    private Long userId;
    

    private String userLogin;

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

        FornecedorDTO fornecedorDTO = (FornecedorDTO) o;

        if ( ! Objects.equals(id, fornecedorDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FornecedorDTO{" +
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
