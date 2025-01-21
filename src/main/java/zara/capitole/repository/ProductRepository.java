package zara.capitole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import zara.capitole.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findProductsByBrandIdAndProductId(String brandId, String productId);
}
