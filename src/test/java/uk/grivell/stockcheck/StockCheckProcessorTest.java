package uk.grivell.stockcheck;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import uk.grivell.stockcheck.persistence.StockCheckRecommendation;
import uk.grivell.stockcheck.persistence.StockCheckRecommendationBuilder;
import uk.grivell.stockcheck.persistence.ProductRule;
import uk.grivell.stockcheck.persistence.ProductStock;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockCheckProcessorTest {

    @Test
    public void testStockCheckProcessor() {
        BiConsumer<Pair<ProductStock, ProductRule>, StockCheckRecommendation> test = (in, expected) -> {
            StockCheckRecommendation actual = StockCheckProcessor.getRecommendation(in.getLeft(), in.getRight());
            assertEquals(expected, actual);
        };

        test.accept(Pair.of(createProduct(3), createRule(2, false, 1)),
                new StockCheckRecommendationBuilder()
                        .withName("A")
                        .withCurrentStock(3)
                        .withOrderQuantity(0)
                        .withComments("Sufficient Stock").build());
        test.accept(Pair.of(createProduct(3), createRule(5, true, 1)),
                new StockCheckRecommendationBuilder()
                        .withName("A")
                        .withCurrentStock(3)
                        .withOrderQuantity(0)
                        .withComments("Product Blocked").build());
        test.accept(Pair.of(createProduct(3), createRule(5, false, 1)),
                new StockCheckRecommendationBuilder()
                        .withName("A")
                        .withCurrentStock(3)
                        .withOrderQuantity(2)
                        .withComments("Order now").build());
        test.accept(Pair.of(createProduct(3), createRule(5, false, 7)),
                new StockCheckRecommendationBuilder()
                        .withName("A")
                        .withCurrentStock(3)
                        .withOrderQuantity(7)
                        .withComments("Order now").build());
    }

    private ProductStock createProduct(int quantity) {
        ProductStock product = new ProductStock();
        product.setName("A");
        product.setQuantity(quantity);
        return product;
    }

    private ProductRule createRule(int minQty, boolean blocked, int minOrder) {
        ProductRule rule = new ProductRule();
        rule.setName("A");
        rule.setMinQty(minQty);
        rule.setBlocked(blocked);
        rule.setMinOrder(minOrder);
        return rule;
    }
}
