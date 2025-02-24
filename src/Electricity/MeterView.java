package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterView extends JPanel implements ActionListener {

    static JButton bUpdateMeter,bRefreshMeter,bMeterClose;
    private final int meter;


    MeterView(int meter) {
        setBackground(MyColor.TRANSPARENT);
        setSize(355, 720);
        setLayout(new BorderLayout());

        this.meter = meter;
        /* --- LABELS & FIELDS --- */
        JLabel title = new JLabel("Meter");
        title.setSize(355, 50);
        title.setLocation(0,20);
        title.setFont(MyFonts.h0);
        title.setForeground(MyColor.BLUE_LIGHT);


        JLabel l1 = new JLabel(MyConnection.getBill(meter) + " Meter is a " + MyConnection.getType(meter));
        l1.setSize(355,50);
        l1.setFont(MyFonts.h6);
        l1.setForeground(Color.WHITE);

        JLabel l2 = new JLabel(  MyConnection.getPhase(meter) + " phase, located " + MyConnection.getLocation(meter) + " the building.");
        l2.setSize(355,50);
        l2.setFont(MyFonts.h6);
        l2.setForeground(Color.WHITE);

        JLabel l4 = new JLabel();
        l4.setBounds(0,100,355,40);
        l4.setLayout(new BorderLayout());
        l4.add(l1,BorderLayout.NORTH);
        l4.add(l2,BorderLayout.SOUTH);

        JLabel l5 = new JLabel("Name: " + MyConnection.getName(meter));
        l5.setFont(MyFonts.h9);
        l5.setSize(355,20);
        l5.setForeground(MyColor.BLUE_LIGHT);

        JLabel l6 = new JLabel("Meter: " + meter);
        l6.setFont(MyFonts.h9);
        l6.setSize(355,20);
        l6.setForeground(MyColor.BLUE_LIGHT);

        JLabel l7 = new JLabel();
        l7.setBounds(0,180,355,50);
        l7.setLayout(new BorderLayout());
        l7.add(l5, BorderLayout.NORTH);
        l7.add(l6, BorderLayout.SOUTH);

        JLabel l8 = new JLabel("Please get in touch if you");
        l8.setSize(355,40);
        l8.setFont(MyFonts.h5);
        l8.setForeground(Color.WHITE);

        JLabel l9 = new JLabel(" need any further assistance.");
        l9.setSize(355,40);
        l9.setFont(MyFonts.h5);
        l9.setForeground(Color.WHITE);

        JLabel l11 = new JLabel();
        l11.setBounds(0,255,355,40);
        l11.setLayout(new BorderLayout());
        l11.add(l8,BorderLayout.NORTH);
        l11.add(l9,BorderLayout.CENTER);


        /* --- BUTTONS --- */

        bUpdateMeter = new MyGradientButton("Update", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bUpdateMeter.setBounds(50,350,120,40);
        bUpdateMeter.setForeground(Color.WHITE);
        bUpdateMeter.addActionListener(this);

        bMeterClose = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMeterClose.setBounds(180,350,120,40);
        bMeterClose.setForeground(Color.WHITE);
        bMeterClose.addActionListener(this);

        /* hidden */
        bRefreshMeter = new MyGradientButton("Refresh", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bRefreshMeter.setBounds(50,350,120,40);
        bRefreshMeter.setForeground(Color.WHITE);
        bRefreshMeter.addActionListener(this);
        bRefreshMeter.setVisible(false);


        setBackground(MyColor.ANOTHER_GREY);
        setForeground(Color.WHITE);
        setSize(360,440);
        setLocation(0,0);
        setLayout(new BorderLayout());


        add(title);
        add(l4);
        add(l7);
        add(l11);
        add(bUpdateMeter);
        add(bMeterClose);

        setLayout(new BorderLayout());
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bUpdateMeter) {
            Dashboard.jAbout.setVisible(false);
            Dashboard.jMeterEdit.setVisible(true);
        }

        if (ae.getSource() == bMeterClose) {
            Dashboard.jMeter.setVisible(false);
            Dashboard.jAbout.setVisible(false);
            Dashboard.jMeterEdit.setVisible(false);
        }
        if (ae.getSource() == bRefreshMeter) {
            new MeterView(meter);
        }
    }
}
