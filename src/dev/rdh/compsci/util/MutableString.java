package dev.rdh.compsci.util;

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
        if (another instanceof MutableString || another instanceof StringBuilder || another instanceof StringBuffer || another instanceof String) {
            return data.equals(another.toString());
        }
        return false;
    }
    public String toString() {
        return data;
    }
    public <T> MutableString append(T t) {
        data += t;
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
        data = data.replace(oldChar, newChar);
        return this;
    }
    public MutableString replace(String oldStr, String newStr) {
        data = data.replace(oldStr, newStr);
        return this;
    }
}
