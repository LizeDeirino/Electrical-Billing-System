package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Signup extends JFrame implements ActionListener {
    private final JButton bSignUp,bCancel;
    private final JTextField tProvence,tSuburb,tAddress,tUsername,tName,tPassword,tEmail,tPhone;
    private final JLabel lCorrectProvence,lCorrectSuburb,lCorrectAddress,lCorrectUser,lCorrectName,lCorrectPassword,lCorrectEmail,lCorrectPhone;


    Signup() {
        super("EPS | Sign Up");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(MyColor.DARK_GREY);
        setSize(380,580);


        /* ---- LABELS & FIELDS --- */
        JLabel lSignUp = new JLabel("Sign Up",SwingConstants.CENTER);
        lSignUp.setBounds(0, 20, 360, 50);
        lSignUp.setForeground(MyColor.LIGHT_GREY);
        lSignUp.setFont(MyFonts.h4);
        add(lSignUp);


        /* -- NAME & VALIDATION -- */
        JLabel lName = new JLabel("Full Name");
        lName.setBounds(70, 100, 100, 30);
        lName.setFont(MyFonts.h5);
        lName.setForeground(MyColor.LIGHT_GREY);
        add(lName);
        tName = new MyTextField();
        tName.setBounds(160, 100, 140,30);
        add(tName);
        lCorrectName = new JLabel("√");
        lCorrectName.setBounds(290, 100, 100, 30);
        lCorrectName.setVisible(false);
        add(lCorrectName);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                tName.requestFocus();
            }
        });
        tName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectName.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (tName.getText().isEmpty()) {
                    lCorrectName.setVisible(false);
                } else {
                    lCorrectName.setText("√");
                    lCorrectName.setVisible(true);
                }
            }
        });



        /* -- ADDRESS & VALIDATION -- */
        JLabel lAddress = new JLabel("Address");
        lAddress.setBounds(70, 140, 100, 30);
        lAddress.setFont(MyFonts.h5);
        lAddress.setForeground(MyColor.LIGHT_GREY);
        add(lAddress);
        tAddress = new MyTextField();
        tAddress.setBounds(160, 140, 140,30);
        add(tAddress);
        lCorrectAddress = new JLabel("√");
        lCorrectAddress.setBounds(290, 140, 100, 30);
        lCorrectAddress.setVisible(false);
        add(lCorrectAddress);
        tAddress.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectAddress.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (tAddress.getText().isEmpty()) {
                    lCorrectAddress.setVisible(false);
                } else {
                    lCorrectAddress.setText("√");
                    lCorrectAddress.setVisible(true);
                }
            }
        });


        /* -- SUBURB & VALIDATION -- */
        JLabel lSuburb = new JLabel("Suburb");
        lSuburb.setBounds(70, 180, 100, 30);
        lSuburb.setFont(MyFonts.h5);
        lSuburb.setForeground(MyColor.LIGHT_GREY);
        add(lSuburb);
        tSuburb = new MyTextField();
        tSuburb.setBounds(160, 180, 140,30);
        add(tSuburb);
        lCorrectSuburb = new JLabel("√");
        lCorrectSuburb.setBounds(290, 180, 100, 30);
        lCorrectSuburb.setVisible(false);
        add(lCorrectSuburb);
        tSuburb.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectSuburb.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (tSuburb.getText().isEmpty()) {
                    lCorrectSuburb.setVisible(false);
                } else {
                    lCorrectSuburb.setText("√");
                    lCorrectSuburb.setVisible(true);
                }
            }
        });


        /* -- SUBURB & VALIDATION -- */
        JLabel lProvence = new JLabel("Provence");
        lProvence.setBounds(70, 220, 100, 30);
        lProvence.setFont(MyFonts.h5);
        lProvence.setForeground(MyColor.LIGHT_GREY);
        add(lProvence);
        tProvence = new MyTextField();
        tProvence.setBounds(160, 220, 140,30);
        add(tProvence);
        lCorrectProvence = new JLabel("√");
        lCorrectProvence.setBounds(290, 220, 100, 30);
        lCorrectProvence.setVisible(false);
        add(lCorrectProvence);
        tProvence.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectProvence.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (tProvence.getText().isEmpty()) {
                    lCorrectProvence.setVisible(false);
                } else {
                    lCorrectProvence.setText("√");
                    lCorrectProvence.setVisible(true);
                }
            }
        });


        /* -- PHONE & VALIDATION -- */
        JLabel lPhone = new JLabel("Phone");
        lPhone.setBounds(70, 260, 100, 30);
        lPhone.setFont(MyFonts.h5);
        lPhone.setForeground(MyColor.LIGHT_GREY);
        add(lPhone);
        tPhone = new MyTextField();
        tPhone.setBounds(160, 260, 140,30);
        lCorrectPhone = new JLabel("√");
        lCorrectPhone.setBounds(290, 260, 100, 30);
        lCorrectPhone.setVisible(false);
        add(lCorrectPhone);
        tPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectPhone.setVisible(false);
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
                    lCorrectPhone.setText("√");
                    lCorrectPhone.setVisible(true);
                } else if (tPhone.getText().length() > 1 && tPhone.getText().length() < 10) {
                    lCorrectPhone.setVisible(false);
                    JOptionPane.showMessageDialog(lCorrectPhone, "Your phone number is incomplete.");
                } else {
                    lCorrectPhone.setVisible(false);
                }

            }
        });
        add(tPhone);

        /* -- EMAIL & VALIDATION -- */
        JLabel lEmail = new JLabel("Email");
        lEmail.setBounds(70, 300, 100, 30);
        lEmail.setFont(MyFonts.h5);
        lEmail.setForeground(MyColor.LIGHT_GREY);
        add(lEmail);
        tEmail = new MyTextField();
        tEmail.setBounds(160, 300, 140,30);
        add(tEmail);
        lCorrectEmail = new JLabel("√");
        lCorrectEmail.setBounds(290, 300, 100, 30);
        lCorrectEmail.setVisible(false);
        add(lCorrectEmail);
        tEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectEmail.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (tEmail.getText().isEmpty()) {
                    lCorrectEmail.setVisible(false);
                } else {
                    Pattern rEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

                    if (!rEmail.matcher(tEmail.getText()).matches()) {
                        lCorrectEmail.setVisible(false);
                        JOptionPane.showMessageDialog(lCorrectPhone, "This email is not valid.");
                    } else {
                        lCorrectEmail.setText("√");
                        lCorrectEmail.setVisible(true);
                    }
                }
            }
        });


        /* -- USERNAME & USER VALIDATION -- */
        JLabel lUsername = new JLabel("Username");
        lUsername.setBounds(70, 340, 100, 30);
        lUsername.setFont(MyFonts.h5);
        lUsername.setForeground(MyColor.LIGHT_GREY);
        add(lUsername);
        tUsername = new MyTextField();
        tUsername.setBounds(160, 340, 140,30);

        add(tUsername);
        lCorrectUser = new JLabel("√");
        lCorrectUser.setBounds(290, 340, 100, 30);
        lCorrectUser.setForeground(MyColor.BLUE_LIGHT);
        lCorrectUser.setVisible(false);
        add(lCorrectUser);
        tUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lCorrectUser.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tUsername.getText().isEmpty()) {
                    try (Connection c = DriverManager.getConnection("JDBC:mysql:///ebs","root","Nothing1234#");
                         PreparedStatement ps = c.prepareStatement("SELECT * FROM login")) {
                        var rs = ps.executeQuery();
                        while(rs.next()) {
                            if (rs.getString("Username").equalsIgnoreCase(tUsername.getText())) {
                                lCorrectUser.setVisible(false);
                                JOptionPane.showMessageDialog(lCorrectUser, "Oh no! This username is taken.");
                                break;
                            } else {
                                lCorrectUser.setText("√");
                                lCorrectUser.setVisible(true);
                            }
                        }

                    } catch (SQLException ex) {
                        System.out.println("SQLException: SignUp - Username Validation " + ex.getMessage());
                    }
                } else lCorrectUser.setVisible(false);
            }
        });


        /* -- PASSWORD & VALIDATION -- */
        JLabel lPassword = new JLabel("Password");
        lPassword.setBounds(70, 380, 100, 30);
        lPassword.setFont(MyFonts.h5);
        lPassword.setForeground(MyColor.LIGHT_GREY);
        add(lPassword);
        tPassword = new JPasswordField();
        String s = "At least 8 characters containing a digit, upper- & lowercase character, one (!@#$%&*-+=^) & no spaces.";
        tPassword.setToolTipText(s);
        tPassword.setBounds(160, 380, 140, 30);
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
        lCorrectPassword = new JLabel("√");
        lCorrectPassword.setBounds(290, 380, 100, 30);
        lCorrectPassword.setVisible(false);
        add(lCorrectPassword);
        tPassword.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                lCorrectPassword.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!tPassword.getText().isEmpty()) {
                    Pattern regexPass = Pattern.compile("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");

                    if (!regexPass.matcher(tPassword.getText()).matches() && !tPassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(lCorrectPassword, "Please rethink your password carefully.");
                    } else {
                        lCorrectPassword.setText("√");
                        lCorrectPassword.setVisible(true);
                    }
                }
            }
        });
        add(tPassword);



        /* -- BUTTONS -- */
        bSignUp = new MyGradientButton("Sign Up", MyColor.PLUM, MyColor.PINK);
        bSignUp.setBounds(50,450,120,40);
        bSignUp.addActionListener(this);
        bSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                bSignUp.setForeground(MyColor.GREY);
            }
            @Override
            public void mouseExited(MouseEvent me) {
                bSignUp.setForeground(Color.WHITE);
            }
        });
        add(bSignUp);

        bCancel = new MyGradientButton("Cancel", MyColor.PINK, MyColor.ORANGE);
        bCancel.setBounds(180,450,120,40);
        bCancel.addActionListener(this);
        bCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                bCancel.setForeground(MyColor.GREY);
            }
            @Override
            public void mouseExited(MouseEvent me) {
                bCancel.setForeground(Color.WHITE);
            }
        });
        add(bCancel);


        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* -- BUTTON ACTIONS -- */
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bSignUp) {
            int meter = MyConnection.setMeter();
            try (var c = DriverManager.getConnection("JDBC:mysql:///ebs", "root", "Nothing1234#");
                 var ps = c.prepareStatement("INSERT INTO login VALUES(?,?,?,?,?,?,?)")) {

                // Populate Database - Table: login
                ps.setObject(1, "Customer");
                ps.setInt(2, meter);
                ps.setObject(3, tName.getText());
                ps.setObject(4, tPhone.getText());
                ps.setObject(5, tEmail.getText());
                ps.setObject(6, tUsername.getText());
                String password = tPassword.getText();
                // salt & hash Password with BCrypt 0.4
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
                ps.setObject(7, hashed);


                // Populate Database - Table: customer
                try (var ps1 = c.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?)")) {
                    ps1.setObject(1,tName.getText());
                    ps1.setInt(2,meter);
                    ps1.setObject(3,tAddress.getText());
                    ps1.setObject(4,tSuburb.getText());
                    ps1.setObject(5,tProvence.getText());
                    ps1.setObject(6,tEmail.getText());
                    ps1.setObject(7,tPhone.getText());
                    ps1.execute();
                } catch (SQLException e) {
                    System.out.println("SQLException: SignUp - customer insert " + e.getMessage());
                }

                // Populate Database - Table: meter
                try (var ps2 = c.prepareStatement("INSERT INTO meter VALUES(?,?,?,?,?,?)")) {
                    ps2.setInt(1,meter);
                    ps2.setObject(2,"Unknown");
                    ps2.setObject(3,"Unknown");
                    ps2.setObject(4,"Unknown");
                    ps2.setObject(5,"Unknown");
                    ps2.setObject(6,"30");
                    ps2.execute();
                } catch (SQLException e) {
                    System.out.println("SQLException: SignUp - meter insert " + e.getMessage());
                }

                // Populate Database - Table: account
                try (var ps3 = c.prepareStatement("INSERT INTO account VALUES(?,?,?,?,?)")) {
                    ps3.setInt(1,meter);
                    ps3.setObject(2,"Unknown");
                    ps3.setInt(3,0);
                    ps3.setInt(4,0);
                    ps3.setObject(5,"Unknown");
                    ps3.execute();
                } catch (SQLException e) {
                    System.out.println("SQLException: SignUp - account insert " + e.getMessage());
                }

                // Execute populate Database - Table: login
                ps.execute();
                JOptionPane.showMessageDialog(null, "Please check your email for your access information");

                this.dispose();
            } catch (SQLException e) {
                System.out.println("SQLException: SignUp - login insert " + e.getMessage());
            }
        }
        if (ae.getSource() == bCancel) {
            System.exit(0);
        }
    }

    /* -- MAIN -- */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}
