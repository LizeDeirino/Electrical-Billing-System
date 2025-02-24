package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountPay extends JPanel implements ActionListener{

    static JButton bPay,bAccountPayClose,bRefreshAccountPay;
    private final Choice cPay;
    private int meter;
    private String name,month;

    AccountPay(int meter) {

        /* --- TITLE --- */
        JLabel title = new JLabel("Pay");
        title.setForeground(MyColor.BLUE_LIGHT);
        title.setBounds(50,10,70, 80);
        title.setFont(MyFonts.h0);
        add(title);

        JLabel extra = new JLabel("PLEASE NOTE: If all is settled,");
        extra.setForeground(MyColor.BLUE_LIGHT);
        extra.setBounds(150,35,200, 10);
        extra.setFont(MyFonts.hi2);
        add(extra);

        JLabel extra1 = new JLabel("no selectable month will appear.");
        extra1.setForeground(MyColor.BLUE_LIGHT);
        extra1.setBounds(150,50,200, 10);
        extra1.setFont(MyFonts.hi2);
        add(extra1);


        /* ---- LABELS, FIELDS & CHOICES --- */
        JLabel l1 = new JLabel("Meter No");
        l1.setFont(MyFonts.h5);
        l1.setForeground(Color.WHITE);
        l1.setBounds(50, 90, 100, 30);
        add(l1);

        JLabel l2 = new JLabel(String.valueOf(meter));
        l2.setFont(MyFonts.h5);
        l2.setForeground(Color.WHITE);
        l2.setBounds(150, 90, 100, 30);
        add(l2);

        JLabel l3 = new JLabel("Name");
        l3.setFont(MyFonts.h5);
        l3.setForeground(Color.WHITE);
        l3.setBounds(50, 130, 100, 30);
        add(l3);

        JLabel l4 = new JLabel(name);
        l4.setFont(MyFonts.h5);
        l4.setForeground(Color.WHITE);
        l4.setBounds(150, 130, 100, 30);
        add(l4);

        JLabel l5 = new JLabel("Month");
        l5.setFont(MyFonts.h5);
        l5.setForeground(Color.WHITE);
        l5.setBounds(50, 170, 100, 30);
        add(l5);

        /* --- CHOICE --- */
        cPay = new Choice();
        cPay.setBounds(150, 173, 150, 20);
        cPay.setFont(MyFonts.h6);
        cPay.add("Unpaid Accounts");
        String sql = "select * from account where meter = ? and status = ?";
        MyConnection conn = new MyConnection();
        try (PreparedStatement s = conn.c.prepareStatement(sql)) {
            s.setObject(1, meter);
            s.setObject(2,"Not Paid");
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                cPay.add(rs.getString("month"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: Set Months from account");
        }
        add(cPay);

        JLabel l6 = new JLabel("Units");
        l6.setFont(MyFonts.h5);
        l6.setForeground(Color.WHITE);
        l6.setBounds(50, 210, 100, 30);
        add(l6);

        JLabel l7 = new JLabel("0");
        l7.setFont(MyFonts.h5);
        l7.setForeground(Color.WHITE);
        l7.setBounds(150, 215, 150, 20);
        add(l7);


        JLabel l8 = new JLabel("Total Bill");
        l8.setFont(MyFonts.h5);
        l8.setForeground(Color.WHITE);
        l8.setBounds(50, 250, 100, 30);
        add(l8);

        JLabel l9 = new JLabel("0.00");
        l9.setFont(MyFonts.h5);
        l9.setForeground(Color.WHITE);
        l9.setBounds(150, 255, 150, 20);
        add(l9);

        JLabel l10 = new JLabel("Status");
        l10.setFont(MyFonts.h5);
        l10.setForeground(Color.WHITE);
        l10.setBounds(50, 290, 100, 30);
        add(l10);

        JLabel l11 = new JLabel("None");
        l11.setFont(MyFonts.h5);
        l11.setForeground(Color.WHITE);
        l11.setBounds(150, 295, 150, 20);
        add(l11);

        // Listening for change from cPay
        cPay.addItemListener(ae -> {
            String sql1 = "SELECT * FROM account WHERE meter=? AND month=?";
            MyConnection con = new MyConnection();
            try (PreparedStatement s1 = con.c.prepareStatement(sql1)) {
                s1.setObject(1, meter);
                s1.setObject(2, cPay.getSelectedItem());
                ResultSet rs = s1.executeQuery();

                while (rs.next()) {
                    l7.setText(rs.getString("units"));
                    l9.setText(rs.getString("total"));
                    l11.setText(rs.getString("status"));
                }
            } catch (SQLException e) {
                System.out.println("months not set");
            }
        });
        add(cPay);

        /* --- BUTTONS --- */
        bPay = new MyGradientButton("Pay", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bPay.setBounds(50, 340, 120, 40);
        bPay.setForeground(Color.WHITE);
        add(bPay);
        bPay.addActionListener(this);

        bAccountPayClose = new MyGradientButton("Cancel", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bAccountPayClose.setBounds(180, 340, 120, 40);
        bAccountPayClose.setForeground(Color.WHITE);
        add(bAccountPayClose);
        bAccountPayClose.addActionListener(this);

        setBackground(MyColor.DARK_GREY);
        setSize(350, 420);
        setLocation(900,155);
        setLayout(new BorderLayout());
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bPay) {
            String payment = "UPDATE account SET status = 'Paid' WHERE meter = ? AND month = ?";
            MyConnection conn = new MyConnection();
            try (var s2 = conn.c.prepareStatement(payment)) {
                s2.setObject(1, meter);
                s2.setObject(2, cPay.getSelectedItem());
                var updated = s2.executeUpdate();
                if(updated != 0) {
                    JOptionPane.showMessageDialog(null, month + " account paid");
                    AccountView.bRefreshAccount.doClick();
                } else JOptionPane.showMessageDialog(null, "Payment failed");
            } catch (SQLException e) {
                System.out.println("AccountPay: Server busy - please try again");
            }

        }
        if (ae.getSource() == bAccountPayClose) {
            this.setVisible(false);
        }
    }


}
