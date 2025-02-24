package Electricity;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProfileView extends JPanel implements ActionListener {

    static JButton bProfileClose,bRefreshProfile,bEditProfile;

    ProfileView(int meter) {
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.ANOTHER_GREY);
        setSize(380,540);
        setLocation(185,90);
        setLayout(new BorderLayout());
        setBorder(new MyRoundedBorder(20));



        /* --- LABELS & FIELDS --- */
        JLabel lTitle = new JLabel(MyConnection.getName(meter),SwingConstants.CENTER);
        lTitle.setBounds(0,80,400, 45);
        lTitle.setFont(MyFonts.h0);
        lTitle.setForeground(MyColor.BLUE_LIGHT);

        JLabel lMeter = new JLabel("Meter number " + meter + " is currently ", SwingConstants.CENTER);
        lMeter.setBounds(0,160,400,15);
        lMeter.setFont(MyFonts.h7);
        lMeter.setForeground(MyColor.ANOTHER_GREY);


        JLabel lAddress = new JLabel("active at " + MyConnection.getAddress(meter) + " in " +
                MyConnection.getSuburb(meter) + ", ",SwingConstants.CENTER);
        lAddress.setBounds(0,180,400,15);
        lAddress.setFont(MyFonts.h7);
        lAddress.setForeground(MyColor.ANOTHER_GREY);


        JLabel lProvence = new JLabel( MyConnection.getProvence(meter) + ".",SwingConstants.CENTER);
        lProvence.setBounds(0,200,400, 15);
        lProvence.setFont(MyFonts.h7);
        lProvence.setForeground(MyColor.ANOTHER_GREY);


        JLabel lEmail = new JLabel("Email: " + MyConnection.getEmail(meter),SwingConstants.CENTER);
        lEmail.setFont(MyFonts.h10);
        lEmail.setBounds(0,250,400, 20);
        lEmail.setForeground(MyColor.BLUE_LIGHT);


        JLabel lPhone = new JLabel("Phone: " + MyConnection.getPhone(meter),SwingConstants.CENTER);
        lPhone.setFont(MyFonts.h10);
        lPhone.setBounds(0,280,400, 20);
        lPhone.setForeground(MyColor.BLUE_LIGHT);


        JLabel lContact = new JLabel("Please check that all contact",SwingConstants.CENTER);
        lContact.setBounds(0,350,400, 15);
        lContact.setFont(MyFonts.h7);
        lContact.setForeground(MyColor.ANOTHER_GREY);


        JLabel lInfo = new JLabel(" information is up to date.",SwingConstants.CENTER);
        lInfo.setBounds(0,375,400, 15);
        lInfo.setFont(MyFonts.h7);
        lInfo.setForeground(MyColor.ANOTHER_GREY);



        /* --- BUTTON NOT IN VIEW, BUT IN USE ON BACKEND --- */
        bRefreshProfile = new MyGradientButton("Refresh", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bRefreshProfile.setBounds(50,350,120,40);
        bRefreshProfile.setForeground(Color.WHITE);
        bRefreshProfile.addActionListener(this);


        /* --- BUTTONS --- */
        bEditProfile = new MyGradientButton("Edit", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bEditProfile.setBounds(80,450,120,40);
        bEditProfile.setForeground(Color.WHITE);
        bEditProfile.addActionListener(this);

        bProfileClose = new MyGradientButton("Back", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bProfileClose.setBounds(120,430,150,40);
        bProfileClose.setForeground(Color.WHITE);
        bProfileClose.addActionListener(this);



        /* --- ADD COMPONENTS TO PANEL --- */
        add(lTitle);
        add(lMeter);
        add(lAddress);
        add(lProvence);
        add(lEmail);
        add(lPhone);
        add(lContact);
        add(lInfo);
        add(bProfileClose);
        setLayout(new BorderLayout());
    }


    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bEditProfile) {
            this.setVisible(false);
            Dashboard.jProfileEdit.setVisible(true);
            Dashboard.jProfile.setVisible(false);
        }

        if (ae.getSource() == bProfileClose) {
            Dashboard.jProfile.setVisible(false);
            Dashboard.jProfilesReport.setVisible(true);
        }

        if (ae.getSource() == bRefreshProfile) {
            Dashboard.jProfile.setVisible(false);
        }

    }
}
