package autoTrade.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.UUID;

public class Upbit {

    String jwtToken = "";

    public void getJWTToken() {
        String accessKey = UpbitConfig.API_KEY;
        String secretKey = UpbitConfig.SECRET_KEY;

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;
        this.jwtToken = jwtToken;
    }
}
