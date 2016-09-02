package com.gabriel.xclientapp.web.rest;

import com.gabriel.xclientapp.XclientappApp;
import com.gabriel.xclientapp.domain.Cliente;
import com.gabriel.xclientapp.repository.ClienteRepository;
import com.gabriel.xclientapp.service.ClienteService;
import com.gabriel.xclientapp.web.rest.dto.ClienteDTO;
import com.gabriel.xclientapp.web.rest.mapper.ClienteMapper;

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
 * Test class for the ClienteResource REST controller.
 *
 * @see ClienteResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XclientappApp.class)
@WebAppConfiguration
@IntegrationTest
public class ClienteResourceIntTest {

    private static final String DEFAULT_CLIENTE_NOME_COMPLETO = "AAAAA";
    private static final String UPDATED_CLIENTE_NOME_COMPLETO = "BBBBB";
    private static final String DEFAULT_CLIENTE_EMAIL = "AAAAA";
    private static final String UPDATED_CLIENTE_EMAIL = "BBBBB";
    private static final String DEFAULT_CLIENTE_TELEFONE = "AAAAA";
    private static final String UPDATED_CLIENTE_TELEFONE = "BBBBB";
    private static final String DEFAULT_CLIENTE_CELULAR = "AAAAA";
    private static final String UPDATED_CLIENTE_CELULAR = "BBBBB";
    private static final String DEFAULT_CLIENTE_CPF_CNPJ = "AAAAA";
    private static final String UPDATED_CLIENTE_CPF_CNPJ = "BBBBB";
    private static final String DEFAULT_CLIENTE_RUA = "AAAAA";
    private static final String UPDATED_CLIENTE_RUA = "BBBBB";
    private static final String DEFAULT_CLIENTE_COD_POSTAL = "AAAAA";
    private static final String UPDATED_CLIENTE_COD_POSTAL = "BBBBB";
    private static final String DEFAULT_CLIENTE_CIDADE = "AAAAA";
    private static final String UPDATED_CLIENTE_CIDADE = "BBBBB";
    private static final String DEFAULT_CLIENTE_ESTADO = "AAAAA";
    private static final String UPDATED_CLIENTE_ESTADO = "BBBBB";

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private ClienteMapper clienteMapper;

    @Inject
    private ClienteService clienteService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restClienteMockMvc;

