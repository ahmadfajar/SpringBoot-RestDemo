package com.fajarconsultant.restdemo.repository;

import com.fajarconsultant.restdemo.domain.data.IAccountInfo;
import com.fajarconsultant.restdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<IAccountInfo> findByOrderByCreatedAtDesc();
}