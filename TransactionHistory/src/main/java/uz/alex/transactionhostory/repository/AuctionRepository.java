package uz.alex.transactionhostory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.transactionhostory.model.Auction;
import uz.alex.transactionhostory.model.TransactionHistory;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
