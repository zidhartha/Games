public class Couple implements Comparable<Couple>{
    private int eggs;
    private int fish;
    public Couple(int eggs,int fish){
        this.eggs = eggs;
        this.fish = fish;
    }
    public int compareTo(Couple coup){
        if(this.eggs > coup.eggs && this.fish > coup.fish){
            return 1;
        }else if(this.eggs < coup.eggs && this.fish < coup.fish){
            return -1;
        }else{
            return 0;
        }
    }

    public int getEggs() {
        return eggs;
    }

    public int getFish() {
        return fish;
    }
}
