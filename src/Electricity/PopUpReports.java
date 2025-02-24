package Electricity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpReports extends JPopupMenu implements ActionListener {
    static JButton bProfilesReport, bMeterReport, bAccountReport;

    public PopUpReports() {
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

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bProfilesReport){
            Dashboard.jProfilesReport.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == bMeterReport){
            Dashboard.jMeterReport.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == bAccountReport){
            Dashboard.jAccountReport.setVisible(true);
            this.setVisible(false);
        }
    }
}
