package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeterEdit extends JPanel implements ActionListener {

    Choice c2,c3,c4,c5;
    static JButton bMeterUpdate,bRefreshMeterUpdate,bMeterUpdateCancel;
    private final int meter;


    MeterEdit(int meter) {
        setLayout(new BorderLayout());
        this.meter = meter;

        /* --- TITLE --- */
        JLabel title = new JLabel("Edit Meter", SwingConstants.CENTER);
        title.setForeground(MyColor.BLUE_LIGHT);
        title.setSize(355,50);
        title.setLocation(0,15);
        title.setFont(MyFonts.h1);
        add(title);

        /* --- LABELS --- */
        JLabel l1 = new JLabel("Meter no: " + meter,SwingConstants.CENTER);
        l1.setSize(355,40);
        l1.setLocation(0,50);
        l1.setForeground(MyColor.BLUE_LIGHT);
        l1.setFont(MyFonts.h9);
        add(l1);

        JLabel l2 = new JLabel("Location");
        l2.setBounds(50, 100,90,35);
        l2.setForeground(Color.WHITE);
        l2.setFont(MyFonts.h5);
        add(l2);

        c2 = new Choice();
        c2.add(MyConnection.getLocation(meter));
        c2.add("Outside");
        c2.add("Inside");
        c2.setForeground(Color.DARK_GRAY);
        c2.setBounds(150, 100, 150, 20);
        add(c2);

        JLabel l3 = new JLabel("Meter Type");
        l3.setFont(MyFonts.h5);
        l3.setForeground(Color.WHITE);
        l3.setBounds(50, 140, 100, 20);
        add(l3);

        c3 = new Choice();
        c3.add(MyConnection.getType(meter));
        c3.add("Electric Meter");
        c3.add("Solar Meter");
        c3.add("Smart Meter");
        c3.setForeground(Color.DARK_GRAY);
        c3.setBounds(150, 140, 150, 20);
        add(c3);

        JLabel l4 = new JLabel("Phase Code");
        l4.setFont(MyFonts.h5);
        l4.setForeground(Color.WHITE);
        l4.setBounds(50, 180, 100, 20);
        add(l4);

        c4 = new Choice();
        c4.add(MyConnection.getPhase(meter));
        c4.add("011");
        c4.add("022");
        c4.add("033");
        c4.add("044");
        c4.add("055");
        c4.add("066");
        c4.add("077");
        c4.add("088");
        c4.add("099");
        c4.setForeground(Color.DARK_GRAY);
        c4.setBounds(150, 180, 150, 20);
        add(c4);

        JLabel l5 = new JLabel("Bill Type");
        l5.setFont(MyFonts.h5);
        l5.setForeground(Color.WHITE);
        l5.setBounds(50, 220, 100, 20);
        add(l5);

        c5 = new Choice();
        c5.add(MyConnection.getBill(meter));
        c5.add("Normal");
        c5.add("Industrial");
        c5.setForeground(Color.DARK_GRAY);
        c5.setBounds(150, 220, 150, 20);
        add(c5);


        JLabel l6 = new JLabel("Days");
        l6.setFont(MyFonts.h5);
        l6.setForeground(Color.WHITE);
        l6.setBounds(50, 260, 100, 20);
        add(l6);

        JLabel l7 = new JLabel("30 Days");
        l7.setFont(MyFonts.h5);
        l7.setForeground(Color.WHITE);
        l7.setBounds(150, 260, 150, 20);
        add(l7);

        JLabel l8 = new JLabel("Customer");
        l8.setFont(MyFonts.h5);
        l8.setForeground(Color.WHITE);
        l8.setBounds(50, 300, 100, 20);
        add(l8);

        JLabel l9 = new JLabel(MyConnection.getName(meter));
        l9.setFont(MyFonts.h5);
        l9.setBounds(150, 300, 150, 20);
        l9.setForeground(Color.WHITE);
        add(l9);

        /* --- HIDDEN BUTTON --- */
        bRefreshMeterUpdate = new MyGradientButton("Refresh", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bRefreshMeterUpdate.setBounds(50,340,120,40);
        bRefreshMeterUpdate.setForeground(Color.WHITE);
        bRefreshMeterUpdate.addActionListener(this);

        /* --- BUTTONS --- */
        bMeterUpdate = new MyGradientButton("Done", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMeterUpdate.setBounds(50, 350,120,40);
        bMeterUpdate.setForeground(Color.WHITE);
        bMeterUpdate.addActionListener(this);
        add(bMeterUpdate);

        bMeterUpdateCancel = new MyGradientButton("Cancel", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMeterUpdateCancel.setBounds(180,350,120,40);
        bMeterUpdateCancel.setForeground(Color.WHITE);
        bMeterUpdateCancel.addActionListener(this);
        add(bMeterUpdateCancel);

        setLayout(new BorderLayout());
        setBackground(MyColor.DARK_GREY);
        setSize(355, 350);
        setLocation(900,155);
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == bMeterUpdate) {
            String sql = "UPDATE meter SET Location = ?, Type = ?, Phase = ?, Bill = ? WHERE Meter = ?";
            MyConnection conn = new MyConnection();
            try (PreparedStatement s = conn.c.prepareStatement(sql)) {
                s.setObject(1,c2.getSelectedItem());
                s.setObject(2,c3.getSelectedItem());
                s.setObject(3,c4.getSelectedItem());
                s.setObject(4,c5.getSelectedItem());
                s.setObject(5,meter);
                s.executeUpdate();
                JOptionPane.showMessageDialog(null, "Meter Updated");
                Dashboard.jMeterEdit.setVisible(false);
                MeterReport.bMetersRefresh.doClick();
                MeterView.bRefreshMeter.doClick();
            } catch (SQLException e) {
                System.out.println("SQLException: Meter not Edited " + e.getMessage());
            }
        }

        if (ae.getSource() == bRefreshMeterUpdate) {
            new MeterEdit(meter);

        }

        if (ae.getSource() == bMeterUpdateCancel) {
            Dashboard.jMeterEdit.setVisible(false);
        }
    }
}
