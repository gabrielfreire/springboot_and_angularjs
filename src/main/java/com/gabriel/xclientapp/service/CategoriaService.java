package com.gabriel.xclientapp.service;

import com.gabriel.xclientapp.domain.Categoria;
import com.gabriel.xclientapp.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Categoria.
 */
@Service
@Transactional
public class CategoriaService {

    private final Logger log = LoggerFactory.getLogger(CategoriaService.class);
    
    @Inject
    private CategoriaRepository categoriaRepository;
    
    /**
     * Save a categoria.
     * 
     * @param categoria the entity to save
     * @return the persisted entity
     */
    public Categoria save(Categoria categoria) {
        log.debug("Request to save Categoria : {}", categoria);
        Categoria result = categoriaRepository.save(categoria);
        return result;
    }

    /**
     *  Get all the categorias.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Categoria> findAll() {
        log.debug("Request to get all Categorias");
        List<Categoria> result = categoriaRepository.findAll();
        return result;
    }

    /**
     *  Get one categoria by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Categoria findOne(Long id) {
        log.debug("Request to get Categoria : {}", id);
        Categoria categoria = categoriaRepository.findOne(id);
        return categoria;
    }

    /**
     *  Delete the  categoria by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Categoria : {}", id);
        categoriaRepository.delete(id);
    }
}
