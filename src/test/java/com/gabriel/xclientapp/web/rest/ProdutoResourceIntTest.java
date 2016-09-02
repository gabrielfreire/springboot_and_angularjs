package com.gabriel.xclientapp.web.rest;

import com.gabriel.xclientapp.XclientappApp;
import com.gabriel.xclientapp.domain.Produto;
import com.gabriel.xclientapp.repository.ProdutoRepository;
import com.gabriel.xclientapp.service.ProdutoService;
import com.gabriel.xclientapp.web.rest.dto.ProdutoDTO;
import com.gabriel.xclientapp.web.rest.mapper.ProdutoMapper;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ProdutoResource REST controller.
 *
 * @see ProdutoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XclientappApp.class)
@WebAppConfiguration
@IntegrationTest
public class ProdutoResourceIntTest {

    private static final String DEFAULT_PROD_CODIGO = "AAAAA";
    private static final String UPDATED_PROD_CODIGO = "BBBBB";
    private static final String DEFAULT_PROD_NOME = "AAAAA";
    private static final String UPDATED_PROD_NOME = "BBBBB";
    private static final String DEFAULT_PROD_MARCA = "AAAAA";
    private static final String UPDATED_PROD_MARCA = "BBBBB";

    private static final BigDecimal DEFAULT_PROD_PRECO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROD_PRECO = new BigDecimal(2);

    private static final Integer DEFAULT_PROD_QTD = 1;
    private static final Integer UPDATED_PROD_QTD = 2;
    private static final String DEFAULT_PROD_COR = "AAAAA";
    private static final String UPDATED_PROD_COR = "BBBBB";

    @Inject
    private ProdutoRepository produtoRepository;

    @Inject
    private ProdutoMapper produtoMapper;

    @Inject
    private ProdutoService produtoService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProdutoMockMvc;

    private Produto produto;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProdutoResource produtoResource = new ProdutoResource();
        ReflectionTestUtils.setField(produtoResource, "produtoService", produtoService);
        ReflectionTestUtils.setField(produtoResource, "produtoMapper", produtoMapper);
        this.restProdutoMockMvc = MockMvcBuilders.standaloneSetup(produtoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        produto = new Produto();
        produto.setProdCodigo(DEFAULT_PROD_CODIGO);
        produto.setProdNome(DEFAULT_PROD_NOME);
        produto.setProdMarca(DEFAULT_PROD_MARCA);
        produto.setProdPreco(DEFAULT_PROD_PRECO);
        produto.setProdQtd(DEFAULT_PROD_QTD);
        produto.setProdCor(DEFAULT_PROD_COR);
    }

    @Test
    @Transactional
    public void createProduto() throws Exception {
        int databaseSizeBeforeCreate = produtoRepository.findAll().size();

        // Create the Produto
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);

        restProdutoMockMvc.perform(post("/api/produtos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(produtoDTO)))
                .andExpect(status().isCreated());

        // Validate the Produto in the database
        List<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(databaseSizeBeforeCreate + 1);
        Produto testProduto = produtos.get(produtos.size() - 1);
        assertThat(testProduto.getProdCodigo()).isEqualTo(DEFAULT_PROD_CODIGO);
        assertThat(testProduto.getProdNome()).isEqualTo(DEFAULT_PROD_NOME);
        assertThat(testProduto.getProdMarca()).isEqualTo(DEFAULT_PROD_MARCA);
        assertThat(testProduto.getProdPreco()).isEqualTo(DEFAULT_PROD_PRECO);
        assertThat(testProduto.getProdQtd()).isEqualTo(DEFAULT_PROD_QTD);
        assertThat(testProduto.getProdCor()).isEqualTo(DEFAULT_PROD_COR);
    }

    @Test
    @Transactional
    public void checkProdNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setProdNome(null);

        // Create the Produto, which fails.
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);

