package Gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawCanvas extends JPanel {
	
	ArrayList<JLabel> labels = new ArrayList<>();
	ArrayList<QuadCurve2D.Double> arcs = new ArrayList<>();
	
	public void addShapes(ArrayList<JLabel> labels , ArrayList<QuadCurve2D.Double> arcs){
		this.labels =new ArrayList<>(labels) ;
		this.arcs = new ArrayList<>(arcs) ;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // paint parent's background
		Graphics2D g2 = (Graphics2D) g;
		for( JLabel i : labels)
			g2.drawString(i.getText(), i.getX(), i.getY()+12);
		for(QuadCurve2D.Double i : arcs)
			g2.draw(i);
	}
}