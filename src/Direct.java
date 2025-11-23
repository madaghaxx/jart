import java.awt.*;
import java.util.Random;

class Direct {
    private String name;
    private Color color;
    private boolean state;
    private Rectangle rectLight;
    private int stop;

    public Direct(String name, Color color, boolean state, Rectangle rectLight, int stop) {
        this.name = name;
        this.color = color;
        this.state = state;
        this.rectLight = rectLight;
        this.stop = stop;
    }

    public static Color randomColor() {
        Color[] colors = { Color.YELLOW, Color.BLUE, Color.RED };
        Random random = new Random();
        return colors[random.nextInt(3)];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Rectangle getRectLight() {
        return rectLight;
    }

    public void setRectLight(Rectangle rectLight) {
        this.rectLight = rectLight;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }
}
