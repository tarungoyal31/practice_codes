package com.company.oldCode;

import java.io.*;
import java.util.*;

public class CAPIMOVE {
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

        ArrayList<Long> values;
        ArrayList<ArrayList<Long>> points;
        Map<Long, Integer> pointValueIndex;
        public void solve(InputReader in, PrintWriter out) {
            long T = in.nextLong();
            while (T-- > 0) {
                int N = in.nextInt();
                values = new ArrayList<>(N);
                points = new ArrayList<>(N);
                pointValueIndex = new HashMap<>(N);
                long[] P = new long[N];
                for (int i = 0; i < N; i++) {
                    long x = in.nextLong();
                    P[i] = x;
                    values.add(x);
                    ArrayList<Long> point = new ArrayList<>();
                    point.add(x);
                    points.add(point);
                    pointValueIndex.put(x,i);
                }
                for (int i = 0; i < N - 1; i++) {
                    int V = in.nextInt() - 1;
                    int U = in.nextInt() - 1;
                    points.get(V).add(values.get(U));
                    points.get(U).add(values.get(V));
                }
                values.sort(Long::compareTo);
                for (int i = 0; i < N; i++) {
                    ArrayList<Long> point = points.get(i);
                    point.sort(Long::compareTo);
                    int j = point.size() - 1;
                    int k = values.size() - 1;
                    while (j >= 0 && point.get(j).equals(values.get(k)) && j >= 0 && k >= 0) {
                        j--;
                        k--;
                    }
                    if (k >= 0) {
                        out.print(pointValueIndex.get(values.get(k)) + 1 + " ");
                    } else {
                        out.print("0 ");
                    }
                }
            }
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

