import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import com.student.attedence.*;
public class Operations {
    public static void addAttendence() {
        Scanner sc=new Scanner(System.in);
        System.out.print("ENTER THE SEC OF THE STUDENT       :: ");
        String sec=sc.nextLine();
        System.out.print("ENTER THE ROLL OF THE STUDENT      :: ");
        String roll=sc.nextLine();
        System.out.print("ENTER THE STREAM OF THE STUDENT    :: ");
        String stream=sc.nextLine();
        System.out.print("ENTER CLASS ID FOR ATTEDENCE       :: ");
        String classId=sc.nextLine();
        System.out.print("ENTER ENROLLMENT NUMBER OF STUDENT :: ");
        String enroll=sc.nextLine();
        System.out.print("ENTER DATE AND TIME OF ATTENDENCE  :: ");
        String date_time=sc.nextLine();

        String temp1=enroll.replaceAll("\\s+","").toLowerCase();
        classId=classId.replaceAll("\\s+","").toLowerCase();
        String temp4=sec.replaceAll("\\s+","").toLowerCase();
        String temp2=roll.replaceAll("\\s+","").toLowerCase();
        String temp3=stream.replaceAll("\\s+","").toLowerCase();
        String temp6=date_time.replaceAll("\\s+","").toLowerCase();

        String[] secs=ConnectionOpratios.secArray();
        String[] rolls=ConnectionOpratios.rollsArray();
        String[] streams=ConnectionOpratios.streamArray();
        String[] classes=ConnectionOpratios.classesArray();
        String[] enrolls=ConnectionOpratios.enrollsArray();
        String[] datetimes=ConnectionOpratios.datesArray();

        int flag=0;
        for(int j=0;j<secs.length;j++) {
            String temp1c=enrolls[j].replaceAll("\\s+","").toLowerCase();
            String temp4c=secs[j].replaceAll("\\s+","").toLowerCase();
            String temp2c=rolls[j].replaceAll("\\s+","").toLowerCase();
            String temp3c=streams[j].replaceAll("\\s+","").toLowerCase();
            String temp5c=classes[j].replaceAll("\\s+","").toLowerCase();
            String temp6c=datetimes[j].replaceAll("\\s+","").toLowerCase();
            if(temp6.equals(temp6c) && temp3.equals(temp3c) && temp4.equals(temp4c) && temp2.equals(temp2c) && classId.equals(temp5c)) {
                if(temp1.equals(temp1c)) {
                    flag=1;
                    break;
                }
                else {
                    System.out.println("Another enrollment number already listed with this same student information");
                    flag=2;
                    break;
                }
            }

        }
        if(flag==0) {
            System.out.print("ENTER NAME OF STUDENT              :: ");
            String name=sc.nextLine();
            System.out.print("ENTER YEAR OF STUDENT              :: ");
            String year=sc.nextLine();
            Attendence at=new Attendence(sec,roll,stream,name,year,enroll,classId,date_time);
            if(ConnectionOpratios.InsertStudentToDB(at)) {
                System.out.println("Attendence Record Added Successfully.....");
            }
            else {
                System.out.println("Something went wrong.....");
            }}
        else if(flag==2) {
            System.out.println("Something went wrong try again.....");
        }
        else {
            System.out.println("This attendence is already listed....");
        }
    }
    public static void fetchAttendence() {
        Scanner sc=new Scanner(System.in);
        System.out.print("ENTER THE CLASS ID TO FETCH ATTENDENCE DETAILS :: ");
        String classID=sc.nextLine();
        classID=classID.replaceAll("\\s+","").toLowerCase();
        ConnectionOpratios.fetchAttendence(classID);
    }
    public static void alldispl() {
        ConnectionOpratios.viewAll();
    }
    public static void totalClasses() {
        String[] classes=ConnectionOpratios.disclassesArray();
        System.out.println("*************************************************************************************************************************************************************************************************************");
        System.out.println("Total "+classes.length+" classes added yet ");
        System.out.println("*************************************************************************************************************************************************************************************************************");
        for(int i=0;i<classes.length;i++) {
            System.out.println((i+1)+"TH CLASS ID IS :: "+classes[i]);
        }
        System.out.println("*************************************************************************************************************************************************************************************************************");
    }
    public static void search() {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the enrollment no. of student :: ");
        String enroll=sc.nextLine();
        String[] classes=ConnectionOpratios.disclassesArray();
        String[] mainClasses=ConnectionOpratios.findStudentForAttendence(enroll);
        System.out.println("*************************************************************************************************************************************************************************************************************");
        int count=0;
        for(int i=0;i<classes.length;i++) {
            for(int j=0;j<mainClasses.length;j++) {
                if(classes[i].equals(mainClasses[j])) {
                    count++;

                }
            }
            System.out.println("CLASS-ID :: "+classes[i].toUpperCase()+" --> ATTENDED => "+count+" TIMES");
            count=0;
        }
        System.out.println("*************************************************************************************************************************************************************************************************************");

    }
    public static void delete(int id) {
        ConnectionOpratios.delete(id);
    }

}
