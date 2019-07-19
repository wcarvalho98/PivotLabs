package com.ufrpe.br.pivotlabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import com.ufrpe.br.pivotlabs.beans.Doctor;
import com.ufrpe.br.pivotlabs.beans.Schedule;
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailAdapter;
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP;
import com.ufrpe.br.pivotlabs.professional_select.model.ProfessionalSelectModel;
import com.ufrpe.br.pivotlabs.professional_select.presenter.ProfessionalSelectPresenter;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    ProfessionalSelectMVP.PresenterImpl presenter = new ProfessionalSelectPresenter();

    ProfessionalSelectModel professionalSelectModel = null;
    Doctor doctor;
    ArrayList<Doctor> doctors = new ArrayList<>();
    private DatabaseReference doctorsReference;
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private DatabaseReference scheduleReference;
    RecyclerView recycleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recycleListView = findViewById(R.id.rc_doc_schedules);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycleListView.setLayoutManager(linearLayoutManager);

        getDataFirebase();


    }

    private void getDataFirebase() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            connectFirebaseDoctorDatabase(b);
            connectFirebaseScheduleDatabase();
        }
    }


    private void connectFirebaseDoctorDatabase(Bundle b) {
        final String p_name = b.getString("professional_name");
        doctorsReference = FirebaseDatabase.getInstance().getReference("doctors");
        doctorsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                getDoctor(dataSnapshot, p_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void mark(final int position) {

        doctorsReference = FirebaseDatabase.getInstance().getReference("doctors");
        doctorsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Doctor d_temp = (Doctor) childSnapshot.getValue(Doctor.class);
                    if (doctor.getName().equals(d_temp.getName())) {
                        childSnapshot.getRef().child("schedule").child("schedule" + position).setValue(true);
                        Toast.makeText(ScheduleActivity.this, "Marcado com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDoctor(DataSnapshot dataSnapshot, String p_name) {
        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
            Doctor d_temp = (Doctor) childSnapshot.getValue(Doctor.class);
            if (p_name.equals(d_temp.getName()))
                doctor = ((Doctor) childSnapshot.getValue(Doctor.class));
        }
    }

    private void connectFirebaseScheduleDatabase() {
        scheduleReference = FirebaseDatabase.getInstance().getReference("schedule");
        scheduleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    schedules.add(((Schedule) childSnapshot.getValue(Schedule.class)));
                }
                createList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void createList() {

        recycleListView.setAdapter(new ProfessionalDetailAdapter(filterSchedules(schedules), this));
    }

    private ArrayList<Schedule> filterSchedules(ArrayList<Schedule> schedules) {

        ArrayList<Schedule> schedulesTemp = new ArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            if (!doctor.getSchedule().get("schedule" + (i + 1)))
                schedulesTemp.add(schedules.get(i));
        }
        return schedulesTemp;
    }


}
