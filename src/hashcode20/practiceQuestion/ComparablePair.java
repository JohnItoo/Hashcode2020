package hashcode20.practiceQuestion;

import javafx.util.Pair;

public class ComparablePair extends Pair<Integer, Integer> implements Comparable<ComparablePair>{
    int key;
    int value;
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public ComparablePair(Integer key, Integer value) {
        super(key, value);
    }


    @Override
    public int compareTo(ComparablePair o) {
        //Ascending order
        return this.getValue().compareTo(o.getValue());

        // Descending order
//        return o.getValue().compareTo(this.getValue());
    }

    @Override
    public String toString() {
        key = super.getKey();
        value = super.getValue();

        return "ComparablePair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }


}
