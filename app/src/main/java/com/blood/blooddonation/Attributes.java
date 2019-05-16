package com.blood.blooddonation;

public class Attributes {
    private String gender, bloodgroup, uid, pid, date, month, year, name, email, visibility;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Attributes(String gender, String bloodgroup, String uid, String pid, String date, String month, String year, String name, String email, String visibility) {
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.uid = uid;
        this.pid = pid;
        this.date = date;
        this.month = month;
        this.year = year;
        this.name = name;
        this.email = email;
        this.visibility = visibility;
    }

    public Attributes() {

    }
}
