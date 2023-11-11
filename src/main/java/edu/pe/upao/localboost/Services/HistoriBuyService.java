package edu.pe.upao.localboost.Services;

import edu.pe.upao.localboost.Models.HistoryBuyModel;
import edu.pe.upao.localboost.Repositories.HistoryBuyRespository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class HistoriBuyService {
    private final HistoryBuyRespository historyBuyRespository;

    public HistoriBuyService(HistoryBuyRespository historyBuyRespository) {
        this.historyBuyRespository = historyBuyRespository;
    }

    public List<HistoryBuyModel> getAllProduct(){
        return historyBuyRespository.findAll();
    }

    public Optional<HistoryBuyModel> getProductById(Long productId){
        return historyBuyRespository.findById(productId);
    }

    public HistoryBuyModel addProduct(HistoryBuyModel historyBuyModel){
        return historyBuyRespository.save(historyBuyModel);
    }

    public void deleteProductById(Long productId){
        historyBuyRespository.deleteById(productId);
    }
}
