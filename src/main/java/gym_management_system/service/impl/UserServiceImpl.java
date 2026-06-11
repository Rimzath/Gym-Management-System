package gym_management_system.service.impl;

import gym_management_system.dto.request.LoginRequest;
import gym_management_system.dto.request.RegisterRequest;
import gym_management_system.dto.response.LoginResponse;
import gym_management_system.entity.User;
import gym_management_system.repository.UserRepository;
import gym_management_system.security.jwt.JwtService;
import gym_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }
    @Override
    public LoginResponse login(
            LoginRequest request
    ) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User Not Found"
                        )
                );

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!matches) {
            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}