import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class CommaSeparatedValuesReader {

    public static void main(String[] args) {

        String localUrl = "jdbc:mysql://localhost/3306/pa2";

        String courseinfoPath = "/Users/jerryren/eclipse-workspace/course_info.csv";
        String studentcourselistPath = "/Users/jerryren/eclipse-workspace/student_course_list.csv";
        String studentinfoPath= "\"/Users/jerryren/eclipse-workspace/student_info.csv";

        Connection sqlConnection = null;

        try {
            sqlConnection = DriverManager.getConnection(localUrl, "root", "password");
            String sqlInsertionString1 = "INSERT INTO course_info (course_id, professor_id, name, credits)"
                    + "VALUES (?, ?, ?, ?)";
            String sqlInsertionString2 = "INSERT INTO student_course_list (student_id, course_id, final_grade)"
                    + "VALUES (?, ?, ?)";
            String sqlInsertionString3 = "INSERT INTO student_info (student_id, first_name, last_name, address, zip_code, primary_phone_number )"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pStatement1 = sqlConnection.prepareStatement(sqlInsertionString1);
            PreparedStatement pStatement2 = sqlConnection.prepareStatement(sqlInsertionString2);
            PreparedStatement pStatement3 = sqlConnection.prepareStatement(sqlInsertionString3);

            String lineObject1 = new String("");
            String lineObject2 = new String("");
            String lineObject3 = new String("");

            BufferedReader courseinfoReader = new BufferedReader(new FileReader(courseinfoPath));
            BufferedReader studentcourselistReader = new BufferedReader(new FileReader(studentcourselistPath));
            BufferedReader studentinfoReader = new BufferedReader(new FileReader(studentinfoPath));
        }

        catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        } catch (FileNotFoundException notfoundExc) {
            notfoundExc.printStackTrace();
        } catch (IOException ioExc) {
            ioExc.printStackTrace();
        }
    }
}     
