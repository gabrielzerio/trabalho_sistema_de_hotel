package view;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

public class BlockedDatesExample extends JFrame {

    public BlockedDatesExample() {
        setTitle("Blocked Dates Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.getDateEditor().addPropertyChangeListener("date", new DateChangeListener());

        add(dateChooser, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DateChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("date".equals(evt.getPropertyName())) {
                Date selectedDate = (Date) evt.getNewValue();

                // Implemente a lógica para verificar se a data é bloqueada
                if (isBlockedDate(selectedDate)) {
                    JOptionPane.showMessageDialog(BlockedDatesExample.this, "Esta data está bloqueada.");
                    ((JDateChooser) evt.getSource()).setDate(null);
                }
            }
        }

        private boolean isBlockedDate(Date date) {
            // Implemente a lógica para verificar se a data é bloqueada
            // Por exemplo, bloqueie datas no passado
            return date != null && date.before(new Date());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BlockedDatesExample());
    }
}
