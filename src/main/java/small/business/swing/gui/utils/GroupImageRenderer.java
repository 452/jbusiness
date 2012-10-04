package small.business.swing.gui.utils;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GroupImageRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        if (value != null) {
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIcon(new ImageIcon((URL) value));
        }
        return label;
    }
}