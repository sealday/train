package me.theegg.train.repository;

import me.theegg.train.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seal on 9/16/15.
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findOneByToken(String token);
}
