package org.ea.blogme.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Names {
    @NotBlank
    private String firstName;
    @NotBlank
    private String middleName;
    @NotBlank
    private String lastName;
}
