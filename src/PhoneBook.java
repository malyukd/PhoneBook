import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class PhoneBook {
    private List<Contact> book;
    PhoneBook(){
        this.book = new ArrayList<>();
    }
    public Contact get(int ind){
        if(ind<book.size() && ind>-1){
            return book.get(ind);
        }else {
            return null;
        }
    } //гетр контакта
    public int size(){
        return book.size();
    }//возвращает размер телефонной книги
    public List<Contact> getList(){
        return book;
    }//возвращает саму телефонную книгу
    public void addContact(String number, String name, String lastname){

        book.add(new Contact(number,name,lastname));
    } //добавление контакта
    public boolean deleteContact(int ind){
        if(ind<book.size() && ind>-1){
            book.remove(ind);
            return true;
        }else {
            System.out.println("Данного элемента не существовало");
            return false;
        }
    }//удаление контакта по индексу
    public int searchContactbyNumber(String number){
        int i=-1;
        for(Contact tmp: book){
            i++;
            if(tmp.number.equals(number)){
                return i;
            }
        }
        return i;
    }//поиск контакта по номеру
    public int searchContactbyName(String fullname){
        int i=-1;
        String[] contact = fullname.split(" ");
        try {
            for (Contact tmp : book) {
                i++;
                if (tmp.name.equals(contact[0]) && tmp.surname.equals(contact[1])) {
                    return i;
                }
            }
        }catch(Exception ex){
            System.out.println("Ошибка формата ввода контакта");
            return -1;
        }
        return -1;
    }//поиск контакта по имени и фамилии
    public void editName(int ind, String name){
        Contact x = book.get(ind);
        x.name = name;
    }//редактирование имени контакта по индексу
    public void editSurname(int ind, String surname){
        Contact x = book.get(ind);
        x.surname = surname;
    }//редактирование фамилии контакта по индексу
    public void editNumber(int ind, String number){
        Contact x = book.get(ind);
        x.number = number;
    }//редактирование номера контакта по индексу
    public void editAll(int ind, String contact){
        try{
            String []contact2 = contact.split(" ");
            book.set(ind, new Contact(contact2[0], contact2[1],contact2[2]));
        }catch(Exception ex){
            System.out.println("Ошибка формата контакта");
        }
    }//редактирование контакта по индексу
    public void printAll(){
        for(Contact c: book){
            System.out.println(c.toString());
        }
    }//печать всей телефонной книги
    public void searchByName(String regex){
        String pattern = "";
        for (int i = 0; i < regex.length(); i++) {
            if (regex.charAt(i) == '*') {
                pattern += ".+";
            } else {
                if (regex.charAt(i) == '_') {
                    pattern += ".{1}";
                } else {
                    pattern += regex.charAt(i);
                }
            }
        }
        for (Contact tmp : book) {
            if (Pattern.matches(pattern, tmp.name)) {
                System.out.println(tmp.toString());
            }
        }
    }//выводит все совпадения по имени
    public void searchBySurname(String regex){
        String pattern = "";
        for (int i = 0; i < regex.length(); i++) {
            if (regex.charAt(i) == '*') {
                pattern += ".+";
            } else {
                if (regex.charAt(i) == '_') {
                    pattern += ".{1}";
                } else {
                    pattern += regex.charAt(i);
                }
            }
        }
        for (Contact tmp : book) {
            if (Pattern.matches(pattern, tmp.surname)) {
                System.out.println(tmp.toString());
            }
        }
    }//выводит все совпадения по фамилии
    public void searchByNumber(String regex){
        String pattern = "";
        for (int i = 0; i < regex.length(); i++) {
            if (regex.charAt(i) == '*') {
                pattern += ".+";
            } else {
                if (regex.charAt(i) == '_') {
                    pattern += ".{1}";
                } else {
                    pattern += regex.charAt(i);
                }
            }
        }
        for (Contact tmp : book) {
            if (Pattern.matches(pattern, tmp.number)) {
                System.out.println(tmp.toString());
            }
        }
    }//выводит все совпадения по номеру
}
