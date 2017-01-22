package com.company.oldCode;

import java.io.*;
import java.util.*;

public class Main391b {

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

        int[] primFactors = {
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401
        };

        HashMap<Integer, Integer> primMap;

        public void solve(InputReader in, PrintWriter out) {
            primMap = new HashMap<>(10);
            int N = in.nextInt();
            int a[] = new int[N];
            for (int i = 0; i < N; i++) {
                updatePrimMap(in.nextInt());
            }
            int count = 0;
            for (Integer x: primMap.values()) {
                count = count > x ? count : x;
            }
            out.println(count > 0 ? count : 1);
        }

        public void updatePrimMap(int x) {
            HashSet<Integer> factors = new HashSet<>();
            int primCount = primFactors.length;
            for (int i = 0; i < primCount; i++) {
                int factor = primFactors[i];
                if (x % factor == 0) {
                    while (x % factor == 0) {
                        x /= factor;
                    }
                    Integer value = primMap.get(factor);
                    value = value == null ? 1 : value + 1;
                    primMap.put(factor, value);
                }
                if (x == 1) return;
            }
            Integer value = primMap.get(x);
            value = value == null ? 1 : value + 1;
            primMap.put(x, value);
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

