/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Operaciones
 */
public class GamePanel extends JPanel implements Runnable{
    //screen seting
    final int originalTileSize = 16;// 16x16 tile 16px
    final int scale = 3;//sacala por la q se multiplica el tile
    
    final int tileSize = originalTileSize*scale;//tile size 48*48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;//768px
    final int screenHeight = tileSize * maxScreenRow;//576px
    //fps
    int FPS=60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    //set player defaul position
    
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval=1000000000/FPS;//0.0166 seg
        double nextDrawTime= System.nanoTime()+drawInterval;
        
        while (gameThread!= null){
            
           update();
           repaint();

            try {
                    double remainigTime = nextDrawTime - System.nanoTime();
                    remainigTime=remainigTime/1000000;
                    if(remainigTime<0){
                        remainigTime=0;
                    }
                    Thread.sleep((long)remainigTime);
                    nextDrawTime+=drawInterval;
                    
            } catch (Exception e) {
            }
        }
    }
    
    public void update(){
        try {
            if (keyH.upPressed == true) {                
                playerY -= playerSpeed;
            } else if (keyH.downPressed == true) {                
                playerY += playerSpeed;
            } else if (keyH.rightPressed == true) {                
                playerX += playerSpeed;
            } else if (keyH.leftPressed == true) {                
                playerX -= playerSpeed;
            }
        } catch (Exception e) {
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
        
        
        
    }
            
    
}
