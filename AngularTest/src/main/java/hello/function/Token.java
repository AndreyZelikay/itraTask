package hello.function;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;

import static hello.function.SequrityConstants.*;

@Service
public class Token {
    public String readToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        String userName="";
        String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(user);
            userName = jsonObject.getString("user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userName;
    }
}
