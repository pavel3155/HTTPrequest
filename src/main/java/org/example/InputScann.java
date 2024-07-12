package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputScann {
    Scanner sc = new Scanner(System.in);
    public InputScann() {
    }
    public User post (){
        //Scanner sc = new Scanner(System.in);
        System.out.print("Введите name:> ");
        String name =sc.nextLine();
        System.out.print("Введите email:> ");
        String email =sc.nextLine();
        System.out.print("Введите gender:> ");
        String gender =sc.nextLine();
        System.out.print("Введите status:> ");
        String status =sc.nextLine();
        return new User(name,email,gender,status);
    }
    public Map<String,String> put (){
        Map<String,String> user = new HashMap<>();
        System.out.print("Введите name:> ");
        String name =sc.nextLine();
        user.put("name",name);
        System.out.print("Введите email:> ");
        String email =sc.nextLine();
        user.put("email",email);
        return user;
    }
    public String token(){
        //Scanner sc = new Scanner(System.in);
        System.out.print("Введите ваш 'token' на ресурсе 'gorest.co.in':> ");
        String token =sc.next();
        //sc.close();
        return token;
    }
}
