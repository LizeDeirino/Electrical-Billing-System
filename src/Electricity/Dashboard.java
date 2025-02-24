package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dashboard extends JFrame implements ActionListener {

    static JPanel pRight,pLeft,pWelcome,
            jProfile,jProfileEdit,jProfilesReport,jProfileAdd,
            jMeter,jMeterDelete,jMeterReport,jMeterEdit,jMeterAdd,jAbout,
            jAccount,jAccountPay,jPayments,jAccountDelete,jAccountAdd,
            jAccountReportPay,jAccountReportEdit,jAccountReport;
    public static String userType,username,myName;
    public static int myMeter,meter;
    public static JButton logout;


    Dashboard(String userType, String username, String name, int meter) {
        super("Welcome to EBS Dashboard");
        setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icon/logo.png")));
        setSize(1100,760);
        setLocationRelativeTo(null);
        getContentPane().setBackground(MyColor.DARKEST_GREY);
        getContentPane().setForeground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        /* --- INITIALIZE VARIABLES --- */
        Dashboard.myMeter = meter;
        Dashboard.userType = userType;
        Dashboard.username = username;
        Dashboard.myName = name;

        /* ---- LEFT PANEL --- */
        pLeft = new DashLeft(userType);
        add(pLeft);



        /* ---- MAIN PANEL --- */
        pRight = new JPanel();
        pRight.setBackground(MyColor.DARKEST_GREY);
        pRight.setSize(800,760);
        pRight.setLocation(300,0);
        pRight.setLayout(new BorderLayout());
        add(pRight);

        pWelcome = new DashWelcome();
        pRight.add(pWelcome);
        pRight.setLayout(new BorderLayout());

        /* --- COMPONENT PANELS --- */
        /* --- profile panels --- */
        jProfilesReport = new ProfilesReport();
        jProfilesReport.setVisible(false);
        pRight.add(jProfilesReport);
        pRight.setLayout(new BorderLayout());

        jProfile = new ProfileView(meter);
        jProfile.setVisible(false);
        pRight.add(jProfile);
        pRight.setLayout(new BorderLayout());

        jProfileAdd = new ProfileAdd();
        jProfileAdd.setVisible(false);
        pRight.add(jProfileAdd);
        pRight.setLayout(new BorderLayout());

        jProfileEdit = new ProfileEdit(meter);
        jProfileEdit.setVisible(false);
        pRight.add(jProfileEdit);
        pRight.setLayout(new BorderLayout());




        /* --- meter panels --- */
        jMeter = new MeterView(meter);
        jMeter.setVisible(false);
        pRight.add(jMeter);

        jMeterAdd = new MeterAdd();
        jMeterAdd.setVisible(false);
        pRight.add(jMeterAdd);

        jAbout = new About();
        jAbout.setVisible(false);
        pRight.add(jAbout);

        jMeterEdit = new MeterEdit(meter);
        jMeterEdit.setVisible(false);
        pRight.add(jMeterEdit);

        jMeterDelete = new MeterDelete(meter);
        jMeterDelete.setVisible(false);
        pRight.add(jMeterDelete);

        jMeterReport = new MeterReport();
        jMeterReport.setVisible(false);
        pRight.add(jMeterReport);


        /* --- account panels  --- */
        jAccount = new AccountView(meter);
        jAccount.setVisible(false);
        pRight.add(jAccount);

        jAccountAdd = new AccountAdd();
        jAccountAdd.setVisible(false);
        pRight.add(jAccountAdd);

        jAccountReportEdit = new AccountEdit(meter);
        jAccountReportEdit.setVisible(false);
        pRight.add(jAccountReportEdit);

        jAccountPay = new AccountPay(meter);
        jAccountPay.setVisible(false);
        pRight.add(jAccountPay);

        jAccountDelete = new AccountDelete(meter);
        jAccountDelete.setVisible(false);
        pRight.add(jAccountDelete);

        jAccountReport = new AccountsReport();
        jAccountReport.setVisible(false);
        pRight.add(jAccountReport);

        logout = new JButton();
        logout.addActionListener(this);

        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == logout) {
            this.dispose();
            new Login();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard("Administrator","Lize","Lize Deirino",0));
    }
}
