package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Reports extends JFrame implements ActionListener {
    private final MyTable t1;
    private final MyTable t2;
    private final MyTable t3;
    private final JButton bClose;
    private final JButton bPrntT1;
    private final JButton bPrntT2;
    private final JButton bPrntT3;
    Reports() {


        /* ---- FRAME LABEL & ICON---- */
        super("PEB || Report Portal");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo.png")));


        /* --- BACKGROUND --- */
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("img/reports.png"));
        Image i3 = ic.getImage().getScaledInstance(735, 717, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel lBackground = new JLabel(icc3);
        lBackground.setSize(735, 717);

        setUndecorated(true);
        setBackground(MyColor.TRANSPARENT);
        setSize(735, 717);
        add(lBackground);


        /* --- BUTTONS --- */
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("btn/report.png"));
        Image i2 = ic2.getImage().getScaledInstance(21, 21, Image.SCALE_DEFAULT);
        ImageIcon icc2 = new ImageIcon(i2);
        bClose = new MyButton(icc2);
        bClose.setBounds(640, 10,21,21);
        bClose.setBackground(MyColor.LIGHT_GREY);
        lBackground.add(bClose);
        bClose.addActionListener(this);

        bPrntT1 = new MyButton("Print");
        bPrntT1.setBackground(MyColor.BLUE);
        bPrntT1.setContentAreaFilled(true);
        bPrntT1.setBounds(645,35,80,35);
        lBackground.add(bPrntT1);

        ChangeListener focusColor1 = e -> {
            ButtonModel bm1 = bPrntT1.getModel();
            if (bm1.isRollover()) bPrntT1.setBackground(MyColor.BLUE_LIGHT);
            else bPrntT1.setBackground(MyColor.BLUE);
        };
        bPrntT1.addChangeListener(focusColor1);
        bPrntT1.addActionListener(this);


        /* --- CUSTOMER REPORT --- */
        JLabel lCust = new JLabel("Customer Report");
        Font h1 = new Font("BEBAS NEUE", Font.PLAIN, 24);
        lCust.setFont(h1);
        lCust.setBounds(10, 30, 200, 50);
        lBackground.add(lCust);


        /* --- CUSTOMER TABLE --- */
        JLabel lCustomer = new JLabel();
        lCustomer.setBounds(10,75,715,200);
        int col1 = 7;
        String[][] y1 = new String[9][col1];
        String[] x1 = {"Customer", "Meter", "Address", "Town", "Province", "Email", "Phone"};
        t1 = new MyTable(y1, x1);
        MyConnection conn  = new MyConnection();
        try (var s = conn.c.prepareStatement("SELECT * FROM customer WHERE meter!='0'")) {
            ResultSet rs1  = s.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs1));
        } catch(SQLException e) {
            System.out.println("SQL Exception in customer report");
        }
        JScrollPane sp1 = new JScrollPane(t1);
        sp1.setBounds(0, 0, 715, 160);
        lCustomer.add(sp1);


        /* --- METER REPORT --- */
        JLabel lMet = new JLabel("Meter Report");
        lMet.setFont(h1);
        lMet.setBounds(10, 270, 200, 50);
        lBackground.add(lMet);

        bPrntT2 = new MyButton("Print");
        bPrntT2.setBounds(645,275,80,35);
        bPrntT2.setBackground(MyColor.BLUE);
        bPrntT2.setContentAreaFilled(true);
        lBackground.add(bPrntT2);

        ChangeListener focusColor2 = e -> {
            ButtonModel bm2 = bPrntT2.getModel();
            if (bm2.isRollover()) bPrntT2.setBackground(MyColor.BLUE_LIGHT);
            else bPrntT2.setBackground(MyColor.BLUE);
        };
        bPrntT2.addChangeListener(focusColor2);
        bPrntT2.addActionListener(this);


        /* --- METER TABLE --- */
        JLabel lMeter = new JLabel();
        lMeter.setBounds(10,315,715,200);
        int col2 = 6;
        String[][] y2 = new String[9][col2];
        String[] x2 = {"Meter", "Location", "Meter Type", "Phase Code", "Bill Type", "Days"};
        t2 = new MyTable(y2, x2);
        try (PreparedStatement s = conn.c.prepareStatement("SELECT * FROM meter WHERE meter!='0'")) {
            ResultSet rs2  = s.executeQuery();
            t2.setModel(DbUtils.resultSetToTableModel(rs2));
        } catch(SQLException e) {
            System.out.println("SQL Exception in meter report");
        }
        JScrollPane sp2 = new JScrollPane(t2);
        sp2.setBounds(0, 0, 715, 150);
        lMeter.add(sp2);


        /* --- BILLING REPORT --- */
        JLabel lAcc = new JLabel("Billing Report");
        lAcc.setFont(h1);
        lAcc.setBounds(10, 510, 200, 50);
        lBackground.add(lAcc);

        bPrntT3 = new MyButton("Print");
        bPrntT3.setBounds(645,515,80,35);
        bPrntT3.setBackground(MyColor.BLUE);
        bPrntT3.setContentAreaFilled(true);
        lBackground.add(bPrntT3);
        ChangeListener focusColor3 = e -> {
            ButtonModel bm3 = bPrntT3.getModel();
            if (bm3.isRollover()) bPrntT3.setBackground(MyColor.BLUE_LIGHT);
            else bPrntT3.setBackground(MyColor.BLUE);
        };
        bPrntT3.addChangeListener(focusColor3);
        bPrntT3.addActionListener(this);


        /* --- BILLING TABLE --- */
        JLabel lAccount = new JLabel();
        lAccount.setBounds(10,555,715,200);
        int col3 = 5;
        String[][] y3 = new String[9][col3];
        String[] x3 = {"Meter Number", "Month", "Units", "Total Bill", "Status"};
        t3 = new MyTable(y3, x3);
        try (PreparedStatement s = conn.c.prepareStatement("SELECT * FROM bill WHERE meter!='0'")) {
            ResultSet rs3  = s.executeQuery();
            t3.setModel(DbUtils.resultSetToTableModel(rs3));
        } catch(SQLException e) {
            System.out.println("SQL Exception in billing report");
        }
        JScrollPane sp3 = new JScrollPane(t3);
        sp3.setBounds(0, 0, 715, 150);
        lAccount.add(sp3);


        /* --- ADD REPORTS TO BACKGROUND --- */
        lBackground.add(lCustomer);
        lBackground.add(lMeter);
        lBackground.add(lAccount);
        setLocation(550,55);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == bPrntT1) {
            try {
                t1.print();
            } catch (Exception e) {
                System.out.println("Unable to print T1");
            }
        }
        if(ae.getSource() == bPrntT2) {
            try {
                t2.print();
            } catch (Exception e) {
                System.out.println("Unable to print T2");
            }
        }
        if(ae.getSource() == bPrntT3) {
            try {
                t3.print();
            } catch (Exception e) {
                System.out.println("Unable to print T3");
            }
        }
        if(ae.getSource() == bClose) this.setVisible(false);
    }

    public static void main(String[] args) {
        new Reports();
    }
}
