package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountAdd extends JPanel implements ActionListener {

    private final JLabel l3,l5,l7;
    private final JTextField t1;
    private final Choice c1,c2;
    static JButton bBillAdd,bBillCancel;
    private String month,status;
    private int meter,units,total;

    AccountAdd() {
        setBackground(MyColor.DARKEST_GREY);
        setSize(800,300);
        setLocation(300,170);
        setLayout(new BorderLayout());


        /* ---- COMPONENTS--- */
        JLabel title = new JLabel("Add a Bill");
        title.setBounds(50,35,200, 40);
        title.setForeground(MyColor.BLUE_LIGHT);
        title.setFont(MyFonts.h2);


        JLabel t2 = new JLabel("* Please enter Billable Units for the Month");
        t2.setBounds(50,75,300, 10);
        t2.setForeground(MyColor.ANOTHER_GREY);
        t2.setFont(MyFonts.f1i);


        JLabel info = new JLabel();
        info.setBounds(0, 120, 350, 200);



        JLabel l1 = new JLabel("Meter No");
        l1.setFont(MyFonts.h6);
        l1.setForeground(MyColor.BLUE_LIGHT);
        l1.setBounds(50, 0, 100, 20);
        info.add(l1);

        c1 = new Choice();
        c1.setFont(MyFonts.hGen);
        c1.setBounds(150, 0, 150, 20);
        MyConnection conn1 = new MyConnection();
        try (PreparedStatement s = conn1.c.prepareStatement("SELECT * FROM customer")) {
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                c1.add(rs.getString("meter"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in BillAdd - Choice - meter");
        }
        info.add(c1);
        c1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String sql2 = "SELECT * FROM customer WHERE meter = ?";
                MyConnection conn2 = new MyConnection();
                try (var s = conn2.c.prepareStatement(sql2)) {
                    s.setObject(1, c1.getSelectedItem());
                    var rs = s.executeQuery();
                    while (rs.next()) {
                        l3.setText(rs.getString("name"));
                        l5.setText(rs.getString("address"));
                        l7.setText(rs.getString("suburb"));
                    }
                } catch (SQLException e) {
                    System.out.println("SQL Exception: BillAdd looking up customer.meter");
                }
            }
        });


        JLabel l2 = new JLabel("Name");
        l2.setFont(MyFonts.h6);
        l2.setForeground(MyColor.ANOTHER_GREY);
        l2.setBounds(50, 30, 100, 20);
        info.add(l2);

        l3 = new JLabel();
        l3.setFont(MyFonts.h6);
        l3.setForeground(MyColor.ANOTHER_GREY);
        l3.setBounds(150, 30, 150, 20);

        JLabel l4 = new JLabel("Address");
        l4.setFont(MyFonts.h6);
        l4.setForeground(MyColor.ANOTHER_GREY);
        l4.setBounds(50, 60, 100, 20);
        info.add(l4);

        l5 = new JLabel();
        l5.setFont(MyFonts.h6);
        l5.setForeground(MyColor.ANOTHER_GREY);
        l5.setBounds(150, 60, 180, 20);

        JLabel l6 = new JLabel("Suburb");
        l6.setFont(MyFonts.h6);
        l6.setForeground(MyColor.ANOTHER_GREY);
        l6.setBounds(50, 90, 100, 20);
        info.add(l6);

        l7 = new JLabel();
        l7.setFont(MyFonts.h6);
        l7.setForeground(MyColor.ANOTHER_GREY);
        l7.setBounds(150, 90, 150, 20);

        String sql3 = "SELECT * FROM customer WHERE meter = ?";
        MyConnection conn3 = new MyConnection();
        try (PreparedStatement s = conn3.c.prepareStatement(sql3)) {
            s.setObject(1, c1.getSelectedItem());
            ResultSet rs = s.executeQuery();

            while(rs.next()) {
                l3.setText(rs.getString("name"));
                l5.setText(rs.getString("address"));
                l7.setText(rs.getString("suburb"));
            }
        } catch (SQLException e){
            System.out.println("SQL Exception: BillAdd Setting fields");
        }
        info.add(l3);
        info.add(l5);
        info.add(l7);

        JLabel l8 = new JLabel("Billable Units");
        l8.setFont(MyFonts.h6);
        l8.setForeground(MyColor.ANOTHER_GREY);
        l8.setBounds(50, 120, 100, 20);
        info.add(l8);

        t1 = new JTextField();
        t1.setBounds(150, 120, 150, 20);
        t1.setBackground(MyColor.LIGHT_GREY);
        info.add(t1);

        JLabel l9 = new JLabel("Month");
        l9.setFont(MyFonts.h6);
        l9.setForeground(MyColor.ANOTHER_GREY);
        l9.setBounds(50, 150, 100, 20);
        info.add(l9);

        c2 = new Choice();
        c2.setFont(MyFonts.hGen);
        c2.setBounds(150, 150, 150, 20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");
        info.add(c2);


        /* --- BUTTONS --- */
        bBillAdd = new MyGradientButton("Add", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillAdd.setBounds(50, 320, 125, 40);
        bBillAdd.addActionListener(this);
        add(bBillAdd);

        bBillCancel = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillCancel.setBounds(185, 320, 125, 40);
        bBillCancel.addActionListener(this);
        add(bBillCancel);

        add(title);
        add(t2);
        add(info);
        setLayout(new BorderLayout());
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bBillAdd) {
            meter = Integer.parseInt(c1.getSelectedItem());
            units = Integer.parseInt(t1.getText());
            total = units*10;
            month = c2.getSelectedItem();
            status = "Not Paid";
            String addBill = "INSERT INTO account VALUES(?,?,?,?,?)";
            MyConnection conn4 = new MyConnection();
            try (var s = conn4.c.prepareStatement(addBill)) {
                s.setObject(1,meter);
                s.setObject(2,month);
                s.setInt(3,units);
                s.setInt(4,total);
                s.setObject(5,status);
                var updated = s.executeUpdate();
                if (updated != 0) {
                    JOptionPane.showMessageDialog(null, month + " Bill added");
                    AccountsReport.bBillReportRefresh.doClick();
                    Dashboard.jAccountReport.setVisible(true);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: BillAdd - adding...");
            }
        }
        if (ae.getSource() == bBillCancel) {
            Dashboard.jAccountAdd.setVisible(false);
        }        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AccountAdd::new);
    }
}
