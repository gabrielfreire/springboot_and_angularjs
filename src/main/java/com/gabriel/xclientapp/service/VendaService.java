package com.gabriel.xclientapp.service;

import com.gabriel.xclientapp.domain.Venda;
import com.gabriel.xclientapp.repository.VendaRepository;
import com.gabriel.xclientapp.web.rest.dto.VendaDTO;
import com.gabriel.xclientapp.web.rest.mapper.VendaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Venda.
 */
@Service
@Transactional
public class VendaService {

    private final Logger log = LoggerFactory.getLogger(VendaService.class);
    
    @Inject
    private VendaRepository vendaRepository;
    
    @Inject
    private VendaMapper vendaMapper;
    
    /**
     * Save a venda.
     * 
     * @param vendaDTO the entity to save
     * @return the persisted entity
     */
    public VendaDTO save(VendaDTO vendaDTO) {
        log.debug("Request to save Venda : {}", vendaDTO);
        Venda venda = vendaMapper.vendaDTOToVenda(vendaDTO);
        venda = vendaRepository.save(venda);
        VendaDTO result = vendaMapper.vendaToVendaDTO(venda);
        return result;
    }

    /**
     *  Get all the vendas.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<VendaDTO> findAll() {
        log.debug("Request to get all Vendas");
        List<VendaDTO> result = vendaRepository.findAll().stream()
            .map(vendaMapper::vendaToVendaDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one venda by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public VendaDTO findOne(Long id) {
        log.debug("Request to get Venda : {}", id);
        Venda venda = vendaRepository.findOne(id);
        VendaDTO vendaDTO = vendaMapper.vendaToVendaDTO(venda);
        return vendaDTO;
    }

    /**
     *  Delete the  venda by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Venda : {}", id);
        vendaRepository.delete(id);
    }
}
