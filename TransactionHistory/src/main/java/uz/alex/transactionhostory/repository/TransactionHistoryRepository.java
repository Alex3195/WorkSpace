package uz.alex.transactionhostory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.transactionhostory.model.TransactionHistory;

import java.util.Collection;
import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByAuctionIdAndTransactionStatus(Long auctionId, String transactionStatus);


    List<TransactionHistory> findByUserIdAndTransactionStatus(Long userId, String completed);
}
