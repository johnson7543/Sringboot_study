package com.example.products_page_demo;

import com.example.products_page_demo.model.Products;
import com.example.products_page_demo.repository.ProductsRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductsRepositoryTests {
    @Autowired
    private ProductsRepository testRepo;

    @Test
    public void testAddNew() {
        Products testProducts = new Products();
        testProducts.setCode("test_code");
        testProducts.setName("test_name");
        testProducts.setDescription("test_description");

        Products savedProduct = testRepo.save(testProducts);
        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void testListAll() {
        Iterable<Products> products = testRepo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for (Products product : products) {
            System.out.println(product);
        }  // for ()
    }

}
