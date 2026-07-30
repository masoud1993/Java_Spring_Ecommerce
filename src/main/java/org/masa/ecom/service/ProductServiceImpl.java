package org.masa.ecom.service;

import lombok.AllArgsConstructor;
import org.masa.ecom.exceptions.ResourceNotFoundException;
import org.masa.ecom.model.Category;
import org.masa.ecom.model.Product;
import org.masa.ecom.payload.ProductDTO;
import org.masa.ecom.repository.CategoryRepository;
import org.masa.ecom.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        product.setCategory(category);
        double specialPrice = product.getPrice() - (product.getPrice() * (product.getDiscount()/100));
        product.setSpecialPrice(specialPrice);
        product.setImage("default.png");

        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductDTO.class);
    }
}
