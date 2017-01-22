package com.company.oldCode;

import java.io.*;
import java.util.*;

public class TOURISTS {
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

        int N,E;
        Set<Road> roadSet;
        ArrayList<Road> roadArrayList;
        ArrayList<City> cities;

        public void solve(InputReader in, PrintWriter out) {
            N = in.nextInt();
            E = in.nextInt();
            roadSet = new HashSet<>(E);
            roadArrayList = new ArrayList<>(E);
            cities = new ArrayList<>(N);
            for (int i = 0; i < N; i++) {
                cities.add(new City(i));
            }
            for (int i = 0; i < E; i++) {
                int origin = in.nextInt() - 1;
                int dest = in.nextInt() - 1;
                Road road = new Road(cities.get(origin), cities.get(dest));
                roadSet.add(road);
                roadArrayList.add(road);
                cities.get(origin).addRoad(road);
                cities.get(dest).addRoad(road);
            }

            if (!chkValid()) {
                out.println("NO");
                return;
            }

            ArrayList<Road> travelledRoads = travel();
            if (travelledRoads.size() == E) {
                out.println("YES");
                for (Road road : roadArrayList) {
                    out.println((road.originCity.index + 1) + " " + (road.destCity.index + 1));
                }
            } else {
                out.println("NO");
            }
        }

        boolean chkValid() {
            for (int i = 0; i < N; i++) {
                if ((cities.get(i).cityRoads.size() & 1) > 0) {
                    return false;
                }
            }
            return isCompletelyConnected();
        }

        boolean isCompletelyConnected() {
            boolean[] isVisited = new boolean[N];
            Stack<Integer> toBeVisited = new Stack<>();
            for (int i = 0; i < N; i++) {
                isVisited[i] = false;
            }
            toBeVisited.add(0);
            isVisited[0] = true;
            while (toBeVisited.size() > 0) {
                int currentCity = toBeVisited.pop();
                for (City x : cities.get(currentCity).connectedCities) {
                    if (!isVisited[x.index]) {
                        isVisited[x.index] = true;
                        toBeVisited.add(x.index);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                if (!isVisited[i]) {
                    return false;
                }
            }
            return true;
        }

        ArrayList<Road> travel() {
            ArrayList<Road> travelledRoads = new ArrayList<>(E);
            City currentCity = cities.get(0);
            for (int i = 0; i < E; i++) {
                Road road = currentCity.getOutRoad();
                if (road == null) {
                    currentCity = roadSet.iterator().next().originCity;
                    road = currentCity.getOutRoad();
                }
                roadSet.remove(road);
                travelledRoads.add(road);
                currentCity = road.destCity;
            }
            return travelledRoads;
        }
    }

    static class City {
        int index;
        ArrayList<Road> cityRoads;
        Set<City> connectedCities;
        int currentRoadIndex;

        City (int index) {
            this.index = index;
            cityRoads = new ArrayList<>();
            connectedCities = new HashSet<>();
            currentRoadIndex = 0;
        }

        void addRoad(Road road) {
            cityRoads.add(road);
            connectedCities.add(road.originCity == this ? road.destCity : road.originCity);
        }

        Road getOutRoad() {
            int roadsCount = cityRoads.size();
            for (; currentRoadIndex < roadsCount; currentRoadIndex++) {
                Road road = cityRoads.get(currentRoadIndex);
                if (!road.isTraversed) {
                    road.isTraversed = true;
                    if (road.destCity == this) {
                        road.changeDirection();
                    }
                    return road;
                }
            }
            return null;
        }
    }

    static class Road {
        City originCity;
        City destCity;
        boolean isTraversed;

        Road (City originCity, City destCity) {
            this.originCity = originCity;
            this.destCity = destCity;
            isTraversed = false;
        }

        void changeDirection() {
            City x = originCity;
            originCity = destCity;
            destCity = x;
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

