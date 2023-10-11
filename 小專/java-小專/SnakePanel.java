package tw.org.iii.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener,ActionListener{
	private static final int Failed = 0;
	//加载所有图片
	ImageIcon up = new ImageIcon("dir1/up.png");
	ImageIcon down = new ImageIcon("dir1/down.png");
	ImageIcon left = new ImageIcon("dir1/left.png");
	ImageIcon right = new ImageIcon("dir1/right.png");
	ImageIcon title = new ImageIcon("dir1/title4.png");
	ImageIcon body = new ImageIcon("dir1/body.png");
	ImageIcon food = new ImageIcon("dir1/food.png");
	
	//蛇的数据结构设计
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	
	int len = 3;
    int score = 0;
	String direction = "R";//R右L左U上D下
	
	//食物生成
	Random r = new Random();
	int foodx = r.nextInt(20)*25+25; // 34个格子，一个格子25排像素，还有25像素空白
	int foody = r.nextInt(15)*25+75; // 24个格子，一个格子25排像素，还有50像素空白
	
	//游戏是否开始
	boolean isStarted = false;
	
	//游戏是否失败
	boolean isFaild = false;

	
	//	初始化蛇
	public void initSnake(){
		isStarted = false;
		isFaild = false;
		len = 3;
		score = 0;
		direction = "R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
		
	}
	public SnakePanel() {
		this.setFocusable(true);
		initSnake(); //放置静态蛇；
		this.addKeyListener(this);//添加键盘监听接口
		timer.start();
	}
	//设置蛇移动速度
	Timer timer = new Timer(160, this);
	
	public void paint(Graphics g){
		//设置背景黑色
				this.setBackground(Color.black);
				g.fillRect(0, 0, 850, 720);
				//设置标题
				title.paintIcon(this, g, 0, -10);
		
		//画蛇头
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//画蛇身
				for(int b=1;b<len;b++){
					body.paintIcon(this, g, snakex[b],snakey[b]);	
		}
			
					
				
		//畫分數
		if(isStarted){
			g.setColor(Color.BLACK);
	        g.setFont(new Font("幼圓",Font.BOLD,20));
	     
	        g.drawString("得分："+score,730,50);
		}
		//画开始提示语
		if(!isStarted){
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Press Enter to Start or Pause", 230, 350);
		}
		//画失败提示语
		if (isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,25));
			g.drawString("Game Over,Press twice Enter to Start", 250, 350);
			
		}
		
		//画食物
		food.paintIcon(this, g, foodx, foody);
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//监听按键
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		
		if(keyCode == KeyEvent.VK_ENTER){
			if(isFaild){
				initSnake();
			}
			else{
			isStarted = !isStarted;
			}
//			repaint();
		}//实现转向
		else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")){
			direction="U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")){
			direction="D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")){
			direction="L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")){
			direction="R";
		}
		
		}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 1.定个闹钟
	 * 2.蛇移动
	 * 3.重画一次蛇
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(isStarted && !isFaild){
			//移动身体
			for(int i=len;i>0;i--){
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//头移动
			if(direction.equals("R")){
				//横坐标+25
				snakex[0] = snakex[0]+25;
				
				
				
			}else if(direction.equals("L")){
				//横坐标-25
				snakex[0] = snakex[0]-25;
				
			}else if(direction.equals("U")){
				//纵坐标-25
				snakey[0] = snakey[0]-25;
				
			}else if(direction.equals("D")){
				//纵坐标+25
				snakey[0] = snakey[0]+25;
				
			}
			//吃食物
			if(snakex[0] == foodx && snakey[0] == foody){
				
			    score+=10;
				len++;
				foodx = r.nextInt(20)*25+25;
				foody = r.nextInt(15)*25+75;
			}
	
	}
			//判断游戏失败
			for(int i=1;i<len;i++){
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]){
					isFaild = true;
				
					
			    }//撞牆失敗
			     if(snakex[0]>800 ||snakex[0]<=0|| snakey[0]>=650||snakey[0]<75) {
			    	 isFaild = true;
			    	
				}
			 
			   
			}
			     repaint();
			     
			  
			
			}
	  
		}

	
