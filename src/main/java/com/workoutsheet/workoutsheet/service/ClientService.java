package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.User;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.repository.ClientRepository;
import com.workoutsheet.workoutsheet.service.record.ClientHistoryService;
import com.workoutsheet.workoutsheet.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public Client getLoggedUser() {
        String loggedUserEmail = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new AppException(ErrorType.LOGGED_USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));

        return repository.findByUserEmail(loggedUserEmail)
                .orElseThrow(() -> new AppException(ErrorType.CLIENT_NOT_FOUND, HttpStatus.UNAUTHORIZED));
    }
}
