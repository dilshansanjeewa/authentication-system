package repository;

import model.entity.User;

public interface SignInRepository {
    User searchUser(String email);
}
