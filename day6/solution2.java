package day6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class solution2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            lines.add(line);
        }

        String ops = lines.get(lines.size() - 1);
        List<String> grid = lines.subList(0, lines.size() - 1);

        int rows = grid.size();
        int cols = ops.length();

        long total = 0;
        int c = 0;

        while (c < cols) {

            if (isEmptyColumn(c, ops, grid)) {
                c++;
                continue;
            }

            int start = c;
            while (c < cols && !isEmptyColumn(c, ops, grid)) {
                c++;
            }
            int end = c; 

            char op = '?';
            for (int i = start; i < end; i++) {
                char ch = ops.charAt(i);
                if (ch == '+' || ch == '*') {
                    op = ch;
                    break;
                }
            }

            if (op == '?') {
                throw new RuntimeException("Não encontrado");
            }

            long value = (op == '+') ? 0 : 1;

            for (int col = end - 1; col >= start; col--) {
                long num = 0;

                for (int r = 0; r < rows; r++) {
                    if (col < grid.get(r).length()) {
                        char ch = grid.get(r).charAt(col);
                        if (ch >= '0' && ch <= '9') {
                            num = num * 10 + (ch - '0');
                        }
                    }
                }

                if (op == '+') value += num;
                else value *= num;
            }

            total += value;
        }

        System.out.println(total);
    }

    private static boolean isEmptyColumn(int col, String ops, List<String> grid) {
        if (col < ops.length() && ops.charAt(col) != ' ') return false;

        for (String row : grid) {
            if (col < row.length() && row.charAt(col) != ' ') {
                return false;
            }
        }
        return true;
    }
}
