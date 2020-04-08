package com.scapor.domain.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "User")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserEntity {
    /** Campo para almacenar el objeto o valor id con un valor autoincrementable*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /* Campo para almacenar el objeto o valor nombre */
    @NotBlank(message = "El campo nombre no puede ser vacio.")
    @Schema(name = "name", example = "Harold")
    private String name;
    /* Campo para almacenar el objeto o valor apellido */
    @NotBlank(message = "El campo apellido no puede ser vacio.")
    @Schema(name = "surname", example = "Horta")
    private String surname;
    /* Campo para almacenar el objeto o valor procesado */
    @Schema(name = "process", example = "false")
    private Boolean process;

    @Column(name = "Create_At")
    private Calendar createAt;
}
