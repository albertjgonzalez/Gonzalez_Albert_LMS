import javax.swing.*;
import java.io.IOException;

public class LMS {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    LMSGUI lmsgui = new LMSGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
