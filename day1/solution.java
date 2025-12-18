import java.util.*;

public class solution {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int pos = 50;
            int countZero = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                char dir = line.charAt(0);
                int dist = Integer.parseInt(line.substring(1));

                if (dir == 'R') {
                    pos = (pos + dist) % 100;
                } else if (dir == 'L') {
                    pos = (pos - dist) % 100;
                    if (pos < 0) pos += 100;  
                }

                if (pos == 0) {
                    countZero++;
                }
            }

            System.out.println(countZero);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
