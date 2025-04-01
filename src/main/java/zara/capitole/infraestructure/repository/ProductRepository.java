package zara.capitole.infraestructure.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zara.capitole.infraestructure.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    List<ProductEntity> findProductsByBrandIdAndProductId(String brandId, String productId);
}
