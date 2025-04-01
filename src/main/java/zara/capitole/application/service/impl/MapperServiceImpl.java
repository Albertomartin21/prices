package zara.capitole.application.service.impl;

import java.time.LocalDateTime;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import zara.capitole.application.service.MapperService;
import zara.capitole.domain.model.Product;
import zara.capitole.domain.model.dto.ProductDTO;
import zara.capitole.infraestructure.entity.ProductEntity;

@Service
public class MapperServiceImpl implements MapperService {

    public MapperServiceImpl(){}

    @Override
    public ProductDTO mapProductToProductDTO(Product product, LocalDateTime date) {
        return ProductDTO.builder()
        .brandId(product.getBrandId())
        .date(date.toString())
        .discount(product.getPriceList())
        .productId(product.getProductId())
        .price(getTotalPrice(product))
        .build();
    }

    private String getTotalPrice(Product product){
        return product.getPrice() + Strings.EMPTY + product.getCurr();
    }

    @Override
    public Product mapProductEntityToProduct(ProductEntity productEntity) {
        return Product.builder()
        .id(productEntity.getId())
        .brandId(productEntity.getBrandId())
        .starDate(productEntity.getStarDate())
        .endDate(productEntity.getEndDate())
        .priceList(productEntity.getPriceList())
        .productId(productEntity.getProductId())
        .priority(productEntity.getPriority())
        .price(productEntity.getPrice())
        .curr(productEntity.getCurr())
        .build();
    }
}
