import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;


public class FlappyBird extends JPanel implements ActionListener, KeyListener{
	int boardWidth = 360;
	int boardHeight = 640;
	//images
	Image backgroundImg;
	Image birdImg;
	Image topPipeImg;
	Image bottomPipeImg;
	//bird variables
	int birdX = boardWidth/8;
	int birdY = boardHeight/2;
	int birdWidth = 34;
	int birdHeight = 24;
	
	class Bird{
		int x = birdX;
		int y = birdY;
		int width = birdWidth;
		int heigh = birdHeight;
		Image img;
		Bird(Image img){
			this.img = img;
		}
	}
	//pipes
	int pipeX = boardWidth;
	int pipeY = 0;
	int pipeWidth = 64; //scaled by 1/6
	int pipeHeight = 512;
	
	class Pipe{
		int x = pipeX;
		int y = pipeY;
		int width = pipeWidth;
		int height = pipeHeight;
		Image img;
		boolean passed = false;
		Pipe(Image img){
			this.img = img;
		}
	}
	
	//game logic
	Bird bird;
	int velocityX = -4; //move pipes to the left speed (simulates bird moving right
	int velocityY = 0;
	int gravity = 1;
	
	ArrayList<Pipe> pipes;
	Random random = new Random();
	
	Timer gameLoop;
	Timer placePipesTimer;
	
	FlappyBird(){
		setPreferredSize(new Dimension(boardWidth,boardHeight));
		setFocusable(true);
		addKeyListener(this);
		//setBackground(Color.blue);
		//load images
		backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
		birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
		topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
		bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
		bird = new Bird(birdImg);
		pipes = new ArrayList<Pipe>();
		//game timer
		//place pipes
		placePipesTimer = new Timer(1500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				placePipes();
			}
		});
		placePipesTimer.start();
		
		gameLoop = new Timer(1000/60, this);
		gameLoop.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		//background
		g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
		//bird
		g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.heigh,null);
		
		//pipes
		for(int i =0; i<pipes.size();i++) {
			Pipe pipe = pipes.get(i);
			g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
		}
		
	}
	
	public void move() {
		//update bird
		velocityY += gravity;
		bird.y += velocityY;
		bird.y = Math.max(bird.y, 0);
		for(int i =0; i<pipes.size();i++) {
			Pipe pipe = pipes.get(i);
			pipe.x += velocityX;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		move();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_SPACE) {
			velocityY = - 9;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void placePipes() {
		//(0-1) * pipeheight/2 (0-256)
		//basically should shift from a 1/4 to 3/4
		int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random() * (pipeHeight/2));
		int openingSpace = boardHeight/4;
		
		
		Pipe topPipe = new Pipe(topPipeImg);
		topPipe.y = randomPipeY;
		pipes.add(topPipe);
		
		Pipe bottomPipe = new Pipe(bottomPipeImg);
		bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
		pipes.add(bottomPipe);
	}
}
