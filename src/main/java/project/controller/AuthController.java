package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.entity.User;
import project.repository.UserRepository;
import project.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/hospital/auth", "/"})
public class AuthController
{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AuthRequest request)
    {
        try
        {
            String name = request.getUserName();
            User user = userRepository.findUserByUserName(name)
                    .orElseThrow(() -> new UsernameNotFoundException("Not found!"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            {
                throw new BadCredentialsException("Invalid password!");
            }


            String token = jwtTokenProvider.createToken(
                    name,
                    user.getRoles()
            );

            Map<Object, Object> model = new HashMap<>();
            model.put("userName", name);
            model.put("token", token);

            return ResponseEntity.ok(model);
        }
        catch (AuthenticationException e)
        {
            throw new BadCredentialsException("Invalid username!");
        }
    }
}
