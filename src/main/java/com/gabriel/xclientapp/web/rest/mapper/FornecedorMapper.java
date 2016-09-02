package com.gabriel.xclientapp.web.rest.mapper;

import com.gabriel.xclientapp.domain.*;
import com.gabriel.xclientapp.web.rest.dto.FornecedorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Fornecedor and its DTO FornecedorDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface FornecedorMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    FornecedorDTO fornecedorToFornecedorDTO(Fornecedor fornecedor);

    List<FornecedorDTO> fornecedorsToFornecedorDTOs(List<Fornecedor> fornecedors);

    @Mapping(target = "produtos", ignore = true)
    @Mapping(source = "userId", target = "user")
    Fornecedor fornecedorDTOToFornecedor(FornecedorDTO fornecedorDTO);

    List<Fornecedor> fornecedorDTOsToFornecedors(List<FornecedorDTO> fornecedorDTOs);
}
