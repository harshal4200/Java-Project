package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient {
    private Connection connecton;
    private Scanner scanner;

   public Patient(Connection connecton, Scanner scanner){
       this.connecton = connecton;
       this.scanner = scanner;
   }

   public void addPatient(){
       System.out.println("Enter Patient Name: ");
       String name = scanner.nextLine();
       System.out.println("Enter Patient Age: ");
       int age = scanner.nextInt();
       System.out.println("Enter Patient Gender: ");
       String gender = scanner.nextLine();

       try{

           String query="INSERT INTO patients(name,age,gender)values(?,?,?)";
           PreparedStatement ps = connecton.prepareStatement(query);
           ps.setString(1,name);
           ps.setInt(2,age);
           ps.setString(3,gender);

           int affectedrow = ps.executeUpdate();
           if(affectedrow > 0){
               System.out.println("Patient added successfully👍");
           }else {
               System.out.println("Patient not added👎");
           }


       }catch (Exception e){
           e.printStackTrace();
       }

   }

   public void viewPatient(){
       String query = "SELECT * FROM patients";

       try {
           PreparedStatement ps = connecton.prepareStatement(query);
           ResultSet rs = ps.executeQuery();
           System.out.println("Patient: ");
           System.out.println("+------------+----------------+--------+---------+");
           System.out.println("| Patient ID |       Name     |   Age  |  Gender |");
           System.out.println("+------------+----------------+--------+---------+");

           while (rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int age = rs.getInt("age");
               String gender = rs.getString("gender");
               System.out.printf("|%-12s|%-16s|%-8s|%-9s|\n",id,name,age,gender);
               System.out.println("+------------+----------------+--------+---------+");

           }

       }catch (Exception e){
           e.printStackTrace();
       }
   }

   public boolean getPatientById(int id){
       String query = "Select * From patients where id = ?";
       try {
           PreparedStatement ps = connecton.prepareStatement(query);
           ps.setInt(1,id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               return true;
           }else {
               return false;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return false;
   }


}
