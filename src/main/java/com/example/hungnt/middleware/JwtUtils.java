package com.example.hungnt.middleware;

import com.example.hungnt.Services.CustomerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public class JwtUtils {
    public Integer getTokenFromHeader(HttpServletRequest request){
        try {
            //  Block of code to try
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Trả về token JWT sau khi đã cắt bỏ phần "Bearer "
                String token= authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey("Hungw").parseClaimsJws(token).getBody();
                Integer customerId = claims.get("customerId", Integer.class);
                return customerId;
            }
            return -1;
        }
        catch(Exception e) {
            //  Block of code to handle errors
            return -1;
        }

    }

}
