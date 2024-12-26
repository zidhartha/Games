import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Reputation {
    private Reputation left;
    private Reputation right;
    Couple coup;
    private int size;
    List<Couple> reputationList = new ArrayList<>();

    public Reputation() {
        Reputation right;
        Reputation left;
        Couple coup;
    }
    private void insert(Couple couple, Comparator<Couple> comp){
        if(coup.compareTo(couple) == 1){
            left.insert(couple,comp);
            size++;
        }else if(coup.compareTo(couple) == -1){
            right.insert(couple,comp);
            size++;
        }if(coup.compareTo(couple) == 0){
            return;
        }
    }
    public Couple[] flatten(){

    }

    public Iterator<Couple> iterator(){
        return new Iterator<Couple>() {
             Reputation next = new Reputation();
            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Couple next() {
                return next.coup;
            }
        };
    }
    public Stream<Couple> stream(){
        return
    }

    public List<Couple> extract(int reput){
        return reputationList;
    }

    private void extract(int reput,Reputation reputation){
        if(this.coup.getFish() + this.coup.getEggs() > reput){
            reputationList.add(this.coup);
        }
        if(this.left == null && this.right == null) {
            extract(reput);
        }
        extract(reput,this.left);
        extract(reput,this.right);
    }

    }
}
