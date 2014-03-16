package functor;

public class GreaterEqual implements Comparator {
    @Override
    public boolean compare(int left, int right) {
        return left >= right;
    }
}
