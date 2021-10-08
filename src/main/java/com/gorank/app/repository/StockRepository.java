package com.gorank.app.repository;

import com.gorank.app.domain.entity.StockRankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockRankEntity,Long> {

}
