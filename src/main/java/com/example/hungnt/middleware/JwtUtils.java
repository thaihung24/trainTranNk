package com.example.hungnt.middleware;

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
                Claims claims = Jwts.parser().setSigningKey("JoinQuit123@").parseClaimsJws(token).getBody();
                return claims.get("customerId",Integer.class);
            }
            // Xác thực thất bại
            return -1;
        }
        catch(Exception e) {
            //-1 là lỗi
            return -1;
        }

    }

}
