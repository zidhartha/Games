import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNIT = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;

    final int x[] = new int[GAME_UNIT]; //this will hold the x coordinates of the body parts
    final int y[] = new int[GAME_UNIT];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GREEN.darker());
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }


    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void restartGame(){
         bodyParts = 6;
         applesEaten = 0;
         running = true;
         direction = 'D';
         newApple();

        for (int i = bodyParts; i > 0; i--) {

            x[i] = 0;
            y[i] = 0;
        }
         timer.restart();
         repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD,20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten,(SCREEN_WIDTH- metrics.stringWidth("Score: " + applesEaten))/2,g.getFont().getSize() );
        }
        else{
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;

            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;

            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {

        if((x[0] == appleX)&& (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }

    }

    public void checkCollisions() {
        //this checks if the head collides with the body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //Checks if the head of the snake collided with either the left,right,up and down border
        if(x[0] < 0){
            x[0] = SCREEN_WIDTH - UNIT_SIZE;
            for (int i = bodyParts; i > 0 ; i--){
                x[i]= x[i-1];
            }
        }else if(y[0] < 0) {
            y[0] = SCREEN_HEIGHT - UNIT_SIZE;
            for (int i = bodyParts; i > 0; i--) {
                y[i] = y[i - 1];
            }
        }else if(x[0] > SCREEN_WIDTH){
                x[0] = 0;
            for (int i = bodyParts; i > 0 ; i--) {
                x[i] = x[i - 1];
            }
            }else if(y[0] > SCREEN_HEIGHT){
            y[0] = 0;
            for (int i = bodyParts; i > 0 ; i--){
                y[i]= y[i-1];
            }
        }
    }


    public void gameOver(Graphics g) {
        //GAME OVER text
        g.setColor(Color.RED);
        g.setFont(new Font("Arial",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER LOSER",(SCREEN_WIDTH- metrics1.stringWidth("GAME OVER LOSER"))/2,SCREEN_HEIGHT/2 );
        g.setColor(Color.RED);
        g.setFont(new Font("Arial",Font.BOLD,20));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten,(SCREEN_WIDTH- metrics2.stringWidth("Score: " + applesEaten))/2,g.getFont().getSize() );

        //Prompt to restart the game
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press SPACE to Restart", (SCREEN_WIDTH - metrics3.stringWidth("Press SPACE to Restart")) / 2, SCREEN_HEIGHT / 2 + 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP, KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;

                case KeyEvent.VK_SPACE:
                    if(!running){
                        restartGame();
                        break;
                    }
            }
        }
    }
}


