import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Session {
    private final File users;
    private final List<User> userList;
    private User currentUser;
    private final Scanner scanner;
    private final File lastUser;
    private final MenuPrinter mp;

    Session() {
        this.users = new File("C:\\Users\\daram\\IdeaProjects\\PhoneBook\\Users");
        this.scanner = new Scanner(System.in);
        this.userList = new ArrayList<>();
        this.mp = new MenuPrinter();
        this.lastUser = new File("C:\\Users\\daram\\IdeaProjects\\PhoneBook\\lastUser.txt");
    }

    private boolean isLoginExist(String login) {
        String[] files = users.list();
        for (String tmp : files) {
            if (tmp.equals(login + ".txt")) {
                return true;
            }
        }
        return false;
    }

    private boolean readUsers() {
        File[] files = users.listFiles();
        if (files.length == 0)
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
    }

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
    }

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
        File user = new File("C:\\Users\\daram\\IdeaProjects\\PhoneBook\\Users\\" + login + ".txt");
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
    }

    private void printUsers() {
        for (User tmp : userList) {
            System.out.println(tmp.login);
        }
    }

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
    }

    public boolean session() {
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
                        return true;
                    }
                }
                System.out.println("Произошла ошибка считывания файла lastUser");
                chooseAction();
            }
        } catch (IOException ex) {
            System.out.println("Произошла ошибка считывания файла lastUser");
            chooseAction();
        }
        return true;
    }

    private boolean chooseAction() {
        mp.chooseAction();
        int input = input(0, 3);
        switch (input) {
            case (0):
                break;
            case (1):
                signIn();
                mainMenu();
                break;
            case (2):
                signUp();
                mainMenu();
                break;
            case (3):
                printUsers();
                chooseAction();
                break;
        }
        return true;
    }

    private boolean mainMenu() {
        mp.mainMenu();
        int input = input(0, 5);
        switch (input) {
            case (0):
                try (FileWriter writer = new FileWriter(lastUser, false)) {
                    writer.write(currentUser.login);
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    return true;
                }
                break;
            case (1):
                contactMenu();
                break;
            case(2):
                printMenu();
                break;
            case (5):
                try (FileWriter writer = new FileWriter(lastUser, false)) {
                    writer.write("");
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    return true;
                }
                chooseAction();
                break;
        }
        return true;
    }
    private boolean contactMenu(){
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
                return true;
    }
    private int searchContact(){
        System.out.println("0 - Найти по номеру телефона\"1 - Найти по имени и фамилии");
        int input2 = input(0,1);
        int ind =0;
        switch(input2){
            case(0):
                System.out.println("Введите номер телефона");
                ind = currentUser.phoneBook.searchContactbyNumber(scanner.nextLine());
                break;
            case(1):
                System.out.println("Введите имя и фамилию через пробел");
                ind = currentUser.phoneBook.searchContactbyName(scanner.nextLine());
                break;
        }
        return ind;
    }
    private boolean editContact(){
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
              break;
            case(1):
                ind = searchContact();
                System.out.println("Введите новую фамилию контакта");
                currentUser.phoneBook.editName(ind, scanner.nextLine());
                break;
            case(2):
                ind = searchContact();
                System.out.println("Введите новый номер телефона контакта");
                currentUser.phoneBook.editName(ind, scanner.nextLine());
                break;
            case(3):
                ind = searchContact();
                System.out.println("Введите новую информацию");
                currentUser.phoneBook.editName(ind, scanner.nextLine());
                break;
        }
        return true;
    }
    private boolean printMenu(){
        mp.printMenu();
        int input = input(-1, 1);
        switch(input){
            case(-1):
                mainMenu();
                break;
            case(0):
                currentUser.phoneBook.printAll();
                break;
        }
        return true;
    }


}
