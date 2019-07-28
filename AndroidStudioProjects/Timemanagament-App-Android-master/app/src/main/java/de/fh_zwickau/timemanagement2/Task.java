package de.fh_zwickau.timemanagement2;

import java.io.Serializable;
import java.util.Date;

/**
 * Our Task
 */
public class Task implements Serializable {
    private String text;
    private boolean isDone = false;
    private Date date;
    private Urgency urgency;
    public Task(String text, Date date, Urgency urgency) {
        this.text = text;
        this.date = date;
        this.urgency = urgency;
    }
    public Task(String text){
        this.text = text;
    }
    public Task() {

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public Urgency getUrgency() {
        return urgency;
    }
}
