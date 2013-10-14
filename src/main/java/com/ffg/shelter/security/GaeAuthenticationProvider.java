package com.ffg.shelter.security;

import com.google.appengine.api.users.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

public class GaeAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		User googleUser = (User) authentication.getPrincipal();
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
