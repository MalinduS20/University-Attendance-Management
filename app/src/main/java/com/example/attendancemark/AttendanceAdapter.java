package com.example.attendancemark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<Student> studentList;

    public AttendanceAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_list_item, parent, false);
        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.studentNameTextView.setText(student.getName());
        holder.presentCheckBox.setChecked(true); // Default to present
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        public TextView studentNameTextView;
        public CheckBox presentCheckBox;

        public AttendanceViewHolder(View view) {
            super(view);
            studentNameTextView = view.findViewById(R.id.studentNameTextView);
            presentCheckBox = view.findViewById(R.id.presentCheckBox);
        }
    }
}