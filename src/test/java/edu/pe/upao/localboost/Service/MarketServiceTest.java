package edu.pe.upao.localboost.Service;

import edu.pe.upao.localboost.Dtos.MarketDTO;
import edu.pe.upao.localboost.Models.Market;
import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.MarketRepository;
import edu.pe.upao.localboost.Services.MarketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class MarketServiceTest {

    @Mock
    private MarketRepository marketRepository;

    @InjectMocks
    private MarketService marketService;

    @Test
    void testAddMarketWithInvalidData() {
        MockitoAnnotations.openMocks(this);

        // Create a sample market with missing required fields
        Market market = new Market();

        // Test the addMarket method
        assertThrows(IllegalStateException.class, () -> marketService.addMarket(market));
    }
    @Test
    void testGetAllMarkets() {
        List<Market> marketsList = new ArrayList<>();
        when(marketRepository.findAll()).thenReturn(marketsList);
        List<MarketDTO> result = marketService.getAllMarkets();
        verify(marketRepository, times(1)).findAll();
    }

}
