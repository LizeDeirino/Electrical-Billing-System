package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PopUpViewAddEditDelete extends JPopupMenu implements ActionListener {

    static JButton bViewAccount,bEditAccount,bDeleteAccount;

    public PopUpViewAddEditDelete() {
        setBackground(MyColor.DARKER_GREY);
        setBorderPainted(false);

        JLabel l = new JLabel(" ");
        add(l);
        bViewAccount = new MyPopupMenuButton(" ","View Account"," ");
        bViewAccount.addActionListener(this);
        add(bViewAccount);
        JLabel l12 = new JLabel(" ");
        l12.setBackground(MyColor.DARK_DARK_GREY);
        add(l12);
        bEditAccount = new MyPopupMenuButton(" ","Update Account"," ");
        bEditAccount.addActionListener(this);
        add(bEditAccount);
        JLabel l13 = new JLabel(" ");
        l13.setBackground(MyColor.DARK_DARK_GREY);
        add(l13);
        bDeleteAccount = new MyPopupMenuButton(" ","Delete Account"," ");
        bDeleteAccount.addActionListener(this);
        add(bDeleteAccount);
        JLabel l14 = new JLabel(" ");
        l14.setBackground(MyColor.DARK_DARK_GREY);
        add(l14);

        setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        int meter;
        if (e.getSource() == bViewAccount){
            if (ProfilesReport.row == -1) {
                JOptionPane.showMessageDialog(null,"Please select an account to view.");
            } else {
                meter = (int) ProfilesReport.t1.getModel().getValueAt(ProfilesReport.row,1);
                Dashboard.jProfile = new ProfileView(meter);
                Dashboard.pRight.setLayout(new BorderLayout());
                Dashboard.pRight.add(Dashboard.jProfile);
                Dashboard.pRight.setLayout(new BorderLayout());
                Dashboard.jProfile.setVisible(true);
                Dashboard.jProfilesReport.setVisible(false);
            }
            this.setVisible(false);
        }


        if (e.getSource() == bEditAccount){
            if (ProfilesReport.row == -1) {
                JOptionPane.showMessageDialog(null,"Please select an account to update.");
            }  else {
                meter = (int) ProfilesReport.t1.getModel().getValueAt(ProfilesReport.row,1);
                Dashboard.jProfileEdit = new ProfileEdit(meter);
                Dashboard.pRight.setLayout(new BorderLayout());
                Dashboard.pRight.add(Dashboard.jProfileEdit);
                Dashboard.pRight.setLayout(new BorderLayout());
                Dashboard.jProfileEdit.setVisible(true);
                Dashboard.jProfilesReport.setVisible(false);
                this.setVisible(false);
            }
            this.setVisible(false);
        }


        if (e.getSource() == bDeleteAccount) {
            if (ProfilesReport.row == -1) {
                JOptionPane.showMessageDialog(null,"Please select an account to delete.");
            } else {
                meter = (int) ProfilesReport.t1.getModel().getValueAt(ProfilesReport.row,1);

                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + meter + "?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM customer WHERE meter=?";
                    String sql1 = "DELETE FROM meter WHERE meter=?";
                    String sql2 = "DELETE FROM account WHERE meter=?";
                    String sql3 = "DELETE FROM login WHERE meter=?";
                    MyConnection c = new MyConnection();
                    try(var s = c.c.prepareStatement(sql)) {
                        s.setObject(1, meter);


                        try(var s1 = c.c.prepareStatement(sql1)) {
                            s1.setObject(1, meter);
                            var rs1 = s1.execute();
                            if(rs1) {
                                System.out.println("Meter deleted");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQLException - popup delete - meter: " + ex.getMessage());
                        }


                        try(var s2 = c.c.prepareStatement(sql2)) {
                            s2.setObject(1, meter);
                            var rs2 = s2.execute();
                            if(rs2) {
                                System.out.println("Account Deleted");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQLException - popup delete - account: " + ex.getMessage());
                        }


                        try(var s3 = c.c.prepareStatement(sql3)) {
                            s3.setObject(1, meter);
                            var rs3 = s3.execute();
                            if(rs3) {
                                System.out.println("Login Deleted");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQLException - popup delete - login: " + ex.getMessage());
                        }

                        var rs = s.execute();
                        if(rs) {
                            System.out.println("Customer Deleted");
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQLException - popup delete - customer: " + ex.getMessage());
                    }
                } else {
                    this.setVisible(false);
                }
                this.setVisible(false);

            }
            ProfilesReport.bCustReportRefresh.doClick();
        }
    }
}