    private Cliente cliente;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClienteResource clienteResource = new ClienteResource();
        ReflectionTestUtils.setField(clienteResource, "clienteService", clienteService);
        ReflectionTestUtils.setField(clienteResource, "clienteMapper", clienteMapper);
        this.restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cliente = new Cliente();
        cliente.setClienteNomeCompleto(DEFAULT_CLIENTE_NOME_COMPLETO);
        cliente.setClienteEmail(DEFAULT_CLIENTE_EMAIL);
        cliente.setClienteTelefone(DEFAULT_CLIENTE_TELEFONE);
        cliente.setClienteCelular(DEFAULT_CLIENTE_CELULAR);
        cliente.setClienteCpfCnpj(DEFAULT_CLIENTE_CPF_CNPJ);
        cliente.setClienteRua(DEFAULT_CLIENTE_RUA);
        cliente.setClienteCodPostal(DEFAULT_CLIENTE_COD_POSTAL);
        cliente.setClienteCidade(DEFAULT_CLIENTE_CIDADE);
        cliente.setClienteEstado(DEFAULT_CLIENTE_ESTADO);
    }

    @Test
    @Transactional
    public void createCliente() throws Exception {
        int databaseSizeBeforeCreate = clienteRepository.findAll().size();

        // Create the Cliente
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isCreated());

        // Validate the Cliente in the database
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeCreate + 1);
        Cliente testCliente = clientes.get(clientes.size() - 1);
        assertThat(testCliente.getClienteNomeCompleto()).isEqualTo(DEFAULT_CLIENTE_NOME_COMPLETO);
        assertThat(testCliente.getClienteEmail()).isEqualTo(DEFAULT_CLIENTE_EMAIL);
        assertThat(testCliente.getClienteTelefone()).isEqualTo(DEFAULT_CLIENTE_TELEFONE);
        assertThat(testCliente.getClienteCelular()).isEqualTo(DEFAULT_CLIENTE_CELULAR);
        assertThat(testCliente.getClienteCpfCnpj()).isEqualTo(DEFAULT_CLIENTE_CPF_CNPJ);
        assertThat(testCliente.getClienteRua()).isEqualTo(DEFAULT_CLIENTE_RUA);
        assertThat(testCliente.getClienteCodPostal()).isEqualTo(DEFAULT_CLIENTE_COD_POSTAL);
        assertThat(testCliente.getClienteCidade()).isEqualTo(DEFAULT_CLIENTE_CIDADE);
        assertThat(testCliente.getClienteEstado()).isEqualTo(DEFAULT_CLIENTE_ESTADO);
    }

    @Test
    @Transactional
    public void checkClienteNomeCompletoIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteNomeCompleto(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteEmail(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteTelefone(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteCpfCnpjIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteCpfCnpj(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteRuaIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteRua(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteCodPostalIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteCodPostal(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteCidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteCidade(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClienteEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setClienteEstado(null);

        // Create the Cliente, which fails.
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        restClienteMockMvc.perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isBadRequest());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClientes() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get all the clientes
        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId().intValue())))
                .andExpect(jsonPath("$.[*].clienteNomeCompleto").value(hasItem(DEFAULT_CLIENTE_NOME_COMPLETO.toString())))
                .andExpect(jsonPath("$.[*].clienteEmail").value(hasItem(DEFAULT_CLIENTE_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].clienteTelefone").value(hasItem(DEFAULT_CLIENTE_TELEFONE.toString())))
                .andExpect(jsonPath("$.[*].clienteCelular").value(hasItem(DEFAULT_CLIENTE_CELULAR.toString())))
                .andExpect(jsonPath("$.[*].clienteCpfCnpj").value(hasItem(DEFAULT_CLIENTE_CPF_CNPJ.toString())))
                .andExpect(jsonPath("$.[*].clienteRua").value(hasItem(DEFAULT_CLIENTE_RUA.toString())))
                .andExpect(jsonPath("$.[*].clienteCodPostal").value(hasItem(DEFAULT_CLIENTE_COD_POSTAL.toString())))
                .andExpect(jsonPath("$.[*].clienteCidade").value(hasItem(DEFAULT_CLIENTE_CIDADE.toString())))
                .andExpect(jsonPath("$.[*].clienteEstado").value(hasItem(DEFAULT_CLIENTE_ESTADO.toString())));
    }

    @Test
    @Transactional
    public void getCliente() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);

        // Get the cliente
        restClienteMockMvc.perform(get("/api/clientes/{id}", cliente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cliente.getId().intValue()))
            .andExpect(jsonPath("$.clienteNomeCompleto").value(DEFAULT_CLIENTE_NOME_COMPLETO.toString()))
            .andExpect(jsonPath("$.clienteEmail").value(DEFAULT_CLIENTE_EMAIL.toString()))
            .andExpect(jsonPath("$.clienteTelefone").value(DEFAULT_CLIENTE_TELEFONE.toString()))
            .andExpect(jsonPath("$.clienteCelular").value(DEFAULT_CLIENTE_CELULAR.toString()))
            .andExpect(jsonPath("$.clienteCpfCnpj").value(DEFAULT_CLIENTE_CPF_CNPJ.toString()))
            .andExpect(jsonPath("$.clienteRua").value(DEFAULT_CLIENTE_RUA.toString()))
            .andExpect(jsonPath("$.clienteCodPostal").value(DEFAULT_CLIENTE_COD_POSTAL.toString()))
            .andExpect(jsonPath("$.clienteCidade").value(DEFAULT_CLIENTE_CIDADE.toString()))
            .andExpect(jsonPath("$.clienteEstado").value(DEFAULT_CLIENTE_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCliente() throws Exception {
        // Get the cliente
        restClienteMockMvc.perform(get("/api/clientes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCliente() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);
        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();

        // Update the cliente
        Cliente updatedCliente = new Cliente();
        updatedCliente.setId(cliente.getId());
        updatedCliente.setClienteNomeCompleto(UPDATED_CLIENTE_NOME_COMPLETO);
        updatedCliente.setClienteEmail(UPDATED_CLIENTE_EMAIL);
        updatedCliente.setClienteTelefone(UPDATED_CLIENTE_TELEFONE);
        updatedCliente.setClienteCelular(UPDATED_CLIENTE_CELULAR);
        updatedCliente.setClienteCpfCnpj(UPDATED_CLIENTE_CPF_CNPJ);
        updatedCliente.setClienteRua(UPDATED_CLIENTE_RUA);
        updatedCliente.setClienteCodPostal(UPDATED_CLIENTE_COD_POSTAL);
        updatedCliente.setClienteCidade(UPDATED_CLIENTE_CIDADE);
        updatedCliente.setClienteEstado(UPDATED_CLIENTE_ESTADO);
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(updatedCliente);

        restClienteMockMvc.perform(put("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
                .andExpect(status().isOk());

        // Validate the Cliente in the database
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeUpdate);
        Cliente testCliente = clientes.get(clientes.size() - 1);
        assertThat(testCliente.getClienteNomeCompleto()).isEqualTo(UPDATED_CLIENTE_NOME_COMPLETO);
        assertThat(testCliente.getClienteEmail()).isEqualTo(UPDATED_CLIENTE_EMAIL);
        assertThat(testCliente.getClienteTelefone()).isEqualTo(UPDATED_CLIENTE_TELEFONE);
        assertThat(testCliente.getClienteCelular()).isEqualTo(UPDATED_CLIENTE_CELULAR);
        assertThat(testCliente.getClienteCpfCnpj()).isEqualTo(UPDATED_CLIENTE_CPF_CNPJ);
        assertThat(testCliente.getClienteRua()).isEqualTo(UPDATED_CLIENTE_RUA);
        assertThat(testCliente.getClienteCodPostal()).isEqualTo(UPDATED_CLIENTE_COD_POSTAL);
        assertThat(testCliente.getClienteCidade()).isEqualTo(UPDATED_CLIENTE_CIDADE);
        assertThat(testCliente.getClienteEstado()).isEqualTo(UPDATED_CLIENTE_ESTADO);
    }

    @Test
    @Transactional
    public void deleteCliente() throws Exception {
        // Initialize the database
        clienteRepository.saveAndFlush(cliente);
        int databaseSizeBeforeDelete = clienteRepository.findAll().size();

        // Get the cliente
        restClienteMockMvc.perform(delete("/api/clientes/{id}", cliente.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
