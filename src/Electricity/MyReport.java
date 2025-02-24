package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MyReport extends JFrame implements ActionListener{

    private MyTable t1;
    private final JButton bPrint, bClose;
    private final MyConnection conn;
    private JLabel title;

    MyReport(String name, int meter) {
        /* ---- FRAME ICON---- */
        super("PEB || Billing Report");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo.png")));
        setSize(735, 450);
        setLayout(null);
        setUndecorated(true);

        title = new JLabel("Billing Report for " + name);
        title.setFont(MyFonts.h2);
        title.setForeground(MyColor.DARK_GREY);
        title.setBounds(200,20,500,50);
        add(title);

        String[] x = {"Meter","Month","Units","Total Bill","Status"};
        int col=5;
        String[][] y = new String[40][col];
        t1 = new MyTable(y,x);

        conn = new MyConnection();
        try (var s = conn.c.prepareStatement("select * from bill where meter = ?")) {
            s.setObject(1,meter);
            var rs  = s.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println("SQL Exception in MyReport");
        }
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 80, 735, 300);
        add(sp);

        bPrint = new MyGradientButton("Print", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bPrint.setBounds(300, 390,100,45);
        bPrint.addActionListener(this);
        add(bPrint);

        bClose = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bClose.setBounds(420, 390,100,45);
        bClose.addActionListener(this);
        add(bClose);

        // center table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < col; i++) {
            t1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        setLocation(550,300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == bPrint) {
            try {
                t1.print();
            } catch(Exception e) {
                System.out.println("Unable to print...");
            }
        } else if (ae.getSource() == bClose) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MyReport("",0);
    }

}
