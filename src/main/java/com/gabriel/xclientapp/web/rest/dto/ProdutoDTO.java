package com.gabriel.xclientapp.web.rest.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Produto entity.
 */
public class ProdutoDTO implements Serializable {

    private Long id;

    private String prodCodigo;

    @NotNull
    private String prodNome;

    private String prodMarca;

    @NotNull
    private BigDecimal prodPreco;

    @NotNull
    private Integer prodQtd;

    private String prodCor;

    private BigDecimal valorCompra;

    private Integer qtdMinima;

    private LocalDate dataValidade;


    private Long userId;
    

    private String userLogin;

    private Long categoriaId;
    

    private String categoriaCatNome;

    private Long fornecedorId;
    

    private String fornecedorFornNome;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProdCodigo() {
        return prodCodigo;
    }

    public void setProdCodigo(String prodCodigo) {
        this.prodCodigo = prodCodigo;
    }
    public String getProdNome() {
        return prodNome;
    }

    public void setProdNome(String prodNome) {
        this.prodNome = prodNome;
    }
    public String getProdMarca() {
        return prodMarca;
    }

    public void setProdMarca(String prodMarca) {
        this.prodMarca = prodMarca;
    }
    public BigDecimal getProdPreco() {
        return prodPreco;
    }

    public void setProdPreco(BigDecimal prodPreco) {
        this.prodPreco = prodPreco;
    }
    public Integer getProdQtd() {
        return prodQtd;
    }

    public void setProdQtd(Integer prodQtd) {
        this.prodQtd = prodQtd;
    }
    public String getProdCor() {
        return prodCor;
    }

    public void setProdCor(String prodCor) {
        this.prodCor = prodCor;
    }
    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }
    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }
    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }


    public String getCategoriaCatNome() {
        return categoriaCatNome;
    }

    public void setCategoriaCatNome(String categoriaCatNome) {
        this.categoriaCatNome = categoriaCatNome;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }


    public String getFornecedorFornNome() {
        return fornecedorFornNome;
    }

    public void setFornecedorFornNome(String fornecedorFornNome) {
        this.fornecedorFornNome = fornecedorFornNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdutoDTO produtoDTO = (ProdutoDTO) o;

        if ( ! Objects.equals(id, produtoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
            "id=" + id +
            ", prodCodigo='" + prodCodigo + "'" +
            ", prodNome='" + prodNome + "'" +
            ", prodMarca='" + prodMarca + "'" +
            ", prodPreco='" + prodPreco + "'" +
            ", prodQtd='" + prodQtd + "'" +
            ", prodCor='" + prodCor + "'" +
            ", valorCompra='" + valorCompra + "'" +
            ", qtdMinima='" + qtdMinima + "'" +
            ", dataValidade='" + dataValidade + "'" +
            '}';
    }
}
