package Electricity;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;


public class ProfileAdd extends JPanel implements ActionListener {

    private JTextField tName,tUsername,tAddress,tSuburb,tProvence,tEmail,tPhone,tPassword;
    static JButton bAddCustomerRefresh,bAddCustomer,bAddCustomerCancel;
    private final JLabel title,lMet,lMeter,lName,lUsername,lAddress,lSuburb,lProvence,lEmail,lPhone,lPassword;
    private boolean vName,vUsername,vAddress,vSuburb,vProvence,vEmail,vPhone,vPassword;


    ProfileAdd() {
        setBackground(MyColor.DARKEST_GREY);
        setForeground(MyColor.ANOTHER_GREY);
        setSize(400,580);
        setLocation(185,90);
        setLayout(new BorderLayout());
        setBorder(new MyRoundedBorder(20));
        Dashboard.meter = MyConnection.setMeter(); //check for duplicates - done




        /* --- COMPONENTS--- */
        title = new JLabel("Add a Profile");
        title.setBounds(90,50,250, 30);
        title.setFont(MyFonts.h2);
        title.setForeground(MyColor.BLUE_LIGHT);
        add(title);


        lMeter = new JLabel("Meter");
        lMeter.setBounds(90,100,50,30);
        lMeter.setForeground(MyColor.ANOTHER_GREY);
        lMeter.setFont(MyFonts.h7);
        lMet = new JLabel("" + Dashboard.meter);
        lMet.setBounds(165,100,50,30);
        lMet.setForeground(MyColor.ANOTHER_GREY);
        lMet.setFont(MyFonts.h7);


        lName = new JLabel("Full Name");
        lName.setBounds(90,140,80,30);
        lName.setForeground(MyColor.ANOTHER_GREY);
        lName.setFont(MyFonts.h7);
        tName = new MyTextField();
        tName.setBounds(165,140,150,30);
        /* --- set cursor to name text field at start --- */
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                tName.requestFocus();
            }
        });
        /* --- Name flag --- */
        tName.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                //*
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(!tName.getText().isEmpty()) {
                    vName = true;
                }

            }
        });



        lUsername = new JLabel("Username");
        lUsername.setBounds(90,180,80, 30);
        lUsername.setForeground(MyColor.ANOTHER_GREY);
        lUsername.setFont(MyFonts.h7);
        tUsername = new MyTextField();
        tUsername.setBounds(165,180,150,30);
        /* --- check if username is available in database --- */
        tUsername.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tUsername.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tUsername.getText().isEmpty()) {
                    try (Connection c = DriverManager.getConnection("JDBC:mysql:///ebs","root","Nothing1234#");
                         PreparedStatement ps = c.prepareStatement("SELECT * FROM login")) {
                        var rs = ps.executeQuery();
                        while(rs.next()) {
                            if (rs.getString("Username").equalsIgnoreCase(tUsername.getText())) {
                                JOptionPane.showMessageDialog(null, "Unfortunately this username is not available.");
                                break;
                                /* --- Username flag --- */
                            } else vUsername = true;
                        }

                    } catch (SQLException ex) {
                        System.out.println("SQLException: SignUp - Username Validation " + ex.getMessage());
                    }
                }
            }
        });


        lAddress = new JLabel("Address");
        lAddress.setBounds(90,220,80, 30);
        lAddress.setForeground(MyColor.ANOTHER_GREY);
        lAddress.setFont(MyFonts.h7);
        tAddress = new MyTextField();
        tAddress.setBounds(165,220,150, 30);
        /* --- address flag --- */
        tAddress.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                //*
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tAddress.getText().isEmpty()) {
                    vAddress = true;
                }

            }
        });



        lSuburb = new JLabel("Suburb");
        lSuburb.setBounds(90,260,80, 30);
        lSuburb.setForeground(MyColor.ANOTHER_GREY);
        lSuburb.setFont(MyFonts.h7);
        tSuburb = new MyTextField();
        tSuburb.setBounds(165,260,150, 30);
        /* --- suburb flag --- */
        tSuburb.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                //*
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!tSuburb.getText().isEmpty()) vSuburb = true;
            }
        });



        lProvence = new JLabel("Provence");
        lProvence.setBounds(90,300,80, 30);
        lProvence.setForeground(MyColor.ANOTHER_GREY);
        lProvence.setFont(MyFonts.h7);
        tProvence = new MyTextField();
        tProvence.setBounds(165,300,150, 30);
        /* --- provence flag --- */
        tProvence.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                //*
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!tProvence.getText().isEmpty()) vProvence = true;
            }
        });


        lEmail = new JLabel("Email");
        lEmail.setBounds(90,340,80, 30);
        lEmail.setForeground(MyColor.ANOTHER_GREY);
        lEmail.setFont(MyFonts.h7);
        tEmail = new MyTextField();
        tEmail.setBounds(165,340,150, 30);
        /* ---- validate email & flag --- */
        tEmail.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                //*
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tEmail.getText().isEmpty()) {
                    Pattern rEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                    if (!rEmail.matcher(tEmail.getText()).matches()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid email address");
                    } else vEmail = true;
                }

            }
        });

        lPhone = new JLabel("Phone");
        lPhone.setBounds(90,380,80, 30);
        lPhone.setForeground(MyColor.ANOTHER_GREY);
        lPhone.setFont(MyFonts.h7);
        tPhone = new MyTextField();
        tPhone.setBounds(165,380,150, 30);
        /* ---- validate & format phone & set flag --- */
        tPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tPhone.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tPhone.getText().isEmpty() && tPhone.getText().length() == 10 || tPhone.getText().length() == 15) {
                    String numberToPhone = tPhone.getText();
                    String a = numberToPhone.substring(1,3);
                    String b = numberToPhone.substring(3,6);
                    String c = numberToPhone.substring(6,10);
                    String formattedPhoneNumber = "+27 (" + a + ") " + b + "-" + c;
                    tPhone.setText(formattedPhoneNumber);
                    vPhone = true;
                } else {
                    if (tPhone.getText().length() > 1 && tPhone.getText().length() < 10)
                        JOptionPane.showMessageDialog(null, "Your phone number is incomplete.");
                }

            }
        });


        lPassword = new JLabel("Password");
        lPassword.setBounds(90,420,80, 30);
        lPassword.setForeground(MyColor.ANOTHER_GREY);
        lPassword.setFont(MyFonts.h7);
        tPassword = new JPasswordField();
        /* ---- tooltip for password ---- */
        String s = "At least 8 characters containing a digit, upper- & lowercase character, one (!@#$%&*-+=^) & no spaces.";
        tPassword.setToolTipText(s);
        tPassword.setBounds(165,420,150, 30);
        tPassword.setBorder(
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
        /* ---- validate password ---- */
        tPassword.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tPassword.setText("");
                String s = "At least 8 characters containing a digit, upper- & lowercase character, one (!@#$%&*-+=^) & no spaces.";
                tPassword.setToolTipText(s);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tPassword.getText().isEmpty()) {
                    Pattern regexPass = Pattern.compile("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");
                    if (!regexPass.matcher(tPassword.getText()).matches()) {
                        JOptionPane.showMessageDialog(null, "Please rethink your password carefully.");
                    } else vPassword = true;
                }
            }
        });
        /* ---- enter key pressed  --- */
        tPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    if (!tPassword.getText().isEmpty()) {
                        Pattern regexPass = Pattern.compile("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");
                        if (!regexPass.matcher(tPassword.getText()).matches()) {
                            JOptionPane.showMessageDialog(null, "Please rethink your password carefully.");
                        } else vPassword = true;
                    }
                    bAddCustomer.doClick();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //Do Nothing
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Do Nothing
            }
        });



        /* --- BUTTONS --- */
        bAddCustomerRefresh = new MyButton("a");
        bAddCustomerRefresh.setFont(MyFonts.hRefresh);
        bAddCustomerRefresh.setForeground(MyColor.BLUE);
        bAddCustomerRefresh.setBounds(360,20,15,15);
        bAddCustomerRefresh.addActionListener(this);

        bAddCustomer = new MyGradientButton("Next", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bAddCustomer.setBounds(80,490,120,40);
        bAddCustomer.addActionListener(this);

        bAddCustomerCancel = new MyGradientButton("Cancel", MyColor.BLUE, MyColor.BLUE_LIGHT);
        bAddCustomerCancel.setBounds(210,490,120,40);
        bAddCustomerCancel.addActionListener(this);


        /* --- ADD COMPONENTS TO PANEL --- */
        add(lMeter);
        add(lMet);
        add(lName);
        add(tName);
        add(lUsername);
        add(tUsername);
        add(lAddress);
        add(tAddress);
        add(lSuburb);
        add(tSuburb);
        add(lProvence);
        add(tProvence);
        add(lEmail);
        add(tEmail);
        add(lPhone);
        add(tPhone);
        add(lPassword);
        add(tPassword);
        add(bAddCustomer);
        add(bAddCustomerCancel);
        add(bAddCustomerRefresh);
        setLayout(new BorderLayout());
    }

    /* --- ACTION EVENTS --- */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bAddCustomerRefresh) {
            Dashboard.meter = MyConnection.setMeter();
            Dashboard.jProfileAdd.setVisible(false);
            Dashboard.jProfileAdd = new ProfileAdd();
            Dashboard.pRight.setLayout(new BorderLayout());
            Dashboard.pRight.add(Dashboard.jProfileAdd);
            Dashboard.pRight.setLayout(new BorderLayout());
            Dashboard.jProfileAdd.setVisible(true);
        }

        if (ae.getSource() == bAddCustomer) {
            /* --- check all flags --- */
            if(!vName) JOptionPane.showMessageDialog(null, "Please add a customer name");
            if(!vAddress) JOptionPane.showMessageDialog(null, "Please add an address");
            if(!vSuburb) JOptionPane.showMessageDialog(null, "Please add a suburb");
            if(!vProvence) JOptionPane.showMessageDialog(null, "Please add a provence");
            if(!vUsername) JOptionPane.showMessageDialog(null, "Please add a valid username");
            if(!vEmail) JOptionPane.showMessageDialog(null, "Please add a valid email");
            if(!vPhone) JOptionPane.showMessageDialog(null, "Please add a contact number");
            if(!vPassword) JOptionPane.showMessageDialog(null, "Please choose a strong password");


            if(vName && vAddress && vSuburb && vProvence && vUsername && vEmail && vPhone && vPassword) {
                String sql1 = "INSERT INTO ebs.customer VALUES(?,?,?,?,?,?,?)";
                MyConnection conn = new MyConnection();
                try (PreparedStatement s = conn.c.prepareStatement(sql1)) {
                    s.setObject(1, tName.getText());
                    s.setObject(2, Dashboard.meter);
                    s.setObject(3, tAddress.getText());
                    s.setObject(4, tSuburb.getText());
                    s.setObject(5, tProvence.getText());
                    s.setObject(6, tEmail.getText());
                    s.setObject(7, tPhone.getText());
                    s.executeUpdate();

                    String sql3 = "INSERT INTO ebs.login VALUES('Customer',?,?,?,?,?,?)";
                    try (PreparedStatement s2 = conn.c.prepareStatement(sql3)) {
                        s2.setObject(1, Dashboard.meter);
                        s2.setObject(2, tName.getText());
                        s2.setObject(3, tPhone.getText());
                        s2.setObject(4, tEmail.getText());
                        s2.setObject(5, tUsername.getText());
                        String password = tPassword.getText();
                        // salt & hash Password with BCrypt 0.4
                        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
                        s2.setObject(6, hashed);
                        s2.execute();
                        tName.setText("");
                        tUsername.setText("");
                        tAddress.setText("");
                        tSuburb.setText("");
                        tProvence.setText("");
                        tEmail.setText("");
                        tPhone.setText("");
                        tPassword.setText("");
                        JOptionPane.showMessageDialog(null, "Customer Added");
                        Dashboard.jProfileAdd.setVisible(false);
                        Dashboard.jMeterAdd.setVisible(true);
                    } catch (SQLException e) {
                        System.out.println("SQLException: CustomerAdd - login " + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Oeps! Something went wrong! Please revisit your entries and try again");
                    }

                } catch (SQLException e) {
                    System.out.println("SQL Exception: CustomerAdd - customer " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Oeps! Something went wrong! Please revisit your entries and try again");
                }

            } else JOptionPane.showMessageDialog(null, "Please make sure you have entered all the fields correctly");

        }

        if (ae.getSource() == bAddCustomerCancel) {
            Dashboard.jProfileAdd.setVisible(false);
            Dashboard.pWelcome.setVisible(true);
        }
    }
}
