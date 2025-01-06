
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordForm {
    private JFrame frame;
    private JPanel panel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JButton exitButton;
    private PasswordVerifier verifier;
    private ComputerLocker locker;
    private RestartProtection protector;

    public PasswordForm() {
        frame = new JFrame("Password Protected Computer");
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        passwordLabel = new JLabel("Enter Password:");
        passwordField = new JPasswordField(10);
        submitButton = new JButton("Submit");
        exitButton = new JButton("Exit");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(exitButton, gbc);

        frame.getContentPane().add(panel);

        verifier = new PasswordVerifier("correct_password");
        locker = new ComputerLocker();
        protector = new RestartProtection();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] enteredPassword = passwordField.getPassword();
                if (verifier.verifyPassword(new String(enteredPassword))) {
                    // Unlock the computer
                    System.out.println("Computer unlocked!");
                } else {
                    locker.lockComputer();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                protector.preventRestart();
            }
        });

        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PasswordForm();
    }
}
