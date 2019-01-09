package applapangan;

/**
 *
 * @author tobyz
 */
public class AppLapangan {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FLogin().setVisible(true);
            }
        });
    }
    
}
