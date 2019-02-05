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
public abstract class MyBoundedShape extends MyShape {
    private boolean fill = false;
    public MyBoundedShape(int x1,int x2,int y1,int y2,Paint paint,boolean fill,Stroke stroke,boolean dash){
        super(x1,x2,y1,y2,paint,stroke,dash);
        this.fill = fill;
    }
    public void setFill(boolean fill){
        this.fill = fill;
    }
    public boolean getFill(){
        return fill;
    }
    public abstract void draw(Graphics2D g);
}
