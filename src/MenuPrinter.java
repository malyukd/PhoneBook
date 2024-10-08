public class MenuPrinter { //печатает интерфейс
    public void chooseAction() {
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|                Choose action :                  |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| 0 - Exit           ( Exit the program )         |\n" +
                        "|-------------------------------------------------|\n" +
                        "| 1 - Sign In        ( Enter go to existing user )|\n" +
                        "|-------------------------------------------------|\n" +
                        "| 2 - Sign Up        ( Register new user )        |\n" +
                        "|-------------------------------------------------|\n" +
                        "| 3 - Print Users    ( Print existing user )      |\n" +
                        "|_________________________________________________|"
        );
    }

    public void mainMenu() {
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|                  Main  menu                     |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| 0 - Save and Exit ( Save and Exit the program)  |\n" +
                        "|-------------------------------------------------|\n" +
                        "| 1 - Contacts      ( Add / Edite / Delete)       |\n" +
                        "|-------------------------------------------------|\n" +
                        "| 2 - Print         ( All / Specific)             |\n" +
                        "|-------------------------------------------------|\n" +
                        "| 3 - Sorting       ( Name / Surname / Number )   |\n" +
                        "|-------------------------------------------------|\n" +
                        "| 4 - Search        ( by Name / Surname / Number )|\n" +
                        "|-------------------------------------------------|\n" +
                        "| 5 - Come Back     ( Save and Exit phonebook )   |\n" +
                        "|_________________________________________________|"
        );
    }
    public void contactMenu() {
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|           Main  menu -> Contact menu            |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the main menu)    |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Add          ( Add new contact )           |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Edit         ( Edit contact )              |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  2 - Delete       ( Delete contact )            |\n" +
                        "|_________________________________________________|"
        );
    }
    public void editContact() {
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|      Main  menu -> Contact menu -> Edit         |\n" +
                        "|          What data do you want to edit ?        |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the Contact menu) |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Name         ( Edit contact name )         |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Surname      ( Edit contact surname )      |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  2 - Number       ( Edit contact number )       |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  3 - All          ( Edit name/surname/number )  |\n" +
                        "|_________________________________________________|"
        );
    }
    public void printMenu() {
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|            Main  menu -> Print menu             |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the main menu )   |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Print        ( All )                       |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Print        ( Specific )                  |\n" +
                        "|_________________________________________________|"
        );
    }
    public void sortingMenu(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|            Main  menu -> Sorting                |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the main menu )   |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Name         ( Sort  A-Z / Z-A )           |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Surname      ( Sort  A-Z / Z-A )           |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  2 - Number       ( Sort  1-9 / 9-1 )           |\n" +
                        "|_________________________________________________|"
        );
    }
    public void sortingName(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|          Main  menu -> Sorting -> Name          |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the Sorting )     |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Name         ( Sort  A - Z )               |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Name         ( Sort  Z - A )               |\n" +
                        "|_________________________________________________|"
        );
    }
    public void sortingNameAZ(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|             You sort Name A to Z                |\n" +
                        "|_________________________________________________|\n"

        );
    }
    public void sortingNameZA(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|             You sort Name Z to A                |\n" +
                        "|_________________________________________________|\n"

        );
    }
    public void sortingSurname(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|         Main  menu -> Sorting -> Surname        |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the Sorting )     |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Surname         ( Sort  A - Z )            |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Surname         ( Sort  Z - A )            |\n" +
                        "|_________________________________________________|"
        );
    }
    public void sortingSurnameAZ(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|            You sort Surname Z to A              |\n" +
                        "|_________________________________________________|\n"

        );
    }
    public void sortingSurnameZA(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|            You sort Surname Z to A              |\n" +
                        "|_________________________________________________|\n"

        );
    }
    public void sortingNumber(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|         Main  menu -> Sorting -> Surname        |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back    ( Return to the Sorting )     |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Number       ( Sort  1 - 9 )               |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Number       ( Sort  9 - 1 )               |\n" +
                        "|_________________________________________________|"
        );
    }
    public void sortingNumber19(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|             You sort Number 1 to 9              |\n" +
                        "|_________________________________________________|\n"

        );
    }
    public void sortingNumber91(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|             You sort Number 9 to 1              |\n" +
                        "|_________________________________________________|\n"

        );
    }
    public void searchMenu(){
        System.out.println(
                " _________________________________________________\n" +
                        "|                                                 |\n" +
                        "|               << Phone book  >>                 |\n" +
                        "|             Main  menu -> Search                |\n" +
                        "|_________________________________________________|\n" +
                        "|                                                 |\n" +
                        "| -1 - Come back  ( Return to the Main menu )     |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  0 - Name       ( Search by Name  )             |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  1 - Surname    ( Search by Surname )           |\n" +
                        "|-------------------------------------------------|\n" +
                        "|  2 - Number     ( Search by  Number )           |\n" +
                        "|_________________________________________________|"
        );
    }

}
