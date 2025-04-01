package zara.capitole.infraestructure.repository.port;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import zara.capitole.application.service.MapperService;
import zara.capitole.domain.model.Product;
import zara.capitole.domain.repository.ProductPort;
import zara.capitole.infraestructure.repository.ProductRepository;


@Service
public class ProductPortImpl implements ProductPort {

    
    private ProductRepository productRepository;
    private MapperService mapperService;

    public ProductPortImpl(@Lazy final ProductRepository productRepository, final MapperService mapperService) {
        this.productRepository = productRepository;
        this.mapperService = mapperService;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll().stream()
            .map(product -> mapperService.mapProductEntityToProduct(product))
            .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByBrandIdAndProductId(String brandId, String productId) {
        return productRepository.findProductsByBrandIdAndProductId(brandId, productId)
        .stream()
        .map(product -> mapperService.mapProductEntityToProduct(product))
        .collect(Collectors.toList());
    }

}