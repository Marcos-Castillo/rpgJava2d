/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
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
    
    Thread gameThread;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
    }
            
    
}
