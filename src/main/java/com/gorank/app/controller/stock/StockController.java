package com.gorank.app.controller.stock;

import com.gorank.app.domain.dto.KospiStockDto;
import com.gorank.app.service.stock.StockService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;

  @GetMapping("/kospi/all")
  public List<KospiStockDto> getKosPiStockList(HttpServletRequest request) {
    return stockService.getKosPiStockList();

  }
}
