package functor;

public class Less implements Comparator {
    @Override
    public boolean compare(int left, int right) {
        return left < right;
    }
}
