package hashcode20.practiceQuestion;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HubPizza {
    int maxPizzas;
    int types;
    List<Integer> slices;
    List<Integer> solution;
    HashMap<Integer, Integer> freqMap;
    HashMap<Integer, Integer> orgMap;

    public void parse(String filename) {
        int bufferSize = 8 * 1024;
        slices = new ArrayList<>();
        solution = new ArrayList<>();
        freqMap = new HashMap<>();
        orgMap = new HashMap<>();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename), bufferSize);
            String line = bufferedReader.readLine();
            String[] request = line.split(" ");
            maxPizzas = Integer.parseInt(request[0]);
            int pizzas = Integer.parseInt(request[1]);

            line = bufferedReader.readLine();
            String[] secondLine = line.split(" ");

            for (int p = 0; p < pizzas; p++) {
                int slice = Integer.parseInt(secondLine[p]);
                slices.add(slice);
                //I am adding to map here because I might need it for Optimisation?
                if (freqMap.containsKey(slice)) {
                    int curr = freqMap.get(slice);
                    freqMap.put(slice, curr + 1);
                    orgMap.put(slice, curr + 1);
                } else {
                    freqMap.put(slice, 1);
                    orgMap.put(slice, 1);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decideOne() {
        Collections.sort(slices); //nlogn;
        int sliz = slices.size();
        for (int i = sliz - 1; i >= 0; --i) {
            if (maxPizzas - slices.get(i) < 0) continue;
            maxPizzas -= slices.get(i);
            solution.add(i);
        }
    }


    public void print(String filename) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
            types = solution.size();
            writer.print(types);
            writer.println();
            writer.print(solution.get(0));
            for (int i = 1; i < types; i++) {
                writer.print(" " + solution.get(i));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void decideTwo() {
        List<Integer>[] adjList = new List[maxPizzas + 1];

        for (int i = 0; i <= maxPizzas; ++i) {
            adjList[i] = new ArrayList<>();
        }

        boolean[] done = new boolean[maxPizzas + 1];
        done[0] = true;
        for (int i = 1; i <= maxPizzas; ++i) {
            for (int j = 0; j < slices.size(); ++j) {
                if (i - slices.get(j) < 0) break;
                if (done[i - slices.get(j)]) {
                    List<Integer> curr = adjList[i - slices.get(j)];
                    System.out.println("We plan to use pizza " + slices.get(j) + " and " + (i - slices.get(j)) + " to get " + i );
                    boolean isUsed = false;
                    for (Integer integer : curr) {
                        if (integer == j) {
                            System.out.println("We have already used pizza at " + slices.get(j));
                            isUsed = true;
                            break;
                        }
                    }
                    if (!isUsed) {
                        for (int st : curr) {
                            adjList[i].add(st);
                        }

                        adjList[i].add(j);
                        System.out.println("We are using " + slices.get(j));
                        done[i] = true;
                        break;
                    }

                }
            }
        }

        for (int i = maxPizzas; i >= 0; --i) {
            if (done[i]) {
                solution = adjList[i];
                System.out.println(i);
                break;
            }
        }
    }

    public void decideThree() {
        //I just want to hold a few of the adjaceny list and let it grow  I dont know how this will work so I want to mark the guyss n someway?
        int slizes = slices.size();
        List<Integer>[] adjList = new List[slizes];

        for (int i = 0; i < slizes; ++i) {
            adjList[i] = new ArrayList<>();
        }

        boolean[] done = new boolean[maxPizzas + 1];
        done[0] = true;
        for (int i = 1; i <= maxPizzas; ++i) {
            System.out.println("maxPizzas is at " + i);
            for (int j = 0; j < slices.size(); ++j) {
                if (i - slices.get(j) < 0) continue;
                System.out.println("Get to work at" + i + " " + j);
                System.out.println("is done ? " + done[i - slices.get(j)]);
                if (done[i - slices.get(j)]) {
                    int count = freqMap.get(slices.get(j));
                    if (count <= 0) continue;
                    adjList[j].add(i);
                    System.out.println("Marked as done : " + i);
                    freqMap.put(slices.get(j), count - 1);
                    done[i] = true;
                    break;
                }

            }
        }

        int maxAtt = 0;
  //look for everywhere in adjList that has that maxValue?
        //adjlist here is a list of all values that this slice can make
        for (int i = maxPizzas; i >= 0; --i) {
            if (done[i]) {
               maxAtt = i;
                break;
            }
        }

        for(int i = 0; i<slizes; ++i) {
            List<Integer> currList = adjList[i];
            for(int el : currList) {
                if(el == maxAtt) {
                   solution.add(i);
                }
            }
        }
    }

}