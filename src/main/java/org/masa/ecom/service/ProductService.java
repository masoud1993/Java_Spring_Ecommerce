package org.masa.ecom.service;


import org.masa.ecom.model.Product;
import org.masa.ecom.payload.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
