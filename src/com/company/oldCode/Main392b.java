package com.company.oldCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main392b {
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
            String s = in.next();
            int len = s.length();
            char[] bulbs = new char[4];
            Map<Character, Integer> bulbMap = new HashMap<>(4);
            bulbMap.put('R', 0);
            bulbMap.put('Y', 0);
            bulbMap.put('B', 0);
            bulbMap.put('G', 0);
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) != '!') {
                    bulbs[i % 4] = s.charAt(i);
                }
            }
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '!') {
                    bulbMap.replace(bulbs[i%4], bulbMap.get(bulbs[i%4]) + 1);
                }
            }
            out.print(bulbMap.get('R') + " ");
            out.print(bulbMap.get('B') + " ");
            out.print(bulbMap.get('Y') + " ");
            out.println(bulbMap.get('G'));
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

