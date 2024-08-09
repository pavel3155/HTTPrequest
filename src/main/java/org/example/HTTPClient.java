package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class HTTPClient {
    private String token="";
    HttpClient httpClient;
    URI uri=null;

    public HTTPClient() {
        httpClient=HttpClient.newHttpClient();
    }
    public void setURI(String url){
        this.uri= URI.create(url+this.token);
    }
    public void setToken (String token){
        this.token="?access-token="+token;
    }

    public HttpRequest Request(User user) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        String RequestBody =mapper.writeValueAsString(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(RequestBody))
                .build();
        return request;
    }
    public  void Response(HttpRequest request) throws Exception {
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.statusCode());
        int responseCode=response.statusCode();
        if(responseCode != 201){
            System.out.println(responseCode);
            System.out.println("Error!");
        }
    }
    public List getList(String nameFile) throws IOException {
        File file = new File(nameFile);
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        List<User> users = objectMapper.readValue(file, new TypeReference<>() {});

        return users;
    }

}
