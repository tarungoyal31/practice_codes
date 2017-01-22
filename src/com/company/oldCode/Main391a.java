package com.company.oldCode;

import java.io.*;
import java.util.StringTokenizer;

public class Main391a {
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
            String str = in.next();
            int Bcount = 0;
            int ucount = 0;
            int lcount = 0;
            int bcount = 0;
            int acount = 0;
            int scount = 0;
            int rcount = 0;
            int strlen = str.length();
            for (int i = 0; i <strlen; i++) {
                switch (str.charAt(i)) {
                    case 'B' :
                        Bcount++;
                        break;
                    case 'u' :
                        ucount++;
                        break;
                    case 'l' :
                        lcount++;
                        break;
                    case 'b' :
                        bcount++;
                        break;
                    case 'a' :
                        acount++;
                        break;
                    case 's' :
                        scount++;
                        break;
                    case 'r' :
                        rcount++;
                        break;
                }
            }
            int finalCount =  100000;
            ucount /=2;
            acount /=2;
            finalCount = finalCount > Bcount ? Bcount : finalCount;
            finalCount = finalCount > ucount ? ucount : finalCount;
            finalCount = finalCount > lcount ? lcount : finalCount;
            finalCount = finalCount > bcount ? bcount : finalCount;
            finalCount = finalCount > acount ? acount : finalCount;
            finalCount = finalCount > scount ? scount : finalCount;
            finalCount = finalCount > rcount ? rcount : finalCount;
            out.println(finalCount);
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

