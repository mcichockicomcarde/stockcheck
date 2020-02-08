package uk.grivell.stockcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.grivell.stockcheck.dto.StockCheckResult;
import uk.grivell.stockcheck.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class StockCheckAuditController {
    Logger logger = LoggerFactory.getLogger(StockCheckAuditController.class);

    @Autowired
    private StockCheckRecommendationRepository stockCheckRecommendationRepository;

    @GetMapping("/audit")
    @ResponseBody
    public StockCheckResult getStockCheck() {
        logger.info("Processing stock check audit request.");
        List<StockCheckRecommendation> stockCheckRecommendations = new ArrayList<>();
        stockCheckRecommendationRepository.findAll().forEach(r -> {
            stockCheckRecommendations.add(r);
        });
        return new StockCheckResult(stockCheckRecommendations);
    }


}
