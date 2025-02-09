package com.burakpekisik.ecommerce_backend.api.controller.product;

import com.burakpekisik.ecommerce_backend.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testProductList() throws Exception {
        mvc.perform(get("/product"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Transactional
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        product.setShortDescription("Test Short Description");
        product.setLongDescription("Test Long Description");
        product.setPrice(99.99);

        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.shortDescription").value("Test Short Description"))
                .andExpect(jsonPath("$.price").value(99.99));
    }

    @Test
    @Transactional
    public void testCreateProductWithInvalidData() throws Exception {
        Product product = new Product();
        // Missing required fields
        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Transactional
    public void testUpdateProduct() throws Exception {
        // First create a product
        Product product = new Product();
        product.setName("Initial Product");
        product.setShortDescription("Initial Short Description");
        product.setPrice(50.00);

        String createResult = mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn().getResponse().getContentAsString();

        Product createdProduct = objectMapper.readValue(createResult, Product.class);

        // Then update it
        createdProduct.setName("Updated Product");
        createdProduct.setPrice(75.00);

        mvc.perform(put("/product/" + createdProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdProduct)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(75.00));
    }

    @Test
    @Transactional
    public void testUpdateInventory() throws Exception {
        // First create a product
        Product product = new Product();
        product.setName("Inventory Test Product");
        product.setShortDescription("Test Short Description");
        product.setPrice(25.00);

        String createResult = mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn().getResponse().getContentAsString();

        Product createdProduct = objectMapper.readValue(createResult, Product.class);

        // Update inventory
        mvc.perform(post("/product/" + createdProduct.getId() + "/inventory")
                .param("quantity", "10"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.inventory.quantity").value(10));
    }

    @Test
    public void testUpdateNonExistentProduct() throws Exception {
        Product product = new Product();
        product.setId(999L); // Non-existent ID
        product.setName("Non-existent Product");
        product.setShortDescription("Test Description");
        product.setPrice(10.00);

        mvc.perform(put("/product/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testUpdateInventoryForNonExistentProduct() throws Exception {
        mvc.perform(post("/product/999/inventory")
                .param("quantity", "10"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
