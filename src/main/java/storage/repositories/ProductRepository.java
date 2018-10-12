package storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.domain.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByQuantityLessThanEqual(Integer restLimit);
    List<Product> findAllByName(String name);
    List<Product> findAllByBrandName(String brandName);
    List<Product> findAllByNameAndBrandName(String name, String brandName);
}