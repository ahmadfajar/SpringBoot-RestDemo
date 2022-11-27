package com.fajarconsultant.restdemo.entity;

import com.fajarconsultant.restdemo.domain.AccountType;
import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;
import java.util.UUID;

/**
 * JPA persistence entity class used for master data Account.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 27/11/2022, modified: 27/11/2022 19:21
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "account")
public class Account extends CreatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uid", nullable = false)
    private UUID uid;

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID id) {
        this.uid = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 10)
    private AccountType type;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "rate", nullable = false)
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return uid != null && Objects.equals(uid, account.uid) && Objects.equals(type, account.type) &&
                Objects.equals(active, account.active) && Objects.equals(amount, account.amount) &&
                Objects.equals(rate, account.rate);
    }

    @Override
    public int hashCode() {
        return 32 + Objects.hash(uid, type, active, amount, rate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uid = " + uid + ", " +
                "type = " + type + ", " +
                "amount = " + amount + ", " +
                "rate = " + rate + ", " +
                "active = " + active + ")";
    }
}