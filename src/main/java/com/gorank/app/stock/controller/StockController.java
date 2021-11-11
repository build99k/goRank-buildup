package com.gorank.app.stock.controller;

import com.gorank.app.model.dto.KospiStockDto;
import com.gorank.app.stock.service.StockService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;
  private final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;


  @GetMapping("/kospi/all")
  public List<KospiStockDto> getKosPiStockList(HttpServletRequest request) {
    return stockService.getKosPiStockList();
  }

  @GetMapping("/kospi/db")
  public List<KospiStockDto> getKosPiStockListFromDB(HttpServletRequest request) {
    return stockService.getKospiStockListFromDB();
  }

  @GetMapping("/kospi/add")
  public void addKospiData(HttpServletRequest request) {
    stockService.saveKosPiStock();
  }
}
