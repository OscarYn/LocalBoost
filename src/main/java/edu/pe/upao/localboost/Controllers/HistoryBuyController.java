package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Models.HistoryBuyModel;
import edu.pe.upao.localboost.Services.HistoriBuyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class HistoryBuyController {
    private final HistoriBuyService historiBuyService;

    public HistoryBuyController(HistoriBuyService historiBuyService) {
        this.historiBuyService = historiBuyService;
    }
    @GetMapping
    private List<HistoryBuyModel> getAllProduct(){
        return historiBuyService.getAllProduct();
    }

    @GetMapping("/{productId}")
    public HistoryBuyModel getProductById(@PathVariable Long productId){
        return historiBuyService.getProductById(productId).orElse(new HistoryBuyModel());
    }

    @PostMapping
    public void addProduct(@RequestBody HistoryBuyModel historyBuyModel){
        historiBuyService.addProduct(historyBuyModel);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        historiBuyService.deleteProductById(productId);
    }
}
