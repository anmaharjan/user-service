package org.ea.blogme.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdate {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    @Email
    private String email;

    private String intro="";
    private String profile="";

    private List<Long> roles = new ArrayList<>();
}
