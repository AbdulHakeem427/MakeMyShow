package com.example.bookmyshowcb.models;

import com.example.bookmyshowcb.models.enums.PaymentMode;
import com.example.bookmyshowcb.models.enums.PaymentProvider;
import com.example.bookmyshowcb.models.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private int amount;
    private String referenceId;

    @Enumerated
    private PaymentMode paymentMode;

    @Enumerated
    private PaymentProvider paymentProvider;

    @Enumerated
    private PaymentStatus paymentStatus;
}
