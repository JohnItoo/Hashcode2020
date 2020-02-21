package hashcode20;

import java.util.ArrayList;
import java.util.Random;

public class Library implements Comparable<Library>{

    int allBooks;
    int numberBooks; int daysToSignUp; int numberOfDaysShip; int current;
    ArrayList<Integer> ids;
    boolean isSigned = false;
    ArrayList<Integer> allSigned;
    int idx;

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Integer> ids) {
        this.ids = ids;
    }

    public Library(int numberBooks, int daysToSignUp, int numberOfDaysShip, int idx, int allBooks) {
        this.numberBooks = numberBooks;
        this.daysToSignUp = daysToSignUp;
        this.numberOfDaysShip = numberOfDaysShip;
        this.current = 0;
        ids = new ArrayList<>();
        allSigned = new ArrayList<>();
        this.idx = idx;
        this.allBooks = allBooks;
    }

    public int getNumberBooks() {
        return numberBooks;
    }

    public void setNumberBooks(int numberBooks) {
        this.numberBooks = numberBooks;
    }

    public int getDaysToSignUp() {
        return daysToSignUp;
    }

    public void setDaysToSignUp(int daysToSignUp) {
        this.daysToSignUp = daysToSignUp;
    }

    public int getNumberOfDaysShip() {
        return numberOfDaysShip;
    }

    public void setNumberOfDaysShip(int numberOfDaysShip) {
        this.numberOfDaysShip = numberOfDaysShip;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSigned() {
        return this.isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public ArrayList<Integer> getAllSigned() {
        return allSigned;
    }

    public void setAllSigned(ArrayList<Integer> allSigned) {
        this.allSigned = allSigned;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getRatio() {
//        return allBooks / (daysToSignUp + (numberBooks/numberOfDaysShip));
//        return numberBooks/numberOfDaysShip;
//        return allBooks / daysToSignUp;;
        return daysToSignUp + (numberBooks/numberOfDaysShip) / allBooks;

    }

    @Override
    public int compareTo(Library o) {
//        return (this.getRatio() > o.getRatio() ? 1 : -1);
        int s1 = this.getRatio();
        int s2 = o.getRatio();

        if(s1 == s2) {
            return 0;
        } else if(s2 > s1) {
            return 1;
        } else {
            return  -1;
        }

    }

    @Override
    public String toString() {
        return "Library{" +
                "allBooks=" + allBooks +
                ", numberBooks=" + numberBooks +
                ", daysToSignUp=" + daysToSignUp +
                ", numberOfDaysShip=" + numberOfDaysShip +
                ", current=" + current +
                ", ids=" + ids +
                ", isSigned=" + isSigned +
                ", allSigned=" + allSigned +
                ", idx=" + idx +
                ", ratio=" + getRatio() + '}';
    }
}
