package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton implements ActionListener {



    MyButton() {
        setForeground(Color.WHITE);
        setFont(MyFonts.h6);
        setSize(100, 100);
        setBackground(MyColor.DARK_DARK_GREY);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
    MyButton(String title) {
        super(title);
        setForeground(Color.WHITE);
        setSize(100,25);
        setFont(MyFonts.h6);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                setForeground(Color.lightGray);

            }

            @Override
            public void mouseExited(MouseEvent me) {
                setForeground(Color.white);
            }
        });
    }

    MyButton(String title, Color color) {
        super(title);
        setBackground(color);
        setForeground(Color.WHITE);
        setSize(100,25);
        setFont(MyFonts.h6);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                setForeground(Color.lightGray);

            }

            @Override
            public void mouseExited(MouseEvent me) {
                setForeground(Color.white);
            }
        });
    }

    MyButton(ImageIcon icon) {
        super(icon);
        JLabel iconLabel = new JLabel(icon);
        setLayout(new BorderLayout());
        add(iconLabel, BorderLayout.CENTER);
        setSize(30, 30);
        setBackground(MyColor.PINK);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    MyButton(String title, JLabel icon) {
        super();
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(MyFonts.h6);
        setLayout(new BorderLayout());
        add(icon, BorderLayout.WEST);
        add(label, BorderLayout.EAST);
        setSize(100, 100);
        setBackground(MyColor.LIGHT_GREY);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    MyButton(String space1, String title, String space2) {
        super();
        JLabel l1 = new JLabel(space1, SwingConstants.CENTER);
        l1.setForeground(MyColor.TRANSPARENT);
        l1.setFont(MyFonts.h6);
        JLabel l2 = new JLabel(title, SwingConstants.CENTER);
        l2.setForeground(Color.LIGHT_GRAY);
        l2.setFont(MyFonts.h6);
        JLabel l3 = new JLabel(space2, SwingConstants.CENTER);
        l3.setForeground(MyColor.TRANSPARENT);
        l3.setFont(MyFonts.h6);
        setLayout(new BorderLayout());
        add(l1, BorderLayout.EAST);
        add(l2, BorderLayout.CENTER);
        add(l3, BorderLayout.WEST);
        setSize(100, 100);
        setBackground(MyColor.DARKER_GREY);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(true);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                l2.setForeground(MyColor.BLUE_LIGHT);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                l2.setForeground(Color.LIGHT_GRAY);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
