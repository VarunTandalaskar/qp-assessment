package com.grocery.booking.service;

import com.grocery.booking.constants.AppConstants;
import com.grocery.booking.domian.User;
import com.grocery.booking.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public void addUser(User user) {
        if (user!=null) {
            if (user.getUsername().endsWith(AppConstants.DOMAIN)) {
                user.setRole(AppConstants.ROLE_ADMIN);
            } else {
                user.setRole(AppConstants.ROLE_USER);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else throw new ServiceException(AppConstants.USER_NULL_EXCEPTION);
    }
}
