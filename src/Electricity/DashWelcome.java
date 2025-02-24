package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashWelcome extends JPanel implements ActionListener {

    final JLabel lbTitle,lbLine1,lbLine2,lbLabel1,lbLabel2,lbLabel3;
    static JButton bWelcomeAdd,backButton2,bWelcomeReport;

    DashWelcome() {
        setBackground(MyColor.DARKEST_GREY);
        setSize(800,300);
        setLocation(0,170);
        setLayout(new BorderLayout());


        /* ---- COMPONENTS--- */
        lbTitle = new JLabel("Welcome to the Dashboard",SwingConstants.CENTER);
        lbTitle.setBounds(0, 0, 800, 60);
        lbTitle.setFont(MyFonts.h3);
        lbTitle.setForeground(MyColor.ANOTHER_GREY);

        lbLine1 = new JLabel("Create new accounts from scratch.",SwingConstants.CENTER);
        lbLine1.setBounds(0, 75, 800, 20);
        lbLine1.setFont(MyFonts.h6);
        lbLine1.setForeground(MyColor.ANOTHER_GREY);

        lbLine2 = new JLabel("Open existing accounts and reports to update and export.",SwingConstants.CENTER);
        lbLine2.setBounds(0, 95, 800, 20);
        lbLine2.setFont(MyFonts.h6);
        lbLine2.setForeground(MyColor.ANOTHER_GREY);

        bWelcomeAdd = new MyRoundedButton("a",10);
        bWelcomeAdd.setBounds(220, 170, 70, 70);
        bWelcomeAdd.setFont(MyFonts.hUserSmaller);
        bWelcomeAdd.addActionListener(this);

        backButton2 = new MyRoundedButton("a",10);
        backButton2.setBounds(370, 170, 70, 70);
        backButton2.setFont(MyFonts.hEditSmaller);
        backButton2.addActionListener(this);

        bWelcomeReport = new MyRoundedButton("a",10);
        bWelcomeReport.setBounds(520, 170, 70, 70);
        bWelcomeReport.setFont(MyFonts.hReportSmaller);
        bWelcomeReport.addActionListener(this);

        lbLabel1 = new JLabel("Add Account",SwingConstants.CENTER);
        lbLabel1.setBounds(215, 230, 80, 70);
        lbLabel1.setFont(MyFonts.h6);
        lbLabel1.setForeground(MyColor.GREY);

        lbLabel2 = new JLabel("Update Account",SwingConstants.CENTER);
        lbLabel2.setBounds(355, 230, 100, 70);
        lbLabel2.setFont(MyFonts.h6);
        lbLabel2.setForeground(MyColor.GREY);

        lbLabel3 = new JLabel("Reports",SwingConstants.CENTER);
        lbLabel3.setBounds(520, 230, 70, 70);
        lbLabel3.setFont(MyFonts.h6);
        lbLabel3.setForeground(MyColor.GREY);


        /* --- COMPONENTS PANEL --- */
        add(lbTitle);
        add(lbLine1);
        add(lbLine2);
        add(bWelcomeAdd);
        add(backButton2);
        add(bWelcomeReport);
        add(lbLabel1);
        add(lbLabel2);
        add(lbLabel3);
        setLayout(new BorderLayout());

    }

    /* --- ACTION EVENTS --- */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bWelcomeAdd) {
            Dashboard.jProfileAdd.setVisible(true);
            Dashboard.pWelcome.setVisible(false);
        }
        if(e.getSource() == backButton2) {
            Dashboard.jAccountReport.setVisible(true);
            Dashboard.pWelcome.setVisible(false);
        }
        if(e.getSource() == bWelcomeReport) {
            Dashboard.jProfilesReport.setVisible(true);
            Dashboard.pWelcome.setVisible(false);
        }
    }
}
