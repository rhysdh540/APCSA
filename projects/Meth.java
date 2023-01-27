import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

// helpful methods
@SuppressWarnings("unused")
public class Meth {
    private Meth(){}

    /**
     * Recursive for loop<br>
     * {@code for(int i = 0; i < n; i++) { ... }} is the same as {@code forLoop(0, n, i -> { ... });}
     * @param start place to start at
     * @param end place to end at
     * @param step how much to increment every time
     * @param action what code to execute
     */
    public static void forLoop(int start, int end, int step, @NotNull Consumer<Integer> action) {
        // base cases
        if(step == 0) throw new IllegalArgumentException("you made step 0 lol");
        if(step>0) {
            if(start>end) return;
        }else if(start<end) return;

        action.accept(start); // the thing actually happening
        forLoop(start + step, end, step, action); // loop iterating
    }

    /**
     * Recursive for-each (enhanced for) loop<br>
     * {@code for(int i : arr) { ... }} is the same as {@code forEach(arr, i -> { ... });}
     * @param arr the array to perform actions on
     * @param action what to perform
     */
    public static <T> void forEach(@NotNull T[] arr, @NotNull Consumer<T> action) {
        feHelper(0, arr, action);
    }
    /**
     * helper method for {@link Meth#forEach(T[], Consumer)}
     * @param index what index of the array to perform the action on
     * @param arr the array to perform actions on
     * @param action what to perform
     */
    private static <T> void feHelper(int index, T[] arr, Consumer<T> action) {
        if(index>=arr.length) return;
        action.accept(arr[index]);
        feHelper(index+1, arr, action);
    }

    /**
     * recursive while loop<br>
     * Not recommended for infinite loops (will throw a {@code StackOverflowException})<br>
     * {@code while(condition) { ... }} is the same as {@code whileLoop(() -> condition, () -> { ... });}
     * @param condition what to check before executing the action
     * @param action the action to perform
     */
    public static void whileLoop(Supplier<Boolean> condition, Runnable action) {
        if(!condition.get()) return;
        action.run();
        whileLoop(condition, action);
    }
    public static int random(int start, int end){
        return new Random().nextInt(start, end+1);
    }
}
