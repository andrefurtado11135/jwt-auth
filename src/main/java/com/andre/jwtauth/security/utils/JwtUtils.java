package com.andre.jwtauth.security.utils;

import com.andre.jwtauth.security.model.Authority;
import com.andre.jwtauth.security.model.AuthorityDto;
import com.andre.jwtauth.security.model.JwtObject;
import io.jsonwebtoken.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class JwtUtils {

    public static final String KEY = "CYi]8OimkfeRv`3^<;w'Lwka(6K.fsJ+\">(r0vMnI=@F:C:~U9o/<C%r\"mV!=0J";
    public static final String PREFIX = "Bearer ";
    public static final long TOKEN_EXPIRATION =  1 * 60 * 60 * 250;
    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static String create(String subject, List<Authority> authorityList, Date issuedAt, Date expriration) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("auth", authorityList.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expriration)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
        return PREFIX + token;
    }

    public static JwtObject object(String token) {
        JwtObject jwtObject = null;
        try {
            token = token.replace(PREFIX, "");
            Claims claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody();

            jwtObject = new JwtObject();
            List<LinkedHashMap<String, String>> claimsMapList = (List<LinkedHashMap<String, String>>) claims.get("auth");
            List<AuthorityDto> authorityDtoList = new ArrayList<>();
            for (LinkedHashMap<String, String> map : claimsMapList){
                AuthorityDto authorityDto = new AuthorityDto();
                authorityDto.setName(map.get("authority"));
                authorityDtoList.add(authorityDto);
            }
            jwtObject.setAuthorities(authorityDtoList);
            jwtObject.setSubject(claims.getSubject());
            jwtObject.setExpiration(claims.getExpiration());
            jwtObject.setIssuedAt(claims.getIssuedAt());


        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return jwtObject;
    }
}
