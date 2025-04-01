package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.ClientHistory;
import com.workoutsheet.workoutsheet.repository.record.ClientHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientHistoryService {

    private final ClientHistoryRepository repository;

    public void createClientHistory(
            Client client,
            BigDecimal weight,
            BigDecimal height
    ) {
        if (weight == null && height == null) {
            return;
        }

        ClientHistory clientHistory = ClientHistory
                .builder()
                .weight(weight)
                .height(height)
                .date(LocalDate.now())
                .client(client)
                .build();

        repository.save(clientHistory);
    }
}
