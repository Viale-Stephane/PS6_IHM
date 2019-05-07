package com.example.georesto.Model;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.georesto.R;

public class PopUpTimePickerModel {
    private TimePicker simpleTimePicker;
    private Button cancel, next;

    public ToggleButton getDayButton() {
        return dayButton;
    }

    public ToggleButton getFullWeekButton() {
        return fullWeekButton;
    }

    public ToggleButton getWeekButton() {
        return weekButton;
    }

    private ToggleButton dayButton, fullWeekButton, weekButton;
    private TextView schedule;
    private int openingHour, openingMin, endingHour, endingMin;
    private boolean endingSchedule;

    public PopUpTimePickerModel(View popUp){
        this.simpleTimePicker = popUp.findViewById(R.id.simpleTimePicker);
        this.cancel = popUp.findViewById(R.id.cancelButton);
        this.next = popUp.findViewById(R.id.nextButton);
        this.dayButton = popUp.findViewById(R.id.dayButton);
        this.fullWeekButton = popUp.findViewById(R.id.fullWeekButton);
        this.weekButton = popUp.findViewById(R.id.weekButton);
        this.simpleTimePicker.setIs24HourView(true);
        this.schedule = popUp.findViewById(R.id.schedule);
        this.openingHour = -1;
        this.openingMin = -1;
        this.endingHour = -1;
        this.endingMin = -1;
        this.endingSchedule = false;
    }

    public TimePicker getSimpleTimePicker() {
        return this.simpleTimePicker;
    }

    public Button getNext(){
        return this.next;
    }

    public Button getCancel(){
        return this.cancel;
    }


    public void clickOnDayButton() {
        this.dayButton.setOnClickListener(v -> {
            this.dayButton.setChecked(true);
            this.fullWeekButton.setChecked(false);
            this.weekButton.setChecked(false);
        });
    }

    public void clickOnFullWeekButton() {
        this.fullWeekButton.setOnClickListener(v -> {
            this.dayButton.setChecked(false);
            this.fullWeekButton.setChecked(true);
            this.weekButton.setChecked(false);
        });
    }

    public void clickOnWeekButton() {
        this.weekButton.setOnClickListener(v -> {
            this.dayButton.setChecked(false);
            this.fullWeekButton.setChecked(false);
            this.weekButton.setChecked(true);
        });
    }

    public String whichToggleButtonIsChecked() {
        if(this.dayButton.isChecked())
            return "day";
        else if (this.fullWeekButton.isChecked())
            return "fullWeek";
        else
            return "week";
    }


    public void takeTime() {
        if(this.openingHour == -1 || this.openingMin == -1 || this.endingSchedule == false) {
            this.openingHour = this.simpleTimePicker.getHour();
            this.openingMin = this.simpleTimePicker.getMinute();
            this.endingSchedule = true;
        }
        this.endingHour = this.simpleTimePicker.getHour();
        this.endingMin = this.simpleTimePicker.getMinute();
    }

    public boolean isEndingSchedule(){
        return this.endingSchedule;
    }

    public void cancelEndingSchedule() {
        this.endingSchedule = false;
    }

    public void setSchedule(String text) {
        this.schedule.setText(text);
    }

    public String schedulize() {
        String openingMin, openingHour, endingMin, endingHour;
        if(this.openingMin<10){
            openingMin = "0"+this.openingMin;
        } else {
            openingMin = Integer.toString(this.openingMin);
        }

        if (this.openingHour<10) {
            openingHour = "0"+this.openingHour;
        } else {
            openingHour = Integer.toString(this.openingHour);
        }

        if (this.endingHour<10) {
            endingHour = "0"+this.endingHour;
        } else {
            endingHour = Integer.toString(this.endingHour);
        }

        if (this.endingMin<10) {
            endingMin = "0"+this.endingMin;
        } else {
            endingMin = Integer.toString(this.endingMin);
        }

        return "de "+openingHour+":"+openingMin+" à "+endingHour+":"+endingMin;
    }
}
