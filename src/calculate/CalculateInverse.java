package calculate;
import javax.swing.JLabel;

public class CalculateInverse {
    public static void calculateInverse(JLabel label, JLabel info, double result) {
        double num = Double.parseDouble(label.getText());

        if (num == 0) {
            info.setText("Cannot divide by zero");
            label.setText("Error");
        } else {
            if (info.getText().equals("")) {
                result = 1.0 / num;
                result = Math.round(result * 1_000_000_0000.0) / 1_000_000_0000.0;
                info.setText("1/(" + num + ")");
                label.setText(String.valueOf(result));
            } else {
                result = 1.0 / result;
                result = Math.round(result * 1_000_000_0000.0) / 1_000_000_0000.0;
                info.setText("1/(" + result + ")");
                label.setText(String.valueOf(result));
            }
        }
    }
}