        restProdutoMockMvc.perform(post("/api/produtos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(produtoDTO)))
                .andExpect(status().isBadRequest());

        List<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProdPrecoIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setProdPreco(null);

        // Create the Produto, which fails.
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);

        restProdutoMockMvc.perform(post("/api/produtos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(produtoDTO)))
                .andExpect(status().isBadRequest());

        List<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProdQtdIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setProdQtd(null);

        // Create the Produto, which fails.
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);

        restProdutoMockMvc.perform(post("/api/produtos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(produtoDTO)))
                .andExpect(status().isBadRequest());

        List<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProdutos() throws Exception {
        // Initialize the database
        produtoRepository.saveAndFlush(produto);

        // Get all the produtos
        restProdutoMockMvc.perform(get("/api/produtos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(produto.getId().intValue())))
                .andExpect(jsonPath("$.[*].prodCodigo").value(hasItem(DEFAULT_PROD_CODIGO.toString())))
                .andExpect(jsonPath("$.[*].prodNome").value(hasItem(DEFAULT_PROD_NOME.toString())))
                .andExpect(jsonPath("$.[*].prodMarca").value(hasItem(DEFAULT_PROD_MARCA.toString())))
                .andExpect(jsonPath("$.[*].prodPreco").value(hasItem(DEFAULT_PROD_PRECO.intValue())))
                .andExpect(jsonPath("$.[*].prodQtd").value(hasItem(DEFAULT_PROD_QTD)))
                .andExpect(jsonPath("$.[*].prodCor").value(hasItem(DEFAULT_PROD_COR.toString())));
    }

    @Test
    @Transactional
    public void getProduto() throws Exception {
        // Initialize the database
        produtoRepository.saveAndFlush(produto);

        // Get the produto
        restProdutoMockMvc.perform(get("/api/produtos/{id}", produto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(produto.getId().intValue()))
            .andExpect(jsonPath("$.prodCodigo").value(DEFAULT_PROD_CODIGO.toString()))
            .andExpect(jsonPath("$.prodNome").value(DEFAULT_PROD_NOME.toString()))
            .andExpect(jsonPath("$.prodMarca").value(DEFAULT_PROD_MARCA.toString()))
            .andExpect(jsonPath("$.prodPreco").value(DEFAULT_PROD_PRECO.intValue()))
            .andExpect(jsonPath("$.prodQtd").value(DEFAULT_PROD_QTD))
            .andExpect(jsonPath("$.prodCor").value(DEFAULT_PROD_COR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProduto() throws Exception {
        // Get the produto
        restProdutoMockMvc.perform(get("/api/produtos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduto() throws Exception {
        // Initialize the database
        produtoRepository.saveAndFlush(produto);
        int databaseSizeBeforeUpdate = produtoRepository.findAll().size();

        // Update the produto
        Produto updatedProduto = new Produto();
        updatedProduto.setId(produto.getId());
        updatedProduto.setProdCodigo(UPDATED_PROD_CODIGO);
        updatedProduto.setProdNome(UPDATED_PROD_NOME);
        updatedProduto.setProdMarca(UPDATED_PROD_MARCA);
        updatedProduto.setProdPreco(UPDATED_PROD_PRECO);
        updatedProduto.setProdQtd(UPDATED_PROD_QTD);
        updatedProduto.setProdCor(UPDATED_PROD_COR);
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(updatedProduto);

        restProdutoMockMvc.perform(put("/api/produtos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(produtoDTO)))
                .andExpect(status().isOk());

        // Validate the Produto in the database
        List<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(databaseSizeBeforeUpdate);
        Produto testProduto = produtos.get(produtos.size() - 1);
        assertThat(testProduto.getProdCodigo()).isEqualTo(UPDATED_PROD_CODIGO);
        assertThat(testProduto.getProdNome()).isEqualTo(UPDATED_PROD_NOME);
        assertThat(testProduto.getProdMarca()).isEqualTo(UPDATED_PROD_MARCA);
        assertThat(testProduto.getProdPreco()).isEqualTo(UPDATED_PROD_PRECO);
        assertThat(testProduto.getProdQtd()).isEqualTo(UPDATED_PROD_QTD);
        assertThat(testProduto.getProdCor()).isEqualTo(UPDATED_PROD_COR);
    }

    @Test
    @Transactional
    public void deleteProduto() throws Exception {
        // Initialize the database
        produtoRepository.saveAndFlush(produto);
        int databaseSizeBeforeDelete = produtoRepository.findAll().size();

        // Get the produto
        restProdutoMockMvc.perform(delete("/api/produtos/{id}", produto.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
