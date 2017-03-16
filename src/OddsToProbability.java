import java.awt.HeadlessException;

import javax.swing.JOptionPane;

public class OddsToProbability {

    public static void main(String[] args) {
        double odds = 0, convert;
        int prob = 0, result;
        boolean running = true;
        String message;
        String title = "Odds converter";
        String dispMessage = "Välj funktion:";
        String[] options = new String[] { "Cancel", "Sannolikhet till odds",
                "Odds till sannolikhet" };
        int response = JOptionPane.showOptionDialog(null, dispMessage, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);

        switch (response) {
            case 0: // cancel clicked

                break;
            case 1: // prob to odds chosen
                while (running) {
                    try {
                        prob = Integer.parseInt(JOptionPane
                                .showInputDialog("Skriv in sannolikheten i %:"));
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block

                        e.printStackTrace();
                    } catch (HeadlessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (prob != 0) {
                        convert = (prob * 1.0 / 100.0);
                        odds = 1 / convert;
                        String oddsFormated = String.format("%.2f", odds);

                        message = "Sannolikheten " + prob + "% ger oddset "
                                + oddsFormated;
                        result = JOptionPane.showConfirmDialog(null, message
                                        + "\nVill du omvandla mer?", null,
                                JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.NO_OPTION) {
                            running = false;
                        }
                    } else {
                        message = "Du skrev inte in det korrekt!";
                        result = JOptionPane.showConfirmDialog(null, message
                                        + "\nVill du omvandla mer?", null,
                                JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.NO_OPTION) {
                            running = false;
                        }
                    }

                }

                break;
            case 2: // odds to prob chosen
                while (running) {
                    try {
                        odds = Double.parseDouble(JOptionPane
                                .showInputDialog("Skriv in odds"));
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (HeadlessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    convert = 1 / odds;
                    prob = (int) (convert * 100);
                    message = "Oddset " + odds + " ger sannolikhet på " + prob
                            + "%";
                    result = JOptionPane.showConfirmDialog(null, message
                                    + "\nVill du omvandla mer?", null,
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.NO_OPTION) {
                        running = false;
                    }
                }
                break;
        }

    }
}
