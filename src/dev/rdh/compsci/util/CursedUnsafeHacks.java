package dev.rdh.compsci.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Optional;

public class CursedUnsafeHacks {
    private static final Unsafe theUnsafe;

    static {
        try {
            final var field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            theUnsafe = (Unsafe) field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Failed to get theUnsafe", e);
        }
    }

    public static <T> T allocateInstance(Class<T> clazz) {
        try {
            return (T) theUnsafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to allocate instance of " + clazz, e);
        }
    }

    public static <T> T getField(Field field, Object object) {
        final long l = theUnsafe.objectFieldOffset(field);
        return (T) theUnsafe.getObject(object, l);
    }

    public static Optional<Field> findField(Class<?> clazz, String name) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(name)) {
                return Optional.of(field);
            }
        }
        return Optional.empty();
    }

    public static void setField(Field field, Object object, Object value) {
        final long offset = theUnsafe.objectFieldOffset(field);
        theUnsafe.putObject(object, offset, value);
    }
}
