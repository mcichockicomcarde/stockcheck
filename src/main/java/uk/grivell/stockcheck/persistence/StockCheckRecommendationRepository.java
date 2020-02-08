package uk.grivell.stockcheck.persistence;

import org.springframework.data.repository.CrudRepository;

public interface StockCheckRecommendationRepository extends CrudRepository<StockCheckRecommendation, String> {
    @Override
    Iterable<StockCheckRecommendation> findAll();

    @Override
    StockCheckRecommendation save(StockCheckRecommendation s);
}

