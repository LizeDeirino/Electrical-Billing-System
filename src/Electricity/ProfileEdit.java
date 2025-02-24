package Electricity;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class ProfileEdit extends JPanel implements ActionListener {

    static JButton bProfileUpdateClose,bProfileUpdate;
    private final JTextField t1,t2,t3,t4,t5,tName;
    private final int meter;

    ProfileEdit(int meter) {
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.ANOTHER_GREY);
        setSize(380,540);
        setLocation(185,90);
        setLayout(new BorderLayout());
        setBorder(new MyRoundedBorder(20));
        this.meter = meter;

        /* --- LABELS & FIELDS --- */
        JLabel title = new JLabel("Update Profile", SwingConstants.CENTER);
        title.setFont(MyFonts.h3);
        title.setSize(355,50);
        title.setLocation(0,15);
        title.setForeground(MyColor.BLUE_LIGHT);
        add(title);

        JLabel lName = new JLabel("Name: ");
        lName.setForeground(Color.WHITE);
        lName.setFont(MyFonts.h7);
        lName.setBounds(50, 80,90,35);
        add(lName);
        tName = new MyTextField();
        tName.setText(MyConnection.getName(meter));
        tName.setBounds(145, 80,155,35);
        tName.setFont(MyFonts.h7);
        tName.setForeground(Color.DARK_GRAY);
        add(tName);

        JLabel l1 = new JLabel("Address: ");
        l1.setForeground(Color.WHITE);
        l1.setFont(MyFonts.h7);
        l1.setBounds(50, 130,90,35);
        add(l1);
        t1 = new MyTextField();
        t1.setText(MyConnection.getAddress(meter));
        t1.setBounds(145, 130,155,35);
        t1.setFont(MyFonts.h7);
        t1.setForeground(Color.DARK_GRAY);
        add(t1);

        JLabel l2 = new JLabel("Suburb:");
        l2.setBounds(50, 180,90,35);
        l2.setFont(MyFonts.h7);
        l2.setForeground(Color.WHITE);
        add(l2);
        t2 = new MyTextField();
        t2.setText(MyConnection.getSuburb(meter));
        t2.setBounds(145, 180,155,35);
        t2.setFont(MyFonts.h7);
        t2.setForeground(Color.DARK_GRAY);
        add(t2);

        JLabel l3 = new JLabel("Provence: ");
        l3.setBounds(50, 230,90,35);
        l3.setFont(MyFonts.h7);
        l3.setForeground(Color.WHITE);
        add(l3);
        t3 = new MyTextField();
        t3.setText(MyConnection.getProvence(meter));
        t3.setBounds(145, 230,155,35);
        t3.setFont(MyFonts.h7);
        t3.setForeground(Color.DARK_GRAY);
        add(t3);

        JLabel l4 = new JLabel("Email: ");
        l4.setBounds(50, 280,90,35);
        l4.setFont(MyFonts.h7);
        l4.setForeground(Color.WHITE);
        add(l4);
        t4 = new MyTextField();
        t4.setText(MyConnection.getEmail(meter));
        t4.setBounds(145, 280,155,35);
        t4.setFont(MyFonts.h7);
        t4.setForeground(Color.DARK_GRAY);
        add(t4);

        JLabel l5 = new JLabel("Phone: ");
        l5.setBounds(50, 330,90,35);
        l5.setFont(MyFonts.h7);
        l5.setForeground(Color.WHITE);
        add(l5);
        t5 = new MyTextField();
        t5.setText(MyConnection.getPhone(meter));
        t5.setBounds(145, 330,155,35);
        t5.setFont(MyFonts.h7);
        t5.setForeground(Color.DARK_GRAY);
        add(t5);

        /* --- BUTTONS --- */
        bProfileUpdate = new MyGradientButton("Done", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bProfileUpdate.setBounds(50,430,120,40);
        add(bProfileUpdate);
        bProfileUpdate.addActionListener(this);

        bProfileUpdateClose = new MyGradientButton("Cancel", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bProfileUpdateClose.setBounds(180,430,120,40);
        add(bProfileUpdateClose);
        bProfileUpdateClose.addActionListener(this);

        setLayout(new BorderLayout());
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bProfileUpdate) {
            String sql = "UPDATE customer SET Address=?, Suburb=?, Provence=?, Email=?, Phone=?, Name = ? WHERE meter = ?";
            MyConnection conn = new MyConnection();
            try (var s = conn.c.prepareStatement(sql)) {

                s.setObject(1,t1.getText());
                s.setObject(2,t2.getText());
                s.setObject(3,t3.getText());
                s.setObject(4,t4.getText());
                s.setObject(5,t5.getText());
                s.setObject(6,tName.getText());
                s.setObject(7,meter);
                s.executeUpdate();

                JOptionPane.showMessageDialog(null,"Updated");
                Dashboard.jProfileEdit.setVisible(false);
                Dashboard.jProfilesReport.setVisible(true);
            } catch (SQLException e) {
                System.out.println("ProfileEdit: Profile not Updated");
            }
        }
        if (ae.getSource() == bProfileUpdateClose) {
            Dashboard.jProfileEdit.setVisible(false);
            Dashboard.jProfilesReport.setVisible(true);
        }
    }

}
