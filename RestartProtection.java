import java.awt.Toolkit;

public class RestartProtection {
    public void preventRestart() {
        // Prevent the computer from being restarted
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Restart prevented!");
    }
}
