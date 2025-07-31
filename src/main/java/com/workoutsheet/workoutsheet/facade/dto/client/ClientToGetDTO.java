package com.workoutsheet.workoutsheet.facade.dto.client;

import com.workoutsheet.workoutsheet.facade.dto.user.UserToGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientToGetDTO {

    private String firstName;

    private String lastName;

    private BigDecimal weight;

    private BigDecimal height;

    private UserToGetDTO user;
}
