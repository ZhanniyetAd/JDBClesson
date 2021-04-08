package bit.zhanniyet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {

    private Connection connection;

    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsbd?useUnicode=true&serverTimezone=UTC", "root", "");

            System.out.println("CONNECTED!");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Students> getAllStudents(){
        ArrayList<Students> students = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                students.add(new Students(id, name, surname));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    public void addStudent(Students student){
        try {
            PreparedStatement statement = connection.prepareStatement("Insert into students (name, surname) values (?,?)");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.executeUpdate();

            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editStudent(Students student){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE students set name = ?, surname = ? where id = ?");
            statement.setLong(3, student.getId());
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.executeUpdate();

            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("Delete from students where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();

            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
