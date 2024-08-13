package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class HTTPClient {
    private String token="";
    HttpClient httpClient;
    URI uri=null;

    /**
     * Конструктор, который создает объект HttpClient
     */
    public HTTPClient() {
        httpClient=HttpClient.newHttpClient();
    }

    /**
     * метод создает объект URI
     * @param url
     */
    public void setURI(String url){
        this.uri= URI.create(url+this.token);
    }
    public void setToken (String token){
        this.token="?access-token="+token;
    }

    /**
     * метод создает запрос HttpRequest
     * @param user
     * @return
     * @throws JsonProcessingException
     */
    public HttpRequest Request(User user) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        String RequestBody =mapper.writeValueAsString(user);
        System.out.println(user);
        System.out.println("request: "+RequestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(RequestBody))
                .build();
        return request;
    }

    /**
     * метод создает ответ HttpResponse
     * @param request
     * @throws Exception
     */
    public  void Response(HttpRequest request) throws Exception {
        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.statusCode());
        int responseCode=response.statusCode();
        if(responseCode != 201){
            System.out.println("Error!");
            if (responseCode==422) {
                System.out.println("Сервер не принимает пользователя....");
                System.out.println("Возможно пользователь уже существует или введены не корректные данные....");
            }
        }
    }

    /**
     * метод выполняет десериализацию json-файла, сосзадет список объектов
     * @param nameFile
     * @return
     * @throws IOException
     */
    public List getList(String nameFile) throws IOException {
        File file = new File(nameFile);
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        List<User> users = objectMapper.readValue(file, new TypeReference<>() {});

        return users;
    }

}
