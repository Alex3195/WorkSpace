package uz.alex.transactionhostory.controller;

import org.springframework.web.bind.annotation.*;
import uz.alex.transactionhostory.service.AuctionService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/{auctionId}/bid")
    public void placeBid(@PathVariable Long auctionId, @RequestParam Long userId, @RequestParam BigDecimal bidAmount) {
        auctionService.placeBid(auctionId, userId, bidAmount);
    }

    @GetMapping("/validateBalance/{userId}")
    public boolean validateBalance(@PathVariable Long userId) {
        return auctionService.validateUserBalance(userId);
    }
}

