package com.gabriel.xclientapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Venda.
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "pedido_data", nullable = false)
    private ZonedDateTime pedidoData;

    @NotNull
    @Column(name = "pedido_qtd", nullable = false)
    private Integer pedidoQtd;

    @Column(name = "forma_de_pagamento")
    private String formaDePagamento;

    @ManyToOne
    private Cliente cliente;


    @ManyToOne
    private Produto itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getPedidoData() {
        return pedidoData;
    }

    public void setPedidoData(ZonedDateTime pedidoData) {
        this.pedidoData = pedidoData;
    }

    public Integer getPedidoQtd() {
        return pedidoQtd;
    }

    public void setPedidoQtd(Integer pedidoQtd) {
        this.pedidoQtd = pedidoQtd;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Produto getItens() {
        return itens;
    }

    public void setItens(Produto produto) {
        this.itens = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Venda venda = (Venda) o;
        if(venda.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Venda{" +
            "id=" + id +
            ", pedidoData='" + pedidoData + "'" +
            ", pedidoQtd='" + pedidoQtd + "'" +
            ", formaDePagamento='" + formaDePagamento + "'" +
            '}';
    }
}
