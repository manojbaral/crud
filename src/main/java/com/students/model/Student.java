package com.students.model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String guardianName;
    private String address;
    private String DOB;

    public Student(String firstName, String lastName, String guardianName, String address, String DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianName = guardianName;
        this.address = address;
        this.DOB = DOB;
    }

    public Student(int id, String firstName, String lastName, String guardianName, String address, String DOB) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianName = guardianName;
        this.address = address;
        this.DOB = DOB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
