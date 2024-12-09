package daySix;

public class Player {
    private int x;
    private int y;
    private String name;
    private boolean isActive;
    private char[][] map;
    private Direction facing;

    // Constants
    public static final char WALL = '#';
    public static final char BOUNDARY = '∎';
    public static final char TRAIL = 'X';
    public static final int MOVE_SUCCESS = 0;
    public static final int MOVE_WALL = 1;
    public static final int MOVE_BOUNDARY = -1;

    public enum Direction {
        NORTH('^'), EAST('>'), SOUTH('v'), WEST('<');

        private final char symbol;

        Direction(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        public Direction turnRight() {
            return values()[(ordinal() + 1) % 4];
        }

    }

    public Player(int x, int y, String name, char[][] map, Direction facing) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.isActive = true;
        this.map = map;
        this.facing = facing;
        updateMapPosition(); // Place initial player position
    }

    // Update map with player position and direction
    private void updateMapPosition() {
        map[y][x] = facing.getSymbol();
    }

    // Leave trail at old position and update new position
    private void moveAndUpdateMap(int oldX, int oldY) {
        map[oldY][oldX] = TRAIL;
        updateMapPosition();
    }

    // Movement methods updated to leave trails
    public int moveLeft() {
        if (x - 1 < 0) return MOVE_BOUNDARY;
        if (map[y][x - 1] == WALL) return MOVE_WALL;
        int oldX = x;
        int oldY = y;
        x--;
        moveAndUpdateMap(oldX, oldY);
        return MOVE_SUCCESS;
    }

    public int moveRight() {
        if (x + 1 >= map[0].length) return MOVE_BOUNDARY;
        if (map[y][x + 1] == WALL) return MOVE_WALL;
        int oldX = x;
        int oldY = y;
        x++;
        moveAndUpdateMap(oldX, oldY);
        return MOVE_SUCCESS;
    }

    public int moveUp() {
        if (y - 1 < 0) return MOVE_BOUNDARY;
        if (map[y - 1][x] == WALL) return MOVE_WALL;
        int oldX = x;
        int oldY = y;
        y--;
        moveAndUpdateMap(oldX, oldY);
        return MOVE_SUCCESS;
    }

    public int moveDown() {
        if (y + 1 >= map.length) return MOVE_BOUNDARY;
        if (map[y + 1][x] == WALL) return MOVE_WALL;
        int oldX = x;
        int oldY = y;
        y++;
        moveAndUpdateMap(oldX, oldY);
        return MOVE_SUCCESS;
    }

    public void turnRight() {
        facing = facing.turnRight();
        updateMapPosition(); // Update symbol after turning
    }



    public int getX() { return x; }
    public int getY() { return y; }




    public char lookInFacingDirection() {
        switch (facing) {
            case NORTH:
                if (y - 1 < 0) return BOUNDARY;
                return map[y - 1][x];
            case EAST:
                if (x + 1 >= map[0].length) return BOUNDARY;
                return map[y][x + 1];
            case SOUTH:
                if (y + 1 >= map.length) return BOUNDARY;
                return map[y + 1][x];
            case WEST:
                if (x - 1 < 0) return BOUNDARY;
                return map[y][x - 1];
            default:
                return BOUNDARY;
        }
    }

    public int moveForward() {
        switch (facing) {
            case NORTH: return moveUp();
            case EAST: return moveRight();
            case SOUTH: return moveDown();
            case WEST: return moveLeft();
            default: return MOVE_BOUNDARY;
        }
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=(" + x + "," + y + ")" +
                ", facing=" + facing +
                ", active=" + isActive +
                '}';
    }
}