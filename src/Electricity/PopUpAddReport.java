package Electricity;

import javax.swing.*;
import java.awt.event.*;

public class PopUpAddReport extends JPopupMenu implements ActionListener {

    static JButton bAddProfile, bAddMeter, bAddAccount,
            bProfilesReport, bMeterReport, bAccountReport;

    public PopUpAddReport() {
        setBackground(MyColor.DARKER_GREY);
        setBorderPainted(false);

        JLabel l = new JLabel(" ");
        add(l);
        bProfilesReport = new MyPopupMenuButton(" ... ","Customer Report"," ... ");
        bProfilesReport.addActionListener(this);
        add(bProfilesReport);
        JLabel l1 = new JLabel(" ");
        add(l1);
        bMeterReport = new MyPopupMenuButton(" ","Meter Report"," ");
        bMeterReport.addActionListener(this);
        add(bMeterReport);
        JLabel l8 = new JLabel(" ");
        l8.setBackground(MyColor.DARK_DARK_GREY);
        add(l8);
        bAccountReport = new MyPopupMenuButton(" ","Account Report"," ");
        bAccountReport.addActionListener(this);
        add(bAccountReport);
        JLabel l13 = new JLabel(" ");
        l13.setBackground(MyColor.DARK_DARK_GREY);
        add(l13);
        addSeparator();

        JLabel l5 = new JLabel(" ");
        l5.setBackground(MyColor.DARK_DARK_GREY);
        add(l5);
        bAddProfile = new MyPopupMenuButton(" ","New Customer"," ");
        bAddProfile.addActionListener(this);
        add(bAddProfile);
        JLabel l2 = new JLabel(" ");
        add(l2);
        bAddMeter = new MyPopupMenuButton(" ","New Meter"," ");
        bAddMeter.addActionListener(this);
        add(bAddMeter);
        JLabel l10 = new JLabel(" ");
        l10.setBackground(MyColor.DARK_DARK_GREY);
        add(l10);
        bAddAccount = new MyPopupMenuButton(" ","New Account"," ");
        bAddAccount.addActionListener(this);
        add(bAddAccount);
        JLabel l12 = new JLabel(" ");
        l12.setBackground(MyColor.DARK_DARK_GREY);
        add(l12);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAddProfile) {
            Dashboard.jProfileAdd.setVisible(true);
            Dashboard.jMeterAdd.setVisible(false);
            Dashboard.jAccountAdd.setVisible(false);
            Dashboard.jProfile.setVisible(false);
            Dashboard.jMeter.setVisible(false);
            Dashboard.jAccount.setVisible(false);
            this.setVisible(false);
        }
        if (e.getSource() == bProfilesReport){
            Dashboard.jProfilesReport.setVisible(true);
            Dashboard.jMeterReport.setVisible(false);
            Dashboard.jAccountReport.setVisible(false);
            this.setVisible(false);
        }
        if (e.getSource() == bAddMeter){
            Dashboard.jProfileAdd.setVisible(false);
            Dashboard.jMeterAdd.setVisible(true);
            Dashboard.jAccountAdd.setVisible(false);
            Dashboard.jProfile.setVisible(false);
            Dashboard.jMeter.setVisible(false);
            Dashboard.jAccount.setVisible(false);
            this.setVisible(false);
        }
        if (e.getSource() == bMeterReport){
            Dashboard.jProfilesReport.setVisible(false);
            Dashboard.jMeterReport.setVisible(true);
            Dashboard.jAccountReport.setVisible(false);
            this.setVisible(false);
        }
        if (e.getSource() == bAddAccount){
            Dashboard.jProfileAdd.setVisible(false);
            Dashboard.jMeterAdd.setVisible(false);
            Dashboard.jAccountAdd.setVisible(true);
            Dashboard.jProfile.setVisible(false);
            Dashboard.jMeter.setVisible(false);
            Dashboard.jAccount.setVisible(false);
            this.setVisible(false);
        }
        if (e.getSource() == bAccountReport){
            Dashboard.jProfilesReport.setVisible(false);
            Dashboard.jMeterReport.setVisible(false);
            Dashboard.jAccountReport.setVisible(true);
            this.setVisible(false);
        }
    }
}
