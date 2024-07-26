package com.ducnm.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VNPay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String txnRef;
    private String transactionDate;
    private boolean isQueried = false;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String paymentUrl;
}
