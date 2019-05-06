package com.example.georesto.Model;

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
        return "de "+this.openingHour+":"+this.openingMin+" à "+this.endingHour+":"+this.endingMin;
    }
}
