package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    private final JTextField tUser,tPassword;
    private final JButton bLogin,bSignup,bClose;

    Login() {
        super("EPS | Login");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(MyColor.DARK_GREY);
        setSize(320,440);


        /* ---- LABELS & FIELDS --- */
        JLabel lWelcome = new JLabel("Welcome", SwingConstants.CENTER);
        lWelcome.setBounds(0, 50, 320, 55);
        lWelcome.setFont(MyFonts.h4);
        lWelcome.setForeground(MyColor.LIGHT_GREY);
        add(lWelcome);

        JLabel lUser = new JLabel("User");
        lUser.setBounds(78, 147, 50, 32);
        lUser.setFont(MyFonts.h5);
        lUser.setForeground(MyColor.LIGHT_GREY);
        add(lUser);
        tUser = new MyTextField(15);
        tUser.setBounds(125, 147, 125,32);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                tUser.requestFocus();
            }
        });
        add(tUser);

        JLabel lPassword = new JLabel("Password");
        lPassword.setBounds(45, 190, 70, 32);
        lPassword.setFont(MyFonts.h5);
        lPassword.setForeground(MyColor.LIGHT_GREY);
        add(lPassword);
        tPassword = new JPasswordField(15);
        tPassword.setBounds(125, 190, 125, 32);
        /* ---- password enter = login --- */
        tPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    bLogin.doClick();
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
        tPassword.setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createTitledBorder(
                                null, null,
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("Verdana", Font.PLAIN, 11)
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)
                )
        );
        add(tPassword);



        /* ---- BUTTONS --- */
        bLogin = new MyGradientButton("Login", MyColor.PLUM, MyColor.PINK);
        bLogin.setBounds(42,285,105,40);
        add(bLogin);

        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                bLogin.setForeground(MyColor.GREY);
            }
            @Override
            public void mouseExited(MouseEvent me) {
                bLogin.setForeground(Color.WHITE);
            }
        });


        bClose = new MyGradientButton("Close", MyColor.PINK, MyColor.ORANGE);
        bClose.setBounds(157,285,105,40);
        add(bClose);

        bClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                bClose.setForeground(MyColor.GREY);
            }
            @Override
            public void mouseExited(MouseEvent me) {
                bClose.setForeground(Color.WHITE);
            }
        });

        JLabel lSignup = new JLabel("Don’t have an account?");
        lSignup.setBounds(70,330,130,50);
        lSignup.setForeground(MyColor.LIGHT_GREY);
        lSignup.setFont(MyFonts.hGen);
        add(lSignup);

        bSignup = new MyButton("Sign Up");
        bSignup.setBounds(170,330,90,50);
        bSignup.setForeground(MyColor.LIGHT_GREY);
        bSignup.setFont(MyFonts.h8);
        add(bSignup);

        bClose.addActionListener(this);
        bLogin.addActionListener(this);
        bSignup.addActionListener(this);


        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }



    /* ---- CONNECTION & BUTTON DIRECTIONS --- */
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bLogin) {

            String name,username,user;
            int meter;
            try(Connection conn = DriverManager.getConnection("JDBC:mysql:///ebs","root","Nothing1234#");
                var ps = conn.prepareStatement("SELECT * FROM login WHERE Username=?")) {
                ps.setObject(1,tUser.getText());
                var rs = ps.executeQuery();

                if (rs.next()) {
                    // Check password match
                    if (BCrypt.checkpw(tPassword.getText(), rs.getString("Password"))) {
                        name = rs.getString("Name");
                        username = rs.getString("Username");
                        user = rs.getString("User");
                        meter = rs.getInt("Meter");
                        new Dashboard(name, username, user, meter);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(bLogin,"Incorrect Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(bLogin,"Your username is not present in our database, would you like to Sign Up for access?");
                }


            } catch (SQLException ex) {
                System.out.println("SQLException: bSignIn - " + ex.getMessage());
            }
        }
        if (ae.getSource() == bSignup) {
            this.dispose();
            new Signup();
        }
        if (ae.getSource() == bClose) {
            System.exit(0);
        }
    }


    /* ---- MAIN --- */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}



