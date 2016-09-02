package com.gabriel.xclientapp.web.rest.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Venda entity.
 */
public class VendaDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime pedidoData;

    @NotNull
    private Integer pedidoQtd;

    private String formaDePagamento;


    private Long clienteId;

    

    private String clienteClienteNomeCompleto;

    private Long itensId;
    

    private String itensProdNome;

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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }


    public String getClienteClienteNomeCompleto() {
        return clienteClienteNomeCompleto;
    }

    public void setClienteClienteNomeCompleto(String clienteClienteNomeCompleto) {
        this.clienteClienteNomeCompleto = clienteClienteNomeCompleto;
    }

    public Long getItensId() {
        return itensId;
    }

    public void setItensId(Long produtoId) {
        this.itensId = produtoId;
    }


    public String getItensProdNome() {
        return itensProdNome;
    }

    public void setItensProdNome(String produtoProdNome) {
        this.itensProdNome = produtoProdNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VendaDTO vendaDTO = (VendaDTO) o;

        if ( ! Objects.equals(id, vendaDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "VendaDTO{" +
            "id=" + id +
            ", pedidoData='" + pedidoData + "'" +
            ", pedidoQtd='" + pedidoQtd + "'" +
            ", formaDePagamento='" + formaDePagamento + "'" +
            '}';
    }
}
