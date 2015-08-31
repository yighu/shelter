package com.ffg.shelter.security;

import com.google.appengine.api.users.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import java.util.logging.Logger;

public class GaeAuthenticationProvider implements AuthenticationProvider {

	private static final Logger log=Logger.getLogger(GaeAuthenticationProvider.class.getName());
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		User googleUser = (User) authentication.getPrincipal();
		log.info("google user not found in authentication...");
		if (googleUser == null)
			throw new PreAuthenticatedCredentialsNotFoundException(
					"google user not found");
		return new PreAuthenticatedAuthenticationToken(googleUser,
				authentication.getDetails());
	}

	public final boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class
				.isAssignableFrom(authentication);
	}
}
