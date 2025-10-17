package com.example.attendancemark;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendanceActivity extends AppCompatActivity {

    private Spinner subjectSpinner;
    private RecyclerView attendanceRecyclerView;
    private AttendanceAdapter attendanceAdapter;
    private List<Student> studentList;
    private Button saveAttendanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        subjectSpinner = findViewById(R.id.subjectSpinner);
        attendanceRecyclerView = findViewById(R.id.attendanceRecyclerView);
        saveAttendanceButton = findViewById(R.id.saveAttendanceButton);

        // Dummy data for subjects
        List<String> subjects = new ArrayList<>();
        subjects.add("Mobile Development");
        subjects.add("Web Development");
        subjects.add("Database Systems");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        // Dummy data for students
        studentList.add(new Student("S001", "John Doe", "john.doe@example.com"));
        studentList.add(new Student("S002", "Jane Smith", "jane.smith@example.com"));

        attendanceAdapter = new AttendanceAdapter(studentList);
        attendanceRecyclerView.setAdapter(attendanceAdapter);

        saveAttendanceButton.setOnClickListener(v -> {
            Toast.makeText(this, "Attendance Saved Successfully", Toast.LENGTH_SHORT).show();
        });
    }
}