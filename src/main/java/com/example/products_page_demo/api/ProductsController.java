package com.example.products_page_demo.api;

import com.example.products_page_demo.model.Products;
import com.example.products_page_demo.repository.ProductsRepository;
import com.example.products_page_demo.service.ProductsService;
import com.example.products_page_demo.service.RestTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/products")
@Controller
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private RestTemplateService restTemplateService;
    @Autowired
    private ProductsService productsService;

    @GetMapping
    @ResponseBody
    public List<Products> getAllProducts() {
        return (List<Products>) productsRepository.findAll();
    }

    @RequestMapping(("/index"))
    public String returnAllproducts(Model model) {
        model.addAttribute("productsList", productsRepository.findAll());
        return "index";
    }

    @PostMapping
    @ResponseBody
    public Products add(@RequestBody Products products){
        productsRepository.save(products);
        return products;
    }

    @PostMapping("/restTemplate/hktv")
    @ResponseBody
    public String getFromRestTemplate() throws JsonProcessingException, JSONException {
        productsService.addMultiData(restTemplateService.getFromHktvApi());
        return "Calling HKTV Api...";
    }


}


