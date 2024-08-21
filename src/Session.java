import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Session {
    private final File users;
    private final List<User> userList;
    private User currentUser;
    private final Scanner scanner;
    private final File lastUser;
    private final MenuPrinter mp;

    Session() {
        this.users = new File("Users");
        this.scanner = new Scanner(System.in);
        this.userList = new ArrayList<>();
        this.mp = new MenuPrinter();
        this.lastUser = new File("lastUser.txt");
    }

    public void session() {
        readUsers();
        try (BufferedReader br = new BufferedReader(new FileReader(lastUser))) {
            String s = br.readLine();
            if (s == null) {
                chooseAction();
            } else {
                for (User tmp : userList) {
                    if (s.equals(tmp.login)) {
                        currentUser = tmp;
                        mainMenu();
                    }
                }
                chooseAction();
            }
        } catch (IOException ex) {
            System.out.println("Произошла ошибка считывания файла lastUser");
            chooseAction();
        }
    } //главный метод, точка старта программы
    private boolean isLoginExist(String login) {
        String[] files = users.list();
        if(files==null){
            return false;
        }
        for (String tmp : files) {
            if (tmp.equals(login + ".txt")) {
                return true;
            }
        }
        return false;
    } //проверяет наличие файла с данным логином в папке Users
    private boolean readUsers() {
        File[] files = users.listFiles();
        if (files == null)
            return false;
        for (File tmp : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(tmp))) {
                try {
                    User x = new User(br.readLine(), br.readLine(), br.readLine(), tmp);
                    String s;
                    while ((s = br.readLine()) != null) {
                        String[] contact = s.split(" ");
                        if (contact.length == 3) {
                            x.phoneBook.addContact(contact[0], contact[1], contact[2]);
                        }
                    }
                    userList.add(x);
                } catch (Exception ex) {
                    System.out.println("Что-то не так с файлом " + tmp.getName());
                }
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    } //читает информацию из файлов пользователей

    private void chooseAction() {
        mp.chooseAction();
        int input = input(0, 3);
        switch (input) {
            case (0):
                break;
            case (1):
                if(signIn()){
                    mainMenu();
                }else{
                    chooseAction();
                }
                break;
            case (2):
                if(signUp()){
                    mainMenu();
                }else{
                    chooseAction();
                }
                break;
            case (3):
                printUsers();
                chooseAction();
                break;
        }
    } //реализует меню chooseAction
    private boolean signIn() {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        while (login.equals("")) {
            login = scanner.nextLine();
        }
        for (User tmp : userList) {
            if (tmp.login.equals(login)) {
                System.out.println("Введите пароль");
                String password = scanner.nextLine();
                while (!tmp.isPasswordRight(password) && !password.equals("0")) {
                    System.out.println("Пароль неверен. Попробуйте снова или введите \"0\" для выхода");
                    password = scanner.nextLine();
                }
                if (password.equals("0")) {
                    return false;
                } else {
                    System.out.println("Вход в аккаунт произошел успешно!");
                    currentUser = tmp;
                    return true;
                }
            }
        }
        System.out.println("Пользователя с логином \"" + login + "\" не существует");
        return false;
    } //реализует вход в аккаунт
    private boolean signUp() {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        while (login.equals("")) {
            login = scanner.nextLine();
        }
        while (isLoginExist(login) && !login.equals("0") || login.equals("")) {
            System.out.println("Данный логин уже существует или не может быть использован. Придумайте новый или введите \"0\" для выхода");
            login = scanner.nextLine();
        }
        if (login.equals("0"))
            return false;
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        while (name == "") {
            System.out.println("Данное имя не может быть использовано. Придумайте новый или введите \"0\" для выхода");
            name = scanner.nextLine();
        }
        if (name.equals("0"))
            return false;
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        while (password == "") {
            System.out.println("Данный пароль не может быть использован. Придумайте новый или введите \"0\" для выхода");
            password = scanner.nextLine();
        }
        if (password.equals("0"))
            return false;
        System.out.println("Повторите пароль");
        while (!password.equals(scanner.nextLine())) {
            System.out.println("Пароли не совпадают. Повторите попытку позже");
        }
        File user = new File("Users\\" + login + ".txt");
        try {
            user.createNewFile();
        } catch (Exception ex) {
            System.out.println("Произошла ошибка создания аккаунта");
            return false;
        }
        User x = new User(name, login, password, user);
        currentUser = x;
        userList.add(x);
        x.writeFile();
        return true;
    } //реализует создание аккаунта
    private void printUsers() {
        for (User tmp : userList) {
            System.out.println(tmp.login);
        }
    } //выводит список пользователей

    private void mainMenu() {
        mp.mainMenu();
        int input = input(0, 5);
        switch (input) {
            case (0):
                try (FileWriter writer = new FileWriter(lastUser, false)) {
                    writer.write(currentUser.login);
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("Произошла ошибка записи файла lastUser");
                    mainMenu();
                }
                break;
            case (1):
                contactMenu();
                break;
            case(2):
                printMenu();
                break;
            case(3):
                sortingMenu();
                break;
            case(4):
                searchMenu();
                break;
            case (5):
                try (FileWriter writer = new FileWriter(lastUser, false)) {
                    writer.write("");
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("Произошла ошибка записи файла lastUser");
                    mainMenu();
                }
                chooseAction();
                break;
        }
    } //реализует меню mainMenu
    private int input(int a, int b) {
        int number;
        do {
            System.out.println("Пожалуйста введите число от " + a + " до " + b);
            while (!scanner.hasNextInt()) {
                System.out.println("Пожалуйста введите число от " + a + " до " + b);
                scanner.next();
            }
            number = scanner.nextInt();
        } while (number < a || number > b);
        return number;
    } //реализует ввод

    private void contactMenu(){
        mp.contactMenu();
        int input = input(-1,2);
        switch (input){
            case(-1):
                currentUser.writeFile();
                mainMenu();
                break;
            case(0):
                System.out.println("Введите номер телефона, имя и фамилию через пробел");
                String s= scanner.nextLine();
                while (s==""){
                    s= scanner.nextLine();
                }
                String[] contact = s.split(" ");
                try {
                    currentUser.phoneBook.addContact(contact[0], contact[1], contact[2]);
                }catch(Exception ex){
                    System.out.println("Ошибка формата контакта");
                }
                contactMenu();
                break;
            case(1):
                editContact();
                break;
            case(2):
                currentUser.phoneBook.deleteContact(searchContact());
                contactMenu();
                break;
        }
    } //реализует меню contactMenu
    private void editContact(){
        mp.editContact();
        int input = input(-1,3);
        int ind=0;
        switch (input){
            case(-1):
                currentUser.writeFile();
                contactMenu();
                break;
            case(0):
                ind = searchContact();
                System.out.println("Введите новое имя контакта");
                currentUser.phoneBook.editName(ind, scanner.nextLine());
                editContact();
                break;
            case(1):
                ind = searchContact();
                System.out.println("Введите новую фамилию контакта");
                currentUser.phoneBook.editSurname(ind, scanner.nextLine());
                editContact();
                break;
            case(2):
                ind = searchContact();
                System.out.println("Введите новый номер телефона контакта");
                currentUser.phoneBook.editNumber(ind, scanner.nextLine());
                editContact();
                break;
            case(3):
                ind = searchContact();
                System.out.println("Введите новую информацию");
                currentUser.phoneBook.editAll(ind, scanner.nextLine());
                editContact();
                break;
        }
    } //реализует меню editContact
    private int searchContact(){
        System.out.println("0 - Найти по номеру телефона\n1 - Найти по имени и фамилии");
        int input2 = input(0,1);
        int ind =0;
        String sc;
        switch(input2){
            case(0):
                System.out.println("Введите номер телефона");
                sc="";
                while(sc=="") {
                    sc=scanner.nextLine();
                }
                ind = currentUser.phoneBook.searchContactbyNumber(sc);
                break;
            case(1):
                System.out.println("Введите имя и фамилию через пробел");
                sc = "";
                while(sc=="") {
                    sc=scanner.nextLine();
                }
                ind = currentUser.phoneBook.searchContactbyName(sc);
                break;

        }
        return ind;
    } //реализует поиск по контактам

    private void printMenu(){
        mp.printMenu();
        int input = input(-1, 1);
        switch(input){
            case(-1):
                mainMenu();
                break;
            case(0):
                currentUser.phoneBook.printAll();
                printMenu();
                break;
            case(1):
                System.out.println("Введите маску поиска. * - любое кол-во символов, _ - один любой символ");
                String s="";
                while(s==""){
                    s=scanner.nextLine();
                }
                printSpecific(s);
                printMenu();
                break;

        }
    } //реализует меню printMenu
    private void printSpecific(String regex) {
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
        String s;
        for (Contact tmp : currentUser.phoneBook.getList()) {
            s = tmp.toString();
            if (Pattern.matches(pattern, s)) {
                System.out.println(s);
            }
        }
    } //реализует функцию print specific

    private void sortingMenu(){
        mp.sortingMenu();
        int input = input(-1, 2);
        switch (input){
            case(-1):
                mainMenu();
                break;
            case(0):
                sortingName();
                break;
            case(1):
                sortingSurname();
                break;
            case(2):
                sortingNumber();
                break;
        }
    } //реализует меню sortingMenu
    private void sortingName(){
        mp.sortingName();
        int input = input(-1, 1);
        switch(input){
            case(-1):
                sortingMenu();
                break;
            case(0):
                mp.sortingNameAZ();
                currentUser.phoneBook.getList().stream().sorted((x,y)->x.name.compareTo(y.name)).forEach(x-> System.out.println(x.toString()));
                sortingName();
                break;
            case(1):
                mp.sortingNameZA();
                currentUser.phoneBook.getList().stream().sorted((x,y)->y.name.compareTo(x.name)).forEach(x-> System.out.println(x.toString()));
                sortingName();
                break;
        }

    } //реализует меню sortingName
    private void sortingSurname() {
        mp.sortingSurname();
        int input = input(-1, 1);
        switch (input) {
            case (-1):
                sortingMenu();
                break;
            case (0):
                mp.sortingSurnameAZ();
                currentUser.phoneBook.getList().stream().sorted((x, y) -> x.surname.compareTo(y.surname)).forEach(x -> System.out.println(x.toString()));
                sortingSurname();
                break;
            case (1):
                mp.sortingSurnameZA();
                currentUser.phoneBook.getList().stream().sorted((x, y) -> y.surname.compareTo(x.surname)).forEach(x -> System.out.println(x.toString()));
                sortingSurname();
                break;
        }
    } //реализует меню sortingSurname
    private void sortingNumber() {
        mp.sortingNumber();
        int input = input(-1, 1);
        switch (input) {
            case (-1):
                sortingMenu();
                break;
            case (0):
                mp.sortingNumber19();
                currentUser.phoneBook.getList().stream().sorted((x, y) -> x.number.compareTo(y.number)).forEach(x -> System.out.println(x.toString()));
                sortingNumber();
                break;
            case (1):
                mp.sortingNumber91();
                currentUser.phoneBook.getList().stream().sorted((x, y) -> y.number.compareTo(x.number)).forEach(x -> System.out.println(x.toString()));
                sortingNumber();
                break;
        }
    } //реализует меню sortingNumber

    private void searchMenu(){
        mp.searchMenu();
        int input = input(-1,2);
        String sc;
        switch (input){
            case(-1):
                mainMenu();
                break;
            case(0):
                System.out.println("Введите маску поиска. * - любое кол-во символов, _ - один любой символ");
                sc="";
                while(sc==""){
                    sc=scanner.nextLine();
                }
                currentUser.phoneBook.searchByName(sc);
                searchMenu();
                break;
            case(1):
                System.out.println("Введите маску поиска. * - любое кол-во символов, _ - один любой символ");
                sc="";
                while(sc==""){
                    sc=scanner.nextLine();
                }
                currentUser.phoneBook.searchBySurname(sc);
                searchMenu();
                break;
            case(2):
                System.out.println("Введите маску поиска. * - любое кол-во символов, _ - один любой символ");
                sc="";
                while(sc==""){
                    sc=scanner.nextLine();
                }
                currentUser.phoneBook.searchByNumber(sc);
                searchMenu();
                break;
        }
    } //реализует меню searchMenu
}
