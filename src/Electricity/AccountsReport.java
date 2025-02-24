package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsReport extends JPanel implements ActionListener{
 
    private final MyTable t1;
    private final JLabel billSearchPanel,billReportButtonPanel;
    static JButton bBillReportBillAdd,bBillReportPay,bBillReportBillEdit,bBillReportSearch,bBillReportRefresh,bBillReportPrint,bBillReportClose;
    private int row = -1;
    private final Choice cMeter,cMonth;
    private final MyConnection conn;
    private final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private final int col = 5;
    private int meter;

    AccountsReport() {
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.ANOTHER_GREY);
        setSize(760, 700);
        setLocation(10,10);
        setLayout(new BorderLayout());
        setBorder(new MyRoundedBorder(20));

        /* --- FILTER FIELDS --- */
        billSearchPanel = new JLabel();
        billSearchPanel.setLocation(90,100);
        billSearchPanel.setSize(600, 40);
        add(billSearchPanel);

        JLabel l1 = new JLabel("Meter No:");
        l1.setBounds(10, 10, 80, 20);
        l1.setForeground(MyColor.GREY);
        l1.setFont(MyFonts.h7);
        billSearchPanel.add(l1);

        cMeter = new Choice();
        cMeter.setForeground(MyColor.GREY);
        cMeter.setBounds(80, 10, 100, 20);
        billSearchPanel.add(cMeter);

        JLabel l2 = new JLabel("Month:");
        l2.setForeground(MyColor.GREY);
        l2.setBounds(240, 10, 50, 20);
        l1.setFont(MyFonts.h7);
        billSearchPanel.add(l2);

        cMonth = new Choice();
        cMonth.setForeground(MyColor.GREY);
        cMonth.setBounds(300, 10, 100, 20);
        billSearchPanel.add(cMonth);

        bBillReportSearch = new MyGradientButton("Search", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportSearch.setBounds(460, 0,120,40);
        bBillReportSearch.addActionListener(this);
        billSearchPanel.add(bBillReportSearch);

        setLayout(new BorderLayout());

        /* --- TABLE --- */
        String[] x = {"Meter Number","Month","Units","Total Bill","Status"};
        String[][] y = new String[40][col];
        t1 = new MyTable(y,x);
        conn = new MyConnection();
        try (var s = conn.c.prepareStatement("SELECT * FROM account WHERE meter!='0'")) {
            var rs  = s.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs));
            rs = s.executeQuery();
            while(rs.next()) {
                cMonth.add(rs.getString("Month"));
                cMeter.add(rs.getString("Meter"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: BillReport - Table Populate");
        }

        /* --- QUICK LINKS --- */
        t1.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent mouseEvent) {
                AccountView.bRefreshAccount.doClick();
                Dashboard.jAccount.setVisible(true);
                if(Dashboard.jAccountPay.isVisible()) {
                    AccountPay.bRefreshAccountPay.doClick();
                }
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                row = table.rowAtPoint(point);
                meter = (int)t1.getModel().getValueAt(row,0);
                AccountView.bRefreshAccount.doClick();
            }
        });

        /* --- CENTER CELL CONTENTS --- */
        for (int i = 0; i < col; i++) {
            t1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(10, 180, 720, 160);
        add(sp);

        sp.setBorder(BorderFactory.createLineBorder(MyColor.DARK_DARK_GREY,3));

        setLayout(new BorderLayout());


        /* --- HIDDEN BUTTON --- */
        bBillReportRefresh = new MyGradientButton("Refresh", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportRefresh.setBounds(180, 390,100,45);
        bBillReportRefresh.addActionListener(this);


        /* --- BUTTONS --- */
        billReportButtonPanel = new JLabel();
        billReportButtonPanel.setLocation(0,230);
        billReportButtonPanel.setSize(740, 50);
        add(billReportButtonPanel);


        bBillReportBillAdd = new MyGradientButton("Add", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportBillAdd.setBounds(50, 0,120,50);
        bBillReportBillAdd.addActionListener(this);
        billReportButtonPanel.add(bBillReportBillAdd);

        bBillReportBillEdit = new MyGradientButton("Edit", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportBillEdit.setBounds(180, 0,120,50);
        bBillReportBillEdit.addActionListener(this);
        billReportButtonPanel.add(bBillReportBillEdit);

        bBillReportPay = new MyGradientButton("Pay", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportPay.setBounds(310, 0,120,50);
        bBillReportPay.addActionListener(this);
        billReportButtonPanel.add(bBillReportPay);

        bBillReportPrint = new MyGradientButton("Print", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportPrint.setBounds(440, 0,120,50);
        bBillReportPrint.addActionListener(this);
        billReportButtonPanel.add(bBillReportPrint);

        bBillReportClose = new MyGradientButton("Close", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bBillReportClose.setBounds(570, 0,120,50);
        bBillReportClose.addActionListener(this);
        billReportButtonPanel.add(bBillReportClose);

        setLayout(new BorderLayout());
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bBillReportBillAdd) {
            Dashboard.jAccountAdd.setVisible(true);
        }
        if (ae.getSource() == bBillReportBillEdit) {
            Dashboard.jAccountReportEdit.setVisible(true);
        }
        if (ae.getSource() == bBillReportPay) {
            Dashboard.jAccountReportPay.setVisible(true);
        }
        if (ae.getSource() == bBillReportSearch) {
            String sql = "SELECT * FROM account WHERE meter = ? AND month = ?";
            try (PreparedStatement s = conn.c.prepareStatement(sql)) {
                s.setObject(1,cMeter.getSelectedItem());
                s.setObject(2,cMonth.getSelectedItem());
                ResultSet rs = s.executeQuery();
                t1.setModel(DbUtils.resultSetToTableModel(rs));
                for (int i = 0; i < col; i++) {
                    t1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
            } catch (SQLException e){
                System.out.println("SQL Exception: BillReport Search");
            }
        }
        if (ae.getSource() == bBillReportRefresh) {
            Dashboard.jAccountReport.setVisible(false);
            Dashboard.jAccountReport = new MeterReport();
            Dashboard.jPayments.add(Dashboard.jAccountReport);
            Dashboard.jPayments.setLayout(new BorderLayout());
            Dashboard.jAccountReport.setSize(740,220);
            Dashboard.jAccountReport.setBackground(MyColor.DARK_GREY);
            Dashboard.jAccountReport.setLocation(0,0);
            Dashboard.jAccountReport.setVisible(true);
        }
        if (ae.getSource() == bBillReportPrint) {
            try {
                t1.print();
            } catch(Exception e) {
                System.out.println("Unable to print...");
            }
        }
        if (ae.getSource() == bBillReportClose) {
            Dashboard.jAccountReport.setVisible(false);
            Dashboard.jAccountAdd.setVisible(false);
            Dashboard.jAccountReportEdit.setVisible(false);
            Dashboard.jAccountReportPay.setVisible(false);
            Dashboard.jPayments.setVisible(false);
        }
    }

    /* --- MAIN method --- */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AccountsReport::new);
    }
    
}
