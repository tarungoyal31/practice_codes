package com.company.oldCode;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main2 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskG solver = new TaskG();
        solver.solve(in, out);
        out.close();
    }

    static class TaskG {

        public void solve(InputReader in, PrintWriter out) {
            Point[] oldPoints = new Point[3];
            for (int i = 0; i < 3; i++) {
                long x = in.nextLong();
                long y = in.nextLong();
                oldPoints[i] = new Point(x,y);
            }
            Point[] newPoints = new Point[3];
            out.println(3);
            for (int i = 0; i < 3; i++) {
                long x = oldPoints[i].x + oldPoints[(i+1)%3].x - oldPoints[(i+2)%3].x;
                long y = oldPoints[i].y + oldPoints[(i+1)%3].y - oldPoints[(i+2)%3].y;
                newPoints[i] = new Point(x,y);
                out.println(x + " " + y);
            }
        }
    }

    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point point) {
            return x == point.x && y == point.y;
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

