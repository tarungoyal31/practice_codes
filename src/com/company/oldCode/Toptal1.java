package com.company.oldCode;

import java.io.*;
import java.util.StringTokenizer;

public class Toptal1 {
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
            String S = in.next();
            int len = S.length();
            int[] openBrackets = new int[len];
            int[] closeBrackets = new int[len];
            openBrackets[0] = S.charAt(0) == '(' ? 1 : 0;
            closeBrackets[len - 1] = S.charAt(len - 1) == ')' ? 1 : 0;
            for (int i = 1; i < len; i++) {
                openBrackets[i] = (S.charAt(i) == '(') ? openBrackets[i-1] + 1 : openBrackets[i-1];
            }
            for (int i = len - 2; i >= 0; i--) {
                closeBrackets[i] = (S.charAt(i) == ')') ? closeBrackets[i+1] + 1 : closeBrackets[i+1];
            }
            for (int i = 0; i < len-1; i++) {
                if (openBrackets[i] == closeBrackets[i+1]) {
                    out.print(i + 1);
                }
            }
            if (openBrackets[len - 1] == len) {
                out.print(0);
            } else if (closeBrackets[0] == len){
                out.print(len);
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

