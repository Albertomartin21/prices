package zara.capitole.service;

import java.time.LocalDateTime;

import zara.capitole.model.Product;
import zara.capitole.model.dto.ProductDTO;

public interface MapperService {
    
    ProductDTO mapProductToProductDTO(Product product, LocalDateTime date);
}
