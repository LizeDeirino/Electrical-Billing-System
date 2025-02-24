package Electricity;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;


public class MyConnection {

    Connection c;
    static ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
    static Image i3 = ic3.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    public static ImageIcon logo = new ImageIcon(i3);
    private static int meter,total,units;
    private static String name, address, suburb, provence, email, phone, status, month, userType, username, password, location, type, phase, bill, days;

    public MyConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                c = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
            } catch (NullPointerException n) {
                System.out.println("No Connection.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection Failure.");
        }
    }

    public static String getUserType(int meter) {
        loginData(meter);
        return userType;
    }
    public static void setUserType(String userType, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE login SET user=? WHERE meter=?")) {
            s1.setObject(1,userType);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"User type successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setUserType() " + e.getMessage());
        }
    }

    public static String getUsername(int meter) {
        loginData(meter);
        return username;
    }
    public static void setUsername(String username, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET username=? WHERE meter=?")) {
            s1.setObject(1,username);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Username successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setUsername() " + e.getMessage());
        }
    }


    public static String getName(int meter) {
        customerData(meter);
        return name;
    }
    public static void setName(String name, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET name=? WHERE meter=?")) {
            s1.setObject(1,name);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Profile name successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn customerData " + e.getMessage());
        }
    }

    public static int getMeter(String name) {
        meterNumber(name);
        return meter;
    }
    public static int setMeter() {
        Random r = new Random();
        int rm = (r.nextInt() % 1000000);
        int possibly = Math.abs(rm);

        try(var c = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
            var ps = c.prepareStatement("SELECT * FROM login WHERE METER=?")) {
            ps.setInt(1,rm);
            var rs = ps.executeQuery();

            while (rs.next()) {
                int maybe = rs.getInt("meter");
                if (maybe == possibly) {
                    setMeter();
                }
                else meter = possibly;
            }
        } catch (SQLException e) {
            System.out.println("SQLException in setMeter()");
        }
        return meter = possibly;
    }

    public static String getAddress(int meter) {
        customerData(meter);
        return address;
    }
    public static void setAddress(String address, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET address=? WHERE meter=?")) {
            s1.setObject(1,address);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Address successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setAddress() " + e.getMessage());
        }
    }

    public static String getSuburb(int meter) {
        customerData(meter);
        return suburb;
    }
    public static void setSuburb(String suburb, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET suburb=? WHERE meter=?")) {
            s1.setObject(1,suburb);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Suburb successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setSuburb() " + e.getMessage());
        }
    }

    public static String getProvence(int meter) {
        customerData(meter);
        return provence;
    }
    public static void setProvence(String provence, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET provence=? WHERE meter=?")) {
            s1.setObject(1,provence);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Provence successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setSuburb() " + e.getMessage());
        }
    }

    public static String getEmail(int meter) {
        customerData(meter);
        return email;
    }
    public static void setEmail(String email, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET email=? WHERE meter=?")) {
            s1.setObject(1,email);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Email successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setEmail() " + e.getMessage());
        }
    }

    public static String getPhone(int meter) {
        customerData(meter);
        return phone;
    }
    public static void setPhone(String phone, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE customer SET phone=? WHERE meter=?")) {
            s1.setObject(1,phone);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Phone successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setPhone() " + e.getMessage());
        }
    }

    public static String getLocation(int meter) {
        meterData(meter);
        return location;
    }
    public static void setLocation(String location, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE meter SET location=? WHERE meter=?")) {
            s1.setObject(1,location);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Location successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setLocation() " + e.getMessage());
        }
    }

    public static String getType(int meter) {
        meterData(meter);
        return type;
    }
    public static void setType(String type, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE meter SET type=? WHERE meter=?")) {
            s1.setObject(1,type);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Type successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setType() " + e.getMessage());
        }
    }

    public static String getPhase(int meter) {
        meterData(meter);
        return phase;
    }
    public static void setPhase(String phase, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE meter SET phase=? WHERE meter=?")) {
            s1.setObject(1,phase);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Phase successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setPhase() " + e.getMessage());
        }
    }

    public static String getBill(int meter) {
        meterData(meter);
        return bill;
    }
    public static void setBill(String bill, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE meter SET bill=? WHERE meter=?")) {
            s1.setObject(1,bill);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Bill successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setBill() " + e.getMessage());
        }
    }

    public static String getMonth(int meter) {
        accountData(meter);
        return month;
    }
    public static void setMonth(String month, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE account SET month=? WHERE meter=?")) {
            s1.setObject(1,month);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Month successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setMonth() " + e.getMessage());
        }
    }

    public static int getUnits(int meter) {
        int AccountUnits = 0;
        try (var c6 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s6 = c6.prepareStatement("SELECT * FROM account WHERE meter=? AND status=?")) {
            s6.setObject(1,meter);
            s6.setObject(2,"Unpaid");
            ResultSet rs6 = s6.executeQuery();
            while (rs6.next()) {
                AccountUnits += rs6.getInt(total);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn accountData");
        }
        return AccountUnits;
    }
    public static void setUnits(int units, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE account SET units=? WHERE meter=?")) {
            s1.setObject(1,units);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Units successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setUnits() " + e.getMessage());
        }
    }

    public static int getTotal(int meter) {
        int AccountTotal = 0;
        try (var c5 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s5 = c5.prepareStatement("SELECT * FROM account WHERE meter=? AND status=?")) {
            s5.setObject(1,meter);
            s5.setObject(2,"Unpaid");
            ResultSet rs5 = s5.executeQuery();
            while (rs5.next()) {
               AccountTotal += rs5.getInt(total);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn accountData");
        }
        return AccountTotal;
    }
    public static void setTotal(int total, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE account SET total=? WHERE meter=?")) {
            s1.setObject(1,total);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Total successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setTotal() " + e.getMessage());
        }
    }

    public static String getStatus(int meter) {
        accountData(meter);
        return status;
    }
    public static void setStatus(String status, int meter) {
        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("UPDATE account SET status=? WHERE meter=?")) {
            s1.setObject(1,status);
            s1.setObject(2,meter);
            var rs1 = s1.executeQuery();
            if (rs1.next()) {
                JOptionPane.showConfirmDialog(null,"Status successfully updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn setStatus() " + e.getMessage());
        }
    }

    static void meterNumber(String name) {
        try (var c = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s = c.prepareStatement("SELECT * FROM customer WHERE name=?")) {
            s.setObject(1,name);
            var rs = s.executeQuery();
            while (rs.next()) {
                meter = rs.getInt(2);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn customerData");
        }
    }


    static void customerData(int meter) {

        try (var c1 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s1 = c1.prepareStatement("SELECT * FROM customer WHERE meter=?")) {
            s1.setObject(1,meter);
            var rs1 = s1.executeQuery();
            while (rs1.next()) {
                name = rs1.getString(1);
                address = rs1.getString(3);
                suburb = rs1.getString(4);
                provence = rs1.getString(5);
                email = rs1.getString(6);
                phone = rs1.getString(7);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn customerData");
        }
    }
    static void meterData(int meter) {
        try (var c2 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s2 = c2.prepareStatement("SELECT * FROM meter WHERE meter=?")) {
             s2.setObject(1,meter);
             var rs2 = s2.executeQuery();
            while (rs2.next()) {
                location = rs2.getString(2);
                type = rs2.getString(3);
                phase = rs2.getString(4);
                bill = rs2.getString(5);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn meterData");
        }
    }
    static void accountData(int meter) {
        try (var c3 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s3 = c3.prepareStatement("SELECT * FROM account WHERE meter=?")) {
            s3.setObject(1,meter);
            ResultSet rs3 = s3.executeQuery();
            while (rs3.next()) {
                month = rs3.getString(2);
                units = rs3.getInt(3);
                total = rs3.getInt(4);
                status = rs3.getString(5);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn accountData");
        }
    }
    static void loginData(int meter) {
        try (var c4 = DriverManager.getConnection("jdbc:mysql:///ebs","root","Nothing1234#");
             var s4 = c4.prepareStatement("SELECT * FROM login WHERE meter=?")) {
            s4.setObject(1,meter);
            ResultSet rs4 = s4.executeQuery();
            while (rs4.next()) {
                username = rs4.getString(6);
                name = rs4.getString(3);
                userType = rs4.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Conn accountData " + e.getMessage());
        }
    }
}  