package com.company.oldCode;

import java.io.*;
import java.util.StringTokenizer;

public class Toptal2 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskG solver = new TaskG();
        out.println(solver.solve(in, out));
        out.close();
    }

    static class TaskG {

        public int[] solve(InputReader in, PrintWriter out) {
            int[] A = {1,0,0,1,1};
            int Alen = A.length;
            if (Alen == 0) {
                return new int[0];
            }
            int[] B = new int[Alen + 1];
            int Blen = Alen + 1;
            for (int i = 0; i < Alen; i++) {
                if (A[i] == 1) {
                    B[i]++;
                    B[i+1]++;
                }
            }
            for (int i = 0; i < Blen; i++) {
                if (B[i] == 2) {
                    B[i] = 0;
                    B[i+1]--;
                }
            }
            int emptyCount = 0;
            for (int i = Blen - 1; i >=0; i++) {
                if (B[i] == 0) {
                    emptyCount++;
                } else {
                    break;
                }
            }
            int[] C = new int[Blen - emptyCount];
            for (int i = 0; i < Blen - emptyCount; i++) {
                C[i] = B[i];
            }
            return C;
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

