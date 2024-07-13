package uz.alex.transactionhostory.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.alex.transactionhostory.model.Auction;
import uz.alex.transactionhostory.model.TransactionHistory;
import uz.alex.transactionhostory.model.User;
import uz.alex.transactionhostory.repository.AuctionRepository;
import uz.alex.transactionhostory.repository.TransactionHistoryRepository;
import uz.alex.transactionhostory.repository.UserRepository;
import uz.alex.transactionhostory.service.AuctionService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final UserRepository userRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository, TransactionHistoryRepository transactionHistoryRepository, UserRepository userRepository) {
        this.auctionRepository = auctionRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void placeBid(Long auctionId, Long userId, BigDecimal bidAmount) {
        Auction auction = auctionRepository.findById(auctionId).orElseThrow(() -> new RuntimeException("Auction not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (bidAmount.compareTo(auction.getCurrentHighestBid()) > 0) {
            rollbackPreviousTransactions(auctionId);

            auction.setCurrentHighestBid(bidAmount);
            auction.setCurrentHighestBidder(user);
            auctionRepository.save(auction);

            updateUserBalance(user, bidAmount.negate());

            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setAuctionId(auctionId);
            transactionHistory.setUser(user);
            transactionHistory.setBidAmount(bidAmount);
            transactionHistory.setTransactionStatus("COMPLETED");
            transactionHistoryRepository.save(transactionHistory);
        } else {
            throw new RuntimeException("Bid amount must be higher than the current highest bid");
        }
    }

    @Override
    public boolean validateUserBalance(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        BigDecimal totalSpent = transactionHistoryRepository
                .findByUserIdAndTransactionStatus(userId, "COMPLETED")
                .stream()
                .map(TransactionHistory::getBidAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return user.getBalance().add(totalSpent).compareTo(user.getInitialBalance()) == 0;
    }

    private void rollbackPreviousTransactions(Long auctionId) {
        List<TransactionHistory> transactions = transactionHistoryRepository.findByAuctionIdAndTransactionStatus(auctionId, "COMPLETED");
        for (TransactionHistory transaction : transactions) {
            updateUserBalance(transaction.getUser(), transaction.getBidAmount());
            transaction.setTransactionStatus("ROLLED_BACK");
            transactionHistoryRepository.save(transaction);
        }
    }

    private void updateUserBalance(User user, BigDecimal amount) {
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
    }
}
