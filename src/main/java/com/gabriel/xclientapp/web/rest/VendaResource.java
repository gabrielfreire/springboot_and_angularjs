package com.gabriel.xclientapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gabriel.xclientapp.domain.Venda;
import com.gabriel.xclientapp.service.VendaService;
import com.gabriel.xclientapp.web.rest.util.HeaderUtil;
import com.gabriel.xclientapp.web.rest.dto.VendaDTO;
import com.gabriel.xclientapp.web.rest.mapper.VendaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Venda.
 */
@RestController
@RequestMapping("/api")
public class VendaResource {

    private final Logger log = LoggerFactory.getLogger(VendaResource.class);
        
    @Inject
    private VendaService vendaService;
    
    @Inject
    private VendaMapper vendaMapper;
    
    /**
     * POST  /vendas : Create a new venda.
     *
     * @param vendaDTO the vendaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendaDTO, or with status 400 (Bad Request) if the venda has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/vendas",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<VendaDTO> createVenda(@Valid @RequestBody VendaDTO vendaDTO) throws URISyntaxException {
        log.debug("REST request to save Venda : {}", vendaDTO);
        if (vendaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("venda", "idexists", "A new venda cannot already have an ID")).body(null);
        }
        VendaDTO result = vendaService.save(vendaDTO);
        return ResponseEntity.created(new URI("/api/vendas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("venda", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vendas : Updates an existing venda.
     *
     * @param vendaDTO the vendaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendaDTO,
     * or with status 400 (Bad Request) if the vendaDTO is not valid,
     * or with status 500 (Internal Server Error) if the vendaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/vendas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<VendaDTO> updateVenda(@Valid @RequestBody VendaDTO vendaDTO) throws URISyntaxException {
        log.debug("REST request to update Venda : {}", vendaDTO);
        if (vendaDTO.getId() == null) {
            return createVenda(vendaDTO);
        }
        VendaDTO result = vendaService.save(vendaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("venda", vendaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendas : get all the vendas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vendas in body
     */
    @RequestMapping(value = "/vendas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<VendaDTO> getAllVendas() {
        log.debug("REST request to get all Vendas");
        return vendaService.findAll();
    }

    /**
     * GET  /vendas/:id : get the "id" venda.
     *
     * @param id the id of the vendaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendaDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/vendas/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<VendaDTO> getVenda(@PathVariable Long id) {
        log.debug("REST request to get Venda : {}", id);
        VendaDTO vendaDTO = vendaService.findOne(id);
        return Optional.ofNullable(vendaDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /vendas/:id : delete the "id" venda.
     *
     * @param id the id of the vendaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/vendas/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        log.debug("REST request to delete Venda : {}", id);
        vendaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("venda", id.toString())).build();
    }

}
