package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyRoundedButton extends JButton  {
    private int radius;

    MyRoundedButton(String title, int radius) {
        super(title);
        this.radius = radius;
        setBorderPainted(true);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.BLUE);
        setBorder(new MyRoundedBorder(10));
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setForeground(MyColor.BLUE_LIGHT);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                setForeground(MyColor.BLUE);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }


}
