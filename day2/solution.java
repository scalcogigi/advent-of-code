package day2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        long total = 0;

        for (String r : line.split(",")) {
            String[] parts = r.split("-");
            long start = Long.parseLong(parts[0]);
            long end   = Long.parseLong(parts[1]);

            total += sumInvalid(start, end);
        }

        System.out.println(total);
    }


    public static long sumInvalid(long start, long end) {
        long sum = 0;
        int maxDigits = digitCount(end);

        for (int blockLen = 1; blockLen <= maxDigits / 2; blockLen++) {

            long blockStart = pow10(blockLen - 1);
            long blockEnd   = pow10(blockLen) - 1;

            for (long block = blockStart; block <= blockEnd; block++) {

                if (!isPrimitive(block, blockLen)) continue;

                long value = block;

                while (true) {
                    value = append(value, block, blockLen);

                    if (value > end) break;
                    if (value >= start) sum += value;
                }
            }
        }

        return sum;
    }


    private static int digitCount(long n) {
        int count = 0;
        do {
            count++;
            n /= 10;
        } while (n > 0);
        return count;
    }

    private static long pow10(int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) result *= 10;
        return result;
    }

    private static long append(long prefix, long block, int blockLen) {
        long result = prefix;
        for (int i = 0; i < blockLen; i++) result *= 10;
        return result + block;
    }

    private static boolean isPrimitive(long block, int len) {
        for (int subLen = 1; subLen <= len / 2; subLen++) {
            if (len % subLen != 0) continue;

            long base = block / pow10(len - subLen);
            long value = base;

            for (int i = 1; i < len / subLen; i++) {
                value = append(value, base, subLen);
            }

            if (value == block) return false;
        }
        return true;
    }
}
