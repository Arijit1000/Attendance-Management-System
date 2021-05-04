package com.student.attedence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;



public class ConnectionOpratios {
    public static boolean InsertStudentToDB(Attendence st) {
        boolean success=false;
        try {
            //JDBC CODE............
            Connection con=ConnectionEstablish.createRelation();
            //QUERY.....
            String Query="insert into students_attendence(ROLL,stream,name,year,enroll,classid,date,sec) values(?,?,?,?,?,?,?,?)";
            //PREPARED STATEMENT....
            PreparedStatement pst=con.prepareStatement(Query);
            //SET VALUES 1 BY 1
            pst.setString(1, st.getRoll());
            pst.setString(2, st.getStream());
            pst.setString(3, st.getStnm());
            pst.setString(4, st.getYr());
            pst.setString(5, st.getEnroll());
            pst.setString(6, st.getClassId());
            pst.setString(7, st.getDate());
            pst.setString(8, st.getSec());
            //EXECUTE
            pst.executeUpdate();
            autoIdUpdate();
            success=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return success;

    }
    public static void viewAll() {
        try {
            Connection con=ConnectionEstablish.createRelation();
            Statement smt=con.createStatement();
            String Query="select * from students_attendence";
            ResultSet rs=smt.executeQuery(Query);
            System.out.println("*************************************************************************************************************************************************************************************************************");
            System.out.printf("%-10s%-20s%-25s%-35s%-25s%-20s%-15s%-15s%-20s\n","ID","STUDENT ROLLS","STREAM","STUDENT NAME","YEAR","ENROLLMENT NO","CLASS ID","SECTION","DATE & TIME");
            System.out.println("*************************************************************************************************************************************************************************************************************");
            if(rs.next()){
                do{
                    System.out.printf("%-10s%-20s%-25s%-35s%-25s%-20s%-15s%-15s%-20s",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(9),rs.getString(8));
                    System.out.println();
                }while(rs.next());
            }
            else{
                System.out.println("Record Not Found...");
            }
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static void fetchAttendence(String classid) {
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q=String.format("select name,ROLL,sec,stream,year,date from students_attendence where classid=\"%s\" order by sec asc",classid);
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);
            System.out.println("*************************************************************************************************************************************************************************************************************");
            System.out.printf("%-50s%-10s%-15s%-15s%-15s%-25s\n","NAME","ROLL","SECTION","STREAM","YEAR","DATE & TIME");
            System.out.println("*************************************************************************************************************************************************************************************************************");

            if(rs.next()){
                do{
                    System.out.printf("%-50s%-10s%-15s%-15s%-15s%-25s",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                    System.out.println();
                }while(rs.next());
            }
            else{
                System.out.println("Record Not Found...");
            }
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static String[] findStudentForAttendence(String enroll) {
        String  classids[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            Statement smt=con.createStatement();
            Statement smt2=con.createStatement();
            String q1=String.format("select name,year,stream,ROLL,classid,date from students_attendence where enroll=\"%s\" order by classid asc",enroll);
            ResultSet rs=smt.executeQuery(q1);
            System.out.println("*************************************************************************************************************************************************************************************************************");
            System.out.printf("%-30s%-15s%-15s%-15s%-15s%-25s\n","NAME","YEAR","STREAM","ROLL","CLASS_ID","DATE & TIME");
            System.out.println("*************************************************************************************************************************************************************************************************************");
            int i=0;
            if(rs.next()){
                do{
                    System.out.printf("%-30s%-15s%-15s%-15s%-15s%-25s",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                    System.out.println();
                    classids=Arrays.copyOf(classids,classids.length+1);
                    classids[i]=rs.getString(5);
                    i++;
                }while(rs.next());
                con.close();
                return classids;
            }
            else{
                System.out.println("Record Not Found...");
                return classids;
            }


        }
        catch(Exception e) {
            e.printStackTrace();
            return classids;
        }
    }

    public static void delete(int id) {
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Query1=String.format("select name from students_attendence where sno=%d",id);
            Statement smt=con.createStatement();
            ResultSet rs = smt.executeQuery(Query1);
            if(rs.next()) {
                do {
                    System.out.println("STUDENT NAME :: "+rs.getString(1));
                    System.out.println("DELETED SUCCESSFULLY !!!!");
                }while(rs.next());}
            else {
                System.out.println("Record Not Found...");
            }
            String Query="delete from students_attendence where sno=?";
            PreparedStatement pstmt=con.prepareStatement(Query);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            autoIdUpdate();

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static String[] disclassesArray() {
        String  classes[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select distinct classid from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    classes=Arrays.copyOf(classes,classes.length+1);
                    classes[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return classes;
            }
            return classes;


        }catch(Exception e)
        {
            e.printStackTrace();
            return classes;
        }
    }
    public static void autoIdUpdate() {
        try {
            String Q1="SET  @num := 0";
            String Q2="UPDATE students_attendence SET SNO = @num := (@num+1)";
            String Q3="ALTER TABLE students_attendence AUTO_INCREMENT =1";
            Connection con=ConnectionEstablish.createRelation();
            PreparedStatement pst1=con.prepareStatement(Q1);
            PreparedStatement pst2=con.prepareStatement(Q2);
            PreparedStatement pst3=con.prepareStatement(Q3);
            pst1.executeUpdate();
            pst2.executeUpdate();
            pst3.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static String[] enrollsArray() {
        String  stens[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select enroll from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    stens=Arrays.copyOf(stens,stens.length+1);
                    stens[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return stens;
            }
            return stens;


        }catch(Exception e)
        {
            e.printStackTrace();
            return stens;
        }
    }
    public static String[] secArray() {
        String  stsec[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select sec from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    stsec=Arrays.copyOf(stsec,stsec.length+1);
                    stsec[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return stsec;
            }
            return stsec;


        }catch(Exception e)
        {
            e.printStackTrace();
            return stsec;
        }
    }
    public static String[] streamArray() {
        String  streams[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select stream from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    streams=Arrays.copyOf(streams,streams.length+1);
                    streams[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return streams;
            }
            return streams;


        }catch(Exception e)
        {
            e.printStackTrace();
            return streams;
        }
    }
    public static String[] rollsArray() {
        String  rolls[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select ROLL from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    rolls=Arrays.copyOf(rolls,rolls.length+1);
                    rolls[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return rolls;
            }
            return rolls;


        }catch(Exception e)
        {
            e.printStackTrace();
            return rolls;
        }
    }
    public static String[] classesArray() {
        String  classes[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select classid from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    classes=Arrays.copyOf(classes,classes.length+1);
                    classes[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return classes;
            }
            return classes;


        }catch(Exception e)
        {
            e.printStackTrace();
            return classes;
        }
    }
    public static String[] datesArray() {
        String  dates[] = new String[0];
        try {
            Connection con=ConnectionEstablish.createRelation();
            String Q="select date from students_attendence";
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(Q);

            int i=0;
            if(rs.next()) {
                do {
                    dates=Arrays.copyOf(dates,dates.length+1);
                    dates[i]=rs.getString(1);
                    i++;

                }while(rs.next());
                return dates;
            }
            return dates;


        }catch(Exception e)
        {
            e.printStackTrace();
            return dates;
        }
    }
}

