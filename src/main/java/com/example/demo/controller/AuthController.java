// package com.example.demo.controller;
// import com.example.demo.config.JwtUtil;
// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.Employee;
// import com.example.demo.service.EmployeeService;
// import jakarta.validation.Valid;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {
//     private final EmployeeService employeeService;
//     private final JwtUtil jwtUtil;
//     public AuthController(EmployeeService employeeService, JwtUtil jwtUtil){ this.employeeService=employeeService; this.jwtUtil=jwtUtil; }
//     @PostMapping("/login")
// public ResponseEntity<?> login(@Valid @RequestBody AuthRequest req){
//     Employee emp = employeeService.findByEmail(req.getEmail());
//     if (emp == null) {
//         return ResponseEntity.status(401).body("Invalid credentials");
//     }

//     String token = jwtUtil.generateToken(
//             emp.getId(),
//             emp.getEmail(),
//             emp.getRole()
//     );

//     return ResponseEntity.ok(new AuthResponse(token));
// }

// }
package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        return new LoginResponse(token, user.getEmail(), user.getRole());
    }
}
