
import java.awt.Toolkit;

public class ComputerLocker {
    public void lockComputer() {
        // Lock the computer
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Computer locked!");
    }
}