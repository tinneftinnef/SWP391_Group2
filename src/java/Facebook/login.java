/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facebook;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author dell
 */
public class login {
    public static String getToken(String code) throws ClientProtocolException,IOException{
        String response=Request.Post(IConstant.FACEBOOK_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                        .add("client_id", IConstant.FACEBOOK_CLIENT_ID)
                        .add("client_secret", IConstant.FACEBOOK_CLIENT_SECRET)
                        .add("redirect_uri", IConstant.FACEBOOK_REDIRECT_URI)
                        .add("code", code)
                .build()
                ).execute().returnContent().asString();
        JsonObject jobj=new Gson().fromJson(response, JsonObject.class);
        String accessToken=jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
     public static AccountFb getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link =IConstant.FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
         System.out.println(response);
       AccountFb googlePojo = new Gson().fromJson(response, AccountFb.class);

        return googlePojo;
    }
}
