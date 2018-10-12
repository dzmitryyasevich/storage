package storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.domain.Product;
import storage.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(String name, String brandName, Integer restLimit) {
        List<Product> result;

        if (restLimit != null) {
            result = productRepository.findAllByQuantityLessThanEqual(restLimit);
        } else {
            if (isStringNullOrEmpty(name) && isStringNullOrEmpty(brandName)) {
                result = productRepository.findAll();
            } else if (!isStringNullOrEmpty(name) && isStringNullOrEmpty(brandName)) {
                result = productRepository.findAllByName(name);
            } else if (isStringNullOrEmpty(name) && !isStringNullOrEmpty(brandName)) {
                result = productRepository.findAllByBrandName(brandName);
            } else {
                result = productRepository.findAllByNameAndBrandName(name, brandName);
            }
        }
        return result;
    }

    @Override
    public void saveAll(List<Product> read) {
        productRepository.saveAll(read);
    }

    boolean isStringNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
