package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Request {
    String token="";
    String strURL;
    HttpURLConnection con;

    public Request() {

    }
    public Request(String token) {
        this.token="?access-token="+token;
    }

    public void connect(String URL,String method)throws IOException {
        strURL = URL;
        URL url=new URL(strURL+token);
        con=(HttpsURLConnection)url.openConnection();
        con.setRequestMethod(method);
        con.setDoOutput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestProperty("Content-Type","application/json");
        con.addRequestProperty("User-Agent","Mozilla");
        con.connect();
    }

    public User post(User user) throws Exception {
        Gson gson = new Gson();
        String jsonUser  = gson.toJson(user);
        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonUser.getBytes("utf-8");
            os.write(input,0, input.length);
        }
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        if(responseCode != 201){
            System.out.println(responseCode);
            throw new Exception("Error!");
        }
//        Scanner scanner = new Scanner(con.getInputStream());
//        StringBuilder response  = new StringBuilder();
//        while(scanner.hasNextLine()){
//            response.append(scanner.nextLine());
//        }
//        System.out.println(response);
//        scanner.close();
        return get();


    }
    public User put(User user) throws Exception {
        Gson gson = new Gson();
        String jsonUser  = gson.toJson(user);
        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonUser.getBytes("utf-8");
            os.write(input,0, input.length);
        }
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        if(responseCode != 200){
            System.out.println(responseCode);
            throw new Exception("Error!");
        }
        return get();
    }
    public User get() throws IOException {
        StringBuilder strBuild=new StringBuilder();
        Scanner sc=new Scanner(con.getInputStream());
        while (sc.hasNextLine()){
            strBuild.append(sc.nextLine());
        }
        sc.close();
        Gson gson =new Gson();
        System.out.println(strBuild.toString());
        User user=gson.fromJson(strBuild.toString(), User.class);
        System.out.println(user);
        return user;
    }

    public User[] getList() throws IOException {
        StringBuilder strBuild=new StringBuilder();
        Scanner sc=new Scanner(con.getInputStream());
        while (sc.hasNextLine()){
            strBuild.append(sc.nextLine());
        }
        sc.close();
        Gson gson =new Gson();
        User[] users=gson.fromJson(strBuild.toString(), User[].class);
//        Type listOfMyClassObject = new TypeToken<ArrayList<User>>(){}.getType();
//        List<User> users=gson.fromJson(strBuild.toString(), listOfMyClassObject);
        System.out.println(users);
        for (User u:users){
            System.out.println(u);
        }
        return users;
    }

    public void disconnect(){
        con.disconnect();
    }
}
