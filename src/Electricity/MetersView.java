package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MetersView extends JFrame implements ActionListener {

    String location,type,phase,bill;
    int meter;
    MyGradientButton bDone;
    MyConnection conn;
    MyButton bClose;
    Font f1 = new Font("AVENIR", Font.PLAIN, 14),
            f2 = new Font("BEBAS NEUE", Font.PLAIN, 30);
    JLabel lMeterView,l1,l3,l4,l5,l6,l7,l8,l9,l10;


    MetersView(int meter) {
        super("PEB  ||  Meter View");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo.png")));
        this.meter = meter;
        setUndecorated(true);


        /* --- LABELS & FIELDS --- */
        l1 = new JLabel("Meter number: " + meter);
        l1.setBounds(50,20,600, 80);
        l1.setFont(f2);
        l1.setForeground(Color.DARK_GRAY);
        add(l1);

        l3 = new JLabel("Location");
        l3.setBounds(50, 100, 100, 20);
        l3.setFont(f1);
        add(l3);

        l4 = new JLabel(location);
        l4.setBounds(150, 100, 100, 20);
        l4.setFont(f1);
        add(l4);

        l5 = new JLabel("Meter Type");
        l5.setBounds(50, 140, 100, 20);
        l5.setFont(f1);
        add(l5);

        l6 = new JLabel(type);
        l6.setBounds(150, 140, 100, 20);
        l6.setFont(f1);
        add(l6);

        l7 = new JLabel("Phase Code");
        l7.setBounds(50, 180, 100, 20);
        l7.setFont(f1);
        add(l7);

        l8 = new JLabel(phase);
        l8.setBounds(150, 180, 100, 20);
        l8.setFont(f1);
        add(l8);

        l9 = new JLabel("Bill Type");
        l9.setBounds(50, 220, 100, 20);
        l9.setFont(f1);
        add(l9);

        l10 = new JLabel(bill);
        l10.setBounds(150, 220, 150, 20);
        l10.setFont(f1);
        add(l10);

        conn = new MyConnection();
        try(var s = conn.c.prepareStatement("SELECT * FROM meter_info WHERE meter_number = " + meter)) {
            var rs = s.executeQuery();
            while (rs.next()) {
                l4.setText(rs.getString(2));
                l6.setText(rs.getString(3));
                l8.setText(rs.getString(4));
                l10.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in MetersView while populating meter_info");
        }

        /* --- BUTTONS --- */
        bDone = new MyGradientButton("DONE", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bDone.setBounds(50,340,240,50);
        bDone.setForeground(Color.WHITE);
        add(bDone);

        bDone.addActionListener(this);

        /* --- BACKGROUND --- */
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("img/small-cAdd.png"));
        Image i3 = ic3.getImage().getScaledInstance(350, 430, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        lMeterView = new JLabel(icc3);
        lMeterView.setSize( 350, 430);
        add(lMeterView);

        setLayout(new BorderLayout());
        setBackground(MyColor.TRANSPARENT);
        setSize( 350, 430);
        setLocation(550,300);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == bDone) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MetersView(0);
    }


}
