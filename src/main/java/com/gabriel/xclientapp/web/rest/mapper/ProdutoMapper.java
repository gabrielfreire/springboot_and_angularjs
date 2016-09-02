package com.gabriel.xclientapp.web.rest.mapper;

import com.gabriel.xclientapp.domain.*;
import com.gabriel.xclientapp.web.rest.dto.ProdutoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Produto and its DTO ProdutoDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface ProdutoMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.catNome", target = "categoriaCatNome")
    @Mapping(source = "fornecedor.id", target = "fornecedorId")
    @Mapping(source = "fornecedor.fornNome", target = "fornecedorFornNome")
    ProdutoDTO produtoToProdutoDTO(Produto produto);

    List<ProdutoDTO> produtosToProdutoDTOs(List<Produto> produtos);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "categoriaId", target = "categoria")
    @Mapping(source = "fornecedorId", target = "fornecedor")
    Produto produtoDTOToProduto(ProdutoDTO produtoDTO);

    List<Produto> produtoDTOsToProdutos(List<ProdutoDTO> produtoDTOs);

    default Categoria categoriaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setId(id);
        return categoria;
    }

    default Fornecedor fornecedorFromId(Long id) {
        if (id == null) {
            return null;
        }
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        return fornecedor;
    }
}
