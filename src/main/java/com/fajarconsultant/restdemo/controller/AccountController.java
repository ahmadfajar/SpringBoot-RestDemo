package com.fajarconsultant.restdemo.controller;

import com.fajarconsultant.restdemo.domain.data.CollectionModel;
import com.fajarconsultant.restdemo.domain.data.EntityModel;
import com.fajarconsultant.restdemo.domain.data.IAccountInfo;
import com.fajarconsultant.restdemo.domain.data.IClientAccount;
import com.fajarconsultant.restdemo.entity.Account;
import com.fajarconsultant.restdemo.entity.Client;
import com.fajarconsultant.restdemo.repository.AccountRepository;
import com.fajarconsultant.restdemo.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = {"/accounts"},
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class AccountController {
    private final AccountRepository repository;
    private final ClientRepository clientRepository;
    private final ProjectionFactory projectionFactory;

    public AccountController(AccountRepository repository,
                             ClientRepository clientRepository,
                             ProjectionFactory projectionFactory) {
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.projectionFactory = projectionFactory;
    }

    @GetMapping
    public CollectionModel<IAccountInfo> displayAccounts() {
        List<IAccountInfo> accounts = repository.findByOrderByCreatedAtDesc();

        return new CollectionModel<>(accounts, accounts.size());
    }

    @PostMapping
    public EntityModel<IAccountInfo> addAccount(@RequestBody Account account) {
        Client clientInfo = account.getClient();
        Optional<Client> optClient = clientRepository.findById(clientInfo.getId());

        if (optClient.isEmpty()) {
            throw new EntityNotFoundException("Client: \"" + clientInfo.getName() + "\" doesn't exists.");
        }

        Client client = optClient.get();
        account.setClient(client);
        account.setCreatedAt(LocalDateTime.now());
        client.addAccount(account);
        repository.saveAndFlush(account);

        return new EntityModel<>(projectionFactory.createProjection(IAccountInfo.class, account));
    }

    @GetMapping("/{uid}")
    public EntityModel<IClientAccount> displayClientAccounts(@PathVariable UUID uid) {
        Optional<Client> optClient = clientRepository.findById(uid);

        if (optClient.isEmpty()) {
            throw new EntityNotFoundException("ClientID: \"" + uid + "\" doesn't exists.");
        }

        return new EntityModel<>(projectionFactory.createProjection(IClientAccount.class, optClient.get()));
    }
}
