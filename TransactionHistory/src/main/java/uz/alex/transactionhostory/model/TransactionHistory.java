package uz.alex.transactionhostory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_history_sequence")
    @SequenceGenerator(name = "transaction_history_sequence", sequenceName = "transaction_history_seq")
    private Long transactionId;

    private Long auctionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal bidAmount;
    private String transactionStatus;
    private LocalDateTime transactionTime;

}
