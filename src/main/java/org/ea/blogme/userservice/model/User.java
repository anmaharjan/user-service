package org.ea.blogme.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(length = 100)),
            @AttributeOverride(name = "middleName", column = @Column(length = 100)),
            @AttributeOverride(name = "lastName", column = @Column(length = 100))
    })
    private Names names;

    @NotBlank
    @Column(length = 15)
    private String mobile;
    @NotBlank
    @Column(length = 50)
    @Email
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    @Column(name = "registered_at")
    private Date registeredAt;

    @Lob
    private String intro;
    @Lob
    private String profile;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User(Names names, String mobile, String email, String password, String intro, String profile){
        this.names = names;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.intro = intro;
        this.profile = profile;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }
}
