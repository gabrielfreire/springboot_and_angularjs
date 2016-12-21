package com.gabriel.xclientapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Produto.
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "prod_codigo")
    private String prodCodigo;

    @NotNull
    @Column(name = "prod_nome", nullable = false)
    private String prodNome;

    @Column(name = "prod_marca")
    private String prodMarca;

    @NotNull
    @Column(name = "prod_preco", precision=10, scale=2, nullable = false)
    private BigDecimal prodPreco;

    @NotNull
    @Column(name = "prod_qtd", nullable = false)
    private Integer prodQtd;

    @Column(name = "prod_cor")
    private String prodCor;

    @Column(name = "valor_compra", precision=10, scale=2)
    private BigDecimal valorCompra;

    @Column(name = "qtd_minima")
    private Integer qtdMinima;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @ManyToOne
    private User user;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Fornecedor fornecedor;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Produto produto = (Produto) o;
        if(produto.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
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
