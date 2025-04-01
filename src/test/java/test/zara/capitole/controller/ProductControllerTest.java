package test.zara.capitole.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import zara.capitole.CapitoleApplication;
import zara.capitole.domain.model.dto.ProductDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(classes = CapitoleApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
}

    @ParameterizedTest
    @MethodSource("providerParameters")
    void shouldFilterProducts(String branchId, String date, String productId, int numProducts) throws Exception {

        String url = "/products/{productId}/brands/{brandId}"
                            .replace("{productId}", productId)
                            .replace("{brandId}", branchId);
   
        MvcResult mvcResult = mockMvc.perform(get(url)
                            .param("actualTime",date))
                            .andExpect(status().isOk()) 
                            .andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();   
        assertNotNull(responseContent);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductDTO> productList = objectMapper.readValue(responseContent, new TypeReference<List<ProductDTO>>() {});
        assertEquals(productList.size(), numProducts);   
    }


    private static Stream<Arguments> providerParameters() {
        return Stream.of(
        Arguments.of("1", "2020-06-14-10.00.00","35455",1),
        Arguments.of("1", "2020-06-14-16.00.00","35455",2),
        Arguments.of("1", "2020-06-14-21.00.00","35455",1),
        Arguments.of("1", "2020-06-15-10.00.00","35455",2),
        Arguments.of("1", "2020-06-16-21.00.00","35455",2)
    );
}
}
