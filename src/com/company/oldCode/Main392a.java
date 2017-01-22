package com.company.oldCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main392a {
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
            long N = in.nextLong();
            long max = 0;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                long x = in.nextLong();
                max = Math.max(x, max);
                sum += x;
            }
            out.println(max * N - sum);
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

