package uk.grivell.stockcheck.dto;

import uk.grivell.stockcheck.persistence.StockCheckRecommendation;

import java.util.ArrayList;
import java.util.List;

public class StockCheckResult {
    ArrayList<StockCheckRecommendation> recommendations;

    public StockCheckResult() {
    }

    public StockCheckResult(List<StockCheckRecommendation> recommendations) {
        this.recommendations = new ArrayList<>(recommendations);
    }

    public List<StockCheckRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<StockCheckRecommendation> recommendations) {
        this.recommendations = new ArrayList<>(recommendations);
    }
}
