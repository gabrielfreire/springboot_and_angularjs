package com.gabriel.xclientapp.web.rest;

import com.gabriel.xclientapp.XclientappApp;
import com.gabriel.xclientapp.domain.Venda;
import com.gabriel.xclientapp.repository.VendaRepository;
import com.gabriel.xclientapp.service.VendaService;
import com.gabriel.xclientapp.web.rest.dto.VendaDTO;
import com.gabriel.xclientapp.web.rest.mapper.VendaMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the VendaResource REST controller.
 *
 * @see VendaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XclientappApp.class)
@WebAppConfiguration
@IntegrationTest
public class VendaResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_PEDIDO_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_PEDIDO_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_PEDIDO_DATA_STR = dateTimeFormatter.format(DEFAULT_PEDIDO_DATA);

    private static final Integer DEFAULT_PEDIDO_QTD = 1;
    private static final Integer UPDATED_PEDIDO_QTD = 2;
    private static final String DEFAULT_FORMA_DE_PAGAMENTO = "AAAAA";
    private static final String UPDATED_FORMA_DE_PAGAMENTO = "BBBBB";

    @Inject
    private VendaRepository vendaRepository;

    @Inject
    private VendaMapper vendaMapper;

    @Inject
    private VendaService vendaService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restVendaMockMvc;

    private Venda venda;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VendaResource vendaResource = new VendaResource();
        ReflectionTestUtils.setField(vendaResource, "vendaService", vendaService);
        ReflectionTestUtils.setField(vendaResource, "vendaMapper", vendaMapper);
        this.restVendaMockMvc = MockMvcBuilders.standaloneSetup(vendaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        venda = new Venda();
        venda.setPedidoData(DEFAULT_PEDIDO_DATA);
        venda.setPedidoQtd(DEFAULT_PEDIDO_QTD);
        venda.setFormaDePagamento(DEFAULT_FORMA_DE_PAGAMENTO);
    }

    @Test
    @Transactional
    public void createVenda() throws Exception {
        int databaseSizeBeforeCreate = vendaRepository.findAll().size();

        // Create the Venda
        VendaDTO vendaDTO = vendaMapper.vendaToVendaDTO(venda);

        restVendaMockMvc.perform(post("/api/vendas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(vendaDTO)))
                .andExpect(status().isCreated());

        // Validate the Venda in the database
        List<Venda> vendas = vendaRepository.findAll();
        assertThat(vendas).hasSize(databaseSizeBeforeCreate + 1);
        Venda testVenda = vendas.get(vendas.size() - 1);
        assertThat(testVenda.getPedidoData()).isEqualTo(DEFAULT_PEDIDO_DATA);
        assertThat(testVenda.getPedidoQtd()).isEqualTo(DEFAULT_PEDIDO_QTD);
        assertThat(testVenda.getFormaDePagamento()).isEqualTo(DEFAULT_FORMA_DE_PAGAMENTO);
    }

    @Test
    @Transactional
    public void checkPedidoDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendaRepository.findAll().size();
        // set the field null
        venda.setPedidoData(null);

        // Create the Venda, which fails.
        VendaDTO vendaDTO = vendaMapper.vendaToVendaDTO(venda);

        restVendaMockMvc.perform(post("/api/vendas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(vendaDTO)))
                .andExpect(status().isBadRequest());

        List<Venda> vendas = vendaRepository.findAll();
        assertThat(vendas).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPedidoQtdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendaRepository.findAll().size();
        // set the field null
        venda.setPedidoQtd(null);

        // Create the Venda, which fails.
        VendaDTO vendaDTO = vendaMapper.vendaToVendaDTO(venda);

        restVendaMockMvc.perform(post("/api/vendas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(vendaDTO)))
                .andExpect(status().isBadRequest());

        List<Venda> vendas = vendaRepository.findAll();
        assertThat(vendas).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVendas() throws Exception {
        // Initialize the database
        vendaRepository.saveAndFlush(venda);

        // Get all the vendas
        restVendaMockMvc.perform(get("/api/vendas?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(venda.getId().intValue())))
                .andExpect(jsonPath("$.[*].pedidoData").value(hasItem(DEFAULT_PEDIDO_DATA_STR)))
                .andExpect(jsonPath("$.[*].pedidoQtd").value(hasItem(DEFAULT_PEDIDO_QTD)))
                .andExpect(jsonPath("$.[*].formaDePagamento").value(hasItem(DEFAULT_FORMA_DE_PAGAMENTO.toString())));
    }

    @Test
    @Transactional
    public void getVenda() throws Exception {
        // Initialize the database
        vendaRepository.saveAndFlush(venda);

        // Get the venda
        restVendaMockMvc.perform(get("/api/vendas/{id}", venda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(venda.getId().intValue()))
            .andExpect(jsonPath("$.pedidoData").value(DEFAULT_PEDIDO_DATA_STR))
            .andExpect(jsonPath("$.pedidoQtd").value(DEFAULT_PEDIDO_QTD))
            .andExpect(jsonPath("$.formaDePagamento").value(DEFAULT_FORMA_DE_PAGAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVenda() throws Exception {
        // Get the venda
        restVendaMockMvc.perform(get("/api/vendas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVenda() throws Exception {
        // Initialize the database
        vendaRepository.saveAndFlush(venda);
        int databaseSizeBeforeUpdate = vendaRepository.findAll().size();

        // Update the venda
        Venda updatedVenda = new Venda();
        updatedVenda.setId(venda.getId());
        updatedVenda.setPedidoData(UPDATED_PEDIDO_DATA);
        updatedVenda.setPedidoQtd(UPDATED_PEDIDO_QTD);
        updatedVenda.setFormaDePagamento(UPDATED_FORMA_DE_PAGAMENTO);
        VendaDTO vendaDTO = vendaMapper.vendaToVendaDTO(updatedVenda);

        restVendaMockMvc.perform(put("/api/vendas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(vendaDTO)))
                .andExpect(status().isOk());

        // Validate the Venda in the database
        List<Venda> vendas = vendaRepository.findAll();
        assertThat(vendas).hasSize(databaseSizeBeforeUpdate);
        Venda testVenda = vendas.get(vendas.size() - 1);
        assertThat(testVenda.getPedidoData()).isEqualTo(UPDATED_PEDIDO_DATA);
        assertThat(testVenda.getPedidoQtd()).isEqualTo(UPDATED_PEDIDO_QTD);
        assertThat(testVenda.getFormaDePagamento()).isEqualTo(UPDATED_FORMA_DE_PAGAMENTO);
    }

    @Test
    @Transactional
    public void deleteVenda() throws Exception {
        // Initialize the database
        vendaRepository.saveAndFlush(venda);
        int databaseSizeBeforeDelete = vendaRepository.findAll().size();

        // Get the venda
        restVendaMockMvc.perform(delete("/api/vendas/{id}", venda.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Venda> vendas = vendaRepository.findAll();
        assertThat(vendas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
