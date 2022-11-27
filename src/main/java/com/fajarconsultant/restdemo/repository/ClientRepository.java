package com.fajarconsultant.restdemo.repository;

import com.fajarconsultant.restdemo.domain.data.IClientInfo;
import com.fajarconsultant.restdemo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<IClientInfo> findAllByOrderByNameAsc();

    boolean existsByName(String name);

    Client findFirstByName(String name);
}