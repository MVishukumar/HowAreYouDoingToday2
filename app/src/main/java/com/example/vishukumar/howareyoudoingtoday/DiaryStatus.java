package com.example.vishukumar.howareyoudoingtoday;

public class DiaryStatus {

    String date;
    String mood;
    String description;

    public DiaryStatus(String date, String mood, String description) {
        this.date = date;
        this.mood = mood;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getDate() + "," + getMood() + "," + getDescription();
    }
}
