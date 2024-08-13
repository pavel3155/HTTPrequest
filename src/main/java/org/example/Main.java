package org.example;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        InputScann input = new InputScann();
        HttpUrlConn httpUrlConn =new HttpUrlConn();
        Boolean run = true;
        while (run){
            int reqN= input.menu();
            switch (reqN){
                //Получить список пользователей с текущей страницы
                case 1: {
                    String putUrl = "https://gorest.co.in/public/v2/users";
                    httpUrlConn.connect(putUrl,"GET");
                    httpUrlConn.getList();
                    httpUrlConn.disconnect();
                    break;
                }
                //Получить список пользователей указанной страницы
                case 2:{
                    Map<String,String> page= input.page();
                    String pageN=page.get("N");
                    String per_page= page.get("per");
                    String putUrl = "https://gorest.co.in/public/v2/users?page="+pageN+"&per_page="+per_page;
                    httpUrlConn.connect(putUrl,"GET");
                    httpUrlConn.getList();
                    httpUrlConn.disconnect();
                    break;
                }
                //Добавить пользователя
                case 3:{
                    String postURL = "https://gorest.co.in/public/v2/users/";
                    httpUrlConn.connect(postURL,"POST");
                    httpUrlConn.post(input.post());
                    httpUrlConn.disconnect();
                    break;
                }
                //Редактировать пользователя
                case 4: {
                    int idUser = input.idUser();
                    String id = String.valueOf(idUser);
                    String putUrl = "https://gorest.co.in/public/v2/users/" + id;
                    httpUrlConn.connect(putUrl, "GET");
                    User user= httpUrlConn.get();
                    httpUrlConn.disconnect();
                    if (user!= null) {
                        httpUrlConn.connect(putUrl, "PUT");
                        Map<String, String> edit = input.put();
                        user.name = edit.get("name");
                        user.email = edit.get("email");
                        httpUrlConn.put(user);
                        httpUrlConn.disconnect();
                    }
                    break;
                }
                //Удалить пользователя
                case 5:{
                    int idUser = input.idUser();
                    String id = String.valueOf(idUser);
                    if (id!=null&&!id.isEmpty()) {
                        String delUrl = "https://gorest.co.in/public/v2/users/" + id;
                        httpUrlConn.connect(delUrl,"GET");
                        if (httpUrlConn.get()!=null) {
                            httpUrlConn.disconnect();
                            if ("да".equals(input.del())) {
                                httpUrlConn.connect(delUrl, "DELETE");
                                httpUrlConn.delete();
                                httpUrlConn.disconnect();
                                System.out.println("Пользователь удален...");
                            } else System.out.println("Удаление отменено...");
                        } else httpUrlConn.disconnect();
                    }else System.out.println("!!!не задано id пользователя!!!");
                    break;
                }
                //Сериализация в файл "users.json"
                case 6: {
                    Map<String,String> page= input.page();
                    String pageN=page.get("N");
                    String per_page= page.get("per");
                    String getUrl = "https://gorest.co.in/public/v2/users?page="+pageN+"&per_page="+per_page;
                    httpUrlConn.connect(getUrl,"GET");
                    httpUrlConn.WriteToFile("users.json");
                    httpUrlConn.disconnect();
                    break;
                }
                //Десериализация из файла "ruser.json"
                case 7:{
                    String postURL = "https://gorest.co.in/public/v2/users/";
                    User[] users = httpUrlConn.getList("ruser.json");
                    for (User u:users){
                        httpUrlConn.connect(postURL,"POST");
                        httpUrlConn.post(u);
                        httpUrlConn.disconnect();
                    }
                    break;
                }
                // Ввести 'token' для ресурса 'gorest.co.in'
                case 8:{
                    httpUrlConn.setToken(input.token());
                }
                break;
                //(HttpClient)Десериализация из файла "ruser.json"
                case 9:{
                    HTTPClient client = new HTTPClient();
                    client.setToken(input.token());
                    String postURL = "https://gorest.co.in/public/v2/users/";
                    client.setURI(postURL);
                    List<User> users = client.getList("ruser.json");
                    for (User u:users){
                        client.Response(client.Request(u));
                    }
                }
                break;
                //Выход
                case 0:{
                    run=false;
                    break;
                }
                //неизвестна команда
                default:{
                    System.out.println("Неизвестная команда... Повторите ввод... ");

                }
            }
        }
    }
}