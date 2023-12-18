import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
	private class Tile{
		int x;
		int y;
		Tile(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	Tile snakeHead; //green
	Tile food; //red
	ArrayList<Tile> snakeBody;
	
	Random random;// generate a random num
	
	//game logic
	Timer gameloop;
	boolean gameOver=false;
	
	int movX;
	int movY;//movement for snake head
	
	int width;
	int height;
	int tileSize=25; //every tile i.e,..snake and food size
	
	SnakeGame(int width,int height){
		this.width=width;
		this.height=height;
//		setPreferredSize(new Dimension(this.width,this.height));
		setBackground(Color.black);
		snakeHead=new Tile(5,5);
		
		addKeyListener(this); // arrow key needs to listen to the game
		setFocusable(true);
		
		food=new Tile(10,10);
		random=new Random();
		placeFood();
		
		gameloop=new Timer(100,this);
		gameloop.start();
		
		movX=0;
		movY=0;
		
		
		snakeBody=new ArrayList<>();
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		//food
		g.setColor(Color.red);
		g.fill3DRect(food.x*25, 25*food.y, 25, 25,true);
		//snake 
		g.setColor(Color.green);
		g.fill3DRect(snakeHead.x*25,25* snakeHead.y, 25, 25,true);
		
		//snake body
		for(int i=0;i<snakeBody.size();i++) {
			Tile snakeBack= snakeBody.get(i);
			g.fill3DRect(snakeBack.x*25, snakeBack.y*25, 25, 25,true);
		}
		
		//grid structure
//		for(int i=0;i<width/25;i++) {
//			g.drawLine(i*25, 0, i*25, height);
//			g.drawLine(0, i*25, width, i*25);
//		}
		//score board
		g.setFont(new Font("Arial",Font.PLAIN,16));
		if(gameOver) {
			g.setColor(Color.red);
			g.drawString("Game Over: "+String.valueOf(snakeBody.size()), 9, 25);
		}
		else {
			g.setColor(Color.yellow);
			g.drawString("Score: "+String.valueOf(snakeBody.size()), 9, 25);
		}
	}
	
	public boolean collision(Tile tile1,Tile tile2) {
		return tile1.x==tile2.x && tile1.y==tile2.y;
	}
	
	public void placeFood() {
		food.x=random.nextInt(width/25);
		food.y=random.nextInt(height/25);//600/25=24....i.e..,,0 to 24 a random num
	}
	public void move() {
		// eat food ..i.e, collision
		if(collision(snakeHead,food)) {
			snakeBody.add(new Tile(food.x,food.y));
			placeFood();
		}
		
		//snake part adding
		for(int i=snakeBody.size()-1;i>=0;i--) {
			Tile snakeBack=snakeBody.get(i);
			if(i==0) {
				
				snakeBack.x=snakeHead.x;
				snakeBack.y=snakeHead.y;
			}
			else {
				Tile prevSnakeBack=snakeBody.get(i-1);
				snakeBack.x=prevSnakeBack.x;
				snakeBack.y=prevSnakeBack.y;
			}
		}
		
		//snake head movement
		snakeHead.x +=movX;
		snakeHead.y+=movY;
		
		//game over conditions
		for(int i=0;i<snakeBody.size();i++) {
			Tile snakeBack=snakeBody.get(i);
			//snake collides with its own
			if(collision(snakeHead,snakeBack)) {
				gameOver=true;
			}
		}
		if(snakeHead.x*25 <0 || snakeHead.x*25>width || snakeHead.y*25 <0 || snakeHead.y*25>height)  gameOver=true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		if(gameOver) gameloop.stop();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	// for every keys
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP  && movY!=1) {
			movX=0;
			movY=-1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN && movY!=-1) {
			movX=0;
			movY=1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT && movX!=1) {
			movX=-1;
			movY=0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT && movX!=-1) {
			movX=1;
			movY=0;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
