package dev.rdh.compsci.util;

import java.util.Set;

@SuppressWarnings("unused")
public class MutableString {
    private String data;

    public MutableString(String str) {
        data = str;
    }
    public MutableString() {
        this("");
    }
    public boolean equals(Object another) {
        if (another instanceof MutableString || another instanceof StringBuilder || another instanceof StringBuffer) {
            return data.equals(another.toString());
        }
        if(another instanceof String) {
            return data.equals(another);
        }
        return false;
    }
    public String toString() {
        return data;
    }
    public <T> MutableString append(T t) {
        final Set<Class<?>> classes = Set.of(
                Byte.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                Character.class,
                Boolean.class,
                String.class
        );
        if(classes.contains(t.getClass())) // basically if it's a primitive or a String
            data += t;
        else data += t.toString();
        return this;
    }
    public char charAt(int index) {
        return data.charAt(index);
    }
    public int length() {
        return data.length();
    }
    public MutableString substring(int start, int end) {
        return new MutableString(data.substring(start, end));
    }
    public MutableString substring(int start) {
        return new MutableString(data.substring(start));
    }
    public MutableString replace(char oldChar, char newChar) {
        return new MutableString(data.replace(oldChar, newChar));
    }
    public MutableString replace(String oldStr, String newStr) {
        return new MutableString(data.replace(oldStr, newStr));
    }

}
