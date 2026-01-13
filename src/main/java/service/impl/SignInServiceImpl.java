package service.impl;

import model.dto.UserDTO;
import model.entity.User;
import org.modelmapper.ModelMapper;
import repository.SignInRepository;
import repository.impl.SignInRepositoryImpl;
import service.SignInService;
import util.PasswordUtil;

public class SignInServiceImpl implements SignInService {

    SignInRepository signInRepository = new SignInRepositoryImpl();

    ModelMapper mapper = new ModelMapper();

    @Override
    public UserDTO isValidateUser(String email, String password) {
        User user = searchUser(email);
        if (user == null) return null;

        return mapper.map(user, UserDTO.class);
    }

    @Override
    public boolean isCorrectPassword(String plainPassword, String hashedPassword) {
        System.out.println(plainPassword+" , "+hashedPassword);
        return PasswordUtil.verifyPassword(plainPassword, hashedPassword);
    }

    private User searchUser(String email){
        return signInRepository.searchUser(email);
    }
}
