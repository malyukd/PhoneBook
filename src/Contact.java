public class Contact {
    public String number;
    public String name;
    public String surname;

    Contact(String number, String name, String surname){
        this.number = number;
        this.name = name;
        this.surname = surname;
    }
    public String toString(){//преобразует контакт в строку
        return number + " " + name + " " + surname;
    }
}
