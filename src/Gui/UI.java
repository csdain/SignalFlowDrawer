package Gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import GraphData.ConnectedCycle;
import GraphData.Cycle;
import GraphData.Data;
import GraphData.DirectPath;
import GraphData.Edge;
import GraphData.Node;
import VisualDrawer.Drawer;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class UI {

	private JFrame MenuFrame;
	private JTextField txtP;
	private JTextField txtP_1;
	private JTextField textField_2;
	private JTextField txtP_2;
	private JTextField txtP_3;
	private JTextField textField_5;
	private Drawer drawer = new Drawer();
	private Data data = new Data();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.MenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		MenuFrame = new JFrame();
		MenuFrame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		MenuFrame.getContentPane().setBackground(new Color(0, 0, 255));
		MenuFrame.getContentPane().setLayout(null);
		MenuFrame.setBackground(UIManager.getColor("textHighlight"));
		MenuFrame.setFont(new Font("Brush Script MT", Font.PLAIN, 12));
		MenuFrame.setResizable(false);
		MenuFrame.setTitle("FlowGraphPainter");
		MenuFrame.setBounds(100, 100, 1789, 872);
		MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(148, 0, 211)));
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 870, 843);
		MenuFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(459, 60, 373, 153);
		panel.add(scrollPane);

		
		JTextArea edgesArea = new JTextArea();
		edgesArea.setEditable(false);
		scrollPane.setViewportView(edgesArea);
		edgesArea.setFont(new Font("MV Boli", Font.BOLD, 15));
		
		JLabel lblEdges = new JLabel("Edges");
		lblEdges.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdges.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		lblEdges.setBackground(new Color(147, 112, 219));
		lblEdges.setBounds(321, 60, 107, 153);
		panel.add(lblEdges);
		
		JLabel lblDirectpaths = new JLabel("DirectPaths");
		lblDirectpaths.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirectpaths.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblDirectpaths.setBackground(new Color(147, 112, 219));
		lblDirectpaths.setBounds(321, 249, 107, 155);
		panel.add(lblDirectpaths);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPane_1.setBounds(459, 249, 373, 155);
		panel.add(scrollPane_1);
		
		JTextArea directPathArea = new JTextArea();
		directPathArea.setEditable(false);
		scrollPane_1.setViewportView(directPathArea);
		directPathArea.setFont(new Font("MV Boli", Font.BOLD, 15));
		
		JLabel la = new JLabel("Cycles");
		la.setHorizontalAlignment(SwingConstants.CENTER);
		la.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		la.setBackground(new Color(147, 112, 219));
		la.setBounds(321, 445, 107, 144);
		panel.add(la);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPane_2.setBounds(459, 445, 373, 144);
		panel.add(scrollPane_2);
		
		JTextArea cycleArea = new JTextArea();
		cycleArea.setEditable(false);
		scrollPane_2.setViewportView(cycleArea);
		cycleArea.setFont(new Font("MV Boli", Font.BOLD, 15));
		
		
		JLabel lblNewLabel = new JLabel("From");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(147, 112, 219));
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		lblNewLabel.setBounds(62, 60, 61, 26);
		panel.add(lblNewLabel);
		
		txtP = new JTextField();
		txtP.setBounds(151, 60, 133, 26);
		panel.add(txtP);
		txtP.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		lblTo.setBackground(new Color(147, 112, 219));
		lblTo.setBounds(62, 108, 61, 26);
		panel.add(lblTo);
		
		txtP_1 = new JTextField();
		txtP_1.setColumns(10);
		txtP_1.setBounds(151, 108, 133, 26);
		panel.add(txtP_1);
		
		
		JLabel lblGain = new JLabel("Gain");
		lblGain.setHorizontalAlignment(SwingConstants.CENTER);
		lblGain.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		lblGain.setBackground(new Color(147, 112, 219));
		lblGain.setBounds(62, 156, 61, 26);
		panel.add(lblGain);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 156, 133, 26);
		panel.add(textField_2);

		JPanel cavnas = new JPanel();
		cavnas.setBackground(Color.LIGHT_GRAY);
		cavnas.setBounds(869, 0, 916, 843);
		MenuFrame.getContentPane().add(cavnas);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				drawer.draw(data,cavnas);
			}
		});
		btnDraw.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		btnDraw.setBackground(new Color(255, 69, 0));
		btnDraw.setBounds(62, 265, 222, 49);
		panel.add(btnDraw);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String from = txtP.getText() , to = txtP_1.getText() ;
				Node nfrom = new Node(from) , nto = new Node(to);
				double gain = Double.valueOf(textField_2.getText());
				data.addEdge(nfrom, nto, gain);
				ArrayList<Edge> edges = data.getEdges();
				edgesArea.setText("");
				String toAdd = "" ; 
				for(Edge i : edges){
					toAdd += i.getFrom().getLabel();
					toAdd += " >>>> " ;
					toAdd += i.getTo().getLabel();
					toAdd += " Gain: " ;
					toAdd += i.getGain();
					toAdd += " \n";
				}
				edgesArea.setText(toAdd);
			}
		});
		btnNewButton.setIcon(new ImageIcon(UI.class.getResource("/Icons/plus.png")));
		btnNewButton.setBounds(151, 203, 133, 39);
		btnNewButton.setOpaque(false);
		btnNewButton.setBorderPainted(true);
		btnNewButton.setContentAreaFilled(false);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("From");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		label.setBackground(new Color(147, 112, 219));
		label.setBounds(62, 337, 61, 26);
		panel.add(label);
		
		txtP_2 = new JTextField();
		txtP_2.setColumns(10);
		txtP_2.setBounds(151, 337, 133, 26);
		panel.add(txtP_2);
		
		JLabel label_1 = new JLabel("To");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Script MT Bold", Font.BOLD, 23));
		label_1.setBackground(new Color(147, 112, 219));
		label_1.setBounds(62, 386, 61, 26);
		panel.add(label_1);
		
		txtP_3 = new JTextField();
		txtP_3.setColumns(10);
		txtP_3.setBounds(151, 386, 133, 26);
		panel.add(txtP_3);
		
		JButton btnNewButton_1 = new JButton("Calculate Gain");
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String from = txtP_2.getText() , to = txtP_3.getText() ;
				Node nfrom = new Node(from) , nto = new Node(to); 				
				try {
					if(!from.equals(to))textField_5.setText(String.valueOf(data.getGain(nfrom, nto)));
					else textField_5.setText("1") ;
					directPathArea.setText("");
					String toAdd = "";
					ArrayList<DirectPath> direct = data.getDirectedPaths();
					for(int i=0 ; i<direct.size() ; i++){
						toAdd+= "Path" + String.valueOf(i+1) + " : ";
						ArrayList<Node> path = direct.get(i).getPath();
						for(Node j : path){
							toAdd += j.getLabel() + " " ;
						}
						toAdd+="\n";
					}
					directPathArea.setText(toAdd);
					
					cycleArea.setText("");
					toAdd = "";
					ArrayList<Cycle> singleCycles = data.getSingleCycles();
					for(int i=0 ; i<singleCycles.size() ; i++){
						toAdd+= "Single Cycle " + String.valueOf(i+1) + " : ";
						ArrayList<Node> path = singleCycles.get(i).getPath();
						for(Node j : path){
							toAdd += j.getLabel() + " " ;
						}
						toAdd+="\n";
					}
					ArrayList<ArrayList<ConnectedCycle>> cons = data.getNCycles();
					for(int i=0 ;i<cons.size() ; i++){
						toAdd+="Connected "+String.valueOf(i+1)+" : \n";
						for(ConnectedCycle c : cons.get(i)){
							toAdd+="\tCycles : ";
							for(int k : c.getIndeces())
								toAdd+=String.valueOf(k);
							toAdd+=" \n";
						}
					}
					cycleArea.setText(toAdd);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), "Error in calculating gain" , "Error" , JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBackground(new Color(255, 69, 0));
		btnNewButton_1.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		btnNewButton_1.setBounds(62, 448, 222, 33);
		panel.add(btnNewButton_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(62, 502, 222, 26);
		panel.add(textField_5);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				data = new Data();
				directPathArea.setText("");
				cycleArea.setText("");
				edgesArea.setText("");
				textField_2.setText("");
				textField_5.setText("");
				txtP.setText("");
				txtP_2.setText("");
				txtP_1.setText("");
				txtP_3.setText("");
				cavnas.removeAll();
				cavnas.revalidate();
				cavnas.repaint();
				
			}
		});
		btnClear.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		btnClear.setBackground(new Color(255, 69, 0));
		btnClear.setBounds(62, 556, 222, 33);
		panel.add(btnClear);	
	}
}
