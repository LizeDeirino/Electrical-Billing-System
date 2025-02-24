package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashLeft extends JPanel implements ActionListener {

    static MyGradientButton customers,profile,myMeter,meters,myAccount,
            accounts,refresh,reports,calculator,notepad,browser,logout;

    public DashLeft(String userType) {
        setBackground(MyColor.DARK_DARK_GREY);
        setSize(300, 1200);
        setLocation(0,0);
        setLayout(new BorderLayout());


        /* ---- BUTTONS--- */
        customers = new MyGradientButton("Customers", MyIcon.iCustomer, MyColor.ORANGE_LIGHT, MyColor.ORANGE);
        customers.setLocation(0, 0);
        customers.addActionListener(this);

        profile = new MyGradientButton("My Profile", MyIcon.iUser, MyColor.ORANGE_LIGHT, MyColor.ORANGE);
        profile.setLocation(0, 0);
        profile.addActionListener(this);

        myMeter = new MyGradientButton("My Meter", MyIcon.iMeter, MyColor.ORANGE, MyColor.PINK);
        myMeter.setLocation(110, 0);
        myMeter.addActionListener(this);

        meters = new MyGradientButton("Meters", MyIcon.iMeters, MyColor.ORANGE, MyColor.PINK);
        meters.setLocation(110, 0);
        meters.addActionListener(this);

        myAccount = new MyGradientButton("My Account", MyIcon.iPay, MyColor.ORANGE, MyColor.PINK);
        myAccount.setLocation(0, 110);
        myAccount.addActionListener(this);

        accounts = new MyGradientButton("Accounts", MyIcon.iAccount, MyColor.PINK, MyColor.PLUM);
        accounts.setLocation(0, 110);
        accounts.addActionListener(this);

        refresh = new MyGradientButton("Refresh", MyIcon.iRefresh, MyColor.PINK, MyColor.PLUM);
        refresh.setLocation(110, 110);
        refresh.addActionListener(this);

        reports = new MyGradientButton("Reports", MyIcon.iReport, MyColor.PLUM, MyColor.PURPLE);
        reports.setLocation(110, 110);
        reports.addActionListener(this);

        calculator = new MyGradientButton("Calculator", MyIcon.iCalculator, MyColor.PINK, MyColor.PLUM);
        calculator.setLocation(0, 220);
        calculator.addActionListener(this);

        notepad = new MyGradientButton("Notepad", MyIcon.iNote, MyColor.PLUM, MyColor.BLUE);
        notepad.setLocation(110, 220);
        notepad.addActionListener(this);

        browser = new MyGradientButton("Browser", MyIcon.iBrowse, MyColor.PLUM, MyColor.BLUE);
        browser.setLocation(0, 330);
        browser.addActionListener(this);

        logout = new MyGradientButton("Logout", MyIcon.iLogout, MyColor.BLUE, MyColor.GREEN);
        logout.setLocation(110, 330);
        logout.addActionListener(this);



        /* --- BUTTON PANEL --- */
        JPanel pButtons = new JPanel();
        pButtons.setSize(240, 440);
        pButtons.setBackground(MyColor.DARK_DARK_GREY);
        pButtons.setLayout(new BorderLayout());

        if (userType.equals("Administrator")) {
            pButtons.add(customers);
            pButtons.add(meters);
            pButtons.add(accounts);
            pButtons.add(reports);
        } else {
            pButtons.add(profile);
            pButtons.add(myMeter);
            pButtons.add(myAccount);
            pButtons.add(refresh);
        }

        pButtons.add(calculator);
        pButtons.add(notepad);
        pButtons.add(browser);
        pButtons.add(logout);
        pButtons.setLocation(45,45);
        pButtons.setLayout(new BorderLayout());
        add(pButtons);

        /* --- TIME PANEL --- */
        JPanel pTime = new MyTime();
        add(pTime);
        setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Dashboard.pWelcome.setVisible(false);



        /* --- CUSTOMER buttons --- */
        if(ae.getSource() == profile) {
            Dashboard.jProfile.setVisible(!Dashboard.jProfile.isVisible());
            Dashboard.jProfileEdit.setVisible(Dashboard.jProfile.isVisible());
        }
        if(ae.getSource() == myMeter) {
            Dashboard.jMeter.setVisible(!Dashboard.jMeter.isVisible());
            Dashboard.jAbout.setVisible(Dashboard.jMeter.isVisible());
        }
        if(ae.getSource() == myAccount) {
            Dashboard.jAccount.setVisible(!Dashboard.jAccount.isVisible());
            Dashboard.jAccountPay.setVisible(Dashboard.jAccount.isVisible());
        }

        /* --- ADMINISTRATOR buttons --- */
        if(ae.getSource() == customers) {
            Dashboard.jProfilesReport.setVisible(true);
            Dashboard.jMeterReport.setVisible(false);
            Dashboard.jAccountReport.setVisible(false);
        }
        if(ae.getSource() == meters) {
            Dashboard.jProfilesReport.setVisible(false);
            Dashboard.jMeterReport.setVisible(true);
            Dashboard.jAccountReport.setVisible(false);

        }
        if(ae.getSource() == accounts) {
            Dashboard.jProfilesReport.setVisible(false);
            Dashboard.jMeterReport.setVisible(false);
            Dashboard.jAccountReport.setVisible(true);
        }
        if(ae.getSource() == reports) {
            Dashboard.jProfilesReport.setLocation(0,0);
            Dashboard.jProfilesReport.setVisible(true);
            Dashboard.jMeterReport.setLocation(0,300);
            Dashboard.jMeterReport.setVisible(true);
            Dashboard.jAccountReport.setLocation(0,600);
            Dashboard.jAccountReport.setVisible(true);
        }


        /* --- GENERAL buttons --- */
        if(ae.getSource() == calculator) {
            try {
                Runtime.getRuntime().exec(new String[]{"calc.exe"});
            } catch (Exception e) {
                System.out.println("Calculator not available.");
            }
        }
        if(ae.getSource() == notepad) {
            try {
                Runtime.getRuntime().exec(new String[]{"notepad.exe"});
            } catch (Exception e) {
                System.out.println("Notepad not available.");
            }
        }
        if(ae.getSource() == browser) {
            try {
                Runtime.getRuntime().exec(new String[]{"C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"});
            } catch (Exception e) {
                System.out.println("Browser unavailable.");
            }
        }
    }
}
