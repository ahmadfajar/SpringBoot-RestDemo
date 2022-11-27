package com.fajarconsultant.restdemo.domain.data;

import com.fajarconsultant.restdemo.domain.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * A Projection for the {@link com.fajarconsultant.restdemo.entity.Client} entity
 */
public interface IClientAccount {
    UUID getId();

    String getName();

    List<IAccount> getDebitAccounts();

    List<IAccount> getCreditAccounts();

    /**
     * A Projection for the {@link com.fajarconsultant.restdemo.entity.Account} entity
     */
    interface IAccount {
        UUID getUid();

        AccountType getType();

        Double getAmount();

        Double getRate();

        Boolean getActive();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime getCreatedAt();
    }
}