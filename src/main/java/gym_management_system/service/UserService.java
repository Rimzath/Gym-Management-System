package gym_management_system.service;

import gym_management_system.dto.request.RegisterRequest;

public interface UserService {

    String register(RegisterRequest request);
}