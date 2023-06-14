package dev.rdh.compsci.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Helpful methods for things and stuff
 */
@SuppressWarnings("unused")
public class Meth { // short for methods (obviously)
    private Meth(){ throw new RuntimeException(new InstantiationException("no")); }

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
        if(step == 0)
            throw new IllegalArgumentException("you made step 0 lol");
        if(!((step > 0 && start > end) || (step < 0 && start < end))) {
            action.accept(start); // the thing actually happening
            try {
                forLoop(start + step, end, step, action); // loop iterating
            } catch (StackOverflowError e) {
                throw new IllegalStateException("Recursive for loop exceeded stack size. Ran " + (end - start) / step + " times.");
            }
        }
    }

    //shortcut for for-loops with step 1
    public static void forLoop(int start, int end, @NotNull Consumer<Integer> action) {
        if(start > end) forLoop(start, end, -1, action);
        else forLoop(start, end, 1, action);
    }

    /**
     * Recursive for-each (enhanced for) loop<br>
     * {@code for(int i : arr) { ... }} is the same as {@code forEach(arr, i -> { ... });}
     * @param arr the array to perform actions on
     * @param action what to perform
     */
    public static <T> void forEach(@NotNull T[] arr, @NotNull Consumer<T> action) {
        forEach0(0, arr, action);
    }
    /**
     * helper method for {@link Meth#forEach(T[], Consumer)}
     * @param index what index of the array to perform the action on
     * @param arr the array to perform actions on
     * @param action what to perform
     */
    private static <T> void forEach0(int index, T[] arr, Consumer<T> action) {
        if(index<=arr.length) {
            action.accept(arr[index]);
            forEach0(index + 1, arr, action);
        }
    }

    /**
     * recursive while loop<br>
     * Not recommended for infinite loops (will throw a {@code StackOverflowError})<br>
     * {@code while(condition) { ... }} is the same as {@code whileLoop(() -> condition, () -> { ... });}
     * <p>Please do note that all variables referenced in the lambda must be final or effectively final, so either use {@code new int[1]} or an {@code AtomicInteger} instead of an {@code int}.</p>
     * <p>Suppliers are used because regular booleans are passed in as either true or false and cannot be recomputed every iteration of the loop.</p>
     * @param condition what to check before executing the action
     * @param action the action to perform
     */
    public static void whileLoop(Supplier<Boolean> condition, Runnable action) {
        if(condition.get()) {
            action.run();
            whileLoop(condition, action);
        }
    }
    public static int random(int start, int end) {
        return new Random().nextInt(start, end+1);
    }

    /**
     * Takes and input and returns a string that can be used to recreate the input, in the form of a String constructed from a byte array.
     * <p>For example, if the input were, "Hello world!", the output would be:<br>{@code new String(new byte[]{72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33})}</p>
     * @param input the string to obfuscate
     * @return the obfuscated string
     */
    public static String obfuscate(String input) {
        String bytes = Arrays.toString(input.getBytes());
        return "new String(new byte[]{" + bytes.substring(1, bytes.length() - 1) + "})";
    }
    public static <T> T[] arrayAdd(T[] arr, T toAdd) {
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[arr.length] = toAdd;
        return newArr;
    }

    /**
     * Returns the hashcode of an object, or 0 if the object is null.
     * @param o the object to get the hashcode of
     * @return the hashcode of the object, or 0 if the object is null
     */
    public static int nullHash(Object o) {
        return o == null ? 0 : o.hashCode();
    }
}
