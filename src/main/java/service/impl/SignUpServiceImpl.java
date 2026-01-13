package service.impl;

import model.dto.UserDTO;
import model.entity.User;
import org.modelmapper.ModelMapper;
import repository.SignUpRepository;
import repository.impl.SignUpRepositoryImpl;
import service.SignUpService;
import util.PasswordUtil;

public class SignUpServiceImpl implements SignUpService {
    SignUpRepository signUpRepository = new SignUpRepositoryImpl();
    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean save(UserDTO userDTO) {
        userDTO.setPassword(PasswordUtil.hashPassword(userDTO.getPassword()));
        return signUpRepository.save(mapper.map(userDTO, User.class));
    }
}
