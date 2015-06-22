

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.StringTokenizer;

import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
 
public class SelectableTree extends JFrame
implements TreeSelectionListener,ChangeListener
{
 
   protected Component makeJTreePanel(JTree tree)
   {
      JPanel panel = new JPanel(false);
      panel.setLayout(new GridLayout(1, 1));
      panel.add(new JScrollPane(tree));
      return panel;
   }
 
   protected JTree makeJTree(String text)
   {
      DefaultMutableTreeNode root 
               = new DefaultMutableTreeNode(text);
      for(int i = 0; i < 4; i++)
      {
         DefaultMutableTreeNode child 
               = new DefaultMutableTreeNode(text + i);
         for(int j = 0; j < 4; j++)
            child.add(new DefaultMutableTreeNode(text + i + j));
         root.add(child);
      }
 
      JTree tree = new JTree(root);
      tree.addTreeSelectionListener(this);
      return tree;
   }
 
   public void valueChanged(TreeSelectionEvent event)
   {
      String temp = event.getPath().toString();
      for(StringTokenizer token = new StringTokenizer(temp, ","); token.hasMoreTokens();)
         temp = token.nextToken();
      textArea.setText("Current Selection: " + temp.substring(0, temp.length() - 1));
   }
 
   /**Sole method of ChangeListener*/
   public void stateChanged(ChangeEvent e)
   {
		DefaultSingleSelectionModel dSM 
				= (DefaultSingleSelectionModel)e.getSource();
      int index = dSM.getSelectedIndex();
		String tabName = tabbedPane.getTitleAt(index);
		/** I imagine you will do more than this*/
		if(textArea != null)
			textArea.setText("Tab Selected: " + tabName);
		
   }
   /****************************************/
   
   public SelectableTree()
   {
      super("ECHO Service Demo");
      //WindowUtilities.setNativeLookAndFeel();
      //addWindowListener(new ExitListener());
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      Container content = getContentPane();
      tabbedPane = new JTabbedPane();
      
      /**add ChangeListener to Model*/
      tabbedPane.getModel().addChangeListener(this);
      /*******************************************/
      
      Component panel1 = makeJTreePanel(makeJTree("Taxonomy1"));
      tabbedPane.addTab("Taxonomy1", null, panel1, null);
      tabbedPane.setSelectedIndex(0);
      Component panel2 = makeJTreePanel(makeJTree("Taxonomy2"));
      tabbedPane.addTab("Taxonomy2", null, panel2, null);
      Component panel3 = makeJTreePanel(makeJTree("Taxonomy3"));
      tabbedPane.addTab("Taxonomy3", null, panel3, null);
      Component panel4 = makeJTreePanel(makeJTree("Taxonomy4"));
      tabbedPane.addTab("Taxonomy4", null, panel4, null);
      content.add(tabbedPane, "Center");
      textArea = new JTextArea("Services of Current Category: NONE");
      content.add(textArea, "South");
      setSize(350, 375);
      setVisible(true);
      }
 
      public static void main(String args[])
      {
         new SelectableTree().setVisible(true);
      }
 
private JTabbedPane tabbedPane;
private JTextArea textArea;
}

