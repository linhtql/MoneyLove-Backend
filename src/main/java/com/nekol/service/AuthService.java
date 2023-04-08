package com.nekol.service;

import com.nekol.payload.request.LoginRequest;
import com.nekol.payload.request.RegisterRequest;
import com.nekol.payload.response.JwtResponse;
import com.nekol.payload.response.MessageResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest request);
    MessageResponse registerUser(RegisterRequest request);
    MessageResponse logoutUser();

}
