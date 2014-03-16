package functor;

public class Equal implements Comparator {

    @Override
    public boolean compare(int left, int right) {
        return left == right;
    }
}
