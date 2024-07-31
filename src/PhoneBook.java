import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneBook {
    private List<Contact> book;
    PhoneBook(){
        this.book = new ArrayList<>();
    }
    public void addContact(String number, String name, String lastname){
        book.add(new Contact(number,name,lastname));
    }
    boolean deleteContact(int ind){
        if(ind<book.size() && ind>-1){
            book.remove(ind);
            return true;
        }else {
            System.out.println("Данного элемента не существовало");
            return false;
        }
    }
    int searchContactbyNumber(String number){
        int i=-1;
        for(Contact tmp: book){
            i++;
            if(tmp.number.equals(number)){
                return i;
            }
        }
        return i;
    }
    int searchContactbyName(String fullname){
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
    }
    void editName(int ind, String name){
        Contact x = book.get(ind);
        x.name = name;
    }
    void editSurame(int ind, String surname){
        Contact x = book.get(ind);
        x.surname = surname;
    }
    void editNumber(int ind, String number){
        Contact x = book.get(ind);
        x.number = number;
    }
    void editAll(int ind, String contact){
        try{
            String []contact2 = contact.split(" ");
            book.set(ind, new Contact(contact2[0], contact2[1],contact2[2]));
        }catch(Exception ex){
            System.out.println("Ошибка формата контакта");
        }
    }
    Contact get(int ind){
        if(ind<book.size() && ind>-1){
            return book.get(ind);
        }else {
            return null;
        }
    }
    int size(){
        return book.size();
    }
    void printAll(){
        for(Contact c: book){
            System.out.println(c.toString());
        }
    }

}
