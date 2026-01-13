package model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
