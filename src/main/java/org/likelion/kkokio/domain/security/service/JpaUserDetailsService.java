package org.likelion.kkokio.domain.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.adminAccount.repository.AdminAccountRepository;
import org.likelion.kkokio.domain.security.dto.UserDto;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final static UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("User not found");
    private final AdminAccountRepository adminAccountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AdminAccount adminAccount = adminAccountRepository.findByAccountLoginId(username)
                    .orElseThrow(() ->  usernameNotFoundException);
            return UserDto.ofAdmin(adminAccount);
        } catch (UsernameNotFoundException e) {
            log.debug("User not found(there is no user with the given username)");
            throw e;
        } catch (DataAccessException e) {
            log.error("Failed to load user by username", e);
            throw usernameNotFoundException;
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw usernameNotFoundException;
        }
    }
}
