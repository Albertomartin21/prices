package zara.capitole.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import zara.capitole.model.Product;
import zara.capitole.service.MapperService;
import zara.capitole.model.dto.ProductDTO;

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
   
}
