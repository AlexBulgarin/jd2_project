package by.sep.security;


import by.sep.dao.ClientLoginDao;
import by.sep.pojo.ClientLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unused"})
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    ClientLoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ClientLogin clientLogin = loginDao.findByLogin(username);
            if (clientLogin == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            return new User(
                    clientLogin.getLogin(),
                    clientLogin.getPassword(),
                    true, true, true, true,
                    List.of(new SimpleGrantedAuthority(clientLogin.getRole()))
            );

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + username, e);
        }
    }

    public String getIdByUsername(String username) {
        return loginDao.findByLogin(username).getId();
    }
}
