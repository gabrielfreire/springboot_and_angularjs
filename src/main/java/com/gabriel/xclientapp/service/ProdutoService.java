package com.gabriel.xclientapp.service;

import com.gabriel.xclientapp.domain.Produto;
import com.gabriel.xclientapp.repository.ProdutoRepository;
import com.gabriel.xclientapp.web.rest.dto.ProdutoDTO;
import com.gabriel.xclientapp.web.rest.mapper.ProdutoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Produto.
 */
@Service
@Transactional
public class ProdutoService {

    private final Logger log = LoggerFactory.getLogger(ProdutoService.class);
    
    @Inject
    private ProdutoRepository produtoRepository;
    
    @Inject
    private ProdutoMapper produtoMapper;
    
    @Inject
    private UserService userService;
    
    /**
     * Save a produto.
     * 
     * @param produtoDTO the entity to save
     * @return the persisted entity
     */
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        log.debug("Request to save Produto : {}", produtoDTO);
        Produto produto = produtoMapper.produtoDTOToProduto(produtoDTO);
        produto.setUser(userService.getUserWithAuthorities());
        produto = produtoRepository.save(produto);
        ProdutoDTO result = produtoMapper.produtoToProdutoDTO(produto);
        return result;
    }

    /**
     *  Get all the produtos.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ProdutoDTO> findAll() {
        log.debug("Request to get all Produtos");
        List<ProdutoDTO> result = produtoRepository.findAll().stream()
            .map(produtoMapper::produtoToProdutoDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one produto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdutoDTO findOne(Long id) {
        log.debug("Request to get Produto : {}", id);
        Produto produto = produtoRepository.findOne(id);
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
        return produtoDTO;
    }

    /**
     *  Delete the  produto by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Produto : {}", id);
        produtoRepository.delete(id);
    }
}
