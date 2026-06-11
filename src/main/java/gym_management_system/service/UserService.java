package gym_management_system.service;

import gym_management_system.dto.request.LoginRequest;
import gym_management_system.dto.request.RegisterRequest;
import gym_management_system.dto.response.LoginResponse;

public interface UserService {

    String register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}