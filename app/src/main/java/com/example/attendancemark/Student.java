package com.example.attendancemark;

public class Student {
    public String indexNumber;
    public String name;
    public String email;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    public Student(String indexNumber, String name, String email) {
        this.indexNumber = indexNumber;
        this.name = name;
        this.email = email;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}