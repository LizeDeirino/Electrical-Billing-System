package Electricity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUpAddReportListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        /* right click popup */
        PopUpAddReport menu = new PopUpAddReport();
        menu.show(e.getComponent(), e.getX(), e.getY());

    }
}
