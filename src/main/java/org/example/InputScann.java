package org.example;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputScann {
    Scanner sc = new Scanner(System.in);

    public InputScann() {
    }

    /**
     * Ввод данных для добавления объекта
     * @return
     */
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

    /**
     * Ввод данных для выполнения обновления объекта
     * @return
     */
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

    /**
     * Ввод токена
     * @return
     */
    public String token(){
        System.out.print("Введите ваш 'token' на ресурсе 'gorest.co.in':> ");
        String token =sc.next();
        return token;
    }

    /**
     * Ввод команды основного меню
     * @return
     */
    public int menu(){
        System.out.println("*****************************************************************");
        System.out.println("***           Пример работы с HTTP - запросами                ***");
        System.out.println("*****************************************************************");
        System.out.println("***             Применяем: HttpsURLConnection                 ***");
        System.out.println("*****************************************************************");
        System.out.println("*** 1. Получить список пользователей с текущей страницы       ***");
        System.out.println("*** 2. Получить список пользователей указанной страницы       ***");
        System.out.println("*** 3. Добавить пользователя                                  ***");
        System.out.println("*** 4. Редактировать пользователя                             ***");
        System.out.println("*** 5. Удалить пользователя                                   ***");
        System.out.println("*** 6. Сериализация в файл \"users.json\"                        ***");
        System.out.println("*** 7. Десериализация из файла \"ruser.json\"                    ***");
        System.out.println("*** 8. Ввести 'token' для ресурса 'gorest.co.in'              ***");
        System.out.println("*****************************************************************");
        System.out.println("*****************************************************************");
        System.out.println("***                  Применяем: HttpClient                    ***");
        System.out.println("*** 9. Десериализация из файла \"ruser.json\"                   ***");
        System.out.println("*****************************************************************");
        System.out.println("*** 0. Выход                                                  ***");
        System.out.println("*****************************************************************");
        System.out.print("Введите номер команды:> ");
        int reqN =sc.nextInt();
        return reqN;
    }

    /**
     * подтверждение удаления объекта
     * @return
     */
    public String del(){
        System.out.print("Удалить пользователя (да/нет):> ");
        String del=sc.next();
        return del;
    }

    /**
     * ввод id пользователя
     * @return
     */

    public int idUser () {
        System.out.print("Введите id пользователя:> ");
        int idUser = sc.nextInt();
        System.out.println("ID: "+idUser);
        return idUser;
    }

    /***
     * ввод страницы и масимального количества объектов на странице
     * @return
     */
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
