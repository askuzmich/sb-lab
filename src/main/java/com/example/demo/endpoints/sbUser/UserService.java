package com.example.demo.endpoints.sbUser;

import com.example.demo.exception.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
    }

    public SbUser findById(Integer id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User", id));
    }

    public List<SbUser> getAll() {
        return this.userRepository.findAll();
    }

    public SbUser add(SbUser user) {
        // with encode pass! TODO
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    // not for change pass
    public SbUser update(Integer id, SbUser candidate) {
        return userRepository
                .findById(id)
                .map((user) -> {
                    user.setName(candidate.getName());
                    user.setEnabled(candidate.isEnabled());
                    user.setRoles(candidate.getRoles());

                    return userRepository.save(user);
                }).orElseThrow(() -> new CustomNotFoundException("User", id));
    }

    public void delete(Integer id) {
        this.userRepository.findById(id).orElseThrow(() -> {
            return new CustomNotFoundException("User", id);
        });

        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByName(username)
            // if found user with this name -> wrap
            .map((sbUser) -> {
                return new SbUserPrincipal(sbUser);
            })
            .orElseThrow(() -> {
                return new UsernameNotFoundException(
                    "username" + username + "is not found"
                );
            });
    }
}
