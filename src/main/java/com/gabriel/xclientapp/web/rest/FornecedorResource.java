package com.gabriel.xclientapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gabriel.xclientapp.domain.Fornecedor;
import com.gabriel.xclientapp.service.FornecedorService;
import com.gabriel.xclientapp.web.rest.util.HeaderUtil;
import com.gabriel.xclientapp.web.rest.dto.FornecedorDTO;
import com.gabriel.xclientapp.web.rest.mapper.FornecedorMapper;
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
 * REST controller for managing Fornecedor.
 */
@RestController
@RequestMapping("/api")
public class FornecedorResource {

    private final Logger log = LoggerFactory.getLogger(FornecedorResource.class);
        
    @Inject
    private FornecedorService fornecedorService;
    
    @Inject
    private FornecedorMapper fornecedorMapper;
    
    /**
     * POST  /fornecedors : Create a new fornecedor.
     *
     * @param fornecedorDTO the fornecedorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fornecedorDTO, or with status 400 (Bad Request) if the fornecedor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/fornecedors",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FornecedorDTO> createFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) throws URISyntaxException {
        log.debug("REST request to save Fornecedor : {}", fornecedorDTO);
        if (fornecedorDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("fornecedor", "idexists", "A new fornecedor cannot already have an ID")).body(null);
        }
        FornecedorDTO result = fornecedorService.save(fornecedorDTO);
        return ResponseEntity.created(new URI("/api/fornecedors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("fornecedor", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fornecedors : Updates an existing fornecedor.
     *
     * @param fornecedorDTO the fornecedorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fornecedorDTO,
     * or with status 400 (Bad Request) if the fornecedorDTO is not valid,
     * or with status 500 (Internal Server Error) if the fornecedorDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/fornecedors",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FornecedorDTO> updateFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) throws URISyntaxException {
        log.debug("REST request to update Fornecedor : {}", fornecedorDTO);
        if (fornecedorDTO.getId() == null) {
            return createFornecedor(fornecedorDTO);
        }
        FornecedorDTO result = fornecedorService.save(fornecedorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("fornecedor", fornecedorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fornecedors : get all the fornecedors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fornecedors in body
     */
    @RequestMapping(value = "/fornecedors",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<FornecedorDTO> getAllFornecedors() {
        log.debug("REST request to get all Fornecedors");
        return fornecedorService.findAll();
    }

    /**
     * GET  /fornecedors/:id : get the "id" fornecedor.
     *
     * @param id the id of the fornecedorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fornecedorDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/fornecedors/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FornecedorDTO> getFornecedor(@PathVariable Long id) {
        log.debug("REST request to get Fornecedor : {}", id);
        FornecedorDTO fornecedorDTO = fornecedorService.findOne(id);
        return Optional.ofNullable(fornecedorDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /fornecedors/:id : delete the "id" fornecedor.
     *
     * @param id the id of the fornecedorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/fornecedors/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        log.debug("REST request to delete Fornecedor : {}", id);
        fornecedorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("fornecedor", id.toString())).build();
    }

}
