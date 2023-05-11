package dev.rdh.apcsa.util;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Helpful methods for things and stuff
 */
@SuppressWarnings("unused")
public class Meth { // short for methods (obviously)
    private Meth(){ throw new UnsupportedOperationException("no"); }

    /**
     * Recursive for loop<br>
     * {@code for(int i = 0; i < n; i+= k) { ... }} is the same as {@code forLoop(0, n, k, i -> { ... });}
     * @param start place to start at
     * @param end place to end at
     * @param step how much to increment every time
     * @param action what code to execute
     */
    public static void forLoop(int start, int end, int step, @NotNull Consumer<Integer> action) {
        // base cases
        if(step == 0) throw new IllegalArgumentException("you made step 0 lol");
        if((step > 0 && start > end) || (step < 0 && start < end))
            return;

        action.accept(start); // the thing actually happening
        forLoop(start + step, end, step, action); // loop iterating
    }

    //shortcut for for-loops with step 1
    public static void forLoop(int start, int end, @NotNull Consumer<Integer> action) {
        forLoop(start, end, 1, action);
    }

    /**
     * Recursive for-each (enhanced for) loop<br>
     * {@code for(int i : arr) { ... }} is the same as {@code forEach(arr, i -> { ... });}
     * @param arr the array to perform actions on
     * @param action what to perform
     */
    public static <Type> void forEach(@NotNull Type[] arr, @NotNull Consumer<Type> action) {
        forEach0(0, arr, action);
    }
    /**
     * helper method for {@link Meth#forEach(Type[], Consumer)}
     * @param index what index of the array to perform the action on
     * @param arr the array to perform actions on
     * @param action what to perform
     */
    private static <Type> void forEach0(int index, Type[] arr, Consumer<Type> action) {
        if(index>=arr.length) return;
        action.accept(arr[index]);
        forEach0(index+1, arr, action);
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
