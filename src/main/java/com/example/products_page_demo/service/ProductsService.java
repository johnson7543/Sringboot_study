package com.example.products_page_demo.service;

import com.example.products_page_demo.model.Products;
import com.example.products_page_demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public void addMultiData(List<Products> productsList) {
        for (int i = 0; i < productsList.size(); i++)
            productsRepository.save(productsList.get(i));

    }

}
