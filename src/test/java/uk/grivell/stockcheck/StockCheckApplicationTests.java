package uk.grivell.stockcheck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import uk.grivell.stockcheck.persistence.StockCheckRecommendation;
import uk.grivell.stockcheck.persistence.StockCheckRecommendationBuilder;
import uk.grivell.stockcheck.dto.StockCheckResult;
import uk.grivell.stockcheck.persistence.StockCheckRecommendationRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockCheckApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StockCheckRecommendationRepository stockCheckRecommendationRepository;

    @Test
    public void testStockCheckController() {
        StockCheckResult expectedResult = expectedResult();
        StockCheckResult actualResult = restTemplate.getForObject("http://localhost:" + port + "/stockcheck",
                StockCheckResult.class);
        for(int i = 0; i < expectedResult.getRecommendations().size(); i++) {
            assertEquals( expectedResult.getRecommendations().get(i), actualResult.getRecommendations().get(i), "Item " + i + " does not match ");
        }

        List<StockCheckRecommendation> auditEntries = new ArrayList<>();
        stockCheckRecommendationRepository.findAll().forEach(r ->auditEntries.add(r));
        assertEquals(5, auditEntries.size());

    }

    private StockCheckResult expectedResult() {
        return new StockCheckResult(Arrays.asList(
                new StockCheckRecommendationBuilder()
                        .withName("A")
                        .withCurrentStock(5)
                        .withOrderQuantity(0)
                        .withComments("Sufficient Stock").build(),
                new StockCheckRecommendationBuilder()
                        .withName("B")
                        .withCurrentStock(8)
                        .withOrderQuantity(0)
                        .withComments("Sufficient Stock").build(),
                new StockCheckRecommendationBuilder()
                        .withName("C")
                        .withCurrentStock(2)
                        .withOrderQuantity(0)
                        .withComments("Product Blocked").build(),
                new StockCheckRecommendationBuilder()
                        .withName("D")
                        .withCurrentStock(0)
                        .withOrderQuantity(15)
                        .withComments("Order now").build(),
                new StockCheckRecommendationBuilder()
                        .withName("E")
                        .withCurrentStock(1)
                        .withOrderQuantity(3)
                        .withComments("Order now").build()));
    }
}