public class Coordinates {
    int x, y;
    String direction;
    int g; // Cost to reach this node
    int h; // Heuristic (estimated cost to the goal)

    Coordinates(int x, int y, String direction, int g, int h) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.g = g;
        this.h = h;
    }
}