package by.birukov.service;


import by.birukov.entity.User;
import by.birukov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    public static final String UNBLOCKED_STATUS = "UNBLOCKED";
    public static final String BLOCKED_STATUS = "BLOCKED";

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user){

        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB!=null){
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegDate(new Date());
        user.setStatus(UNBLOCKED_STATUS);
        userRepository.save(user);
        return true;
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

    public void block(){

    }

    public void unblock(){

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
