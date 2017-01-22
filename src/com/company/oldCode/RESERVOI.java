package com.company.oldCode;

import java.io.*;
import java.util.StringTokenizer;

public class RESERVOI {
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

        String[] reserviors;
        int N,M;

        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            while (T-- > 0) {
                N = in.nextInt();
                M = in.nextInt();
                reserviors = new String[N];
                for (int i = 0; i < N; i++) {
                    reserviors[i] = in.next();
                }
                if (isValid()) {
                    out.println("yes");
                } else {
                    out.println("no");
                }
            }
        }

        public boolean isValid() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <M; j++) {
                    if (reserviors[i].charAt(j) == 'W') {
                        if ((j == 0) || (j==M-1) || (i == N-1) || (reserviors[i].charAt(j-1) == 'A')
                                || (reserviors[i].charAt(j+1) == 'A') || (reserviors[i+1].charAt(j) == 'A')) {
                            return false;
                        }
                    } else if (reserviors[i].charAt(j) == 'B') {
                        if ((i != N-1 && reserviors[i + 1].charAt(j) != 'B')) {
                            return false;
                        }
                    }
                }
            }
            return true;
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

