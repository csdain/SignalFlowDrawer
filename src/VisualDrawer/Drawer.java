package VisualDrawer;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import GraphData.Data;
import GraphData.Edge;
import Gui.DrawCanvas;

public class Drawer {


	public Drawer() {
	}
	
	public synchronized void draw(Data data , JPanel canvas){
		Graph graph = new SingleGraph("Flow Graph");
        
        System.setProperty("gs.ui.renderer",
                "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        
        ArrayList<Edge> edges = data.getEdges();
        for( Edge i : edges){
        	if(graph.getNode(i.getFrom().getLabel())==null)graph.addNode(i.getFrom().getLabel());
        	if(graph.getNode(i.getTo().getLabel())==null)graph.addNode(i.getTo().getLabel());
        	graph.addEdge(i.getFrom().getLabel()+i.getTo().getLabel(), i.getFrom().getLabel(), i.getTo().getLabel() , true);
        	graph.getEdge(i.getFrom().getLabel()+i.getTo().getLabel()).addAttribute("ui.label",String.valueOf(i.getGain()));
        }
        
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
         
        Viewer viewer =  new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = viewer.addDefaultView(false);
        canvas.removeAll();
        canvas.setLayout(new GridLayout(1,1,50,50));
        canvas.add(view);
        view.getCamera().setViewCenter(2, 3, 4);
        view.getCamera().setAutoFitView(true);
        view.getCamera().resetView();
        canvas.revalidate();
	}

	protected String styleSheet = 
    		"graph { "
    		+ "padding: 40px; "
    		+ "fill-mode: gradient-diagonal2;"
    		+ "fill-color: grey ;"	
    		+ "}" 
    		+"node { "
    		+ "size: 20px;"
    		+ "fill-color: aquamarine, #254;"
    		+ "fill-mode: gradient-diagonal2;"
    		+"text-size : 20;"
    		+"text-padding: 3px, 2px;"
    		+ "text-alignment: at-right; "
    		+ "text-background-mode: none; "
    		+ "text-background-color: #EB2; "
    		+ "text-color: #222;  "
    		+ "}"
    		+"edge { "
    		+"text-size : 20;"
    		+ "shape: cubic-curve; "
    		+ "fill-color: blue;"	
    		+ "arrow-shape: arrow;"
    		+ " size : 2px ;"
    		+ "}"
    		;
}
