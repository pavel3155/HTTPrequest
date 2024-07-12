package org.example;


import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
//        System.out.println("Пример работы с HTTP - запросами...");
//        System.out.println("Добавим пользователя на 'gorest.co.in/public/v2/users/'.... ");
        Request request=new Request(new InputScann().token());
//        String postURL = "https://gorest.co.in/public/v2/users/";
//        request.connect(postURL,"POST");
//        //User user=request.post(new User("Михаил","mi@list.ru","male","active"));
//        User user=request.post(new InputScann().post());
//        request.disconnect();
//        System.out.println("Изменим 'name', 'email' добавленного пользователя.... ");
//        String id = String.valueOf(user.getId());
//        String putUrl = "https://gorest.co.in/public/v2/users/"+id;
//        request.connect(putUrl,"PUT");
//        Map<String,String> edit=new InputScann().put();
//        user.name=edit.get("name");
//        user.email=edit.get("email");
//        request.put(user);
//        request.disconnect();


        String putUrl = "https://gorest.co.in/public/v2/users";
        request.connect(putUrl,"GET");
        request.getList();
        request.disconnect();


    }
}