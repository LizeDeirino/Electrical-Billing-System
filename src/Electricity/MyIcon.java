package Electricity;

import javax.swing.*;

import java.awt.*;

public class MyIcon extends JLabel {

    static JLabel iUser,iMeter,iPlay,iPay,iReport,iBrowse,iCalculator,
            iUpdate,iDelete,iRefresh,iClose,iSearch,iNote,iEbs,iLogout,
            iCustomer,iMeters,iAccount;

    static {
        iUser = new MyIcon("a", SwingConstants.CENTER);
        iUser.setFont(MyFonts.hUser);

        iMeter = new MyIcon("a", SwingConstants.CENTER);
        iMeter.setFont(MyFonts.hMeter);

        iPay = new MyIcon("a", SwingConstants.CENTER);
        iPay.setFont(MyFonts.hPay);

        iRefresh = new MyIcon("a", SwingConstants.CENTER);
        iRefresh.setFont(MyFonts.hRefresh);

        iCustomer = new MyIcon("a", SwingConstants.CENTER);
        iCustomer.setFont(MyFonts.hUser);

        iMeters = new MyIcon("a", SwingConstants.CENTER);
        iMeters.setFont(MyFonts.hMeter);

        iAccount = new MyIcon("a", SwingConstants.CENTER);
        iAccount.setFont(MyFonts.hPay);

        iClose = new MyIcon("a", SwingConstants.CENTER);
        iClose.setFont(MyFonts.hClose);

        iCalculator = new MyIcon("a", SwingConstants.CENTER);
        iCalculator.setFont(MyFonts.hCalculator);

        iBrowse = new MyIcon("a", SwingConstants.CENTER);
        iBrowse.setFont(MyFonts.hBrowse);

        iNote = new MyIcon("a", SwingConstants.CENTER);
        iNote.setFont(MyFonts.hNote);

        iLogout = new MyIcon("a", SwingConstants.CENTER);
        iLogout.setFont(MyFonts.hLogout);

        iReport = new MyIcon("a", SwingConstants.CENTER);
        iReport.setFont(MyFonts.hReport);

        iUpdate = new MyIcon("a", SwingConstants.CENTER);
        iUpdate.setFont(MyFonts.hEdit);

        iPlay = new MyIcon("a", SwingConstants.CENTER);
        iPlay.setFont(MyFonts.hPlay);

        iDelete = new MyIcon("a", SwingConstants.CENTER);
        iDelete.setFont(MyFonts.hDelete);

        iEbs = new MyIcon("a", SwingConstants.CENTER);
        iEbs.setBackground(MyColor.TRANSPARENT);
        iEbs.setForeground(MyColor.ANOTHER_GREY);
        iEbs.setFont(MyFonts.hEBS);

        iSearch = new MyIcon("a", SwingConstants.CENTER);
        iSearch.setFont(MyFonts.hSearch);
    }

    MyIcon(String string, int align) {
        super(string, SwingConstants.CENTER);
        setForeground(Color.WHITE);
    }

}
