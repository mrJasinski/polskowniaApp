package polskowniaApp.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import polskowniaApp.security.JwtService;
import polskowniaApp.user.UserService;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserService userService;

    JwtAuthFilter(final JwtService jwtService, final UserService userService)
    {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request
            , final HttpServletResponse response
            , final FilterChain filterChain) throws ServletException, IOException
    {
        var token = "";
        var email = "";

        var authHeader = request.getHeader("Authorization");

        if (authHeader != null)
        {
            token = this.jwtService.getToken(request);
            email = this.jwtService.getUserEmail(request);
        }

        if (!email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null)
        {

            var user = this.userService.getUserByEmailAsDto(email);

            if (this.jwtService.validateToken(token, user))
            {
//                TODO authorities do ogarnięcia
                var authToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
