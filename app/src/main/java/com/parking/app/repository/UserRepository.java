package com.parking.app.repository;


import com.parking.app.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUserEmail(String userEmail);
    User findUserByUserPassword(String password);
    User findUserByCookie(String cookie);
}
