package service;

import model.dto.UserDTO;

public interface SignInService {
    UserDTO isValidateUser(String email, String password);

    boolean isCorrectPassword(String text, String password);
}
