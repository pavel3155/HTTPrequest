package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.Files.newBufferedReader;

public class HttpUrlConn {
    private String token="";
    String strURL;
    HttpURLConnection con;

    public HttpUrlConn() {
    }
    public HttpUrlConn(String token) {
        this.token="?access-token="+token;
    }

    /**
     * метод записывает токен ресурса 'gorest.co.in' в переменныю класса token
     * @param token
     */
    public void setToken (String token){
        this.token="?access-token="+token;
    }

    /** метод создает соединение HttpURLConnection
     *
     * @param URL
     * @param method
     * @throws IOException
     */
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

    /**
     * метод удаляет объект с 'gorest.co.in'
     */
    public void  delete(){
        try {
            con.getInputStream();
            int responeCode= con.getResponseCode();
            System.out.println(responeCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * метод выполняет десериализацию json-файла, возвращает массив объектов
     * @param nameFile
     * @return
     * @throws IOException
     */
    public User[] getList(String nameFile) throws IOException {
        BufferedReader br = newBufferedReader(Paths.get(nameFile), StandardCharsets.UTF_8);
        StringBuilder strBuild=new StringBuilder();
        String line;
        while ((line=br.readLine())!=null){
            strBuild.append(line);
        }
        Gson gson =new Gson();
        User[] users=gson.fromJson(strBuild.toString(), User[].class);
// еще один вариант
//        Type listOfMyClassObject = new TypeToken<ArrayList<User>>(){}.getType();
//        List<User> users=gson.fromJson(strBuild.toString(), listOfMyClassObject);
        System.out.println(users);
        for (User u:users){
            System.out.println(u);
        }
        return users;
    }

    /**
     * метод добавляет объект на ресурс 'gorest.co.in'
     * @param user
     * @return
     * @throws Exception
     */
    public User post(User user) throws Exception {
        Gson gson = new Gson();
        String jsonUser  = gson.toJson(user);
        System.out.println(jsonUser);
        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonUser.getBytes("utf-8");
            os.write(input,0, input.length);
        }
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        if(responseCode != 201){
            System.out.println(responseCode);
            System.out.println("Error!");
            if (responseCode==401) {
                System.out.println("Для выполнения команды необходимо ввести 'token'...");
            }
            if (responseCode==422) {
                System.out.println("Сервер не принимает пользователя....");
                System.out.println("Возможно пользователь уже существует или введены не корректные данные....");
            }
            return null;
        }

        return get();
    }

    /**
     * метод обновляет объект по id на ресурсе 'gorest.co.in'
     * @param user
     * @return
     * @throws Exception
     */
    public User put(User user) throws Exception {
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
            if (responseCode==401) {
                System.out.println("Для выполнения команды необходимо ввести 'token'...");
            }
            if (responseCode==422) {
                System.out.println("Сервер не принимает пользователя....");
                System.out.println("Данные не корректны....");
            }
            return null;
        }
        return get();
    }

    /**
     * десериализация, одного пользователя с ресурса 'gorest.co.in'
     * @return
     * @throws IOException
     */
    public User get() throws IOException {
        if (con.getResponseCode()==404){
            System.out.println("Пользователь не найден!!!");
            return null;
        }
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
    /**
     * десериализация, полученного пользователей с ресурса 'gorest.co.in'
     * @return
     * @throws IOException
     */
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

    /**
     * сериализация пользователей в json-файл
     * @param nameFile
     * @throws IOException
     */
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

    /**
     * метод прерывает соединение
     */
    public void disconnect(){
        con.disconnect();
    }
}
