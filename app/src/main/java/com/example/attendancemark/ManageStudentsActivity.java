package com.example.attendancemark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageStudentsActivity extends AppCompatActivity {

    private RecyclerView studentsRecyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private DatabaseReference databaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);

        databaseStudents = FirebaseDatabase.getInstance().getReference("students");

        studentsRecyclerView = findViewById(R.id.studentsRecyclerView);
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        studentsRecyclerView.setAdapter(studentAdapter);

        FloatingActionButton addStudentFab = findViewById(R.id.addStudentFab);
        addStudentFab.setOnClickListener(v -> showAddStudentDialog());
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Student student = postSnapshot.getValue(Student.class);
                    studentList.add(student);
                }
                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ManageStudentsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddStudentDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_student, null);
        dialogBuilder.setView(dialogView);

        final EditText studentIndexEditText = dialogView.findViewById(R.id.studentIndexEditText);
        final EditText studentNameEditText = dialogView.findViewById(R.id.studentNameEditText);
        final EditText studentEmailEditText = dialogView.findViewById(R.id.studentEmailEditText);

        dialogBuilder.setTitle("Add Student");
        dialogBuilder.setPositiveButton("Add", (dialog, which) -> {
            String index = studentIndexEditText.getText().toString().trim();
            String name = studentNameEditText.getText().toString().trim();
            String email = studentEmailEditText.getText().toString().trim();

            if (!index.isEmpty() && !name.isEmpty() && !email.isEmpty()) {
                String id = databaseStudents.push().getKey();
                Student student = new Student(index, name, email);
                if (id != null) {
                    databaseStudents.child(id).setValue(student);
                    Toast.makeText(getApplicationContext(), "Student added", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}