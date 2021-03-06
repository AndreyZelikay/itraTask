package hello.function;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@Service
public class JsonString {
    public JsonString() {
    }

    public String Token(String token)throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("token",token);

        StringWriter out = new StringWriter();
            obj.writeJSONString(out);
        return obj.toString();
    }
    public String UserInfo(String user,String authorities) throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("role",authorities);
        obj.put("user",user);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        return obj.toString();
    }
    public String ReturnInfo(String info)throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("cause",info);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        return obj.toString();
    }
}
