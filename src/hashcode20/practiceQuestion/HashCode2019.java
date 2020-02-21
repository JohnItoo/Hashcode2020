/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode20.practiceQuestion;


/**
 * @author mac
 */
public class HashCode2019 {

    public static void main(String[] args) {
        String[] inputs = {"a_examples"};

        for (String in : inputs) {
            String filepath = "/Users/mac/IdeaProjects/hashcode20Prep/src/hashcode2019/" + in;


            HubPizza pz = new HubPizza();
            pz.parse(filepath + ".in");
            pz.decideTwo();
            pz.print(filepath + ".out");
        }
    }
}