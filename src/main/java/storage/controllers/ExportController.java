package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import storage.security.IsViewer;
import storage.service.IProductService;

@Controller
@RequestMapping("/products/download")
public class ExportController {

    private IProductService productService;

    @Autowired
    public ExportController(IProductService productService) {
        this.productService = productService;
    }

    @IsViewer
    @GetMapping
    public String download(Model model,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam(value = "brand", required = false, defaultValue = "") String brandName
    ) {
        model.addAttribute("products", productService.findAll(name, brandName, null));
        return "";
    }
}
