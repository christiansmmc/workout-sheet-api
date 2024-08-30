package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.User;
import com.workoutsheet.workoutsheet.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    private final ClientHistoryService clientHistoryService;
    private final UserService userService;

    public Client createClient(Client client) {
        User user = userService.createUser(client.getUser().getEmail(), client.getUser().getPassword());

        client.setUser(user);
        Client clientCreated = repository.save(client);

        clientHistoryService.createClientHistory(client, client.getWeight(), client.getHeight());

        return clientCreated;
    }
}
