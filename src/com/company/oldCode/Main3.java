package com.company.oldCode;

import java.io.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main3 {
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
            long stringLen = in.nextLong();
            String str = in.next();
            LinkedList<Employee> democrats = new LinkedList<Employee>();
            LinkedList<Employee> republicans = new LinkedList<Employee>();
            for (int i = 0; i < stringLen; i++) {
                if(str.charAt(i) == 'D') {
                    democrats.add(new Employee(i, EmployeeType.D));
                } else {
                    republicans.add(new Employee(i, EmployeeType.R));
                }
            }

            if (republicans.size() == 0) {
                out.println("D");
                return;
            } else if (democrats.size() == 0){
                out.println("R");
                return;
            }

            ListIterator<Employee> dIterator = democrats.listIterator();
            ListIterator<Employee> rIterator = republicans.listIterator();
            Employee dNext = dIterator.next();
            Employee rNext = rIterator.next();

            int curIndex = 0;
            while (democrats.size() > 0 && republicans.size() > 0) {
                if (dNext.index == curIndex) {
                    rIterator.remove();
                    if (dIterator.hasNext()) {
                        dNext = dIterator.next();
                    } else if (democrats.size() > 0){
                        dIterator = democrats.listIterator();
                        dNext = dIterator.next();
                    }
                    if (rIterator.hasNext()) {
                        rNext = rIterator.next();
                    } else if (republicans.size() > 0){
                        rIterator = republicans.listIterator();
                        rNext = rIterator.next();
                    }
                } else if (rNext.index == curIndex){
                    dIterator.remove();
                    if (dIterator.hasNext()) {
                        dNext = dIterator.next();
                    } else if (democrats.size() > 0){
                        dIterator = democrats.listIterator();
                        dNext = dIterator.next();
                    }
                    if (rIterator.hasNext()) {
                        rNext = rIterator.next();
                    } else if (republicans.size() > 0){
                        rIterator = republicans.listIterator();
                        rNext = rIterator.next();
                    }
                }
                curIndex += 1;
                curIndex %= stringLen;
            }
            if (republicans.size() == 0) {
                out.println("D");
            } else {
                out.print("R");
            }
        }
    }

    static class Employee {
        long index;
        EmployeeType type;

        public Employee(long index, EmployeeType type) {
            this.index = index;
            this.type = type;
        }
    }

    static enum EmployeeType {
        D,R
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

    }
}

