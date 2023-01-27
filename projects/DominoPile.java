import java.util.ArrayList;

@SuppressWarnings("unused")
public class DominoPile {
    private ArrayList<Domino> pile = new ArrayList<>();
    public DominoPile(){}
    public void newStack6() {
        pile = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Domino test = new Domino(i,j);
                if(!pile.contains(test) && !pile.contains(test.flip()))
                    pile.add(test.flip());
            }
        }
    }
    public void shuffle() {
        ArrayList<Domino> shuffled = new ArrayList<>();
        while(!pile.isEmpty()){
            int rand = (int)(Math.random() * (pile.size()-1));
            Domino d = pile.get(rand);
            shuffled.add(d);
        }
        pile = shuffled;
    }
    // extra stuff
    public DominoPile add(Domino d) {
        pile.add(d);
        return this;
    }
    public DominoPile remove(int index) {
        pile.remove(index);
        return this;
    }
    public DominoPile remove(Domino target) {
        for(Domino d : pile)
            if(d.equals(target)){
                pile.remove(d);
                return this;
            }
        return this;
    }
    public String toString() {
        return pile.toString();
    }
}
