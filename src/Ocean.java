import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ocean {
    List<Fish> ocean;

    public Ocean(Fish ... fu){
        ocean = new ArrayList<>();
        ocean.addAll(Arrays.asList(fu));
    }

    public synchronized void hunt(int speed){
        for(Fish fish : ocean){
            if(fish.flight() < speed && fish.taste() != 0){
                fish.string();
                ocean.remove(fish);
            }else{
                System.out.println("Nothing!");
            }
        }
    }
}
