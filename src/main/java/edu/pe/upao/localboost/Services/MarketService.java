package edu.pe.upao.localboost.Services;

import edu.pe.upao.localboost.Dtos.MarketDTO;
import edu.pe.upao.localboost.Models.Market;
import edu.pe.upao.localboost.Repositories.MarketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MarketService {

    private final MarketRepository marketRepository;

@Autowired
    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }
    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String addMarket(Market market){
        validateMarket(market);
        Market savedmarket = marketRepository.save(market);
        return "Se creo su tienda correctamente";
    }

    public List<MarketDTO> getAllMarkets() {
        List<Market> markets = marketRepository.findAll();
        List<MarketDTO> marketsAll = new ArrayList<>();
        for (Market market : markets) {
            marketsAll.add(new MarketDTO(market));
        }
        return marketsAll;
    }
    private void validateMarket(Market market) {
        if (isEmptyOrWhitespace(market.getMarketInfo()) || isEmptyOrWhitespace(market.getMarketName())
                || isEmptyOrWhitespace(String.valueOf(market.getProduct())) || market.getUser() == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }
    }
}
