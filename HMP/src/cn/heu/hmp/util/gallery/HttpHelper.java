package cn.heu.hmp.util.gallery;
/**
 * User: Yingtao.Q
 * Date: 12-3-1
 * Time: …œŒÁ8:38
 */
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpHelper {
    public static final String BASE_URL="http://125.223.113.81:8080/HMPS/";
    public static HttpGet getHttpGet(String url){
        HttpGet request = new HttpGet(url);
        return request;
    }
    public static HttpPost getHttpPost(String url){
        HttpPost request = new HttpPost(url);
        return request;
    }
    public static HttpResponse getHttpResponse(HttpGet request) throws IOException, IOException{
        HttpResponse response = new DefaultHttpClient().execute(request);
        return response;
    }
    public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException{
        HttpResponse response = new DefaultHttpClient().execute(request);
        return response;
    }

    public static String queryStringForPost(String url){
        HttpPost request = HttpHelper.getHttpPost(url);
        request.addHeader("charset", HTTP.UTF_8);
        String result = null;
        try {
            HttpResponse response = HttpHelper.getHttpResponse(request);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£1";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£2";
            return result;
        }
        return null;
    }

    public static String queryStringForPost(String url, final Map<String, String> params){
        HttpPost httpPost = HttpHelper.getHttpPost(url);
        String result = null;
        List<NameValuePair> nvps = null;
        if ((params != null) && (params.size() > 0)){
            nvps = new ArrayList<NameValuePair>();
            for (String key : params.keySet()){
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        if (nvps != null){
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            HttpResponse response = HttpHelper.getHttpResponse(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£1";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£2";
            return result;
        }
        return null;
    }
    public static String queryStringForPost(HttpPost request){
        String result = null;
        try {
            HttpResponse response = HttpHelper.getHttpResponse(request);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£1";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£2";
            return result;
        }
        return null;
    }
    public static  String queryStringForGet(String url){
        HttpGet request = HttpHelper.getHttpGet(url);
        String result = null;
        try {
            HttpResponse response = HttpHelper.getHttpResponse(request);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£1";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "Õ¯¬Á“Ï≥£2";
            return result;
        }
        return null;
    }
}

