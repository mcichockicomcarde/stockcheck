package uk.grivell.stockcheck.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ProductRuleRepository extends CrudRepository<ProductRule, String> {
    ProductRule findByName(String name);
}

