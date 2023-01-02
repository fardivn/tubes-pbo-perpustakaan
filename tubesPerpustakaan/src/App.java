import java.awt.*;
import com.gui.*;

public class App {
    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login l = new Login();
                l.setVisible(true);
            }
        });
    }
}
