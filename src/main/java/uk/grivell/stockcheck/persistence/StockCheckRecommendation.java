package uk.grivell.stockcheck.persistence;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class StockCheckRecommendation {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timeStamp;

    @Column
    private Integer currentStock;

    @Column
    private Integer orderQuantity;

    @Column
    private String recommendation;

    public StockCheckRecommendation() {
    }

    StockCheckRecommendation(String name, Date timeStamp, int currentStock, int orderQuantity, String recommendation) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.currentStock = currentStock;
        this.orderQuantity = orderQuantity;
        this.recommendation = recommendation;
    }

    public String getName() {
        return name;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public String getRecommendation() {
        return recommendation;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, Collections.singleton("timeStamp"));
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
