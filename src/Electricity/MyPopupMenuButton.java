package Electricity;

import java.awt.*;

public class MyPopupMenuButton extends MyButton {

    MyPopupMenuButton(String s1,String title,String s2) {
        super(s1,title,s2);
        setFont(MyFonts.h6);
        setForeground(Color.LIGHT_GRAY);
        setBackground(MyColor.DARKER_GREY);
        setSize(300,50);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);


    }
}
