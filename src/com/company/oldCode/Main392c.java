package com.company.oldCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main392c {
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
            long n,m,k,x,y;
            n = in.nextLong();
            m = in.nextLong();
            k = in.nextLong();
            x = in.nextLong();
            y = in.nextLong();

            if (n <= 2) {
                long completedRounds = k / (m*n);
                k %= (m*n);
                out.println((k > 0 ? completedRounds + 1 : completedRounds)+ " " + completedRounds + " " + ((x-1)*m + y <= k ? completedRounds + 1 : completedRounds));
                return;
            }

            long completeRoundLength = m * (2 * n - 2);
            long completedRounds = k / completeRoundLength;
            long minPupil, maxPupil, me;
            k = k % completeRoundLength;
            minPupil = k < m*n ? completedRounds : completedRounds + 1;
            if (k == 0) {
                maxPupil = completedRounds * 2;
            } else if (k <= m) {
                maxPupil = Math.max(completedRounds + 1, 2*completedRounds);
            } else if (k > m*n) {
                maxPupil = completedRounds * 2 + 2;
            } else {
                maxPupil = completedRounds * 2 + 1;
            }
            if (x == 1 || x == n) {
                me = (k >= (x-1)*m + y) ? completedRounds + 1 : completedRounds;
            } else {
                if (k < (x-1)*m + y) {
                    me = completedRounds * 2;
                } else if (k - (n*m) >= (n-x-1) * m +y) {
                    me = completedRounds * 2 + 2;
                } else {
                    me = completedRounds * 2 + 1;
                }
            }
            out.println(maxPupil + " " + minPupil + " " + me);
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

