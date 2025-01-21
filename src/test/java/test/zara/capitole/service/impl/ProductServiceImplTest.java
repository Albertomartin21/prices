package test.zara.capitole.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import zara.capitole.model.Product;
import zara.capitole.repository.ProductRepository;
import zara.capitole.service.MapperService;
import zara.capitole.service.ProductService;
import zara.capitole.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    private ProductService productService;

    @Mock 
    private ProductRepository productRepository;

    @Mock
    private MapperService mapperService;

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @BeforeEach
    void setUp(){
        productService = new ProductServiceImpl(productRepository,mapperService);
    }

    @ParameterizedTest
    @MethodSource("providerParameters")
    void shouldFilterProducts(String branchId, String date, String productId, int numProducts) {
        List<Product> listRepository = getAllProducts();
        when(productRepository.findProductsByBrandIdAndProductId(branchId, productId)).thenReturn(listRepository);
        var result = productService.getListProductFilteredByActualTimeProuctIdAndBrandId(date,productId,branchId);
        
        assertNotNull(result);
        verify(productRepository).findProductsByBrandIdAndProductId(branchId, productId);
        verify(mapperService, times(numProducts)).mapProductToProductDTO(any(Product.class), any(LocalDateTime.class));
    }


private static Stream<Arguments> providerParameters() {
        return Stream.of(
        Arguments.of("1", "2020-06-14-10.00.00","35455",1),
        Arguments.of("1","2020-06-14-16.00.00","35455",2),
        Arguments.of("1", "2020-06-14-21.00.00","35455",1),
        Arguments.of("1", "2020-06-15-10.00.00","35455",2),
        Arguments.of("1", "2020-06-16-21.00.00","35455",2)
    );
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
