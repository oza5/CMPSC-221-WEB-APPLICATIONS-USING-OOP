/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Paint;

/**
 *
 * @author Osman
 */
public abstract class MyShape extends Object{
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;
    private Paint paint = Color.BLACK;
    private Stroke stroke = new BasicStroke();
    private boolean dash = false;
    
    public MyShape(int x1,int x2,int y1,int y2, Paint paint,Stroke stroke,boolean dash ){
        super();
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.paint = paint;
        this.stroke = stroke;
        this.dash = dash;
    }
    public void setx1(int x1){
        this.x1 = x1;
    }
    public int getx1(){
        return x1;
    }
    public void setx2(int x2){
        this.x2 = x2;
    }
    public int getx2(){
        return x2;
    }
    public void sety1(int y1){
        this.y1 = y1;
    }
    public int gety1(){
        return y1;
    }
    public void sety2(int y2){
        this.y2 = y2;
    }
    public int gety2(){
        return y2;
    }
    public void setPaint(Paint paint){
        this.paint = paint;
    }
    public Paint getPaint(){
        return paint;
    }
    public void setStroke(Stroke stroke){
        this.stroke = stroke;
    }
    public Stroke getStroke(){
        return stroke;
    }
    public abstract void draw(Graphics2D g);
        
}
