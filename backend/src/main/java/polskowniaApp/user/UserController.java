package polskowniaApp.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import polskowniaApp.security.JwtService;
import polskowniaApp.user.dto.UserDTO;
import polskowniaApp.user.dto.UserLoggedDTO;

@RestController
class UserController
{
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    UserController(final JwtService jwtService, final AuthenticationManager authManager)
    {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticateUser(@RequestBody UserDTO user)
    {
        var userDetails = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (!userDetails.isAuthenticated())
            throw new UsernameNotFoundException("Invalid user credentials!");

        var logged = new UserLoggedDTO(user.getEmail(), this.jwtService.generateToken(user.getEmail()));

        return ResponseEntity.ok(logged);
    }
}
