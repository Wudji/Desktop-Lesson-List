package com.wudji.lessonlist.network;

import com.alibaba.fastjson2.JSONObject;
import com.wudji.lessonlist.utils.ExceptionManager;
import com.wudji.lessonlist.utils.FileControl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PoemNetworkRequest {
    public static String getPoemData() {
        String[] requestHeader = {"X-User-Token",getUserDataToken()};
        return sendGetRequestWithHeader("https://v2.jinrishici.com/sentence",requestHeader);
    }

    public static String getDeviceInfo(){
        String[] requestHeader = {"X-User-Token",getUserDataToken()};
        return sendGetRequestWithHeader("https://v2.jinrishici.com/info",requestHeader);
    }

    private static String getUserDataToken() {
        String token;
        token = FileControl.getRoughStr("config/token.txt");
        if (token == ""){
            JSONObject tokenRespond = JSONObject.parseObject(sendGetRequestWithHeader("https://v2.jinrishici.com/token"));
            token = tokenRespond.getString("data");
            FileControl.writeString("config/token.txt",token);
        }
        // System.out.println(token);
        return token;
    }

    private static String sendGetRequestWithHeader(String urlStr, String[]... headers) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(headers != null){
                for (String[] header:
                        headers) {
                    connection.setRequestProperty(header[0], header[1]);
                }
            }


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                response.append("GET request failed. Response Code: ").append(responseCode);
                ExceptionManager.showErrorDialog(new Throwable(response.toString()));
            }
        } catch (Exception e) {
            response.append("An error occurred: ").append(e.getMessage());
            ExceptionManager.showErrorDialog(new Throwable(response.toString()));
        }

        return response.toString();
    }
}
