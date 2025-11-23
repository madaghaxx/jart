import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

class MainLoop extends JPanel {
    private List<Vehicule> vehicles;
    private long lastCreationTime;
    private Map<String, Direct> directions;
    private javax.swing.Timer timer;
    private Random random;

    public MainLoop() {
        vehicles = new ArrayList<>();
        lastCreationTime = System.currentTimeMillis();
        directions = new HashMap<>();
        random = new Random();

        InitializeUtils.initDirections(directions);

        setBackground(Color.BLACK);
        setFocusable(true);
        setDoubleBuffered(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    public void start() {
        timer = new javax.swing.Timer(16, e -> {
            update();
            paintImmediately(0, 0, getWidth(), getHeight());
        });
        timer.start();
    }

    private void handleKeyPress(KeyEvent e) {
        long currentTime = System.currentTimeMillis();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            case KeyEvent.VK_UP:
                if (vehicles.size() < 16 && currentTime - lastCreationTime >= 500) {
                    vehicles.add(new Vehicule("up"));
                    lastCreationTime = currentTime;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (vehicles.size() < 16 && currentTime - lastCreationTime >= 500) {
                    vehicles.add(new Vehicule("down"));
                    lastCreationTime = currentTime;
                }
                break;

            case KeyEvent.VK_LEFT:
                if (vehicles.size() < 16 && currentTime - lastCreationTime >= 500) {
                    vehicles.add(new Vehicule("left"));
                    lastCreationTime = currentTime;
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (vehicles.size() < 16 && currentTime - lastCreationTime >= 500) {
                    vehicles.add(new Vehicule("right"));
                    lastCreationTime = currentTime;
                }
                break;

            case KeyEvent.VK_R:
                if (vehicles.size() < 16 && currentTime - lastCreationTime >= 500) {
                    String[] dirs = { "up", "down", "left", "right" };
                    vehicles.add(new Vehicule(dirs[random.nextInt(4)]));
                    lastCreationTime = currentTime;
                }
                break;

            case KeyEvent.VK_X:
                vehicles.clear();
                break;
        }
    }

    private void update() {
        if (vehicles.isEmpty()) {
            return;
        }

        vehicles.removeIf(v -> v.getRect().x < 0 || v.getRect().y > 800 ||
                v.getRect().x > 800 || v.getRect().y < 0);

        int[] counts = FilterUtils.filterVehicles(directions, vehicles);
        int leftLen = counts[0];
        int rightLen = counts[1];
        int upLen = counts[2];
        int downLen = counts[3];

        if (!Vehicule.someOnIntersect(vehicles)) {
            int maxLen = Math.max(Math.max(leftLen, rightLen), Math.max(upLen, downLen));

            for (Map.Entry<String, Direct> entry : directions.entrySet()) {
                String key = entry.getKey();
                Direct direct = entry.getValue();

                if ((key.equals("left") && maxLen == leftLen) ||
                        (key.equals("right") && maxLen == rightLen) ||
                        (key.equals("up") && maxLen == upLen) ||
                        (key.equals("down") && maxLen == downLen)) {
                    direct.setState(true);
                    direct.setColor(Color.GREEN);
                } else {
                    direct.setState(false);
                    direct.setColor(Color.RED);
                }
            }
        }

        List<Vehicule> allVehicles = new ArrayList<>(vehicles);
        for (Vehicule vehicle : vehicles) {
            Direct direct = directions.get(vehicle.getDirection());
            if (direct != null) {
                vehicle.moves(direct.getStop(), direct.isState(), allVehicles);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);

        DrawUtils.drawLines(g2d);

        for (Direct direct : directions.values()) {
            g2d.setColor(direct.getColor());
            Rectangle rect = direct.getRectLight();
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
        }

        for (Vehicule vehicle : vehicles) {
            g2d.setColor(vehicle.getColor());
            Rectangle rect = vehicle.getRect();
            g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
        }

        Toolkit.getDefaultToolkit().sync();
    }
}
