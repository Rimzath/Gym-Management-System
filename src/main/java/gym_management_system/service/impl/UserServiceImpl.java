package gym_management_system.service.impl;

import gym_management_system.dto.request.RegisterRequest;
import gym_management_system.entity.User;
import gym_management_system.repository.UserRepository;
import gym_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }
}