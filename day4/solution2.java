package day4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class solution2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<char[]> grid = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            grid.add(line.toCharArray());
        }

        int rows = grid.size();
        int cols = grid.get(0).length;

        int totalRemoved = 0;

        while (true) {
            List<int[]> toRemove = new ArrayList<>();

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid.get(r)[c] == '@') {
                        if (adjacentCount(grid, r, c) < 4) {
                            toRemove.add(new int[]{r, c});
                        }
                    }
                }
            }

            if (toRemove.isEmpty()) {
                break; // estabiliza
            }

            for (int[] pos : toRemove) {
                grid.get(pos[0])[pos[1]] = '.';
            }

            totalRemoved += toRemove.size();
        }

        System.out.println(totalRemoved);
    }


    private static int adjacentCount(List<char[]> grid, int r, int c) {
        int rows = grid.size();
        int cols = grid.get(0).length;
        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;

                int nr = r + dr;
                int nc = c + dc;

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;

                if (grid.get(nr)[nc] == '@') {
                    count++;
                }
            }
        }

        return count;
    }
}
