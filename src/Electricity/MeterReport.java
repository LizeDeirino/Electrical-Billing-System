package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MeterReport extends JPanel implements ActionListener {

    private final MyTable t1;
    static JButton bMeterEdit,bMetersRefresh,bMetersPrint,bMetersReportClose;
    private int row = -1;
    private int meter;

    MeterReport() {
        setLocation(0,0);
        setSize(740, 220);
        setForeground(Color.WHITE);
        setBackground(MyColor.DARK_GREY);
        setBorder(new MyRoundedBorder(20));
        setLayout(new BorderLayout());


        /* --- TABLE --- */
        int col = 6;
        String[][] y = new String[6][col];
        String[] x = {"Meter", "Location", "Type", "Phase", "Bill", "Days"};
        t1 = new MyTable(y, x);
        MyConnection conn = new MyConnection();
        try (var s = conn.c.prepareStatement("SELECT * FROM meter WHERE meter!='0'")) {
            var rs  = s.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(SQLException e) {
            System.out.println("SQL Exception: MeterReport");
        }

        /* --- QUICK LINKS --- */
        t1.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent mouseEvent) {
                MeterView.bRefreshMeter.doClick();
                if(Dashboard.jMeterEdit.isVisible()) {
                    MeterEdit.bRefreshMeterUpdate.doClick();
                }
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                row = table.rowAtPoint(point);
                meter = (int) t1.getModel().getValueAt(row,0);
                MeterView.bRefreshMeter.doClick();
            }
        });


        /* --- CENTER CELL CONTENTS --- */
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < col; i++) {
            t1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 0, 740, 160);
        add(sp);
        sp.setBorder(BorderFactory.createLineBorder(MyColor.DARK_DARK_GREY,3));

        setLayout(new BorderLayout());


        /* --- HIDDEN BUTTON --- */
        bMetersRefresh = new MyGradientButton("Refresh", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMetersRefresh.setBounds(240, 170,125,40);
        bMetersRefresh.addActionListener(this);

        /* --- BUTTONS --- */
        JLabel metersReportButtons = new JLabel();
        metersReportButtons.setLocation(152,170);
        metersReportButtons.setSize(435, 50);
        add(metersReportButtons,SwingConstants.CENTER);

        bMeterEdit = new MyGradientButton("Edit", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMeterEdit.setBounds(0, 0,125,40);
        metersReportButtons.add(bMeterEdit);
        bMeterEdit.addActionListener(this);

        bMetersPrint = new MyGradientButton("Print", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMetersPrint.setBounds(130, 0,125,40);
        metersReportButtons.add(bMetersPrint);
        bMetersPrint.addActionListener(this);

        bMetersReportClose = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bMetersReportClose.setBounds(260, 0,125,40);
        metersReportButtons.add(bMetersReportClose);
        bMetersReportClose.addActionListener(this);

        setLayout(new BorderLayout());
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {


        if (ae.getSource() == bMeterEdit) {
            Dashboard.jMeterEdit.setVisible(false);
            if (row == -1) {
                JOptionPane.showMessageDialog(null,"Please select a Meter to edit.");
            } else {
                meter = (int) t1.getModel().getValueAt(row,0);
                new MeterEdit(meter);
            }
        }
        if (ae.getSource() == bMetersRefresh) {
            Dashboard.jMeterReport.setVisible(false);
            Dashboard.jMeterReport = new MeterReport();
            Dashboard.pRight.add(Dashboard.jMeterReport);
            Dashboard.pRight.setLayout(new BorderLayout());
            Dashboard.jMeterReport.setSize(740,220);
            Dashboard.jMeterReport.setBackground(MyColor.DARK_GREY);
            Dashboard.jMeterReport.setLocation(0,0);
            Dashboard.jMeterReport.setVisible(true);
        }
        if (ae.getSource() == bMetersPrint) {
            try {
                t1.print();
            } catch(Exception e) {
                System.out.println("Unable to print...");
            }
        }
        if (ae.getSource() == bMetersReportClose) {
            Dashboard.jMeterReport.setVisible(false);
            Dashboard.jMeter.setVisible(false);
            Dashboard.jMeterEdit.setVisible(false);
        }
    }

    /* --- MAIN method --- */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MeterReport::new);
    }
}
