//To compile as jar you need to comment out this package allocation. For testing you uncomment it:
//package customSortTaskwarrior.compilation;
//package test0;

public class StringPair implements Comparable<StringPair> {
    public final int index;
    public final String value;

    public StringPair(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(StringPair other) {
        int compare = this.value.compareTo(other.value);
        if (compare < 0){
            //System.out.println(this.value+" is before "+other.value);
            return -1;
        }
        else if (compare > 0) {
            //System.out.println(other.value+" is before "+this.value);
            return 1;
        }
        else {
            //System.out.println(other.value+" is same as "+this.value);
            return 0;
        }
    }
}