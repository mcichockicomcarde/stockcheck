package uk.grivell.stockcheck;

import uk.grivell.stockcheck.persistence.ProductRule;
import uk.grivell.stockcheck.persistence.ProductStock;
import uk.grivell.stockcheck.persistence.StockCheckRecommendation;
import uk.grivell.stockcheck.persistence.StockCheckRecommendationBuilder;

public class StockCheckProcessor {

    public static StockCheckRecommendation getRecommendation(ProductStock product, ProductRule rule) {
        int orderQuantity = 0;
        String comments;
        if (product.getQuantity() < rule.getMinQty() && !rule.isBlocked()) {
            orderQuantity = Math.max(rule.getMinQty() - product.getQuantity(), rule.getMinOrder());
            comments = "Order now";
        } else {
            comments = rule.isBlocked() ? "Product Blocked" : "Sufficient Stock";
        }
        return new StockCheckRecommendationBuilder()
                .withName(product.getName())
                .withCurrentStock(product.getQuantity())
                .withOrderQuantity(orderQuantity)
                .withComments(comments).build();
    }
}
