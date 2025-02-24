package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JPanel implements ActionListener {

    static JButton bCloseAbout;

    public About() {

        /* ---- TITLE --- */
        JLabel l1 = new JLabel("About us",SwingConstants.CENTER);
        l1.setFont(MyFonts.h1);
        l1.setSize(355,50);
        l1.setLocation(0,20);
        l1.setForeground(MyColor.BLUE_LIGHT);
        add(l1);


        /* ---- TEXT  --- */
        String s = "This Electricity Billing System is distinctively developed in the Java programming language.\n " +
                "\n" +
                "This project aims to serve the community by digitizing the electrical billing functionality.\n " +
                "\n " +
                "With seamless integration, this unique project evolved into an effortless billing system accessible to the community as a whole.\n" +
                "\n" +
                "All components considered, Java is an alive language that expands daily. For the love of the language, one should remain mindful, upgrades are inevitable.";

        JTextArea t1 = new JTextArea(s);
        t1.setLineWrap(true);
        t1.setWrapStyleWord(true);
        t1.setFont(MyFonts.h6);
        t1.setEditable(false);
        t1.setBackground(MyColor.TRANSPARENT);
        t1.setForeground(Color.WHITE);
        t1.setBounds(50, 80, 255, 250);
        add(t1);

        /* --- BUTTONS --- */
        bCloseAbout = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bCloseAbout.setBounds(50,340,255,40);
        bCloseAbout.setForeground(Color.WHITE);
        add(bCloseAbout);
        bCloseAbout.addActionListener(this);

        setBackground(MyColor.DARK_GREY);
        setSize(355, 440);
        setLayout(new BorderLayout());
        setLocation(900,155);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bCloseAbout) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(About::new);
    }

}
