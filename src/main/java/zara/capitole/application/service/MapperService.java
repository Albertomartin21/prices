package zara.capitole.application.service;

import java.time.LocalDateTime;

import zara.capitole.domain.model.Product;
import zara.capitole.domain.model.dto.ProductDTO;
import zara.capitole.infraestructure.entity.ProductEntity;

public interface MapperService {
    
    ProductDTO mapProductToProductDTO(Product product, LocalDateTime date);
    Product mapProductEntityToProduct(ProductEntity productEntity);
}
