import java.util.*;

public class solution2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int pos = 50;
            long count = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                char dir = line.charAt(0);
                int dist = Integer.parseInt(line.substring(1));

                for (int i = 0; i < dist; i++) {
                    if (dir == 'R') {
                        pos = (pos + 1) % 100;
                    } else {
                        pos = (pos - 1 + 100) % 100;
                    }

                    if (pos == 0) {
                        count++;
                    }
                }
            }

            System.out.println(count);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
