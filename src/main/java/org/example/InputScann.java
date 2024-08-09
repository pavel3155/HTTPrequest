package org.example;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputScann {
    Scanner sc = new Scanner(System.in);

    public InputScann() {
    }

    public User post (){

        System.out.print("Введите name:> ");
        String name =sc.next();
        System.out.print("Введите email:> ");
        String email =sc.next();
        System.out.print("Введите gender:> ");
        String gender =sc.next();
        System.out.print("Введите status:> ");
        String status =sc.next();

        return new User(name,email,gender,status);
    }
    public Map<String,String> put (){
        Map<String,String> user = new HashMap<>();
        System.out.print("Введите name:> ");
        String name =sc.next();
        System.out.println("name= "+name);
        user.put("name",name);
        System.out.print("Введите email:> ");
        String email =sc.next();
        System.out.println("email= "+email);
        user.put("email",email);
        return user;
    }
    public byte[] patch () throws UnsupportedEncodingException {
        System.out.println("Поле доступное для редактирования: name, email, gender, status ");
        System.out.print("Введите name:> ");
        String name =sc.next();
        System.out.print("Введите email:> ");
        String email =sc.next();
        String sName = "\"name\":\""+name+"\"";
        String sEmail = "\"email\":\""+email+"\"";
        String str = "{"+sName+","+sEmail+"}";
        System.out.println(str);
        byte[] patch = str.getBytes("UTF-8");
        return patch;
    }

    public String token(){
        System.out.print("Введите ваш 'token' на ресурсе 'gorest.co.in':> ");
        String token =sc.next();
        return token;
    }
    public int menu(){
        System.out.println("*****************************************************************");
        System.out.println("***        Пример работы с HTTP - запросами                   ***");
        System.out.println("*****************************************************************");
        System.out.println("*** 1. Получить список пользователей с текущей страницы       ***");
        System.out.println("*** 2. Получить список пользователей произвольной страницы    ***");
        System.out.println("*** 3. Добавить пользователя                                  ***");
        System.out.println("*** 4. Редактировать пользователя                             ***");
        System.out.println("*** 5. Удалить пользователя                                   ***");
        System.out.println("*** 6. Сохранить пользователей в файл \"users.json\"          ***");
        System.out.println("*** 7. Добавить пользователя из файла \"ruser.json\"          ***");
        System.out.println("*** 8. Ваш 'token' на ресурсе 'gorest.co.in'                  ***");
        System.out.println("*** 9. HttpClient                                             ***");
        System.out.println("*** 0. Выход                                                  ***");
        System.out.println("*****************************************************************");
        System.out.print("Введите номер запроса:> ");
        int reqN =sc.nextInt();

        return reqN;
    }
    public String del(){
        System.out.print("Удалить пользователя (да/нет):> ");
        String del=sc.next();
        return del;
    }
    public int idUser () {
        System.out.print("Введите id пользователя:> ");
        int idUser = sc.nextInt();
        System.out.println("ID: "+idUser);
        return idUser;
    }
    public  Map<String,String> page () {
        Map<String, String> page = new HashMap<>();
        System.out.print("Введите N страницы:> ");
        String pageN=sc.next();
        page.put("N", pageN);
        System.out.print("Введите максимальное значение пользователей на странице:> ");
        String per=sc.next();
        page.put("per", per);
        System.out.println();
        return page;
    }

}
