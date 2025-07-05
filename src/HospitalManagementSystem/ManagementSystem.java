package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ManagementSystem {

    private static final String url="jdbc:mysql://localhost:3306/hospital";
    private static final String username="root";
    private static final String password="Harshal12!@";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);

            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println();
                System.out.println("Enter your choice: ");

                int choice=scanner.nextInt();
                switch (choice){
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatient();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        bookAppintment(patient,doctor,connection,scanner);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void bookAppintment(Patient patient,Doctor doctor,Connection connection,Scanner scanner){
        System.out.print("Enter Patient id");
        int patientid=scanner.nextInt();
        System.out.print("Enter Doctor id");
        int doctorid=scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appintmentdate=scanner.next();

        if(patient.getPatientById(patientid) && doctor.getDoctorById(doctorid)){
            if(checkDoctorAvalilablity(doctorid,appintmentdate,connection)){
                String appontmentQuery = "INSERT INTO appointments(patients_id,doctors_id,appointments_date)VALUES(?,?,?)";
                try{
                    PreparedStatement ps = connection.prepareStatement(appontmentQuery);
                    ps.setInt(1,patientid);
                    ps.setInt(2,doctorid);
                    ps.setString(3,appintmentdate);

                    int rowAffected = ps.executeUpdate();
                    if(rowAffected > 0){
                        System.out.println("Appintment Booked");
                    }else {
                        System.out.println("Booking Failed");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                System.out.println("Doctor not available on this date.");
            }
        }else {
            System.out.println("One of them are not exist.");
        }
    }
    public static boolean checkDoctorAvalilablity(int doctorid,String appointmentDate,Connection connection){
        String query = "SELECT COUNT(*) FROM appontments WHERE doctor_id=? AND appointment_date=?";
        try {
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,doctorid);
            ps.setString(2,appointmentDate);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count == 0){
                    return true;
                }else {
                    return false;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
