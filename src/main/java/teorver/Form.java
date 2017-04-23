// Form.java
package teorver;

import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Form {
    private JPanel panel;
    private JButton button1;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldF;
    private JTextField textFieldN;
    private JFileChooser fileChooser;

    public Form() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить результаты как");
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV (*.csv)", "csv");
        fileChooser.setFileFilter(filter);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Model model = new Model();
                String func = textFieldF.getText();
                double a = Double.parseDouble(textFieldA.getText());
                double b = Double.parseDouble(textFieldB.getText());
                int n = Integer.parseInt(textFieldN.getText());
                double x[];
                try {
                    x = model.generate(func, a, b, n);
                } catch (ScriptException exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Ошибка в задании функции", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String fileName = fileChooser.getSelectedFile().toString();
                    if (!fileName.endsWith(".csv")) {
                        fileName += ".csv";
                        file = new File(fileName);
                    }
                    saveFile(file, x);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Моделирование случайной величины");
        frame.setContentPane(new Form().panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void saveFile(File file, double x[]) {
        try{
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (int i = 0; i < x.length; i++) {
                writer.println(x[i]);
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
