import java.util.List;
import java.util.Map;

class FilterUtils {
    public static int[] filterVehicles(Map<String, Direct> directions, List<Vehicule> vehicles) {
        int leftCount = 0;
        int rightCount = 0;
        int upCount = 0;
        int downCount = 0;

        for (Vehicule v : vehicles) {
            String dir = v.getDirection();
            Direct direct = directions.get(dir);

            if (direct != null && v.isBeforeStop(direct.getStop())) {
                switch (dir) {
                    case "left":
                        leftCount++;
                        break;
                    case "right":
                        rightCount++;
                        break;
                    case "up":
                        upCount++;
                        break;
                    case "down":
                        downCount++;
                        break;
                }
            }
        }

        return new int[] { leftCount, rightCount, upCount, downCount };
    }
}
