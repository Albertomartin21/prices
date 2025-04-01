package zara.capitole.application.service;
import java.util.List;

import zara.capitole.domain.model.Product;
import zara.capitole.domain.model.dto.ProductDTO;

public interface ProductService {
    List<Product> getListProducts();
    List<ProductDTO> getListProductFilteredByActualTimeProuctIdAndBrandId(String actualTime,String productId, String brandId);
}
