package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountEdit extends JPanel implements ActionListener {
    JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,bg;
    JTextField t1;
    Choice c1,c2;
    static JButton bBillEdit,bBillClose;



    AccountEdit(int meter) {
        setFont(MyFonts.h6);
        /* --- TITLE --- */
        title = new JLabel("Edit a Bill");
        title.setBounds(50,10,230, 80);
        title.setFont(MyFonts.h3);
        add(title);


        /* ---- LABELS, FIELDS & CHOICES --- */
        l1 = new JLabel("Meter");
        l1.setBounds(50, 110, 100, 30);
        add(l1);

        c1 = new Choice();
        c1.add("Please select");
        c1.setBounds(160, 110, 140, 20);
        String sql = "SELECT * FROM customer";
        MyConnection conn = new MyConnection();
        try (PreparedStatement s = conn.c.prepareStatement(sql)) {
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                c1.add(rs.getString("meter"));
            }

        } catch (SQLException e) {
            System.out.println("Meter info not added");
        }
        add(c1);

        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String sql1 = "select * from customer where meter = ?";
                try (PreparedStatement s = conn.c.prepareStatement(sql1)) {
                    s.setObject(1,c1.getSelectedItem());
                    ResultSet rs = s.executeQuery();
                    while(rs.next()) {
                        title.setText("Editing " + rs.getString(1));
                        l3.setText(rs.getString(3));
                        l5.setText(rs.getString(4));
                    }
                } catch (SQLException ex) {
                    System.out.println("Error during update.");
                }

            }
        });

        l2 = new JLabel("Address");
        l2.setBounds(50, 150, 100, 30);
        add(l2);

        l3 = new JLabel();
        l3.setBounds(160, 150, 140, 20);
        add(l3);

        l4 = new JLabel("Suburb");
        l4.setBounds(50, 190, 100, 30);
        add(l4);

        l5 = new JLabel();
        l5.setBounds(160, 190, 140, 20);
        add(l5);

        l6 = new JLabel("Units Consumed");
        l6.setBounds(50, 230, 100, 30);
        add(l6);

        t1 = new JTextField();
        t1.setBounds(160, 230, 140, 20);
        add(t1);

        l7 = new JLabel("Month");
        l7.setBounds(50, 270, 100, 30);
        add(l7);

        c2 = new Choice();
        c2.add("Month to Edit");
        c2.setBounds(160, 270, 140, 20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");

        l8 = new JLabel();
        l8.setBounds(150, 310, 100, 30);
        add(l8);

        String sql2 = "SELECT * FROM bill WHERE month = ?";
        c2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                try (PreparedStatement s = conn.c.prepareStatement(sql2)) {
                    s.setObject(1, c2.getSelectedItem());
                    ResultSet rs = s.executeQuery();

                    if (rs.next()) {
                        t1.setText(rs.getString("units"));
                        l8.setText(rs.getString("status"));
                    } else {
                        t1.setText("0");
                        l8.setText("Not Billed Yet");
                    }
                } catch (SQLException e) {
                    System.out.println("Something silly happened while looking up a meter number");
                }
            }
        });
        add(c2);


        /* --- BUTTONS --- */
        bBillEdit = new MyGradientButton("Update", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillEdit.setBounds(50, 355, 120, 40);
        bBillEdit.addActionListener(this);
        add(bBillEdit);


        bBillClose = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillClose.setBounds(180, 355, 120, 40);
        bBillClose.addActionListener(this);
        add(bBillClose);



        /* --- BACKGROUND --- */
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("img/small-cAdd.png"));
        Image i3 = ic3.getImage().getScaledInstance(350, 420,  Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        bg = new JLabel(icc3);
        bg.setSize(350, 420);
        add(bg);

        setLayout(new BorderLayout());
        setSize(350, 420);
        setLocation(936,300);
        setVisible(true);
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bBillEdit) {
            String sql = "UPDATE bill SET units = ? WHERE month = ? AND meter = ?";
            MyConnection conn = new MyConnection();
            try (PreparedStatement s = conn.c.prepareStatement(sql)) {
                s.setString(1,t1.getText());
                s.setObject(2,c2.getSelectedItem());
                s.setObject(3,c1.getSelectedItem());
                s.executeUpdate();
                JOptionPane.showMessageDialog(null,"Updated");
            } catch (Exception e) {
                System.out.println("Nope! Please try again...");
            }
        }
        if (ae.getSource() == bBillClose) {
            this.setVisible(false);
        }
    }

}
