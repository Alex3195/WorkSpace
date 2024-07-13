package uz.alex.transactionhostory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_sequence")
    @SequenceGenerator(name = "auction_sequence", sequenceName = "auction_seq")
    private Long auctionId;

    private BigDecimal currentHighestBid;

    @ManyToOne
    @JoinColumn(name = "current_highest_bidder")
    private User currentHighestBidder;

}
