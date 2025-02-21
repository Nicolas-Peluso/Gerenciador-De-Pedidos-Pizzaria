package com.nicolas.util.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.nicolas.Constantes.prop;

public class jwtAutCom implements prop{

    public static boolean Auteticar(String jwt, String nomePizzaria){
        try {
            Algorithm algo = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algo)
            .withIssuer("Pizza")
            .withClaim("pizzaria", nomePizzaria)
            .build();

            verifier.verify(jwt);
            return true;
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return false;
        } catch(SignatureVerificationException d){
            d.printStackTrace();
            return false;
        } catch(JWTVerificationException d){
            d.printStackTrace();
            return false;
        }
    }

    public static String gerar(String nomePizzaria){
        try{
            Algorithm algo = Algorithm.HMAC256(key);
            String token = JWT.create()
            .withIssuer("Pizza")
            .withClaim("pizzaria", nomePizzaria)
            .sign(algo);
            return token;
        }catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        return "";
    }
}
