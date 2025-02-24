package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfilesReport extends JPanel implements ActionListener {

    static JTable t1;
    static JButton bCustomerReportSearch,bCustomerAdd,bCustomerEdit,bCustReportRefresh,bCustReportPrint,bCustReportClose;
    private final Choice cSuburb,cProvence;
    static int row = -1;
    private final MyConnection conn;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private static int meter;

    int col = 7;

    ProfilesReport() {
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.ANOTHER_GREY);
        setSize(760, 700);
        setLocation(10,10);
        setLayout(new BorderLayout());
        setBorder(new MyRoundedBorder(20));


        /* ---- COMPONENTS--- */
        JLabel title = new JLabel("Customer Report", SwingConstants.CENTER);
        title.setBounds(0,60,760, 30);
        title.setFont(MyFonts.h2);
        title.setForeground(MyColor.BLUE_LIGHT);
        add(title);

        /* --- FILTER FIELDS --- */
        JLabel customerSearchPanel = new JLabel();
        customerSearchPanel.setLocation(150,150);
        customerSearchPanel.setSize(600, 40);
        add(customerSearchPanel);

        JLabel lSuburb = new JLabel("Suburb:");
        lSuburb.setBounds(0, 10, 70, 20);
        lSuburb.setForeground(MyColor.GREY);
        lSuburb.setFont(MyFonts.h7);
        customerSearchPanel.add(lSuburb);

        cSuburb = new Choice();
        cSuburb.setForeground(MyColor.GREY);
        cSuburb.setBounds(75, 10, 100, 20);
        customerSearchPanel.add(cSuburb);

        JLabel lProvence = new JLabel("Provence:");
        lProvence.setForeground(MyColor.GREY);
        lProvence.setBounds(220, 10, 80, 20);
        lProvence.setFont(MyFonts.h7);
        customerSearchPanel.add(lProvence);

        cProvence = new Choice();
        cProvence.setForeground(MyColor.GREY);
        cProvence.setBounds(300, 10, 120, 20);
        customerSearchPanel.add(cProvence);

        bCustomerReportSearch = new MyRoundedButton("Search",20);
        bCustomerReportSearch.setBounds(460, 0,120,40);
        bCustomerReportSearch.addActionListener(this);
        customerSearchPanel.add(bCustomerReportSearch);

        /* --- TABLE --- */
        String[][] y = new String[7][col];
        String[] x = {"Customer", "Meter", "Address", "Town", "Province", "Email", "Phone"};
        t1 = new MyTable(y, x);
        conn  = new MyConnection();
        try (PreparedStatement s = conn.c.prepareStatement("SELECT * FROM customer WHERE meter!='0'")) {



            try (PreparedStatement s1 = conn.c.prepareStatement("SELECT DISTINCT suburb FROM customer WHERE meter!='0'")){
                var rs1 = s1.executeQuery();
                while(rs1.next()) {
                    cSuburb.add(rs1.getString("Suburb"));
                }
            } catch(SQLException e) {
                System.out.println("SQL Exception: CustomerReport + suburb");
            }


            try (PreparedStatement s2 = conn.c.prepareStatement("SELECT DISTINCT provence FROM customer WHERE meter!='0'")){
                var rs2 = s2.executeQuery();
                while(rs2.next()) {
                    cProvence.add(rs2.getString("Provence"));
                }
            } catch(SQLException e) {
                System.out.println("SQL Exception: CustomerReport + suburb");
            }


            ResultSet rs  = s.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(SQLException e) {
            System.out.println("SQL Exception: CustomerReport");
        }
        /* --- SET CLICK LINKS ON THE TABLE --- */
        t1.addMouseListener(new PopUpViewAddEditDeleteListener());
        t1.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                row = table.rowAtPoint(point);
                meter = (int) t1.getModel().getValueAt(row,1);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Dashboard.jProfile = new ProfileView(meter);
                    Dashboard.pRight.setLayout(new BorderLayout());
                    Dashboard.pRight.add(Dashboard.jProfile);
                    Dashboard.pRight.setLayout(new BorderLayout());
                    Dashboard.jProfile.setVisible(true);
                    Dashboard.jProfilesReport.setVisible(false);
                }
            }
        });
        /* --- CENTER CELL CONTENTS --- */
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < col; i++) {
            t1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(35, 210, 700, 350);
        sp.setBorder(BorderFactory.createLineBorder(MyColor.DARK_DARK_GREY,3));
        add(sp);
        setLayout(new BorderLayout());



        /* --- HIDDEN BUTTON --- */
        bCustomerEdit = new MyButton("Edit", MyColor.BLUE);
        bCustomerEdit.setBounds(130, 0,125,40);
        bCustomerEdit.addActionListener(this);


        /* --- BUTTONS --- */
        JLabel customersReportButtons = new JLabel();
        customersReportButtons.setLocation(120,580);
        customersReportButtons.setSize(680, 50);
        add(customersReportButtons);


        bCustomerAdd = new MyButton("Add", MyColor.BLUE);
        bCustomerAdd.setBounds(0, 0,125,40);
        bCustomerAdd.addActionListener(this);
        customersReportButtons.add(bCustomerAdd);


        bCustReportPrint = new MyButton("Print", MyColor.BLUE);
        bCustReportPrint.setBounds(135, 0,125,40);
        bCustReportPrint.addActionListener(this);
        customersReportButtons.add(bCustReportPrint);


        bCustReportClose = new MyButton("Close", MyColor.BLUE);
        bCustReportClose.setBounds(270, 0,125,40);
        bCustReportClose.addActionListener(this);
        customersReportButtons.add(bCustReportClose);


        bCustReportRefresh = new MyButton("Refresh", MyColor.BLUE);
        bCustReportRefresh.setBounds(405, 0,125,40);
        bCustReportRefresh.addActionListener(this);
        customersReportButtons.add(bCustReportRefresh);


        setLayout(new BorderLayout());
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bCustomerAdd) {
            DashWelcome.bWelcomeAdd.doClick();
            Dashboard.jProfilesReport.setVisible(false);
        }


        if (ae.getSource() == bCustomerEdit) {
            Dashboard.jProfileEdit.setVisible(false);
            if (row == -1) {
                JOptionPane.showMessageDialog(null,"Please select a Customer to edit.");
            } else {
                meter = (int) t1.getModel().getValueAt(row,1);
                Dashboard.jProfileEdit = new ProfileEdit(meter);
                Dashboard.pRight.add(Dashboard.jProfileEdit);
                Dashboard.jProfileEdit.setSize(340,420);
                Dashboard.jProfileEdit.setLocation(10,10);
                Dashboard.jProfileEdit.setVisible(true);
            }
        }


        if (ae.getSource() == bCustReportRefresh) {
            Dashboard.jProfilesReport.setVisible(false);
            Dashboard.jProfilesReport = new ProfilesReport();
            Dashboard.pRight.setLayout(new BorderLayout());
            Dashboard.pRight.add(Dashboard.jProfilesReport);
            Dashboard.pRight.setLayout(new BorderLayout());
            Dashboard.jProfilesReport.setVisible(true);
        }


        if (ae.getSource() == bCustReportPrint) {
            try {
                t1.print();
            } catch(Exception e) {
                System.out.println("Unable to print...");
            }
        }
        if (ae.getSource() == bCustReportClose) {
            Dashboard.jProfilesReport.setVisible(false);
            Dashboard.jProfileEdit.setVisible(false);
            Dashboard.jProfile.setVisible(false);
            Dashboard.jProfileAdd.setVisible(false);
            Dashboard.pWelcome.setVisible(true);
        }

        if (ae.getSource() == bCustomerReportSearch) {
            String sql = "SELECT * FROM customer WHERE suburb = ? AND provence = ?";
            try (PreparedStatement s = conn.c.prepareStatement(sql)) {
                s.setObject(1,cSuburb.getSelectedItem());
                s.setObject(2,cProvence.getSelectedItem());
                ResultSet rs = s.executeQuery();
                t1.setModel(DbUtils.resultSetToTableModel(rs));
                for (int i = 0; i < col; i++) {
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                    t1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
            } catch (SQLException e){
                System.out.println("SQL Exception: ProfileReport Search");
            }
        }
    }
}
