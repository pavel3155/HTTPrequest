package org.example;


import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {



//        System.out.println("Пример работы с HTTP - запросами...");
//        System.out.println("Добавим пользователя на 'gorest.co.in/public/v2/users/'.... ");


        Request request=new Request(new InputScann().token());
        Boolean run = true;
        while (run){
            InputScann input = new InputScann();
            int reqN= input.menu();
            User user = null;
            int idUser = 0;
            switch (reqN){
                case 1: {
                    String putUrl = "https://gorest.co.in/public/v2/users";
                    request.connect(putUrl,"GET");
                    request.getList();
                    request.disconnect();
                    break;
                }
                case 2:{
                    Map<String,String> page= new InputScann().page();
                    String pageN=page.get("N");
                    String per_page= page.get("per");
                    String putUrl = "https://gorest.co.in/public/v2/users?page="+pageN+"&per_page="+per_page;
                    request.connect(putUrl,"GET");
                    request.getList();
                    request.disconnect();
                    break;
                }
                case 3:{
                    String postURL = "https://gorest.co.in/public/v2/users/";
                    request.connect(postURL,"POST");
                    //User user=request.post(new User("Михаил","mi@list.ru","male","active"));
                    idUser=request.post(new InputScann().post()).getId();
                    request.disconnect();
                    break;
                }
                case 4: {
                    if (idUser == 0) {
                        idUser = input.idUser();
                    }
                    String id = String.valueOf(idUser);
                    String putUrl = "https://gorest.co.in/public/v2/users/" + id;
                    request.connect(putUrl, "PUT");
                    Map<String, String> edit = new InputScann().put();
                    user.name = edit.get("name");
                    user.email = edit.get("email");
                    request.put(user);
                    request.disconnect();
                    break;
                }
                case 0:{
                    run=false;
                    break;
                }
            }
        }

    }
}