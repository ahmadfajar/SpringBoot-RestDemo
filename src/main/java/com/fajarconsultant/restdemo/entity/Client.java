package com.fajarconsultant.restdemo.entity;

import com.fajarconsultant.restdemo.domain.AccountType;
import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * JPA persistence entity class used for master data Client.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 27/11/2022, modified: 27/11/2022 19:21
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "client")
public class Client {
    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "client")
    @Where(clause = "account_type = 'DEBIT'")
    private List<Account> debitAccounts = new ArrayList<>();

    public List<Account> getDebitAccounts() {
        return debitAccounts;
    }

    public void setDebitAccounts(List<Account> debitAccounts) {
        this.debitAccounts = debitAccounts;
    }

    @OneToMany(mappedBy = "client")
    @Where(clause = "account_type = 'CREDIT'")
    private List<Account> creditAccounts = new ArrayList<>();

    public List<Account> getCreditAccounts() {
        return creditAccounts;
    }

    public void setCreditAccounts(List<Account> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public void addAccount(Account account) {
        if (account.getType() == AccountType.CREDIT) {
            creditAccounts.add(account);
        } else {
            debitAccounts.add(account);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return 32 + Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}