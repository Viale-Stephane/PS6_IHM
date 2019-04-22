package com.example.georesto.Model;

import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.georesto.R;

public class NewLocationScheduleModel {
    EditText monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    Button cancel, validate;

    public NewLocationScheduleModel(NavigationView profileView) {
        View currentHeader = profileView.getHeaderView(0);
        this.monday = currentHeader.findViewById(R.id.monday);
        this.tuesday = currentHeader.findViewById(R.id.tuesday);
        this.wednesday = currentHeader.findViewById(R.id.wednesday);
        this.thursday = currentHeader.findViewById(R.id.thursday);
        this.friday = currentHeader.findViewById(R.id.friday);
        this.saturday = currentHeader.findViewById(R.id.saturday);
        this.sunday = currentHeader.findViewById(R.id.sunday);
        this.cancel = currentHeader.findViewById(R.id.cancelButton);
        this.validate = currentHeader.findViewById(R.id.validateButton);
    }


    public Button getCancelButton() {
        return this.cancel;
    }

    public Button getValidateButton() {
        return this.validate;
    }

    public String[] getSchedule() {
        String[] schedule = {monday.getText().toString(),tuesday.getText().toString(),wednesday.getText().toString(),thursday.getText().toString(),friday.getText().toString(),saturday.getText().toString(),sunday.getText().toString()};
        return schedule;
    }
}
