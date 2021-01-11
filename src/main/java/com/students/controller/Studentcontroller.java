package com.students.controller;

import com.students.DB.DbConnection;
import com.students.model.Student;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/StudentController")
public class Studentcontroller extends HttpServlet {
    private static final long serialVersionUID = 441530355524737613L;

    private DbConnection dbConnection;

    @Resource(name = "jdbc/student_crud")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            dbConnection=new DbConnection(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String execution=request.getParameter("execution");

            if (execution ==null){
                execution="LIST";
            }

            //Route to the method based on the execution
            switch (execution){
                case "LIST":
                listStudents(request,response);
                break;

                case "ADD":
                    addStudent(request,response);
                    break;
                case "LOAD":
                    loadStudent(request, response);
                    break;

                case "UPDATE":
                    updateStudent(request, response);
                    break;

                case "DELETE":
                    deleteStudent(request, response);
                    break;

                default:
                    listStudents(request, response);
            }

        }
        catch (Exception e) {
            throw new ServletException(e);
        }

    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId=request.getParameter("studentId");
        Student loadStudent=dbConnection.getStudent(studentId);

        request.setAttribute("THE_STUDENT", loadStudent);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
            request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String guardianName=request.getParameter("guardianName");
        String address=request.getParameter("address");
        String DOB=request.getParameter("DOB");

        Student student=new Student(firstName,lastName,guardianName,address,DOB);

        dbConnection.addStudent(student);
        listStudents(request,response);

    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> students = dbConnection.getStudents();

        // add students to the request
        request.setAttribute("STUDENT_LIST", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {


        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String guardianName=request.getParameter("guardianName");
        String address=request.getParameter("address");
        String DOB=request.getParameter("DOB");

        Student student=new Student(id,firstName,lastName,guardianName,address,DOB);
        dbConnection.updateStudent(student);
        listStudents(request,response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //Get student by id
        String studentId=request.getParameter("studentId");

        //Delete student by id
        dbConnection.deleteStudent(studentId);

        listStudents(request,response);
    }
}
