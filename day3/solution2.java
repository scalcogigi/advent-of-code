package day3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class solution2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        long total = 0;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            total += maxJoltage12(line);
        }

        System.out.println(total);
    }


    private static long maxJoltage12(String line) {
        int n = line.length();
        int k = 12;

        char[] stack = new char[k];
        int size = 0;

        for (int i = 0; i < n; i++) {
            char d = line.charAt(i);

            while (size > 0
                    && stack[size - 1] < d
                    && size - 1 + (n - i) >= k) {
                size--;
            }

            if (size < k) {
                stack[size++] = d;
            }
        }

        long value = 0;
        for (int i = 0; i < k; i++) {
            value = value * 10 + (stack[i] - '0');
        }

        return value;
    }
}
