package com.otaku.otakube.common.security.jwt;


import com.otaku.otakube.common.security.helper.AuthInfo;
import lombok.Builder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final AuthInfo authInfo;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    @Builder
    public JwtAuthenticationToken(final Collection<? extends GrantedAuthority> authorities, final AuthInfo authInfo) {
        super(authorities);
        this.authInfo = authInfo;
        setAuthenticated(true);
    }

    public static JwtAuthenticationToken of(final AuthInfo authInfo) {
        return JwtAuthenticationToken.builder()
                .authInfo(authInfo)
                .authorities(Collections.singleton(authInfo.getRole()))
                .build();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.authInfo;
    }

}
