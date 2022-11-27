package com.fajarconsultant.restdemo.controller;

import com.fajarconsultant.restdemo.domain.data.CollectionModel;
import com.fajarconsultant.restdemo.domain.data.EntityModel;
import com.fajarconsultant.restdemo.domain.data.IClientInfo;
import com.fajarconsultant.restdemo.entity.Client;
import com.fajarconsultant.restdemo.repository.ClientRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = {"/clients"},
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class ClientController {
    private final ClientRepository repository;
    private final ProjectionFactory projectionFactory;

    public ClientController(ClientRepository repository, ProjectionFactory projectionFactory) {
        this.repository = repository;
        this.projectionFactory = projectionFactory;
    }

    @GetMapping
    public CollectionModel<IClientInfo> displayClients() {
        List<IClientInfo> clients = repository.findAllByOrderByNameAsc();

        return new CollectionModel<>(clients, clients.size());
    }

    @PostMapping("/create")
    public EntityModel<IClientInfo> createClient(@RequestParam("name") String name) {
        if (repository.existsByName(name)) {
            throw new EntityExistsException("Client: \"" + name + "\" already exists.");
        } else {
            Client client = new Client(name);
            repository.saveAndFlush(client);

            return new EntityModel<>(projectionFactory.createProjection(IClientInfo.class, client));
        }
    }

    @GetMapping("/{uid}")
    public EntityModel<IClientInfo> clientInfo(@PathVariable UUID uid) {
        Optional<Client> optClient = repository.findById(uid);

        if (optClient.isEmpty()) {
            throw new EntityNotFoundException("ClientID: \"" + uid + "\" doesn't exists.");
        }

        return new EntityModel<>(projectionFactory.createProjection(IClientInfo.class, optClient.get()));
    }
}
