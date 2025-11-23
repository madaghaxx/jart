import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Vehicule {
    private Color color;
    private String direction;
    private int speed;
    private Rectangle rect;
    private String turn;
    private boolean turned;

    public Vehicule(String direction) {
        this.color = Direct.randomColor();
        this.direction = direction;
        this.speed = 2;
        this.turned = false;

        switch (direction) {
            case "up":
                this.rect = new Rectangle(400, 770, 30, 30);
                break;
            case "down":
                this.rect = new Rectangle(370, 0, 30, 30);
                break;
            case "left":
                this.rect = new Rectangle(770, 370, 30, 30);
                break;
            case "right":
                this.rect = new Rectangle(0, 400, 30, 30);
                break;
            default:
                this.rect = new Rectangle(0, 0, 30, 30);
        }

        if (color.equals(Color.YELLOW)) {
            this.turn = "right";
        } else if (color.equals(Color.RED)) {
            this.turn = "left";
        } else {
            this.turn = "forward";
        }
    }

    public void addStep() {
        switch (direction) {
            case "up":
                rect.y -= speed;
                break;
            case "down":
                rect.y += speed;
                break;
            case "left":
                rect.x -= speed;
                break;
            case "right":
                rect.x += speed;
                break;
        }
    }

    public void moves(int stop, boolean isGreen, List<Vehicule> vehicles) {
        if (!turned && update(stop)) {
            direction = getNewDirection(direction, turn);
            turned = true;
        }

        if (isBeforeStop(stop)) {
            if (isAtStop(stop, vehicles)) {
                if (isGreen) {
                    addStep();
                }
            } else {
                addStep();
            }
        } else {
            addStep();
        }
    }

    private String getNewDirection(String currentDir, String turnDir) {
        if (turnDir.equals("right")) {
            switch (currentDir) {
                case "up":
                    return "right";
                case "left":
                    return "up";
                case "down":
                    return "left";
                case "right":
                    return "down";
            }
        } else if (turnDir.equals("left")) {
            switch (currentDir) {
                case "up":
                    return "left";
                case "left":
                    return "down";
                case "down":
                    return "right";
                case "right":
                    return "up";
            }
        }
        return currentDir;
    }

    public boolean isAtStop(int stop, List<Vehicule> vehicles) {
        List<Vehicule> vecSameDirect = avoidCollision(vehicles, direction, stop);
        if (direction.equals("up") || direction.equals("down")) {
            vecSameDirect.sort((v1, v2) -> Integer.compare(v1.rect.y, v2.rect.y));
        } else {
            vecSameDirect.sort((v1, v2) -> Integer.compare(v1.rect.x, v2.rect.x));
        }

        for (int i = 0; i < vecSameDirect.size(); i++) {
            Vehicule v = vecSameDirect.get(i);

            switch (direction) {
                case "up":
                    if (v.rect.y == this.rect.y) {
                        if (i == 0) {
                            return this.rect.y - this.speed == stop;
                        }
                        return this.rect.y - this.speed < vecSameDirect.get(i - 1).rect.y + 50;
                    }
                    break;
                case "down":
                    if (v.rect.y == this.rect.y) {
                        if (i == vecSameDirect.size() - 1) {
                            return this.rect.y + this.speed > stop;
                        }
                        return this.rect.y + this.speed > vecSameDirect.get(i + 1).rect.y - 50;
                    }
                    break;
                case "left":
                    if (v.rect.x == this.rect.x) {
                        if (i == 0) {
                            return this.rect.x - this.speed < stop;
                        }
                        return this.rect.x - this.speed < vecSameDirect.get(i - 1).rect.x + 50;
                    }
                    break;
                case "right":
                    if (v.rect.x == this.rect.x) {
                        if (i == vecSameDirect.size() - 1) {
                            return this.rect.x + this.speed > stop;
                        }
                        return this.rect.x + this.speed > vecSameDirect.get(i + 1).rect.x - 50;
                    }
                    break;
            }
        }
        return true;
    }

    public boolean isBeforeStop(int stop) {
        switch (direction) {
            case "up":
                return rect.y >= stop;
            case "down":
                return rect.y <= stop;
            case "left":
                return rect.x >= stop;
            case "right":
                return rect.x <= stop;
            default:
                return false;
        }
    }

    public boolean update(int stop) {
        if (turn.equals("left")) {
            switch (direction) {
                case "up":
                    return rect.y == stop - 60;
                case "down":
                    return rect.y == stop + 60;
                case "left":
                    return rect.x == stop - 60;
                case "right":
                    return rect.x == stop + 60;
            }
        } else if (turn.equals("right")) {
            switch (direction) {
                case "up":
                    return rect.y == stop - 30;
                case "down":
                    return rect.y == stop + 30;
                case "left":
                    return rect.x == stop - 30;
                case "right":
                    return rect.x == stop + 30;
            }
        }
        return false;
    }

    public static List<Vehicule> avoidCollision(List<Vehicule> vehicles, String direction, int stop) {
        List<Vehicule> result = new ArrayList<>();
        for (Vehicule v : vehicles) {
            if (v.direction.equals(direction) && v.isBeforeStop(stop)) {
                result.add(v);
            }
        }
        return result;
    }

    public static boolean someOnIntersect(List<Vehicule> vehicles) {
        for (Vehicule v : vehicles) {
            if (v.rect.x < 430 && v.rect.x > 340 && v.rect.y < 430 && v.rect.y > 340) {
                return true;
            }
        }
        return false;
    }
    public Color getColor() {
        return color;
    }

    public String getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getRect() {
        return rect;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isTurned() {
        return turned;
    }
}
