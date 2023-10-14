package edu.pe.upao.localboost.Repositories;

import edu.pe.upao.localboost.Models.HistoryBuyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface HistoryBuyRespository extends JpaRepository<HistoryBuyModel, Long> {
}
