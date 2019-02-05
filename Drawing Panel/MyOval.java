/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawings;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import static java.lang.Math.abs;

/**
 *
 * @author Osman
 */
public class MyOval extends MyBoundedShape {
    public MyOval(int x1,int x2,int y1,int y2,Paint paint,boolean fill,Stroke stroke,boolean dash){
        super(x1,x2,y1,y2,paint,fill,stroke,dash);
    }
    @Override 
    public void draw(Graphics2D g){
        int xx1;
        int xx2;
        int yy1;
        int yy2;
        if(getx2()>getx1())
        {
            xx1 = getx1();
            xx2 = abs(getx2()- getx1());
        }
        else
        {
            xx1 = getx2();
            xx2 = abs(getx2()- getx1());
        }
        if(gety2()>gety1())
        {
            yy1 = gety1();
            yy2 = abs(gety2()- gety1());
        }
        else
        {
            yy1 = gety2();
            yy2 = abs(gety2()- gety1());
        }
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        if(getFill()== false)
            g.drawOval(xx1,yy1,xx2,yy2);
        else
            g.fillOval(xx1,yy1,xx2,yy2);
    }
    
}
