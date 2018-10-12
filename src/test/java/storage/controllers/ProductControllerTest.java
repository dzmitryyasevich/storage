package storage.controllers;

import com.google.common.collect.ImmutableList;
import storage.domain.Product;
import storage.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import storage.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController controllerTest;

    @Test
    public void getAllProducts() {

        when(repository.findAll()).thenReturn(ImmutableList.of());

        List<Product> productList = controllerTest.getAllProducts(null, null, null);

        verify(repository).findAll();

    }


}