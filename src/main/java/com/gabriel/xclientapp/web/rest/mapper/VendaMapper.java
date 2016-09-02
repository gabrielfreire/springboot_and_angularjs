package com.gabriel.xclientapp.web.rest.mapper;

import com.gabriel.xclientapp.domain.*;
import com.gabriel.xclientapp.web.rest.dto.VendaDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Venda and its DTO VendaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VendaMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.clienteNomeCompleto", target = "clienteClienteNomeCompleto")
    @Mapping(source = "itens.id", target = "itensId")
    @Mapping(source = "itens.prodNome", target = "itensProdNome")
    VendaDTO vendaToVendaDTO(Venda venda);

    List<VendaDTO> vendasToVendaDTOs(List<Venda> vendas);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "itensId", target = "itens")
    Venda vendaDTOToVenda(VendaDTO vendaDTO);

    List<Venda> vendaDTOsToVendas(List<VendaDTO> vendaDTOs);

    default Cliente clienteFromId(Long id) {
        if (id == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }

    default Produto produtoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(id);
        return produto;
    }
}
