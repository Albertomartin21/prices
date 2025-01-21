package zara.capitole.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import zara.capitole.model.Product;
import zara.capitole.repository.ProductRepository;
import zara.capitole.service.MapperService;
import zara.capitole.service.ProductService;
import zara.capitole.model.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private MapperService mapperService;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");



    public ProductServiceImpl(ProductRepository productRepository, MapperService mapperService){
        this.productRepository = productRepository;
        this.mapperService = mapperService;
    }

    @Override
    public List<Product> getListProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> getListProductFilteredByActualTimeProuctIdAndBrandId(String actualTime,String productId, String brandId) {
        var timeParser = LocalDateTime.parse(actualTime,formatter);
        return productRepository.findProductsByBrandIdAndProductId(brandId,productId)
            .stream()
            .filter(product -> isDateRange(timeParser,product))
            .map(product -> mapperService.mapProductToProductDTO(product,timeParser))
            .collect(Collectors.toList());
    }

    private boolean isDateRange(LocalDateTime date, Product product){
        return date.isAfter(product.getStarDate()) && date.isBefore(product.getEndDate())
            || date.isEqual(product.getStarDate())
            || date.isEqual(product.getEndDate());
    }
    
}
