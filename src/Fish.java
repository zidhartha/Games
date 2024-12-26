public interface Fish {
    int flight();
    int taste();

    static Fish breed(int flight,int taste){
        Fish fish = new Fish() {
            @Override
            public int flight() {
                return flight;
            }

            @Override
            public int taste() {
                return taste;
            }
        };
        return fish;
    }
    default String string(){
        return "flight: " + this.flight() + " ,taste: " + this.taste();
    }
}
