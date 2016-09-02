package com.gabriel.xclientapp.web.rest;

import com.gabriel.xclientapp.XclientappApp;
import com.gabriel.xclientapp.domain.Fornecedor;
import com.gabriel.xclientapp.repository.FornecedorRepository;
import com.gabriel.xclientapp.service.FornecedorService;
import com.gabriel.xclientapp.web.rest.dto.FornecedorDTO;
import com.gabriel.xclientapp.web.rest.mapper.FornecedorMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the FornecedorResource REST controller.
 *
 * @see FornecedorResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XclientappApp.class)
@WebAppConfiguration
@IntegrationTest
public class FornecedorResourceIntTest {

    private static final String DEFAULT_FORN_NOME = "AAAAA";
    private static final String UPDATED_FORN_NOME = "BBBBB";
    private static final String DEFAULT_FORN_CNPJ = "AAAAA";
    private static final String UPDATED_FORN_CNPJ = "BBBBB";
    private static final String DEFAULT_FORN_CELULAR = "AAAAA";
    private static final String UPDATED_FORN_CELULAR = "BBBBB";
    private static final String DEFAULT_FORN_TELEFONE = "AAAAA";
    private static final String UPDATED_FORN_TELEFONE = "BBBBB";
    private static final String DEFAULT_FORN_EMAIL = "AAAAA";
    private static final String UPDATED_FORN_EMAIL = "BBBBB";
    private static final String DEFAULT_FORN_RUA = "AAAAA";
    private static final String UPDATED_FORN_RUA = "BBBBB";
    private static final String DEFAULT_FORN_COD_POSTAL = "AAAAA";
    private static final String UPDATED_FORN_COD_POSTAL = "BBBBB";
    private static final String DEFAULT_FORN_CIDADE = "AAAAA";
    private static final String UPDATED_FORN_CIDADE = "BBBBB";
    private static final String DEFAULT_FORN_ESTADO = "AAAAA";
    private static final String UPDATED_FORN_ESTADO = "BBBBB";

    @Inject
    private FornecedorRepository fornecedorRepository;

    @Inject
    private FornecedorMapper fornecedorMapper;

    @Inject
    private FornecedorService fornecedorService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFornecedorMockMvc;

    private Fornecedor fornecedor;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FornecedorResource fornecedorResource = new FornecedorResource();
        ReflectionTestUtils.setField(fornecedorResource, "fornecedorService", fornecedorService);
        ReflectionTestUtils.setField(fornecedorResource, "fornecedorMapper", fornecedorMapper);
        this.restFornecedorMockMvc = MockMvcBuilders.standaloneSetup(fornecedorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        fornecedor = new Fornecedor();
        fornecedor.setFornNome(DEFAULT_FORN_NOME);
        fornecedor.setFornCnpj(DEFAULT_FORN_CNPJ);
        fornecedor.setFornCelular(DEFAULT_FORN_CELULAR);
        fornecedor.setFornTelefone(DEFAULT_FORN_TELEFONE);
        fornecedor.setFornEmail(DEFAULT_FORN_EMAIL);
        fornecedor.setFornRua(DEFAULT_FORN_RUA);
        fornecedor.setFornCodPostal(DEFAULT_FORN_COD_POSTAL);
        fornecedor.setFornCidade(DEFAULT_FORN_CIDADE);
        fornecedor.setFornEstado(DEFAULT_FORN_ESTADO);
    }

