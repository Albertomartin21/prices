package zara.capitole.controller;


import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import zara.capitole.model.Product;
import zara.capitole.model.dto.ProductDTO;
import zara.capitole.service.ProductService;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getListProducts(){
         return productService.getListProducts();
    }

    @GetMapping("/products/{productId}/brands/{brandId}")
    public List<ProductDTO> getListProductFilteredByActualTimeProuctIdAndBrandId(@RequestParam String actualTime,
                                                                                @PathVariable String productId, @PathVariable String brandId){
         return  productService.getListProductFilteredByActualTimeProuctIdAndBrandId(actualTime, productId, brandId);
    }
}
