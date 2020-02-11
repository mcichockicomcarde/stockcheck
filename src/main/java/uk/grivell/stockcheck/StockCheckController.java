package uk.grivell.stockcheck;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import uk.grivell.stockcheck.persistence.*;
import uk.grivell.stockcheck.dto.StockCheckResult;

import java.util.ArrayList;
import java.util.List;


@Controller
public class StockCheckController {
    Logger logger = LoggerFactory.getLogger(StockCheckController.class);

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private ProductRuleRepository productRuleRepository;

    @Autowired
    private StockCheckRecommendationRepository stockCheckRecommendationRepository;

    @GetMapping("/stockcheck")
    @ResponseBody
    public StockCheckResult getStockCheck() {
        logger.info("Processing stock check request.");

        List<StockCheckRecommendation> stockCheckRecommendations = new ArrayList<>();
        productStockRepository.findAll().forEach(product -> {
            ProductRule rule = productRuleRepository.findByName(product.getName());
            StockCheckRecommendation recommendation = StockCheckProcessor.getRecommendation(product, rule);
            stockCheckRecommendationRepository.save(recommendation);
            stockCheckRecommendations.add(recommendation);
        });
        return new StockCheckResult(stockCheckRecommendations);
    }

    @GetMapping("/prepare")
    @ResponseBody
    public StockCheckRecommendation prepare(){
        final StockCheckRecommendation explosion = new StockCheckRecommendationBuilder()
            .withName("prepare")
            .withComments("comments")
            .withCurrentStock(3)
            .withOrderQuantity(1)
            .build();
        return stockCheckRecommendationRepository.save(explosion);
    }

    @GetMapping("/explode")
    @ResponseBody
    public StockCheckRecommendation explode(){
        return stockCheckRecommendationRepository.findById("1").orElseThrow(() -> new RuntimeException("whatever"));
    }
}
