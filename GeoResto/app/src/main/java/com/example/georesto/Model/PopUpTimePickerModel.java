package com.example.georesto.Model;

import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.georesto.R;

public class PopUpTimePickerModel {
    private TimePicker simpleTimePicker;
    private Button cancel, next;
    private TextView schedule;
    private int openingHour, openingMin, endingHour, endingMin;
    private boolean endingSchedule;

    public PopUpTimePickerModel(View popUp){
        this.simpleTimePicker = popUp.findViewById(R.id.simpleTimePicker);
        this.cancel = popUp.findViewById(R.id.cancelButton);
        this.next = popUp.findViewById(R.id.nextButton);
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
