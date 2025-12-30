package day5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class solution2 {

    private static class Interval {
        long start, end;
        Interval(long s, long e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Interval> intervals = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] p = line.split("-");
            long a = Long.parseLong(p[0]);
            long b = Long.parseLong(p[1]);
            intervals.add(new Interval(a, b));
        }

        List<Interval> merged = merge(intervals);

        long totalFresh = 0;
        for (Interval in : merged) {
            totalFresh += (in.end - in.start + 1);
        }

        System.out.println(totalFresh);
    }


    private static List<Interval> merge(List<Interval> list) {
        Collections.sort(list, (a, b) -> Long.compare(a.start, b.start));

        List<Interval> res = new ArrayList<>();
        Interval cur = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            Interval next = list.get(i);

            if (next.start <= cur.end + 1) {
                cur.end = Math.max(cur.end, next.end);
            } else {
                res.add(cur);
                cur = next;
            }
        }

        res.add(cur);
        return res;
    }
}
