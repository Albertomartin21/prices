package zara.capitole.service;
import java.time.LocalDateTime;
import java.util.List;
import zara.capitole.model.Product;
import zara.capitole.model.dto.ProductDTO;

public interface ProductService {
    List<Product> getListProducts();
    List<ProductDTO> getListProductFilteredByActualTimeProuctIdAndBrandId(String actualTime,String productId, String brandId);
}
