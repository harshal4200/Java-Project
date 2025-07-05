package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Doctor {
    private Connection connecton;


    public Doctor(Connection connecton){
        this.connecton = connecton;

    }

    public void viewDoctors(){
        String query = "SELECT * FROM Doctors";

        try {
            PreparedStatement ps = connecton.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+----------------+---------+----------------+");
            System.out.println("| Doctors ID |       Name     |  Gender |  Specialization +");
            System.out.println("+------------+----------------+---------+----------------+");

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String specialization = rs.getString("specialization");
                System.out.printf("|%-12s|%-16s|%-9s|%-16s|\n",id,name,gender,specialization);
                System.out.println("+------------+----------------+--------+---------+");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "Select * From doctors where id = ?";
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
