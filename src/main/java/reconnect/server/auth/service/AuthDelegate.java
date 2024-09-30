package reconnect.server.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reconnect.server.auth.repository.UserInfoRepository;
import reconnect.server.global.model.entity.UserInfo;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthDelegate implements UserDetailsService {
    private final UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findUserByEmail(userId);
        userInfo.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        return userInfo;
    }
}

