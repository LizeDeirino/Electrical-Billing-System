package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AccountView extends JPanel implements ActionListener {

    static JButton bAccountClose,bRefreshAccount;
    private static int meter;

    AccountView(int meter) {
        String status = MyConnection.getStatus(meter);

        /* ---- LABELS & FIELDS --- */
        JLabel title = new JLabel("Account",SwingConstants.CENTER);
        title.setForeground(MyColor.BLUE_LIGHT);
        title.setBounds(0,10,355, 80);
        title.setFont(MyFonts.h0);
        add(title);

        JLabel l2 = new JLabel("Meter: " + meter, SwingConstants.CENTER);
        l2.setSize(355, 50);
        l2.setFont(MyFonts.h9);
        l2.setForeground(Color.WHITE);

        JLabel l3 = new JLabel("", SwingConstants.CENTER);
        if(status != null) {
            new JLabel("Account for " + MyConnection.getMonth(meter) + " 2024 is unpaid.", SwingConstants.CENTER);
        } else l3 = new JLabel("Status: No outstanding fees.", SwingConstants.CENTER);

        l3.setSize(355, 50);
        l3.setFont(MyFonts.f1i);
        l3.setForeground(MyColor.BLUE_LIGHT);

        JLabel l4 = new JLabel(" ", SwingConstants.CENTER);
        l4.setBounds(0, 120, 355, 50);
        l4.setLayout(new BorderLayout());
        l4.add(l2, BorderLayout.NORTH);
        l4.add(l3, BorderLayout.SOUTH);

        JLabel l5;
        if(MyConnection.getUnits(meter) == 0) {
            l5 = new JLabel("No Billable Units.", SwingConstants.CENTER);
        } else l5 = new JLabel(MyConnection.getMonth(meter) + " 2024 = " + MyConnection.getUnits(meter) + " Units", SwingConstants.CENTER);
        l5.setFont(MyFonts.h9);
        l5.setSize(355, 50);
        l5.setForeground(Color.WHITE);

        JLabel l6;
        if(MyConnection.getTotal(meter) != 0) {
            l6 = new JLabel("Total Amount = R" + MyConnection.getTotal(meter) + ".00", SwingConstants.CENTER);
        } else l6 = new JLabel("Well done! You are all paid up!",SwingConstants.CENTER);
        l6.setFont(MyFonts.h9);
        l6.setSize(355, 50);
        l6.setForeground(Color.WHITE);

        JLabel l7 = new JLabel();
        l7.setBounds(0, 200, 355, 60);
        l7.setLayout(new BorderLayout());
        l7.add(l5, BorderLayout.NORTH);
        l7.add(l6, BorderLayout.SOUTH);

        /* --- BUTTONS --- */
        bRefreshAccount = new MyGradientButton("Refresh", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bRefreshAccount.setBounds(50,340,120,40);
        bRefreshAccount.setBackground(Color.WHITE);
        bRefreshAccount.addActionListener(this);

        bAccountClose = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bAccountClose.setBounds(180,340,120,40);
        bAccountClose.setBackground(Color.WHITE);
        bAccountClose.addActionListener(this);

        /* add all to frame */
        setBackground(MyColor.ANOTHER_GREY);
        setForeground(Color.WHITE);
        setSize(355, 720);
        setLayout(new BorderLayout());
        setLocation(530, 55);

        add(l4);
        add(l7);
        add(bRefreshAccount);
        add(bAccountClose);

        setLayout(new BorderLayout());
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bRefreshAccount) {
            Dashboard.jAccount.setVisible(false);
            new AccountView(meter);

        }
        if (ae.getSource() == bAccountClose) {
            Dashboard.jAccount.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountView(meter));
    }
}
