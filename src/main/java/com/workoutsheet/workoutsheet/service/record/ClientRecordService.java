package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.ClientRecord;
import com.workoutsheet.workoutsheet.repository.record.ClientRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientRecordService {

    private final ClientRecordRepository repository;

    public void createClientRecord(
            Client client,
            BigDecimal weight,
            BigDecimal height
    ) {
        if (weight == null && height == null) {
            return;
        }

        ClientRecord clientRecord = ClientRecord
                .builder()
                .weight(weight)
                .height(height)
                .date(LocalDate.now())
                .client(client)
                .build();

        repository.save(clientRecord);
    }
}
