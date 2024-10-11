package com.example.demo.endpoints.sbUser;

import com.example.demo.exception.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        // with encode pass!
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
}
