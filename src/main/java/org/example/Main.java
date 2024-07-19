package org.example;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        InputScann input = new InputScann();
        Request request=new Request(input.token());
        Boolean run = true;
        while (run){
            int reqN= input.menu();
            switch (reqN){
                case 1: {
                    String putUrl = "https://gorest.co.in/public/v2/users";
                    request.connect(putUrl,"GET");
                    request.getList();
                    request.disconnect();
                    break;
                }
                case 2:{
                    Map<String,String> page= input.page();
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
                    request.post(input.post()).getId();
                    request.disconnect();
                    break;
                }
                case 4: {
                    int idUser = input.idUser();
                    String id = String.valueOf(idUser);
                    String putUrl = "https://gorest.co.in/public/v2/users/" + id;
                    request.connect(putUrl, "GET");
                    User user=request.get();
                    request.disconnect();
                    request.connect(putUrl, "PUT");
                    Map<String, String> edit = input.put();
                    user.name = edit.get("name");
                    user.email = edit.get("email");
                    request.put(user);
                    request.disconnect();
                    break;
                }
                case 5:{
                    int idUser = input.idUser();
                    String id = String.valueOf(idUser);
                    if (id!=null&&!id.isEmpty()) {
                        String delUrl = "https://gorest.co.in/public/v2/users/" + id;
                        request.connect(delUrl,"GET");
                        if (request.get()!=null) {
                            request.disconnect();
                            if ("да".equals(input.del())) {
                                request.connect(delUrl, "DELETE");
                                request.delete();
                                request.disconnect();
                                System.out.println("Пользователь удален...");
                            } else System.out.println("Удаление отменено...");
                        } else request.disconnect();
                    }else System.out.println("!!!не задано id пользователя!!!");
                    break;
                }
                case 6: {
                    Map<String,String> page= input.page();
                    String pageN=page.get("N");
                    String per_page= page.get("per");
                    String getUrl = "https://gorest.co.in/public/v2/users?page="+pageN+"&per_page="+per_page;
                    request.connect(getUrl,"GET");
                    request.WriteToFile("users.json");
                    request.disconnect();
                    break;
                }
                case 7:{
                    String postURL = "https://gorest.co.in/public/v2/users/";
                    request.connect(postURL,"POST");
                    request.ReadFromFile("user.json");
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