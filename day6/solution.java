package day6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            lines.add(line);
        }

        String ops = lines.get(lines.size() - 1);
        List<String> nums = lines.subList(0, lines.size() - 1);

        int rows = nums.size();
        int cols = ops.length();

        long total = 0;
        int c = 0;

        while (c < cols) {

            if (isEmptyColumn(c, ops, nums)) {
                c++;
                continue;
            }

            int start = c;

            while (c < cols && !isEmptyColumn(c, ops, nums)) {
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

            for (String row : nums) {
                if (start >= row.length()) continue;
                int rEnd = Math.min(end, row.length());
                String part = row.substring(start, rEnd).trim();
                if (part.isEmpty()) continue;

                long num = Long.parseLong(part);
                if (op == '+') value += num;
                else value *= num;
            }

            total += value;
        }

        System.out.println(total);
    }

    private static boolean isEmptyColumn(int col, String ops, List<String> nums) {
        if (col < ops.length() && ops.charAt(col) != ' ') return false;

        for (String row : nums) {
            if (col < row.length() && row.charAt(col) != ' ') {
                return false;
            }
        }
        return true;
    }
}
