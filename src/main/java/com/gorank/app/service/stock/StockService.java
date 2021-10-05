package com.gorank.app.service.stock;

import com.gorank.app.component.stock.JsoupComponent;
import com.gorank.app.domain.dto.KospiStockDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

  private final JsoupComponent jsoupComponent;

  public List<KospiStockDto> getKosPiStockList() {
    return jsoupComponent.getKosPiStockList();
  }
}
