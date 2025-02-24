package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTextField extends JTextField implements ActionListener {

    MyTextField(){
        super();
        setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createTitledBorder(
                                null, null,
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("Verdana", Font.BOLD, 11)
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)
                )
        );
    }

    MyTextField(int col){
        super(col);
        setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createTitledBorder(
                                null, null,
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("Verdana", Font.BOLD, 11)
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)
                )
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == KeyStroke.getKeyStroke("control X")) {
            this.cut();
        }
        if(e.getSource() == KeyStroke.getKeyStroke("control C")) {
            this.copy();
        }
        if(e.getSource() == KeyStroke.getKeyStroke("control V")) {
            this.paste();
        }

    }
}
