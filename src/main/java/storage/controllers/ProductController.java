package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import storage.domain.Product;
import storage.repositories.ProductRepository;
import storage.security.IsAdmin;
import storage.security.IsViewer;
import storage.service.IProductService;
import storage.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;
    private ProductRepository productRepository;

    @Autowired
    public ProductController(IProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @IsViewer
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "brand", required = false, defaultValue = "") String brandName,
            @RequestParam(value = "limit", required = false) Integer restLimit
    ) {
        return productService.findAll(name, brandName, restLimit);
    }

    @IsAdmin
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        InputStream inputStream = null;
        List<Product> read;
        try {
            inputStream = file.getInputStream();
            read = CsvUtils.read(Product.class, inputStream);
            productService.saveAll(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @IsAdmin
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        productRepository.save(product);
        return product;
    }

}
