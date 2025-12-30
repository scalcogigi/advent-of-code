package day5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class solution {

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
        List<Long> values = new ArrayList<>();

        String line;
        boolean readingIntervals = true;

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                readingIntervals = false;
                continue;
            }

            if (readingIntervals) {
                String[] p = line.split("-");
                long a = Long.parseLong(p[0]);
                long b = Long.parseLong(p[1]);
                intervals.add(new Interval(a, b));
            } else {
                values.add(Long.parseLong(line));
            }
        }

        List<Interval> merged = merge(intervals);

        int count = 0;
        for (long v : values) {
            if (isInInterval(merged, v)) {
                count++;
            }
        }

        System.out.println(count);
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

    private static boolean isInInterval(List<Interval> intervals, long v) {
        int l = 0, r = intervals.size() - 1;

        while (l <= r) {
            int m = (l + r) >>> 1;
            Interval in = intervals.get(m);

            if (v < in.start) {
                r = m - 1;
            } else if (v > in.end) {
                l = m + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
