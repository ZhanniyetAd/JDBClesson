package bit.zhanniyet;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DBManager dbManager = new DBManager();
        dbManager.connect();

        while(true){
            System.out.println("Press [1] to List Students");
            System.out.println("Press [2] to Add Student");
            System.out.println("Press [3] to Edit Student");
            System.out.println("Press [4] to Delete Student");
            System.out.println("Press [0] to Exit");

            int choice = in.nextInt();

            if (choice == 1){
                ArrayList<Students> students = dbManager.getAllStudents();
                for(Students s : students){
                    System.out.println(s);
                }
            } else if (choice == 2){
                System.out.println("Insert name");
                String name = in.next();
                System.out.println("Insert surname");
                String surname = in.next();

                Students student = new Students(null, name, surname);
                dbManager.addStudent(student);

            } else if (choice == 3){
                System.out.println("Insert id to edit student");
                Long id = in.nextLong();
                System.out.println("Insert name");
                String name = in.next();
                System.out.println("Insert surname");
                String surname = in.next();

                Students student = new Students(id, name, surname);
                dbManager.editStudent(student);


            } else if (choice == 4){
                System.out.println("Insert id to delete student");
                Long id = in.nextLong();

                dbManager.deleteStudent(id);

            } else if (choice == 0){
                break;
            } else
                System.out.println("Try again!");
        }

    }
}
