package Electricity;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MyTable extends JTable {
    JTableHeader th1;

    MyTable(String[][] y, String[] x) {
        super(y, x);
        setBorder(BorderFactory.createLineBorder(MyColor.TRANSPARENT));
        Dimension dim = new Dimension(0,2);
        setDragEnabled(false);
        setDefaultEditor(Object.class, null);
        setIntercellSpacing(dim);
        setRowHeight(40);
        setShowVerticalLines(false);
        setAutoCreateRowSorter(true);
        setShowHorizontalLines(false);
        setFillsViewportHeight(true);
        setBackground(MyColor.ANOTHER_GREY);
        setSelectionBackground(MyColor.BLUE_LIGHT);
        setSelectionForeground(Color.WHITE);
        setForeground(Color.WHITE);


        th1 = this.getTableHeader();
        th1.setOpaque(false);
        th1.setBackground(MyColor.DARK_GREY);
        th1.setPreferredSize(new Dimension(this.getWidth(),40));
        th1.setForeground(Color.WHITE);
        th1.setReorderingAllowed(false);

        UIManager.getDefaults().put("TableHeader.cellBorder","MyColor.ANOTHER_GREY");
        UIManager.getDefaults().put("Table.tableBorder","MyColor.GREY");


    }

    public static void main(String[] args) {

    }
}
