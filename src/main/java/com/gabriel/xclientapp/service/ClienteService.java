package com.gabriel.xclientapp.service;

import com.gabriel.xclientapp.domain.Cliente;
import com.gabriel.xclientapp.repository.ClienteRepository;
import com.gabriel.xclientapp.web.rest.dto.ClienteDTO;
import com.gabriel.xclientapp.web.rest.mapper.ClienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Cliente.
 */
@Service
@Transactional
public class ClienteService {

    private final Logger log = LoggerFactory.getLogger(ClienteService.class);
    
    @Inject
    private ClienteRepository clienteRepository;
    
    @Inject
    private ClienteMapper clienteMapper;
    
    @Inject
    private UserService userService;
    
    /**
     * Save a cliente.
     * 
     * @param clienteDTO the entity to save
     * @return the persisted entity
     */
    public ClienteDTO save(ClienteDTO clienteDTO) {
        log.debug("Request to save Cliente : {}", clienteDTO);
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
        
        cliente.setUser(userService.getUserWithAuthorities());
        
        cliente = clienteRepository.save(cliente);
        ClienteDTO result = clienteMapper.clienteToClienteDTO(cliente);
        return result;
    }

    /**
     *  Get all the clientes.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ClienteDTO> findAll() {
        log.debug("Request to get all Clientes");
        List<ClienteDTO> result = clienteRepository.findAll().stream()
            .map(clienteMapper::clienteToClienteDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  Get one cliente by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ClienteDTO findOne(Long id) {
        log.debug("Request to get Cliente : {}", id);
        Cliente cliente = clienteRepository.findOne(id);
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);
        return clienteDTO;
    }

    /**
     *  Delete the  cliente by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Cliente : {}", id);
        clienteRepository.delete(id);
    }
}
