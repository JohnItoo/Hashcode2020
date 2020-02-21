package hashcode20;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Solver {
    int B = 0, L=0, D=0;
    int[] S = new int[1000000];
    List<Library> libraries;
    List<Library> unsort;
    List<Integer> ans;
    int globSignCount;




    public void parse(String filename) {
        libraries = new ArrayList<>();
        unsort = new ArrayList<>();
        ans = new ArrayList<>();
        int bufferSize = 8 * 1024;

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filename), bufferSize);
            String line = bufferedReader.readLine();
            String[] request = line.split(" ");
            B = Integer.parseInt(request[0]); L = Integer.parseInt(request[1]); D= Integer.parseInt(request[2]);

            line = bufferedReader.readLine();
             request = line.split(" ");

             for(int i = 0; i<B; i++) {
               S[i] = Integer.parseInt(request[i]);
             }


            int N =0, T=0, M=0;
            for(int i = 0; i<L; ++i) {
                line = bufferedReader.readLine();
                request = line.split(" ");
                int Ni = Integer.parseInt(request[0]);
                Library lib = new Library(Integer.parseInt(request[0]), Integer.parseInt(request[1]), Integer.parseInt(request[2]), i, B);


                line = bufferedReader.readLine();
                request = line.split(" ");

                ArrayList<Integer> bookIds = new ArrayList<>();
                for(int j = 0; j<request.length; ++j) {
                    bookIds.add(Integer.parseInt(request[j]));
//                    System.out.println(Integer.parseInt(request[j]));
                }

                lib.setIds(bookIds);

                libraries.add(lib);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//use number of books can ship
    public void solve() {
        boolean [] doneBooks = new boolean[B];
        boolean [] signed = new boolean[L];
        boolean moveOn = false;
        int signCount = 0;
         int ct = 0;
        Collections.sort(libraries);
//        while(ct<100) {

          for(int i = 0; i<B; ++i) {
              if(doneBooks[i]) continue;
              if(ct>100000) break;


              System.out.println(libraries);
              for(int x = 0; x<libraries.size(); ++x) {
                  Library library = libraries.get(x);

//              for(int q = 0; q<libraries.size(); q++) {
//                  int rand = new Random().nextInt(libraries.size());
//                  Library library = libraries.get(rand);

                  if(!canComplete(library)) continue;
                  if(library.isSigned()) continue;
                  library.setCurrent(simulate(library));
                  library.setSigned(true);
                  ans.add(x);
                  signCount++;
                  globSignCount = signCount;
                ArrayList<Integer> booksSent = new ArrayList<>();
                  for(int j= 0; j<library.getIds().size(); ++j)  {
                      ArrayList<Integer> ids = library.getIds();
                      doneBooks[ids.get(j)] = true;
//                      System.out.println("adding this book from libray" + ids.get(j) + library.getIdx());
                      booksSent.add(ids.get(j));
                      moveOn = true;
                      ct++;
                  }
                  library.setAllSigned(booksSent);
              }

          }
//          if(!moveOn) {
//              globSignCount = signCount;
//              break;
//          }
      //  }

    }
    public boolean canComplete(Library library) {
        return library.getDaysToSignUp() + library.getCurrent() < D;
    }

    public int simulate(Library library) {
        return library.getDaysToSignUp() + library.getCurrent();
    }

    public void print(String filename) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
            writer.println(globSignCount);
            //writer.print();
            for(int i=0; i<ans.size(); ++i) {
                //ans.geti is index of that libray
                Library library = libraries.get(ans.get(i));
                writer.println(library.getIdx() + " "  + library.getAllSigned().size());

                for(int j =0; j<library.getAllSigned().size(); j++) {
                    writer.print(library.getAllSigned().get(j) + " ");
                }
                writer.println();
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}