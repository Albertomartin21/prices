package zara.capitole.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long id;
    private String brandId;
    private LocalDateTime starDate;
    private LocalDateTime endDate;
    private String priceList;
    private String productId; 
    private int priority;
    private Double price;
    private String curr;

}