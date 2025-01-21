package zara.capitole.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import zara.capitole.model.Product;
import zara.capitole.repository.ProductRepository;

@Component
@Slf4j
public class JpaDatabaseConfig {
    
    private ProductRepository productRepository;

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    public JpaDatabaseConfig(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    
    @EventListener(ContextRefreshedEvent.class)
    public void generateDataInDatabase(){
        log.info("Generate files in database");
        var listProducts = getAllProducts();
        productRepository.saveAll(listProducts);
        log.info("Database generate");
    }

    private List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = Product.builder()
            .brandId("1")
            .starDate(LocalDateTime.parse("2020-06-14-00.00.00",formatter))
            .endDate(LocalDateTime.parse("2020-12-31-23.59.59",formatter))
            .priceList("1")
            .productId("35455")
            .priority(1)
            .price(35.50)
            .curr("EUR")
            .build();
        
        Product product2 = Product.builder()
            .brandId("1")
            .starDate(LocalDateTime.parse("2020-06-14-15.00.00",formatter))
            .endDate(LocalDateTime.parse("2020-06-14-18.30.00",formatter))
            .priceList("2")
            .productId("35455")
            .priority(1)
            .price(25.45)
            .curr("EUR")
            .build();

        Product product3 = Product.builder()
            .brandId("1")
            .starDate(LocalDateTime.parse("2020-06-15-00.00.00",formatter))
            .endDate(LocalDateTime.parse("2020-06-15-11.00.00",formatter))
            .priceList("3")
            .productId("35455")
            .priority(1)
            .price(30.50)
            .curr("EUR")
            .build();
        
        Product product4 = Product.builder()
            .brandId("1")
            .starDate(LocalDateTime.parse("2020-06-15-16.00.00",formatter))
            .endDate(LocalDateTime.parse("2020-12-31-23.59.59",formatter))
            .priceList("4")
            .productId("35455")
            .priority(1)
            .price(38.95)
            .curr("EUR")
            .build();


        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);


        return products;
    }
}
