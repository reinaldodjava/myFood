package com.myFood.order.application.util;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 *
 * @author reinaldo.andre
 */
public class Token {

    public static final String TYPE = "Bearer";
    private static final String KEY = "F1nCh|/|1";
    private static final int EXPIRATION_DAY = 1;

    private Long userId;
    private String login;

    public Token() {
    }

    public Token(Long userId, String login) {
        this.userId = userId;
        this.login = login;
    }

    public void setToken(Token token) {
        this.userId = token.getUserId();
        this.login = token.getLogin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    static public String createToken(Long userId,  String login) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("userId", userId);
        claims.put("login", login);

        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.DAY_OF_WEEK, EXPIRATION_DAY);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration.getTime())
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();

        return Token.TYPE + " " + token;
    }

    static public Token validateToken(String accessToken) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(accessToken)
                    .getBody();

            Token token = new Token(
                    Long.valueOf(claims.get("userId").toString()),
                    claims.get("login").toString()
            );

            return token;

        } catch (UnsupportedJwtException | ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            return null;
        }

    }

    public String createToken() {
        return createToken(this.userId, this.login);
    }
        
}
