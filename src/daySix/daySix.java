package daySix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class daySix {

    public static void main(String[] args) {

        try {
            partOne("src/daySix/input.txt");
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void partOne(String fileName) throws FileNotFoundException{

        Scanner scanner = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();
        int maxColumns = 0;



        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
            maxColumns = Math.max(maxColumns, line.length());
        }


        // Create grid
        int rows = lines.size();
        char[][] grid = new char[rows][maxColumns];

        // fill the grid
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                grid[row][col] = line.charAt(col);
            }

        }

        //set starting location
        int x = 43;
        int y = 37;
        //move ^

        Player player = new Player(x, y, "p1", grid, Player.Direction.NORTH);

        while(true){
            if(player.lookInFacingDirection() == '#'){
                player.turnRight();
            } else {
                int result = player.moveForward();
                if(result == Player.MOVE_BOUNDARY) {
                    break;
                }
            }
        }
        //print number of X's
        grid[player.getY()][player.getX()] = 'X';
        printGrid(grid,rows,maxColumns);

    }

    public static void printGrid(char[][] grid, int rows, int maxColumns){
        System.out.println("Grid dimensions: " + rows + "x" + maxColumns);
        Set<String> uniquePositions = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < maxColumns; j++) {
                if (grid[i][j] == 'X') {
                    uniquePositions.add(i + "," + j);
                }
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println("Number of unique positions visited: " + uniquePositions.size());
    }

    public static int[] findStart(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '^') {
                    return new int[]{row, col};
                }
            }
        }
        return null; // Return null if '^' is not found
    }

}
