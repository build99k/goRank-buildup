package com.gorank.app.stock.repository;

import com.gorank.app.stock.domain.entity.KospiStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<KospiStockEntity,Long> {

}
