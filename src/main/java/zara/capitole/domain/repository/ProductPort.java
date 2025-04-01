package zara.capitole.domain.repository;
import java.util.List;
import zara.capitole.domain.model.Product;

public interface ProductPort {

    List<Product> getAllProducts();
    
    List<Product> getProductsByBrandIdAndProductId(String brandId, String productId);
}
