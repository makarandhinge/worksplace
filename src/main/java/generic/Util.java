package generic;

public class Util {

    public static <T> void printArray(T[] arr){
        for(T t : arr){
            System.out.println(t);
        }
    }

    public static <T extends Comparable<T>> T max(T a, T b){
        return a.compareTo(b) > 0 ? a : b;
    }


}
