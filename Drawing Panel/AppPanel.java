/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Osman
 */
public class AppPanel extends JPanel {
    private MyShape shape[];
    private int s_Count;
    private MyShape currenShape;
    private int shapeType;
    private Color currenCol1;
    private Color currenCol2;
    private Paint currenCol;
    private boolean fillShape;
    private JLabel statusLabel;
    private Stroke currenStroke;
    private boolean dashedStroke;
    private int width = 20;
    private boolean gradStroke;
    private float dashes[] = new float[1];
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        for(int i=0;i<s_Count;i++)
        {
            shape[i].draw(g2);
        }
        if(currenShape!=null) 
        {
            currenShape.draw(g2);
        } 
    }
    public void setShapeType(int shapeType)
    {
        this.shapeType = shapeType;
    }
    
    public void setCurrentCol2(Color currenCol)
    {
        this.currenCol2 = currenCol;
    }
    public void setCurrentCol1(Color currenCol)
    {
        this.currenCol1 = currenCol;
    }
    
    public void setFilledShape(boolean fillShape)
    {
        this.fillShape = fillShape;
    }
    
    public void setBasicStroke()
    {
        currenStroke = new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }
    
    public void setGradientStroke()
    {
        currenCol = new GradientPaint(0,0,currenCol1,50,50,currenCol2,true);
    }
    
    public void setNonGradientStroke()
    {
        currenCol = currenCol1;
    }
    
    public void setDashedStroke()
    {
         currenStroke = new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,10,dashes,0);
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    
    
    public void setIsDashed(boolean isDashed)
    {
        this.dashedStroke = isDashed;
    }
    
    public void setIsGradient(boolean isGrad)
    {
        this.gradStroke = isGrad;
    }
    
    public void setGradientPaint(int shapeType)
    {
        this.shapeType = shapeType;
    }
    
    public boolean getDashedStroke()
    {
       return dashedStroke;
    }
    public boolean getGradientSelected()
    {
       return gradStroke;
    }
    
    public void setDashes(float Dash)
    {
        dashes[0] = Dash;
    }
    
    public void clearLastShape()
    {
        if(s_Count>0)
            s_Count-=1;
        repaint();
    }
    public void clearDrawing()
    {
        s_Count = 0;
        repaint();
        
    }
    private class MouseTracker extends MouseAdapter implements MouseMotionListener
    {
        @Override
        public void mousePressed(MouseEvent event)
        {
            switch (shapeType) {
                case 0:
                    currenShape = new MyLine(event.getX(),event.getX(),event.getY(),event.getY(),currenCol,currenStroke,dashedStroke);
                    break;
                case 1:
                    currenShape = new MyOval(event.getX(),event.getX(),event.getY(),event.getY(),currenCol,fillShape, currenStroke,dashedStroke);
                    break;
                case 2:
                    currenShape = new MyRectangle(event.getX(),event.getX(),event.getY(),event.getY(),currenCol,fillShape,currenStroke,dashedStroke);
                    break;
                default:
                    break;
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent event)
        {
            currenShape.setx2(event.getX());
            currenShape.sety2(event.getY());
            
            shape[s_Count] = currenShape;
            s_Count+=1;
            
            currenShape = null;
            repaint();
        }
        
        @Override
        public void mouseClicked(MouseEvent event)
        {
            switch (shapeType) {
                case 0:
                    currenShape = new MyLine(event.getX(),event.getX(),event.getY(),event.getY(),currenCol,currenStroke,dashedStroke);
                    break;
                case 1:
                    currenShape = new MyOval(event.getX(),event.getX(),event.getY(),event.getY(),currenCol,fillShape,currenStroke,dashedStroke);
                    break;
                case 2:
                    currenShape = new MyRectangle(event.getX(),event.getX(),event.getY(),event.getY(),currenCol,fillShape,currenStroke,dashedStroke);
                    break;
                default:
                    break;
            }
        }
        
        @Override
        public void mouseMoved(MouseEvent event)
        {
            statusLabel.setText("("+event.getX()+","+event.getY()+")");
        }
        
        @Override
        public void mouseDragged(MouseEvent event)
        {
            statusLabel.setText("("+event.getX()+","+event.getY()+")");
            
            currenShape.setx2(event.getX());
            currenShape.sety2(event.getY());
            repaint();
            
        }
    }
    
            
    
    public AppPanel(JLabel label1){
        shape = new MyShape[100];
        s_Count=0;
        statusLabel = label1;
        shapeType = 0;
        currenShape = null;
        currenCol = Color.BLACK;
        dashedStroke = false;
        fillShape = false;
        currenStroke = new BasicStroke();
        currenCol1 = Color.BLACK;
        currenCol2 = Color.BLACK;
        currenCol = new GradientPaint(0,0,currenCol1,50,50,currenCol1,true);
        width=1;
        dashes[0]=10;
        gradStroke = false;
        setBackground(Color.WHITE);
        MouseTracker mouseTracker = new MouseTracker();
        addMouseListener(mouseTracker);
        addMouseMotionListener(mouseTracker);
       
    }
    
    
}
