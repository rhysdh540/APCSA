package dev.rdh.compsci.apcsa;

@SuppressWarnings("unused")
public class Domino {
    /* FIELDS */
    private int top, bottom;

    /* CONSTRUCTORS */
    public Domino(){
        this(0, 0);
    }
    public Domino(int top, int bottom){
        if(top < 0 || top > 6 || bottom < 0 || bottom > 6)
            throw new IllegalArgumentException("The top and bottom of a domino must be between 0 and 6, inclusive.");
        this.top = top;
        this.bottom = bottom;
    }
    public Domino(Domino other){
        this(other.top, other.bottom);
    }

    /* METHODS */
    public int getBottom() {
        return bottom;
    }
    public int getTop() {
        return top;
    }
    public int setBottom(int bottom) {
        int e = this.bottom;
        this.bottom = bottom;
        return e;
    }
    public int setTop(int top) {
        int e = this.top;
        this.top = top;
        return e;
    }
    public Domino flip(){
        int temp = top;
        top = bottom;
        bottom = temp;
        return this;
    }
    @Override
    public String toString(){
        return "[" + top + "|" + bottom + "]";
    }
    public String toStringFancy() {
        switch(top){
            case 0 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC63";
                    case 1 -> "\uD83C\uDC64";
                    case 2 -> "\uD83C\uDC65";
                    case 3 -> "\uD83C\uDC66";
                    case 4 -> "\uD83C\uDC67";
                    case 5 -> "\uD83C\uDC68";
                    case 6 -> "\uD83C\uDC69"; // nice
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            case 1 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC6A";
                    case 1 -> "\uD83C\uDC6B";
                    case 2 -> "\uD83C\uDC6C";
                    case 3 -> "\uD83C\uDC6D";
                    case 4 -> "\uD83C\uDC6E";
                    case 5 -> "\uD83C\uDC6F";
                    case 6 -> "\uD83C\uDC70";
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            case 2 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC71";
                    case 1 -> "\uD83C\uDC72";
                    case 2 -> "\uD83C\uDC73";
                    case 3 -> "\uD83C\uDC74";
                    case 4 -> "\uD83C\uDC75";
                    case 5 -> "\uD83C\uDC76";
                    case 6 -> "\uD83C\uDC77";
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            case 3 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC78";
                    case 1 -> "\uD83C\uDC79";
                    case 2 -> "\uD83C\uDC7A";
                    case 3 -> "\uD83C\uDC7B";
                    case 4 -> "\uD83C\uDC7C";
                    case 5 -> "\uD83C\uDC7D";
                    case 6 -> "\uD83C\uDC7E";
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            case 4 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC7F";
                    case 1 -> "\uD83C\uDC80";
                    case 2 -> "\uD83C\uDC81";
                    case 3 -> "\uD83C\uDC82";
                    case 4 -> "\uD83C\uDC83";
                    case 5 -> "\uD83C\uDC84";
                    case 6 -> "\uD83C\uDC85";
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            case 5 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC86";
                    case 1 -> "\uD83C\uDC87";
                    case 2 -> "\uD83C\uDC88";
                    case 3 -> "\uD83C\uDC89";
                    case 4 -> "\uD83C\uDC8A";
                    case 5 -> "\uD83C\uDC8B";
                    case 6 -> "\uD83C\uDC8C";
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            case 6 -> {
                return switch (bottom) {
                    case 0 -> "\uD83C\uDC8D";
                    case 1 -> "\uD83C\uDC8E";
                    case 2 -> "\uD83C\uDC8F";
                    case 3 -> "\uD83C\uDC90";
                    case 4 -> "\uD83C\uDC91";
                    case 5 -> "\uD83C\uDC92";
                    case 6 -> "\uD83C\uDC93";
                    default -> throw new IllegalStateException("Unexpected value: " + bottom);
                };
            }
            default -> throw new IllegalStateException("Unexpected value: " + top);
        }
    }
    public void settle(){
        if(bottom > top)
            flip();
    }
    public int compareTo(Domino other){
        int thisSmall = Math.min(top, bottom),
            otherSmall = Math.min(other.top, other.bottom),
            thisLarge = Math.max(top, bottom),
            otherLarge = Math.max(other.top, other.bottom);
        return (thisSmall == otherSmall) ?
            Integer.compare(thisLarge, otherLarge) :
        Integer.compare(thisSmall, otherSmall);
    }
    public int compareToWeight(Domino other){
        return Integer.compare(this.bottom, other.top);
    }
    public boolean canConnect(Domino other){
        return top == other.top || top == other.bottom || bottom == other.top || bottom == other.bottom;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(!(obj instanceof Domino other))
            return false;
        return top == other.top && bottom == other.bottom;
    }
}
