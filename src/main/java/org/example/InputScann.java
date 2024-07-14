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
    public int menu(){
        System.out.println("*************************************************************");
        System.out.println("***        Пример работы с HTTP - запросами               ***");
        System.out.println("*************************************************************");
        System.out.println("*** 1. Получить список пользователей с текущей страницы   ***");
        System.out.println("*** 2. Получить список пользователей произвольной страницы***");
        System.out.println("*** 3. Добавить на ресурс пользователя                    ***");
        System.out.println("*** 4. Редактировать добавленного пользователя на ресурсе ***");
        System.out.println("*** 0. Выход                                              ***");
        System.out.println("************************************************************");
        System.out.print("Введите номер запроса:> ");
        int reqN =sc.nextInt();
        return reqN;
    }
    public int idUser () {
        System.out.print("Введите id пользователя:> ");
        int idUser = sc.nextInt();
        return idUser;
    }
    public  Map<String,String> page () {
        Map<String, String> page = new HashMap<>();
        System.out.print("Введите N страницы:> ");
        String pageN=sc.nextLine();
        page.put("N", pageN);
        System.out.print("Введите максимальное значение пользователей на странице:> ");
        String per=sc.nextLine();
        page.put("per", per);
        return page;
    }

}
