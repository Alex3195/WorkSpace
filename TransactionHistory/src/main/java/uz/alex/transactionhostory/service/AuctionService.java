package uz.alex.transactionhostory.service;

import java.math.BigDecimal;

public interface AuctionService {
    void placeBid(Long auctionId, Long userId, BigDecimal bidAmount);
    boolean validateUserBalance(Long userId);
}
