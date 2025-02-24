package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfileDelete extends JPanel implements ActionListener{
    JTextField t,t1,t2,t3,t4,t5,tChoice;
    JLabel title,l1,l2,l3,l4,l5,l6,l7,l13;
    JButton b1,b2;
    Font f1 = new Font("Myriad Pro", Font.PLAIN, 22);
    Font f2 = new Font("Myriad Pro", Font.PLAIN, 14);
    Choice c1;

    ProfileDelete(int meter) {

        /* --- TITLE --- */
        title = new JLabel("Delete Customer");
        title.setBounds(80,20,600, 40);
        title.setFont(f1);
        title.setForeground(Color.DARK_GRAY);
        add(title);


        /* --- LABELS & FIELDS --- */
        l1 = new JLabel("Customer");
        l1.setBounds(40, 80, 100, 20);
        l1.setFont(f2);
        add(l1);

        t = new JTextField();
        t.setBounds(140, 80, 160, 30);
        add(t);

        /* --- */
        l2 = new JLabel("Meter");
        l2.setBounds(40, 120, 100, 20);
        l2.setFont(f2);
        add(l2);


        // populate meter number
        c1 = new Choice();
        c1.setBounds(140, 120, 160, 30);
        String sql = "SELECT * FROM customer";
        MyConnection conn = new MyConnection();
        try (PreparedStatement s = conn.c.prepareStatement(sql)) {
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                c1.add(String.valueOf(rs.getInt("meter")));
            }

        } catch (SQLException e) {
            System.out.println("Nope");
        }
        add(c1);

        tChoice = new JTextField("Select");
        tChoice.setBounds(140,120,160,30);
        tChoice.setBackground(MyColor.LIGHT_GREY);
        add(tChoice);

        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String sql1 = "select * from customer where meter = ?";
                MyConnection conn = new MyConnection();
                try (PreparedStatement s = conn.c.prepareStatement(sql1)) {
                    s.setObject(1,c1.getSelectedItem());
                    ResultSet rs = s.executeQuery();
                    while(rs.next()) {
                        title.setText("Delete " + rs.getString(1));
                        t.setText(rs.getString(1));
                        t1.setText(rs.getString(3));
                        t2.setText(rs.getString(4));
                        t3.setText(rs.getString(5));
                        t4.setText(rs.getString(6));
                        t5.setText(rs.getString(7));
                        tChoice.setText(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Exception: ProfileDelete.itemStateChanged - " + ex.getMessage());
                }

            }
        });

        /* --- */
        l3 = new JLabel("Address");
        l3.setBounds(40, 160, 100, 20);
        l3.setFont(f2);
        add(l3);

        t1 = new JTextField();
        t1.setBounds(140, 160, 160, 30);
        add(t1);

        /* --- */
        l4 = new JLabel("Suburb");
        l4.setBounds(40, 200, 100, 20);
        l4.setFont(f2);
        add(l4);

        t2 = new JTextField();
        t2.setBounds(140, 200, 160, 30);
        add(t2);


        /* --- */
        l5 = new JLabel("Provence");
        l5.setBounds(40, 240, 100, 20);
        l5.setFont(f2);
        add(l5);

        t3 = new JTextField();
        t3.setBounds(140, 240, 160, 30);
        add(t3);


        /* --- */
        l6 = new JLabel("Email");
        l6.setBounds(40, 280, 100, 20);
        l6.setFont(f2);
        add(l6);

        t4 = new JTextField();
        t4.setBounds(140, 280, 160, 30);
        add(t4);


        /* --- */
        l7 = new JLabel("Phone");
        l7.setBounds(40, 320, 100, 20);
        l7.setFont(f2);
        add(l7);

        t5 = new JTextField();
        t5.setBounds(140, 320, 160, 30);
        add(t5);


        /* --- BUTTONS --- */
        b1 = new MyGradientButton("Delete", MyColor.BLUE, MyColor.BLUE_LIGHT);
        b1.setBounds(40,380,125,40);
        b1.setForeground(Color.WHITE);
        add(b1);

        b2 = new MyGradientButton("Cancel", MyColor.BLUE, MyColor.BLUE_LIGHT);
        b2.setBounds(175,380,125,40);
        b2.setForeground(Color.WHITE);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);


        /* --- BACKGROUND --- */
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("img/small-cAdd.png"));
        Image i3 = ic3.getImage().getScaledInstance(350, 466, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        l13 = new JLabel(icc3);
        l13.setSize( 350, 466);
        add(l13);

        setLayout(new BorderLayout());
        setBackground(MyColor.TRANSPARENT);
        setSize( 350, 466);
        setLocation(550,300);
        setVisible(true);
    }


    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1) {
            String meter = c1.getSelectedItem();
            String name = t.getText();
            String sql1 = "DELETE FROM customer WHERE meter = ?";
            String sql2 = "DELETE FROM account WHERE meter = ?";
            String sql3 = "DELETE FROM meter WHERE meter = ?";
            String sql4 = "DELETE FROM login WHERE meter = ?";
            MyConnection conn = new MyConnection();
            try (PreparedStatement s1 = conn.c.prepareStatement(sql1)) {
                s1.setObject(1,meter);
                s1.executeUpdate();
                try (var s2 = conn.c.prepareStatement(sql2)) {
                    s2.setObject(1,meter);
                    s2.executeUpdate();
                    try (var s3 = conn.c.prepareStatement(sql3)) {
                        s3.setObject(1,meter);
                        s3.executeUpdate();
                        try (var s4 = conn.c.prepareStatement(sql4)) {
                            s4.setObject(1,meter);
                            s4.executeUpdate();
                        } catch (SQLException e) {
                            System.out.println("SQL Exception: ProfileDelete - Customer not deleted in login " + e.getMessage());
                        }
                    } catch (SQLException e) {
                        System.out.println("SQL Exception: ProfileDelete - Customer not deleted in meter" + e.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println("SQL Exception: ProfileDelete - Customer not deleted in account");
                }
                JOptionPane.showMessageDialog(null, "Customer Deleted");
                this.setVisible(false);
            } catch (SQLException e) {
                System.out.println("SQL Exception: ProfileDelete - Customer not deleted in customer");
            }

        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        }
    }

}

