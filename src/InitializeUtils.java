import java.awt.*;
import java.util.Map;

class InitializeUtils {
    public static void initDirections(Map<String, Direct> directions) {
        directions.put("up", createDirect("up", Color.RED, new Rectangle(430, 430, 30, 30), 430));
        directions.put("down", createDirect("down", Color.RED, new Rectangle(340, 340, 30, 30), 340));
        directions.put("left", createDirect("left", Color.RED, new Rectangle(430, 340, 30, 30), 430));
        directions.put("right", createDirect("right", Color.RED, new Rectangle(340, 430, 30, 30), 340));
    }

    private static Direct createDirect(String name, Color color, Rectangle rect, int stop) {
        return new Direct(name, color, false, rect, stop);
    }
}
