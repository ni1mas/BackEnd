package NiUnaMas.Seguridad;

import NiUnaMas.Models.Authorization;
import NiUnaMas.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 09/04/2017.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        NiUnaMas.Models.User user = userDao.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found.");
        }else {
            return this.userBuilder(user.getEmail(),user.getPassword(),user.getRoles());
        }
    }

    private User userBuilder(String username, String password, List<Authorization> list){
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> autorities = new ArrayList<>();
        for(Authorization role : list){
            autorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return new User(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, autorities);
    }

}
