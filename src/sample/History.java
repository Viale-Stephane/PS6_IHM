package sample;

import java.util.ArrayList;
import java.util.Date;

public class History {
    Restaurant history;
    Date date;

    public History(Restaurant history, Date date){
        this.history=history;
        this.date=date;
    }

    public Restaurant getHistory(){
        return this.history;
    }

    public Date getDate(){
        return this.date;
    }

}
