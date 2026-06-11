package gym_management_system.controller;

import gym_management_system.dto.request.LoginRequest;
import gym_management_system.dto.request.RegisterRequest;
import gym_management_system.dto.response.LoginResponse;
import gym_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {

        return userService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        return userService.login(request);
    }
}