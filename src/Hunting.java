public class Hunting implements Runnable{
    Ocean ocean;
    int speed;

    public Hunting(Ocean ocean,int speed){
        this.ocean = ocean;
        this.speed = speed;
    }
    @Override
    public synchronized void run() {
        while(true){
            try{
                this.ocean.hunt(this.speed);
                notifyAll();
                wait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Fish fi = new Fish() {
            @Override
            public int flight() {
                return 10;
            }

            @Override
            public int taste() {
                return 0;
            }
        };

        Fish fa = new Fish() {
            @Override
            public int flight() {
                return 5;
            }

            @Override
            public int taste() {
                return 3;
            }
        };
        Fish fo = new Fish() {
            @Override
            public int flight() {
                return 42;
            }

            @Override
            public int taste() {
                return 1;
            }
        };
        Fish fu = new Fish() {
            @Override
            public int flight() {
                return 10;
            }

            @Override
            public int taste() {
                return 7;
            }
        };
        Fish[] fishHunters ={fi,fa,fo,fu};
        Ocean ocean = new Ocean(fishHunters);
//        Hunting ping = new Hunting(ocean,15);
//        Hunting pong = new Hunting(ocean,20);
//        Hunting pung = new Hunting(ocean,25);
//        ping.run();
//        pong.run();
//        pung.run();
        Thread ping = new Thread( new Hunting(ocean,15));
        Thread pong = new Thread(new Hunting(ocean,20));
        Thread pung = new Thread(new Hunting(ocean,25));
        ping.start();
        pong.start();
        pung.start();
        ping.interrupt();
        pong.interrupt();
        pung.interrupt();
    }
}
