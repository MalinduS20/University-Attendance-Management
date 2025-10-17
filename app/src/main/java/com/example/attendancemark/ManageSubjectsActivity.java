package com.example.attendancemark;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ManageSubjectsActivity extends AppCompatActivity {

    private RecyclerView subjectsRecyclerView;
    private SubjectAdapter subjectAdapter;
    private List<Subject> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subjects);

        subjectsRecyclerView = findViewById(R.id.subjectsRecyclerView);
        subjectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        subjectList = new ArrayList<>();
        // Add dummy data
        subjectList.add(new Subject("Mobile Development"));
        subjectList.add(new Subject("Web Development"));
        subjectList.add(new Subject("Database Systems"));

        subjectAdapter = new SubjectAdapter(subjectList);
        subjectsRecyclerView.setAdapter(subjectAdapter);
    }
}