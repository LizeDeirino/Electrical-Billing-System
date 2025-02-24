package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class MyGradientButton extends JButton {
    Color color1;
    Color color2;

    MyGradientButton(String text, Color color1, Color color2) {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        setFont(MyFonts.h6);
        setForeground(Color.white);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setForeground(Color.lightGray);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                setForeground(Color.white);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    MyGradientButton(String title, ImageIcon icon, Color color1, Color color2) {
        super();
        JLabel iconLabel = new JLabel(icon);
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(MyFonts.h6);
        this.color1 = color1;
        this.color2 = color2;

        setLayout(new BorderLayout());
        add(iconLabel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        setSize(100, 100);
        setBackground(MyColor.LIGHT_GREY);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    MyGradientButton(String title, JLabel icon, Color color1, Color color2) {
        super();
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(MyColor.LIGHT_GREY);
        label.setFont(MyFonts.h6);
        this.color1 = color1;
        this.color2 = color2;
        setLayout(new BorderLayout());
        add(icon, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        setSize(100, 100);
        setBackground(Color.white);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                label.setForeground(Color.lightGray);
                icon.setForeground(Color.lightGray);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                label.setForeground(Color.white);
                icon.setForeground(Color.white);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setPaint(new GradientPaint(
                new Point(50, 45),
                color1,
                new Point(120, 70),
                color2));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}

