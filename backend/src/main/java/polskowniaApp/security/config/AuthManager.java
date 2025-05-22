package polskowniaApp.security.config;

import org.springframework.stereotype.Component;
import polskowniaApp.user.UserRole;
import polskowniaApp.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Component
class AuthManager implements AuthenticationManager
{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    AuthManager(final UserService userService, final PasswordEncoder passwordEncoder)
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException
    {
        var username = authentication.getName();
        var pwd = authentication.getCredentials().toString();

        var user = this.userService.getUserByEmailAsDto(username);

        if (!this.passwordEncoder.matches(pwd, user.getPassword()))
            throw new BadCredentialsException("Invalid password!");

        return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserRole role)
    {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));

        return grantedAuthorities;
    }
}