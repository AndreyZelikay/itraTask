package hello.function;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

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
        obj.put("status",info);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        return obj.toString();
    }
    public String RetrunSignUpInfo(String info,String activationCode)throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("status",info);
        obj.put("code",activationCode);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        return obj.toString();
    }
    public String ReturnActivity(String activity) throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("activity",activity);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        return obj.toString();
    }
}
