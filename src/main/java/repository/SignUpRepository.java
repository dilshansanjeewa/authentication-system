package repository;

import model.entity.User;

public interface SignUpRepository {
    boolean save(User user);
}
