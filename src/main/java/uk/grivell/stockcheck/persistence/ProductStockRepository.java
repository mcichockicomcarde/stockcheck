package uk.grivell.stockcheck.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ProductStockRepository extends CrudRepository<ProductStock, String> {
    @Override
    Iterable<ProductStock> findAll();
}

