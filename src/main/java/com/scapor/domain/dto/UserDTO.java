package com.scapor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombox constructors and getter setters
@Builder//Conversiones entre DTO y En
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO  {
    @Schema(name = "id")
    private Integer id;

    @Schema(name = "name", example = "Harold")
    private String name;

    @Schema(name = "surname", example = "Horta")
    private String surname;

    @Schema(name = "process", example = "false")
    private Boolean process;
}
