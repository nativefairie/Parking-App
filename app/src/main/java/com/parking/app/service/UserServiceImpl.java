package com.parking.app.service;




import com.parking.app.domain.User;
import com.parking.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;


@Service()
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public String hashThis(String string){
        int hash = 7;
        char[] ch = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            hash = hash*31 + ch[i];
        }
        return String.valueOf(hash);
    }


    public Cookie logIn(User user){
        if (verifyUser(user)){
            String cookieVal =passHash(user.getUserEmail());
            Cookie userCookie = new Cookie("customCookie",cookieVal);
            user.setCookie(cookieVal);
            saveUserToDb(user);
            return userCookie;
        }
        return null;
    }


    public String passHash(String pass) {
//        int hash = 7;
//        for (int i = 0; i < pass.length(); i++) {
//            hash = hash*31 + pass.charAt(i);
//        }

        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);
        return sha256hex;
    }


    @Override
    public void saveUserToDb (User user){
        String userEmail = user.getUserEmail();

            user.setUserPassword(passHash(user.getUserPassword()));

            userRepository.save(user);


    }

    @Override
    public User findByUserEmail(String userEmail){
        return userRepository.findUserByUserEmail(userEmail);
    }

    @Override
    public boolean verifyUser (User user){
        String userEmail=user.getUserEmail();
        String userPassword = passHash(user.getUserPassword());

        User userDB = userRepository.findUserByUserEmail(userEmail);
        if(userDB==null)
            return false; //emailul nu este in DB
        else if (userDB.getUserPassword().equals(userPassword))    //emailul este in baza de date si parolele sunt identice
            return true;
        return false;

    }

    @Override
    public User findUserByCookie (String cookie){
        return userRepository.findUserByCookie(cookie);
    }
}
