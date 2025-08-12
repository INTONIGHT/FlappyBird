import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
	//game logic
	Bird bird;
	int velocityY = 0;
	int gravity = 1;
	
	
	Timer gameLoop;
	
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
		//game timer
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
		
	}
	public void move() {
		//update bird
		velocityY += gravity;
		bird.y += velocityY;
		bird.y = Math.max(bird.y, 0);
		
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
}
