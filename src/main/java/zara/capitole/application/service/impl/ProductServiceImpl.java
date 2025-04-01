package zara.capitole.application.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import zara.capitole.application.service.MapperService;
import zara.capitole.application.service.ProductService;
import zara.capitole.domain.model.Product;
import zara.capitole.domain.model.dto.ProductDTO;
import zara.capitole.domain.repository.ProductPort;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductPort productPort;
    private final MapperService mapperService;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");



    public ProductServiceImpl(final ProductPort productPort, final MapperService mapperService){
        this.productPort = productPort;
        this.mapperService = mapperService;
    }

    @Override
    public List<Product> getListProducts() {
        return productPort.getAllProducts();
    }

    @Override
    public List<ProductDTO> getListProductFilteredByActualTimeProuctIdAndBrandId(String actualTime,String productId, String brandId) {
        var timeParser = LocalDateTime.parse(actualTime,formatter);
        return productPort.getProductsByBrandIdAndProductId(brandId,productId)
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
