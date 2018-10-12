package storage.service;

import storage.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll(String name, String brandName, Integer restLimit);
    void saveAll(List<Product> read);
}
