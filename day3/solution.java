package day3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        long total = 0;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            total += max(line);
        }

        System.out.println(total);
    }


    private static int max(String line) {
        int best = 0;
        int n = line.length();

        for (int i = 0; i < n - 1; i++) {
            int first = line.charAt(i) - '0';

            int maxSecond = 0;
            for (int j = i + 1; j < n; j++) {
                int d = line.charAt(j) - '0';
                if (d > maxSecond) {
                    maxSecond = d;
                }
            }

            int value = first * 10 + maxSecond;
            if (value > best) {
                best = value;
            }
        }

        return best;
    }
}
