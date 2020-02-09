import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

//inspired by MobileiGames on Youtube

public class Background extends JPanel implements Runnable, KeyListener {
	 
    private static final long serialVersionUID = 1L;
 
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
   
    private Thread thread;
    private boolean runs = true;
 
    private SnakeObject snakeParts;
    private ArrayList<SnakeObject> snakes;
 
    private SnakeObject food;
    private ArrayList<SnakeObject> foods;
   
    private Random random;
   
    private int xPosition = 20;
    private int yPosition = 20;
    private int size = 5;
 
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;
   
    private int count = 0;
    private int maxCount = 100000;
    private int score = 0;
    private int difficulty = 1;
   
    
    
    public Background(int difficulty) {
        setFocusable(true);
       
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
 
        random = new Random();
       
        snakes = new ArrayList<SnakeObject>();
        foods = new ArrayList<SnakeObject>();
        this.difficulty = difficulty;
       
        thread = new Thread(this);
        thread.start();
    }
 
    public void game() throws InterruptedException {
        if (snakes.size() == 0) {
            snakeParts = new SnakeObject(xPosition, yPosition, 10, 10);
            snakes.add(snakeParts);
        }
        if(foods.size() == 0) {
            int xCoor = random.nextInt(49);
            int yCoor = random.nextInt(44)+5;
           
            food = new SnakeObject(xCoor, yCoor, 10,10);
            foods.add(food);
        }
       
        for(int i = 0; i < foods.size(); i++) {
            if(xPosition == foods.get(i).getXPosition() &&
                    yPosition == foods.get(i).getYPosition()) {
                size++;
                foods.remove(i);
                i++;
                score++;
            }
        }
       
        for(int i =0; i < snakes.size(); i++) {
            if(xPosition == snakes.get(i).getXPosition() &&
                    yPosition == snakes.get(i).getYPosition()) {
                if(i != snakes.size() - 1) {
                	end();
                }
            }
        }
        if(xPosition < 0 || xPosition > 49 || yPosition < 5 || yPosition > 49) {
        	end();
        }
       
       
        count++;
        
        //set difficulty
        
        if(difficulty == 1) {
        	maxCount = 2000000;
        }
        if(difficulty == 2) {
        	maxCount = 1000000;
        }
        if(difficulty == 3) {
        	maxCount = 500000;
        }
        if(difficulty == 4) {
        	maxCount = 250000;
        }
        if(difficulty == 5) {
        	maxCount = 125000;
        }
       
  
        if(count > maxCount) {
            if(right) {
            	xPosition++;
            }
            
            else if(left) {
            	xPosition--;
            }
            else if(up) {
            	yPosition--;
            }
            else {
            	yPosition++;
            }
           
            //resets count
            count = 0;
           
            snakeParts = new SnakeObject(xPosition, yPosition, 10, 10);
            snakes.add(snakeParts);
           
            if(snakes.size() > size) {
                snakes.remove(0);
            }
        }
    }
 
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
      
       
        g.setColor(Color.white);
    
 
       
        g.fillRect(0, 0, WIDTH, 50);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawLine(0, 50, WIDTH, 50);
        
        g.drawString("Score: "+Integer.toString(score), 10,20);
        
        for (int i = 0; i < snakes.size(); i++) {
            snakes.get(i).draw(g,new Color(random.nextInt(150),random.nextInt(150)+100,random.nextInt(150)));
        }
        for(int i = 0; i < foods.size(); i++) {
            foods.get(i).draw(g,new Color(random.nextInt(150)+100,random.nextInt(150),random.nextInt(150)));
        }

    }
 
   

 
    public void run() {
        while (runs == true) {
            try {
				game();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            repaint();
        }
    }
    
    public void end() throws InterruptedException {
    	
    	
    	runs = false;
        thread.join();
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left) {
            up = false;
            down = false;
            left = false;
            right = true;
           
        }
        if(key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            right = false;
            left = true;
            
        }
        if(key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            down = false;
            up = true;
        }
        if(key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            up = false;
            down = true;
        }
    }
    public void keyReleased(KeyEvent arg0) {   
    }
    public void keyTyped(KeyEvent arg0) {  
    }     
    }