package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Dtos.MarketDTO;
import edu.pe.upao.localboost.Models.Market;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Services.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/market")
public class MarketController {
    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addMarket(@RequestBody Market market){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String newTip = marketService.addMarket(market);
            return new ResponseEntity<>(newTip, HttpStatus.CREATED);
        } catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewmarket")
    public List<MarketDTO> getAllMarkets() {
        return marketService.getAllMarkets();
    }
}
