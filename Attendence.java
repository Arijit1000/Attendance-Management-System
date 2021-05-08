package com.student.attedence;

public class Attendence {
    private int sno;
    private String sec;
    private String roll;
    private String stream;
    private String stnm;
    private String yr;
    private String enroll;
    private String classId;
    private String date;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getYr() {
        return yr;
    }

    public void setYr(String yr) {
        this.yr = yr;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Attendence(int sno, String sec, String roll, String stream, String stnm, String yr, String enroll, String classId, String date) {
        super();
        this.sno = sno;
        this.sec = sec;
        this.roll = roll;
        this.stream = stream;
        this.stnm = stnm;
        this.yr = yr;
        this.enroll = enroll;
        this.classId = classId;
        this.date = date;
    }

    public Attendence(String sec, String roll, String stream, String stnm, String yr, String enroll, String classId, String date) {
        super();
        this.sec = sec;
        this.roll = roll;
        this.stream = stream;
        this.stnm = stnm;
        this.yr = yr;
        this.enroll = enroll;
        this.classId = classId;
        this.date = date;
    }

    public Attendence() {
    super();
    }

    @Override
    public String toString() {
        return "Attendence{" +
                "sno=" + sno +
                ", sec='" + sec + '\'' +
                ", roll='" + roll + '\'' +
                ", stream='" + stream + '\'' +
                ", stnm='" + stnm + '\'' +
                ", yr='" + yr + '\'' +
                ", enroll='" + enroll + '\'' +
                ", classId='" + classId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
