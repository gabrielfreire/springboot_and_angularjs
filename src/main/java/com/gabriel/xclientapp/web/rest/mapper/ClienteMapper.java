package com.gabriel.xclientapp.web.rest.mapper;

import com.gabriel.xclientapp.domain.*;
import com.gabriel.xclientapp.web.rest.dto.ClienteDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Cliente and its DTO ClienteDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface ClienteMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ClienteDTO clienteToClienteDTO(Cliente cliente);

    List<ClienteDTO> clientesToClienteDTOs(List<Cliente> clientes);

    @Mapping(target = "compras", ignore = true)
    @Mapping(source = "userId", target = "user")
    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);

    List<Cliente> clienteDTOsToClientes(List<ClienteDTO> clienteDTOs);
}
