import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        menuItems m=new menuItems();
        m.Menu();
    }

}
class menuItems extends Menu{
    public void Menu() {
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.println("*************************************************************************************************************************************************************************************************************");
            System.out.println("STUDENTS ATTENDENCE RECORDS ADMIN PANEL");
            System.out.println("*************************************************************************************************************************************************************************************************************");
            System.out.println("PRESS 1 : ADD ATTENDENCE");
            System.out.println("PRESS 2 : FETCH ATTENDENCE DETAILS");
            System.out.println("PRESS 3 : ALL ATTENDENCE");
            System.out.println("PRESS 4 : TOTAL CLASS IDS");
            System.out.println("PRESS 5 : SEARCH STUDENT");
            System.out.println("PRESS 6 : DELETE RECORD BY SERIAL NUMBER");
            System.out.println("PRESS 7 : EXIT");
            System.out.println("*************************************************************************************************************************************************************************************************************");
            System.out.print("ENTER YOUR CHOICE :: ");
            input = sc.nextInt();
            switch (input) {
                case 1: Operations.addAttendence();
                    break;
                case 2: Operations.fetchAttendence();
                    break;
                case 3: Operations.alldispl();
                    break;
                case 4: Operations.totalClasses();
                    break;

                case 5: Operations.search();
                    break;
                case 6:System.out.print("Enter the serial no. to delete record :: ");
                    int id=sc.nextInt();
                    Operations.delete(id);
                    break;
                case 7: System.exit(0);
                default:
                    System.out.print("The entered value is unrecognized!\n");
                    break;
            }
        } while (true);
    }
}
