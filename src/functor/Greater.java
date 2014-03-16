package functor;

public class Greater implements Comparator {

    @Override
    public boolean compare(int left, int right) {
        return left > right;
    }
}
