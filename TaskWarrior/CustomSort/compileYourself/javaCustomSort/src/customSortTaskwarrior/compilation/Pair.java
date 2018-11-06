//To compile as jar you need to comment out this package allocation. For testing you uncomment it:
//package customSortTaskwarrior.compilation;
//package test0;

public class Pair implements Comparable<Pair> {
    public final int index;
    public final double value;

    public Pair(int index, double value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(Pair other) {
        //multiplied to -1 as the author need descending sort order
        //return -1 * Integer.valueOf(this.value).compareTo(other.value);
    	if (this.value<=other.value) {return -1;} else if(this.value==other.value) {return 0;} else {return +1;}    	
    }
}