package com.nicolas.util.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.nicolas.Constantes.prop;

public class jwtAutCom implements prop{

    public static boolean Auteticar(String jwt){
        try {
            Algorithm algo = Algorithm.HMAC256(key);
            com.auth0.jwt.interfaces.JWTVerifier verifier = JWT.require(algo)
            .withIssuer("Pizza")
            .build();
            
            verifier.verify(jwt);
            return true;
        } catch (JWTDecodeException e) {
            return false;
        } catch(SignatureVerificationException d){
            return false;
        }
    }

    public static String gerar(){
        try{
            Algorithm algo = Algorithm.HMAC256(key);
            String token = JWT.create()
            .withIssuer("Pizza")
            .sign(algo);

            System.out.println(token);
            return token;
        }catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        return "";
    }
}
