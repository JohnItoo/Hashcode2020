/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode20;


/**
 * @author mac
 */
public class HashCode2020 {

    public static void main(String[] args) {
//        String[] inputs = {"a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt", "e_so_many_books.txt", "f_libraries_of_the_world.txt};
        String[] inputs = {"e_so_many_books.txt"};


        for (String in : inputs) {
            String dir1 = "inputFiles/"; String dir2 = "outputFiles/";
            String filepath = "/Users/mac/IdeaProjects/hashcode20/src/hashcode20/";
            String inPath  = filepath+dir1+in;
            String outPath = filepath+dir2+in+".out";


            Solver solver = new Solver();
            solver.parse(inPath);
            solver.solve();
            solver.print(outPath);
        }
    }
}