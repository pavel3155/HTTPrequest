package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newOutputStream;

public class Request {
    private String token="";
    String strURL;
    HttpURLConnection con;

    public Request() {

    }

    public Request(String token) {
        this.token="?access-token="+token;
    }

    public void setToken (String token){
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

    public void  delete(){
        try {
            con.getInputStream();
            int responeCode= con.getResponseCode();
            System.out.println(responeCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public User ReadFromFile(String nameFile) throws Exception{
        Gson gson = new Gson();
        try(OutputStream os = con.getOutputStream()) {
            BufferedReader br = newBufferedReader(Paths.get(nameFile), StandardCharsets.UTF_8);
            StringBuilder strBuild=new StringBuilder();
            String line;
            while ((line=br.readLine())!=null){
                strBuild.append(line);
            }
            String str =strBuild.toString();
            System.out.println(str);
            byte[] input=str.getBytes("utf-8");

            os.write(input,0, input.length);
        }
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        if(responseCode != 201){
            System.out.println(responseCode);
            throw new Exception("Error!");
        }
        return get();
    }
    public User[] getList(String nameFile) throws IOException {
        BufferedReader br = newBufferedReader(Paths.get(nameFile), StandardCharsets.UTF_8);
        StringBuilder strBuild=new StringBuilder();
        String line;
        while ((line=br.readLine())!=null){
            strBuild.append(line);
        }

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
            System.out.println("Error!");
            return null;
            //throw new HTTPException("Error!");
        }

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
    public void WriteToFile(String nameFile) throws IOException {
        StringBuilder strBuild=new StringBuilder();
        Scanner sc=new Scanner(con.getInputStream());
        while (sc.hasNextLine()){
            strBuild.append(sc.nextLine());
        }
        sc.close();
        Gson gson =new GsonBuilder().setPrettyPrinting().create();
        FileWriter wr=new FileWriter(nameFile);
        User[] users=gson.fromJson(strBuild.toString(), User[].class);
        gson.toJson(users,wr);
        //wr.write(gson.toJson(strBuild));
        wr.close();
        System.out.println("JSON created!");
    }

    public void disconnect(){
        con.disconnect();
    }
}
