package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class MeterAdd extends JPanel implements ActionListener {


    private final Choice tLocation,tType,tBill,tPhase;
    private final JButton bAddMeterDone;


    MeterAdd() {
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.ANOTHER_GREY);
        setSize(400,540);
        setLocation(185,90);
        setLayout(new BorderLayout());
        setBorder(new MyRoundedBorder(20));


        /* ---- COMPONENTS--- */
        JLabel title = new JLabel("NEW METER");
        title.setBounds(90,50,250, 30);
        title.setFont(MyFonts.h2);
        title.setForeground(MyColor.BLUE_LIGHT);


        JLabel l1 = new JLabel("Meter");
        l1.setBounds(90,120,50,30);
        l1.setFont(MyFonts.h7);
        l1.setForeground(MyColor.ANOTHER_GREY);
        JLabel lm = new JLabel("" + Dashboard.meter);
        lm.setFont(MyFonts.h7);
        lm.setForeground(MyColor.BLUE_LIGHT);
        lm.setBounds(165,120,50,30);

        JLabel l2 = new JLabel("Location");
        l2.setBounds(90,160,80,30);
        l2.setFont(MyFonts.h7);
        l2.setForeground(MyColor.ANOTHER_GREY);
        tLocation = new Choice();
        tLocation.setBounds(165,160,160,30);
        tLocation.add(" Inside");
        tLocation.add(" Outside");
        tLocation.setMinimumSize(new Dimension(150,30));

        JLabel l3 = new JLabel("Type");
        l3.setFont(MyFonts.h7);
        l3.setForeground(MyColor.ANOTHER_GREY);
        l3.setBounds(90,200,80, 30);
        tType = new Choice();
        tType.setBounds(165,200,160,30);
        tType.add(" Solar Meter");
        tType.add(" Electrical Meter");
        tType.add(" Smart Meter");
        tType.setMinimumSize(new Dimension(150,30));

        JLabel l4 = new JLabel("Bill");
        l4.setFont(MyFonts.h7);
        l4.setForeground(MyColor.ANOTHER_GREY);
        l4.setBounds(90,240,80, 30);
        tBill = new Choice();
        tBill.setBounds(165,240,160, 30);
        tBill.add(" Residential");
        tBill.add(" Industrial");
        tBill.setMinimumSize(new Dimension(150,30));

        JLabel l5 = new JLabel("Phase");
        l5.setFont(MyFonts.h7);
        l5.setForeground(MyColor.ANOTHER_GREY);
        l5.setBounds(90,280,80, 30);
        tPhase = new Choice();
        tPhase.setBounds(165,280,160, 30);
        tPhase.add(" 011");
        tPhase.add(" 022");
        tPhase.add(" 033");
        tPhase.add(" 044");
        tPhase.add(" 055");
        tPhase.add(" 066");
        tPhase.add(" 077");
        tPhase.add(" 088");
        tPhase.add(" 099");
        tPhase.setMinimumSize(new Dimension(150,30));

        /* ---- ENTER KEY PRESSED --- */
        tPhase.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    bAddMeterDone.doClick();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //Do Nothing
            }
            @Override
            public void keyReleased(KeyEvent e) {
                //Do Nothing
            }
        });

        JLabel l6 = new JLabel("Days");
        l6.setFont(MyFonts.h7);
        l6.setForeground(MyColor.ANOTHER_GREY);
        l6.setBounds(90,320,80, 30);
        JLabel l7 = new JLabel("30");
        l7.setFont(MyFonts.h7);
        l7.setForeground(MyColor.BLUE_LIGHT);
        l7.setBounds(165,320,150, 30);



        /* --- BUTTONS --- */
        bAddMeterDone = new MyGradientButton("Done", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bAddMeterDone.setBounds(145,450,160,40);
        bAddMeterDone.setForeground(Color.WHITE);
        bAddMeterDone.addActionListener(this);



        /* --- ADD COMPONENTS TO PANEL --- */
        add(title);
        add(l1);
        add(lm);
        add(l2);
        add(tLocation);
        add(l3);
        add(tType);
        add(l4);
        add(tBill);
        add(l5);
        add(tPhase);
        add(l6);
        add(l7);
        add(bAddMeterDone);
        setLayout(new BorderLayout());
    }


    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bAddMeterDone) {

            if(tLocation.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Please choose a meter Location");
            if(tType.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Please choose a Meter type");
            if(tBill.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Please choose a Bill type");
            if(tPhase.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Please choose a Phase code");

            String sql = "INSERT INTO ebs.meter VALUES(?,?,?,?,?,'30')";
            MyConnection conn = new MyConnection();
            try (PreparedStatement s = conn.c.prepareStatement(sql)) {
                s.setObject(1,Dashboard.meter);
                s.setObject(2,tLocation.getSelectedItem());
                s.setObject(3,tType.getSelectedItem());
                s.setObject(4,tPhase.getSelectedItem());
                s.setObject(5,tBill.getSelectedItem());

                try (PreparedStatement ps3 = conn.c.prepareStatement("INSERT INTO account VALUES(?,?,?,?,?)")) {
                    ps3.setInt(1,Dashboard.meter);
                    ps3.setObject(2,"January");
                    ps3.setInt(3,10);
                    ps3.setInt(4,100);
                    ps3.setObject(5,"");
                    ps3.execute();
                } catch (SQLException e) {
                    System.out.println("SQLException: MeterAdd - account INSERT " + e.getMessage());
                }
                s.executeUpdate();

            } catch (SQLException e) {
                System.out.println("SQLException: MeterAdd - " + e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Meter added successfully");
            Dashboard.jMeterAdd.setVisible(false);
            Dashboard.pWelcome.setVisible(true);
        }
    }
}
