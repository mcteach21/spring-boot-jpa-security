package mc.apps.spring.jpa;

import mc.apps.spring.controllers.FrontController;
import mc.apps.spring.security.MyUserDetails;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    final UserRepository userRepository;
    // UserRepository inject.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        // logger.log(Level.INFO, "UserService : repository = "+this.userRepository);
    }

    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if(user!=null) {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
    }

    public void add(User user) {
        userRepository.save(user);
    }
}
