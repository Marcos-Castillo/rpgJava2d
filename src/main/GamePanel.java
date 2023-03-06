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

import entity.Player;
import tile.TileManager;



/**
 *
 * @author Operaciones
 */
public class GamePanel extends JPanel implements Runnable{
    //screen seting
    final int originalTileSize = 16;// 16x16 tile 16px
    final int scale = 3;//sacala por la q se multiplica el tile
    
    public final int tileSize = originalTileSize*scale;//tile size 48*48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768px
    public final int screenHeight = tileSize * maxScreenRow;//576px
    
    //world seting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //fps
    int FPS=60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    
 
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
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
       player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
        
        
        
    }
            
    
}
