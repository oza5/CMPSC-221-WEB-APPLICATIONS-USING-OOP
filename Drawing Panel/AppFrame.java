/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Osman
 */
public class AppFrame extends JFrame {
    private JPanel topCont = new JPanel();//topwindow
    private JPanel Line1 = new JPanel();//t1
    private JPanel Line2 = new JPanel();//t2
    private AppPanel drwpanel;//drawpanel
    private JButton undo = new JButton();
    private JButton clear = new JButton();
    private JLabel SelectShapeType = new JLabel();
    private JComboBox select = new JComboBox();
    private JCheckBox filled = new JCheckBox();
    private JCheckBox grad = new JCheckBox();//gradient
    private JButton col1 = new JButton();
    private JButton col2 = new JButton();
    private JLabel lineWidthLabel = new JLabel();
    private JTextField lineWidthText = new JTextField();
    private JLabel dashLengthLabel = new JLabel();
    private JTextField dashLengthText = new JTextField();
    private JCheckBox dashedcheck = new JCheckBox();
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JLabel statusbar = new JLabel();
    
    public AppFrame(){
        super("Drawing Application");
        setLayout(new BorderLayout());
        String checkboxshapes[] = {"Line","Oval","Rectangle"};
        topCont.setLayout(new BorderLayout());
        
        //make buttons for first 
        
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        SelectShapeType = new JLabel("Shape: ");
        select = new JComboBox(checkboxshapes);
        filled = new JCheckBox("Filled");
        
        //add buttons to first Line
        
        Line1.add(undo);
        Line1.add(clear);
        Line1.add(SelectShapeType);
        Line1.add(select);
        Line1.add(filled);
        
        //make buttons for second Line
        grad = new JCheckBox("Gradient");
        col1 = new JButton("Color 1");
        col2 = new JButton("Color 2");
        lineWidthLabel = new JLabel("Line Width");
        lineWidthText = new JTextField(2);
        dashLengthLabel = new JLabel("Dash Length");
        dashLengthText = new JTextField(2);
        dashedcheck = new JCheckBox("Dashed");
        
        //add buttons to second line
        Line2.add(grad);
        Line2.add(col1);
        Line2.add(col2);
        Line2.add(lineWidthLabel);
        Line2.add(lineWidthText);
        Line2.add(dashLengthLabel);
        Line2.add(dashLengthText);
        Line2.add(dashedcheck);
        
        //Add to top button contaier panel
        topCont.add("North",Line1);
        topCont.add("South",Line2);
        //Add top Cont to top of JFrame
        add("North",topCont);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(statusbar = new JLabel());
        add("South",bottomPanel); 
        drwpanel = new AppPanel(statusbar);
        add("Center",drwpanel);
        
        
        //Action listeners for all Buttons
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drwpanel.clearLastShape();
            }
        });
        
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drwpanel.clearDrawing();
            }
        });
        
        select.addActionListener(new ActionListener() {  
        @Override
        public void actionPerformed(ActionEvent e) { 
            
            drwpanel.setShapeType(select.getSelectedIndex());
        }  
        });     
        filled.addActionListener(new ActionListener() {  
        @Override
        public void actionPerformed(ActionEvent e) { 
            
            drwpanel.setFilledShape(filled.isSelected());
        }  
        });  
        
        grad.addActionListener(new ActionListener() {  
        @Override
        public void actionPerformed(ActionEvent e) { 
            boolean gradselected = grad.isSelected();
            drwpanel.setIsGradient(grad.isSelected());
            if(gradselected == false)
            {
                drwpanel.setNonGradientStroke();
            }
            else
            {
                drwpanel.setGradientStroke();
            }
        }  
        });  
        
        col1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = Color.BLACK;
                    color = JColorChooser.showDialog(AppFrame.this, "Choose a color", color);
                drwpanel.setCurrentCol1(color);
                if(drwpanel.getGradientSelected() == false)
            {
                drwpanel.setNonGradientStroke();
            }
            else
            {
                drwpanel.setGradientStroke();
            }
            }
        });
        
        col2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = Color.BLACK;
                       color = JColorChooser.showDialog(AppFrame.this, "Choose a color", color);
                drwpanel.setCurrentCol2(color);
                if(drwpanel.getGradientSelected() == false)
            {
                drwpanel.setNonGradientStroke();
            }
            else
            {
                drwpanel.setGradientStroke();
            }
            }
        });
        
        dashedcheck.addActionListener(new ActionListener() {  
        @Override
        public void actionPerformed(ActionEvent e) { 
            boolean dashedselected = dashedcheck.isSelected();
            drwpanel.setIsDashed(dashedselected);
            if(dashedselected == false)
            {
                drwpanel.setBasicStroke();
            }
            else
            {
                drwpanel.setDashedStroke();
            }
        }  
        });  
        lineWidthText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                drwpanel.setWidth(Integer.parseInt(lineWidthText.getText()));
                if(drwpanel.getDashedStroke() == false)
            {
                drwpanel.setBasicStroke();
            }
            else
            {
                drwpanel.setDashedStroke();
            }
            }
        });
        
        dashLengthText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                drwpanel.setDashes(Float.parseFloat(dashLengthText.getText()));
                if(drwpanel.getDashedStroke() == false)
            {
                drwpanel.setBasicStroke();
            }
            else
            {
                drwpanel.setDashedStroke();
            }
            }
        });
        
    }
}


                
        
        
        
        
    
            
    
    

    

