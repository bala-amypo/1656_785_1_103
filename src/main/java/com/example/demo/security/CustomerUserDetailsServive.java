package com.example.demo.security;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    public CustomUserDetailsService(EmployeeRepository employeeRepository){ this.employeeRepository=employeeRepository; }
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = employeeRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Employee not found"));
        return new CustomUserDetails(emp);
    }
}
