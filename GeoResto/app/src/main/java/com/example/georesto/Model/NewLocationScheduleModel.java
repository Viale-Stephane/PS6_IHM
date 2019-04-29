package com.example.georesto.Model;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.georesto.R;

public class NewLocationScheduleModel {
    private EditText monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private Button cancel, validate;
    private ToggleButton selectedDay;

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

    public EditText getMonday(){
        return this.monday;
    }

    public EditText getTuesday() { return this.tuesday; }

    public EditText getWednesday() { return this.wednesday; }

    public EditText getThursday() { return this.thursday; }

    public EditText getFriday() { return this.friday; }

    public EditText getSaturday() { return this.saturday; }

    public EditText getSunday() { return  this.sunday; }

    public Button getCancelButton() {
        return this.cancel;
    }

    public Button getValidateButton() {
        return this.validate;
    }

    public EditText[] getDays() {
        EditText[] days = {monday,tuesday,wednesday,thursday,friday,saturday,sunday};
        return days;
    }

    public String[] getSchedule() {
        String[] schedule = {monday.getText().toString(),tuesday.getText().toString(),wednesday.getText().toString(),thursday.getText().toString(),friday.getText().toString(),saturday.getText().toString(),sunday.getText().toString()};
        return schedule;
    }
}
