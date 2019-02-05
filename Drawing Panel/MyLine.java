/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawings;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

/**
 *
 * @author Osman
 */
public class MyLine extends MyShape {
    
    public MyLine(int x1,int x2,int y1,int y2,Paint paint,Stroke stroke,boolean dash){
        super(x1,x2,y1,y2,paint,stroke,dash);
    }
    @Override
    public void draw(Graphics2D g){
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        g.drawLine(getx1(),gety1(),getx2(),gety2());
    }
        
    

    
    
}
