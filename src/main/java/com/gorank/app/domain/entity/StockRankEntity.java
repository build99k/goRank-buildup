package com.gorank.app.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "StockRank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRankEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long STOCK_NO;
  private String ticker;
  private String name;

  @Builder
  public StockRankEntity(String ticker,String name) {
    this.ticker = ticker;
    this.name = name;
  }
}
