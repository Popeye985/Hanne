package org.beta.vzw.db;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String CONN_STRING="jdbc:mysql://localhost/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Brussels";
    private static final String INSERT= "INSERT INTO persoon Values (?,?,?,?)";
    public static void main( String[] args ) throws SQLException {
        try(Connection con = DriverManager.getConnection(CONN_STRING, "root", "Vdab")){
            Scanner invoer = new Scanner(System.in);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try(PreparedStatement prep = con.prepareStatement(INSERT)){
                System.out.println("Geef een id: ");
                int id = Integer.parseInt(invoer.nextLine());
                System.out.println("Geef een voornaam: ");
                String voornaam = invoer.nextLine();
                System.out.println("Geef een achternaam");
                String achternaam = invoer.nextLine();
                System.out.println("Geef geboortedatum: ");
                LocalDate geboortedatum = LocalDate.parse(invoer.nextLine(),dtf);
                prep.setInt(1, id);
                prep.setString(2, voornaam);
                prep.setString(3, achternaam);
                prep.setObject(4, geboortedatum);
                prep.executeUpdate();

            }//catch (SQLException e){
                //e.printStackTrace();
            //}

        }
    }
}
