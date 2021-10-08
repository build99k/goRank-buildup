package com.gorank.app.service.stock;

import com.gorank.app.component.stock.JsoupComponent;
import com.gorank.app.domain.dto.KospiStockDto;
import com.gorank.app.domain.entity.StockRankEntity;
import com.gorank.app.repository.StockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

  private final JsoupComponent jsoupComponent;
  private final StockRepository stockRepository;

  public List<KospiStockDto> getKosPiStockList() {
    List<StockRankEntity> entities = stockRepository.findAll();
    entities.forEach(x -> log.info(x.getName()));
    return jsoupComponent.getKosPiStockList();
  }
}
