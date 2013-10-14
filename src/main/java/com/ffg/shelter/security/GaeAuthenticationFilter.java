package com.ffg.shelter.security;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GaeAuthenticationFilter extends GenericFilterBean {
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> ads = new WebAuthenticationDetailsSource();
    private AuthenticationManager authenticationManager;
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        User googleUser = null;
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if (httpReq.getRequestURL().toString().contains("localhost") || httpReq.getRequestURL().toString().contains("192.168.1")) {
            googleUser = new User("5551212@me.com", "localhost");
            buildSecurityContext((HttpServletResponse) response, googleUser, httpReq);
            chain.doFilter(request, response);

            return;
        }
        UserService userService = UserServiceFactory.getUserService();
        googleUser = userService.getCurrentUser();
        if (googleUser != null) {
            buildSecurityContext((HttpServletResponse) response, googleUser, httpReq);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);

        }

        chain.doFilter(request, response);
    }

    private void buildSecurityContext(HttpServletResponse response, User googleUser, HttpServletRequest httpReq) throws IOException, ServletException {
        // User has returned after authenticating through GAE. Need to
        // authenticate to Spring Security.

        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
                googleUser, null);
        token.setDetails(ads.buildDetails(httpReq));
        try {
            Authentication authentication = authenticationManager
                    .authenticate(token);

            // Setup the security context
            SecurityContextHolder.getContext().setAuthentication(
                    authentication);
        } catch (AuthenticationException e) {
            // Authentication information was rejected by the
            // authentication manager
            failureHandler.onAuthenticationFailure(
                    httpReq,
                    (HttpServletResponse) response, e);

        }

    }

    public void setAuthenticationManager(
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }
}
