package com.gabriel.xclientapp.service;

import com.gabriel.xclientapp.domain.Fornecedor;
import com.gabriel.xclientapp.repository.FornecedorRepository;
import com.gabriel.xclientapp.web.rest.dto.FornecedorDTO;
import com.gabriel.xclientapp.web.rest.mapper.FornecedorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Fornecedor.
 */
@Service
@Transactional
public class FornecedorService {

    private final Logger log = LoggerFactory.getLogger(FornecedorService.class);
    
    @Inject
    private FornecedorRepository fornecedorRepository;
    
    @Inject
    private FornecedorMapper fornecedorMapper;
    
    /**
     * Save a fornecedor.
     * 
     * @param fornecedorDTO the entity to save
     * @return the persisted entity
     */
    public FornecedorDTO save(FornecedorDTO fornecedorDTO) {
        log.debug("Request to save Fornecedor : {}", fornecedorDTO);
        Fornecedor fornecedor = fornecedorMapper.fornecedorDTOToFornecedor(fornecedorDTO);
        fornecedor = fornecedorRepository.save(fornecedor);
        FornecedorDTO result = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);
        return result;
    }

    /**
     *  Get all the fornecedors.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<FornecedorDTO> findAll() {
        log.debug("Request to get all Fornecedors");
        List<FornecedorDTO> result = fornecedorRepository.findAll().stream()
            .map(fornecedorMapper::fornecedorToFornecedorDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one fornecedor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FornecedorDTO findOne(Long id) {
        log.debug("Request to get Fornecedor : {}", id);
        Fornecedor fornecedor = fornecedorRepository.findOne(id);
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);
        return fornecedorDTO;
    }

    /**
     *  Delete the  fornecedor by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Fornecedor : {}", id);
        fornecedorRepository.delete(id);
    }
}
