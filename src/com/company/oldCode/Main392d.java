package com.company.oldCode;

import java.io.*;
import java.util.StringTokenizer;

public class Main392d {
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
            long base = in.nextLong();
            String str = in.next();
            int strlen = str.length();
            if (strlen < 19 && Long.valueOf(str) < base) {
                out.println(Long.valueOf(str));
                return;
            }
            long res = 0;
            int currentEnd = strlen;
            long multiplier = 1;
            for (int i = strlen - 1; i >= 0; i--) {
                if (getValue(str.substring(i, currentEnd), base) == -1) {
                    long val = getValue(str.substring(i + 1, currentEnd), base);
                    currentEnd = i+1;
                    res += val * multiplier;
                    multiplier *= base;
                }
            }
            res += getValue(str.substring(0,currentEnd), base) * multiplier;
            out.println(res);
        }

        long getValue(String str, long base) {
            if (str.charAt(0) != '0') {
                long val = Long.valueOf(str);
                if (val < base) {
                    return val;
                }
                return -1l;
            } else {
                long x = 1;
                for (int i = 0; i < str.length(); i++ ) {
                    x *= 10;
                }
                if (str.length() > 1 && ((x >= base))) {
                    return -1l;
                } else {
                    return Long.valueOf(str);
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

