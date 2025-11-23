import java.awt.*;

class DrawUtils {
    public static void drawLines(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawLine(370, 0, 370, 370);
        g2d.drawLine(0, 400, 370, 400);
        g2d.drawLine(430, 0, 430, 370);

        g2d.drawLine(0, 370, 370, 370);
        g2d.drawLine(400, 0, 400, 370);
        g2d.drawLine(0, 430, 370, 430);

        g2d.drawLine(430, 370, 800, 370);
        g2d.drawLine(430, 400, 800, 400);
        g2d.drawLine(430, 430, 800, 430);

        g2d.drawLine(370, 430, 370, 800);
        g2d.drawLine(400, 430, 400, 800);
        g2d.drawLine(430, 430, 430, 800);
    }
}