    @Test
    @Transactional
    public void createFornecedor() throws Exception {
        int databaseSizeBeforeCreate = fornecedorRepository.findAll().size();

        // Create the Fornecedor
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isCreated());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeCreate + 1);
        Fornecedor testFornecedor = fornecedors.get(fornecedors.size() - 1);
        assertThat(testFornecedor.getFornNome()).isEqualTo(DEFAULT_FORN_NOME);
        assertThat(testFornecedor.getFornCnpj()).isEqualTo(DEFAULT_FORN_CNPJ);
        assertThat(testFornecedor.getFornCelular()).isEqualTo(DEFAULT_FORN_CELULAR);
        assertThat(testFornecedor.getFornTelefone()).isEqualTo(DEFAULT_FORN_TELEFONE);
        assertThat(testFornecedor.getFornEmail()).isEqualTo(DEFAULT_FORN_EMAIL);
        assertThat(testFornecedor.getFornRua()).isEqualTo(DEFAULT_FORN_RUA);
        assertThat(testFornecedor.getFornCodPostal()).isEqualTo(DEFAULT_FORN_COD_POSTAL);
        assertThat(testFornecedor.getFornCidade()).isEqualTo(DEFAULT_FORN_CIDADE);
        assertThat(testFornecedor.getFornEstado()).isEqualTo(DEFAULT_FORN_ESTADO);
    }

    @Test
    @Transactional
    public void checkFornNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornNome(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornCnpjIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornCnpj(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornTelefone(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornEmail(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornRuaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornRua(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornCodPostalIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornCodPostal(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornCidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornCidade(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFornEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setFornEstado(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFornecedors() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedors
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(fornecedor.getId().intValue())))
                .andExpect(jsonPath("$.[*].fornNome").value(hasItem(DEFAULT_FORN_NOME.toString())))
                .andExpect(jsonPath("$.[*].fornCnpj").value(hasItem(DEFAULT_FORN_CNPJ.toString())))
                .andExpect(jsonPath("$.[*].fornCelular").value(hasItem(DEFAULT_FORN_CELULAR.toString())))
                .andExpect(jsonPath("$.[*].fornTelefone").value(hasItem(DEFAULT_FORN_TELEFONE.toString())))
                .andExpect(jsonPath("$.[*].fornEmail").value(hasItem(DEFAULT_FORN_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].fornRua").value(hasItem(DEFAULT_FORN_RUA.toString())))
                .andExpect(jsonPath("$.[*].fornCodPostal").value(hasItem(DEFAULT_FORN_COD_POSTAL.toString())))
                .andExpect(jsonPath("$.[*].fornCidade").value(hasItem(DEFAULT_FORN_CIDADE.toString())))
                .andExpect(jsonPath("$.[*].fornEstado").value(hasItem(DEFAULT_FORN_ESTADO.toString())));
    }

    @Test
    @Transactional
    public void getFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get the fornecedor
        restFornecedorMockMvc.perform(get("/api/fornecedors/{id}", fornecedor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(fornecedor.getId().intValue()))
            .andExpect(jsonPath("$.fornNome").value(DEFAULT_FORN_NOME.toString()))
            .andExpect(jsonPath("$.fornCnpj").value(DEFAULT_FORN_CNPJ.toString()))
            .andExpect(jsonPath("$.fornCelular").value(DEFAULT_FORN_CELULAR.toString()))
            .andExpect(jsonPath("$.fornTelefone").value(DEFAULT_FORN_TELEFONE.toString()))
            .andExpect(jsonPath("$.fornEmail").value(DEFAULT_FORN_EMAIL.toString()))
            .andExpect(jsonPath("$.fornRua").value(DEFAULT_FORN_RUA.toString()))
            .andExpect(jsonPath("$.fornCodPostal").value(DEFAULT_FORN_COD_POSTAL.toString()))
            .andExpect(jsonPath("$.fornCidade").value(DEFAULT_FORN_CIDADE.toString()))
            .andExpect(jsonPath("$.fornEstado").value(DEFAULT_FORN_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFornecedor() throws Exception {
        // Get the fornecedor
        restFornecedorMockMvc.perform(get("/api/fornecedors/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);
        int databaseSizeBeforeUpdate = fornecedorRepository.findAll().size();

        // Update the fornecedor
        Fornecedor updatedFornecedor = new Fornecedor();
        updatedFornecedor.setId(fornecedor.getId());
        updatedFornecedor.setFornNome(UPDATED_FORN_NOME);
        updatedFornecedor.setFornCnpj(UPDATED_FORN_CNPJ);
        updatedFornecedor.setFornCelular(UPDATED_FORN_CELULAR);
        updatedFornecedor.setFornTelefone(UPDATED_FORN_TELEFONE);
        updatedFornecedor.setFornEmail(UPDATED_FORN_EMAIL);
        updatedFornecedor.setFornRua(UPDATED_FORN_RUA);
        updatedFornecedor.setFornCodPostal(UPDATED_FORN_COD_POSTAL);
        updatedFornecedor.setFornCidade(UPDATED_FORN_CIDADE);
        updatedFornecedor.setFornEstado(UPDATED_FORN_ESTADO);
        FornecedorDTO fornecedorDTO = fornecedorMapper.fornecedorToFornecedorDTO(updatedFornecedor);

        restFornecedorMockMvc.perform(put("/api/fornecedors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
                .andExpect(status().isOk());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeUpdate);
        Fornecedor testFornecedor = fornecedors.get(fornecedors.size() - 1);
        assertThat(testFornecedor.getFornNome()).isEqualTo(UPDATED_FORN_NOME);
        assertThat(testFornecedor.getFornCnpj()).isEqualTo(UPDATED_FORN_CNPJ);
        assertThat(testFornecedor.getFornCelular()).isEqualTo(UPDATED_FORN_CELULAR);
        assertThat(testFornecedor.getFornTelefone()).isEqualTo(UPDATED_FORN_TELEFONE);
        assertThat(testFornecedor.getFornEmail()).isEqualTo(UPDATED_FORN_EMAIL);
        assertThat(testFornecedor.getFornRua()).isEqualTo(UPDATED_FORN_RUA);
        assertThat(testFornecedor.getFornCodPostal()).isEqualTo(UPDATED_FORN_COD_POSTAL);
        assertThat(testFornecedor.getFornCidade()).isEqualTo(UPDATED_FORN_CIDADE);
        assertThat(testFornecedor.getFornEstado()).isEqualTo(UPDATED_FORN_ESTADO);
    }

    @Test
    @Transactional
    public void deleteFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);
        int databaseSizeBeforeDelete = fornecedorRepository.findAll().size();

        // Get the fornecedor
        restFornecedorMockMvc.perform(delete("/api/fornecedors/{id}", fornecedor.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        assertThat(fornecedors).hasSize(databaseSizeBeforeDelete - 1);
    }
}
