package com.company.oldCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main391c {
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

        PokemonSetManager pokemonSetManager;

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            long[] permts = new long[1000002];

            permts[0] = 1;
            permts[1] = 1;
            for (int i = 2; i < 1000002; i++) {
                permts[i] = (permts[i-1] * i) % 1000000007;
            }
            pokemonSetManager = new PokemonSetManager();
            pokemonSetManager.init(m);
            for (int i = 0; i < n; i++) {
                int count = in.nextInt();
                HashMap<Integer, Integer> pokemonsCount = new HashMap<>();
                for (int j = 0; j < count; j++) {
                    int pokemon = in.nextInt() - 1;
                    Integer val = pokemonsCount.get(pokemon);
                    pokemonsCount.put(pokemon, val == null ? 1 : val + 1);
                }
                HashMap<Integer, TreeSet<Integer>> pokemonCountMap = new HashMap<>();
                for (HashMap.Entry<Integer, Integer> entry : pokemonsCount.entrySet()) {
                    TreeSet<Integer> pokemons = pokemonCountMap.get(entry.getValue());
                    if (pokemons == null) {
                        pokemons = new TreeSet<>();
                    }
                    pokemons.add(entry.getKey());
                    pokemonCountMap.put(entry.getValue(), pokemons);
                }
                for (TreeSet<Integer> pokemons : pokemonCountMap.values()) {
                    pokemonSetManager.addPokemonSet(pokemons);
                }
            }
            long result = 1l;
            for (TreeSet<Integer> pokemons : pokemonSetManager.pokemonSetMap.values()) {
                result *= permts[pokemons.size()];
                result %= 1000000007;
            }
            out.println(result);
        }
    }

    static class PokemonSetManager {

        Map<Integer,TreeSet<Integer>> pokemonSetMap;
        Map<Integer, Integer> pokemonSetIdentifier;

        void addPokemonSet(TreeSet<Integer> pokemons) {
            if (pokemonSetMap == null) {
                this.pokemonSetMap = new HashMap<>();
                this.pokemonSetMap.put(pokemons.first(), new TreeSet<>(pokemons));
            }
            else {
                while (pokemons.size() > 0) {
                    Integer firstPokemon = pokemons.first();

                    TreeSet<Integer> oldPokemonSet = pokemonSetMap.get(pokemonSetIdentifier.get(firstPokemon));

                    TreeSet<Integer> unionSet = new TreeSet<>(oldPokemonSet);
                    unionSet.retainAll(pokemons);
                    oldPokemonSet.removeAll(unionSet);
                    pokemons.removeAll(unionSet);
                    if (!unionSet.isEmpty()) {
                        int unionSetFirst = unionSet.first();
                        pokemonSetMap.put(unionSet.first(), unionSet);
                        for (Integer x : unionSet) {
                            pokemonSetIdentifier.put(x, unionSetFirst);
                        }
                    }
                    if (!oldPokemonSet.isEmpty()) {
                        int oldPokemonSetFirst = oldPokemonSet.first();
                        pokemonSetMap.put(oldPokemonSet.first(), oldPokemonSet);
                        for (Integer x : oldPokemonSet) {
                            pokemonSetIdentifier.put(x, oldPokemonSetFirst);
                        }
                    }
                }
            }
        }

        void init(int m) {
            pokemonSetIdentifier = new HashMap<>(m);
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < m; i++) {
                set.add(i);
                pokemonSetIdentifier.put(i, 0);
            }
            this.pokemonSetMap = new HashMap<>();
            this.pokemonSetMap.put(0, set);
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

