package com.students.DB;

import com.students.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {

    private DataSource dataSource;

    public DbConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws Exception{
        List<Student> students = new ArrayList<Student>();

        Connection myConnection=null;
        Statement myStatement=null;
        ResultSet myResultSet=null;

        try {
            myConnection=dataSource.getConnection();

            String sql="select * from student order by first_name";

            myStatement=myConnection.createStatement();

            myResultSet=myStatement.executeQuery(sql);

            while (myResultSet.next()){
                int id=myResultSet.getInt("id");
                String firstName=myResultSet.getString("first_name");
                String LastName=myResultSet.getString("last_name");
                String guardianName=myResultSet.getString("guardian_name");
                String address=myResultSet.getString("address");
                String DOB=myResultSet.getString("DOB");

                Student student=new Student(id,firstName,LastName,guardianName,address,DOB);

                students.add(student);
            }

            return students;
        }
        finally {
            close(myConnection,myStatement,myResultSet);
        }
    }

    private void close(Connection myConnection, Statement myStatement,ResultSet myResultSet){
        try {
            if (myResultSet !=null){
                myResultSet.close();
            }

            if (myStatement !=null){
                myStatement.close();
            }

            if (myConnection !=null){
                myConnection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Add student
    public void addStudent(Student addStudent) throws Exception{
        Connection myConnection=null;
        PreparedStatement myStatement=null;

        try {
            myConnection=dataSource.getConnection();

            String sql="insert into student "
                + "(firs_name, last_name,guardian_name,address,DOB) "
                + "values (?,?,?,?,?) ";

            myStatement=myConnection.prepareStatement(sql);

            myStatement.setString(1,addStudent.getFirstName());
            myStatement.setString(2,addStudent.getLastName());
            myStatement.setString(3,addStudent.getGuardianName());
            myStatement.setString(4,addStudent.getAddress());
            myStatement.setString(5,addStudent.getDOB());

            myStatement.execute();

        }

        finally {
            close(myConnection,myStatement,null);
        }

    }

    //Get Student by ID
    public Student getStudent(String theStudentById) throws Exception{

        Student theStudent=null;
        Connection myConnection=null;
        PreparedStatement myStatement=null;
        ResultSet myResultSet=null;
        int studentId;

        try {
            studentId = Integer.parseInt(theStudentById);

            myConnection = dataSource.getConnection();

            String sql = "select * from student where id=?";

            myStatement = myConnection.prepareStatement(sql);

            myStatement.setInt(1, studentId);

            myResultSet = myStatement.executeQuery();

            if (myResultSet.next()) {
                String firstName = myResultSet.getString("first_name");
                String LastName = myResultSet.getString("last_name");
                String guardianName = myResultSet.getString("guardian_name");
                String address = myResultSet.getString("address");
                String DOB = myResultSet.getString("DOB");

                theStudent = new Student(studentId, firstName, LastName, guardianName, address, DOB);
            } else {
                throw new Exception("Could not find the student by ID: " + studentId);
            }

            return theStudent;
        } finally {
            close(myConnection,myStatement,myResultSet);
        }
    }

    //Update student
    public void updateStudent(Student updateStudent) throws Exception{
        Connection myConnection=null;
        PreparedStatement myStatement=null;

        try {
            myConnection=dataSource.getConnection();

            String sql="update student "
                + " set firs_name=?, last_name=?,guardian_name=?,address=?,DOB=? "
                + "where id=?";

            myStatement=myConnection.prepareStatement(sql);

            myStatement.setString(1,updateStudent.getFirstName());
            myStatement.setString(2,updateStudent.getLastName());
            myStatement.setString(3,updateStudent.getGuardianName());
            myStatement.setString(4,updateStudent.getAddress());
            myStatement.setString(5,updateStudent.getDOB());
            myStatement.setInt(6,updateStudent.getId());
            myStatement.execute();

        }

        finally {
            close(myConnection,myStatement,null);
        }
    }

    //delete student
    public void deleteStudent(String deleteStudentById) throws Exception{

        Connection myConnection=null;
        PreparedStatement myStatement=null;


        try {
           int studentId = Integer.parseInt(deleteStudentById);

            myConnection = dataSource.getConnection();

            String sql = "delete * from student where id=?";

            myStatement = myConnection.prepareStatement(sql);

            myStatement.setInt(1, studentId);

           myStatement.execute();
        } finally {
            close(myConnection,myStatement,null);
        }
    }
}
