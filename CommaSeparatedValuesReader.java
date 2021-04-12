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

            while ((lineObject1 = courseinfoReader.readLine()) != null) {
                String[] dataArr = lineObject1.split(",");
                String courseID = dataArr[0];
                String professorID = dataArr[1];
                String name = dataArr[2];
                String credits = dataArr[3];

                pStatement1.setString(1, courseID);
                pStatement1.setString(2, professorID);
                pStatement1.setString(3, name);
                pStatement1.setString(4, credits);
            }

            while ((lineObject2 = studentcourselistReader.readLine()) != null) {
                String[] dataArr = lineObject2.split(",");
                String studentID = dataArr[0];
                String courseID = dataArr[1];
                String finalGrade = dataArr[2];

                pStatement2.setString(1, studentID);
                pStatement2.setString(2, courseID);
                pStatement2.setString(3, finalGrade);
            }

            while ((lineObject3 = studentinfoReader.readLine()) != null) {
                String[] dataArr = lineObject1.split(",");
                String studentID = dataArr[0];
                String firstName = dataArr[1];
                String lastName = dataArr[2];
                String address = dataArr[3];
                String zipCode = dataArr[4];
                String phoneNum = dataArr[5];

                pStatement3.setString(1, studentID);
                pStatement3.setString(2, firstName);
                pStatement3.setString(3, lastName);
                pStatement3.setString(4, address);
                pStatement3.setString(5, zipCode);
                pStatement3.setString(6, phoneNum);
            }

            pStatement1.execute();
            pStatement2.execute();
            pStatement3.execute();

            sqlConnection.commit();
            sqlConnection.close();
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
