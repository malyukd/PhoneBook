import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String name;
    public String login;
    private String password;
    File file;
    public PhoneBook phoneBook;
    User(String name, String login, String password, File file){
        this.name = name;
        this.login = login;
        this.password = password;
        this.file = file;
        this.phoneBook = new PhoneBook();
    }
    public boolean isPasswordRight(String pass){
        if(pass.equals(password)){
            return true;
        }else{
            return false;
        }
    }
    public boolean writeFile(){
        try(FileWriter fw = new FileWriter(file, false))
        {
            String text = name +"\n"+login+"\n"+password+"\n\n";
            fw.write(text);
            for(int i=0; i< phoneBook.size(); i++){
                fw.write(phoneBook.get(i).toString()+"\n");
            }
        }
        catch(IOException ex){
            System.out.println("Произошла ошибка во время записи файла \"" + file.getName() +"\". Пожалуйса, обратитесь в поддержку или запишите данные в файл самостоятельно.");
            return false;
        }
        return true;
    }



}
