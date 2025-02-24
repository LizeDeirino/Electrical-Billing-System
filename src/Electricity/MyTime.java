package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTime extends JPanel implements ActionListener {

    private final JLabel time,date;
    private final DateFormat dateFormat,timeFormat;
    static JPanel pTime;

    MyTime(){
        setBackground(MyColor.DARK_DARK_GREY);
        setSize(250,180);
        setLocation(50,510);
        setLayout(new BorderLayout());


        /* --- ACCESS TYPE --- */
        JLabel access;
        if (Dashboard.userType.equals("Administrator")) {
            access = new JLabel("Administrator");
        } else if (Dashboard.userType.equals("Customer")) {
            access = new JLabel("Customer");
        } else access = new JLabel("Unknown");
        access.setBounds(0,0,220,15);
        access.setFont(MyFonts.hGen);
        access.setForeground(MyColor.ANOTHER_GREY);


        /* --- USERNAME --- */
        JLabel user;
        if (Dashboard.userType.equals("Administrator")) {
            user = new JLabel("Hi " + Dashboard.username);
        } else if (Dashboard.userType.equals("Customer")) {
            user = new JLabel("Hi " + Dashboard.username);
        } else user = new JLabel("Hi There");
        user.setBounds(0,25,220,30);
        user.setFont(MyFonts.hName);
        user.setForeground(MyColor.GREY);

        /* --- DATE --- */
        dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        date = new JLabel();
        date.setForeground(MyColor.ANOTHER_GREY);
        date.setBounds(0,70,220,15);
        date.setFont(MyFonts.hGen);

        /* --- TIME --- */
        timeFormat = new SimpleDateFormat("HH:mm");
        time = new JLabel();
        time.setForeground(MyColor.ANOTHER_GREY);
        time.setBounds(0,90,220,70);
        time.setFont(MyFonts.hTime);

        new Timer(500, this).start();


        /* --- TIME PANEL --- */
        pTime = new JPanel();
        pTime.setSize(250,180);
        pTime.setBackground(MyColor.DARK_DARK_GREY);
        pTime.setLayout(new BorderLayout());
        pTime.add(access);
        pTime.add(user);
        pTime.add(date);
        pTime.add(time);
        pTime.setLayout(new BorderLayout());
        add(pTime);
        setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        date.setText(dateFormat.format(new Date()));
        time.setText(timeFormat.format(new Date()));

    }
}
