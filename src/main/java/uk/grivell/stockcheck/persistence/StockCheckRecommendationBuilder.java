package uk.grivell.stockcheck.persistence;

import java.util.Date;

public class StockCheckRecommendationBuilder {
    String name;
    Date timestamp;
    Integer currentStock;
    Integer orderQuantity;
    String recommendation;

    public StockCheckRecommendationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StockCheckRecommendationBuilder withTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public StockCheckRecommendationBuilder withCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
        return this;
    }

    public StockCheckRecommendationBuilder withOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
        return this;
    }

    public StockCheckRecommendationBuilder withComments(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public StockCheckRecommendation build() {
        return new StockCheckRecommendation(name, timestamp, currentStock, orderQuantity, recommendation);
    }
}
