package com.gorank.app.stock.service;

import com.gorank.app.stock.component.JsoupComponent;
import com.gorank.app.model.dto.KospiStockDto;
import com.gorank.app.stock.domain.entity.KospiStockEntity;
import com.gorank.app.stock.repository.StockRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

  private final JsoupComponent jsoupComponent;
  private final StockRepository stockRepository;

  public List<KospiStockDto> getKosPiStockList() {
    return jsoupComponent.getKosPiStockList();
  }

  public List<KospiStockDto> getKospiStockListFromDB() {
    ModelMapper modelMapper = new ModelMapper();
    return stockRepository.findAll()
                          .stream()
                          .map(x -> modelMapper.map(x, KospiStockDto.class))
                          .collect(Collectors.toList());
  }

  public void saveKosPiStock() {
    ModelMapper modelMapper = new ModelMapper();
    List<KospiStockEntity> entities = jsoupComponent.getKosPiStockList()
                                                    .stream()
                                                    .map(x -> modelMapper.map(x, KospiStockEntity.class))
                                                    .collect(Collectors.toList());
    stockRepository.saveAll(entities);
  }
}
