import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Road Intersections");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            MainLoop mainLoop = new MainLoop();
            frame.add(mainLoop);

            frame.setVisible(true);
            mainLoop.start();
        });
    }
}
