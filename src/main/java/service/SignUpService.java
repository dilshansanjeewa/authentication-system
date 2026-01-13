package service;

import model.dto.UserDTO;

public interface SignUpService {
    boolean save(UserDTO userDTO);

}
