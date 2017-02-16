import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener{
	 ImgArea ima;
	 JFileChooser Choose; 
	 JMenuBar mainmenu;
	 JMenu menu;
	 JMenu editmenu;
	 JMenuItem open;
	 JMenuItem saveas;
	 JMenuItem save;
	 JMenuItem exit; 
	 JMenuItem bright; 
	 JMenuItem compress; 
	 JMenuItem resize;
	 JMenuItem rotate;
	 JMenuItem transparent;
	 JMenuItem addingtext;
	 JMenuItem cancel;
	 String filename;
	 Main(){
	  ima=new ImgArea();
	  Container cont=getContentPane();
	  cont.add(ima,BorderLayout.CENTER );  
	  mainmenu=new JMenuBar();
	  //for file Menu
	  menu=new JMenu("File");
	  menu.setMnemonic(KeyEvent.VK_F);
	  
	  //for option open  in file menu
	  open=new JMenuItem("Open...");
	  open.setMnemonic(KeyEvent.VK_O);
	  open.addActionListener(this);

	  saveas=new JMenuItem("Save as...");
	  saveas.setMnemonic(KeyEvent.VK_S);
	  saveas.addActionListener(this);

	  save=new JMenuItem("Save");
	  save.setMnemonic(KeyEvent.VK_V);
	  save.addActionListener(this);  

	  exit=new JMenuItem("Exit");
	  exit.setMnemonic(KeyEvent.VK_X);
	  exit.addActionListener(this);
	  menu.add(open);
	  menu.add(saveas);
	  menu.add(save);
	  menu.add(exit);  

	  editmenu=new JMenu("Edit");
	  editmenu.setMnemonic(KeyEvent.VK_E);
	  bright=new JMenuItem("Image brightness");
	  bright.setMnemonic(KeyEvent.VK_B);
	  bright.addActionListener(this);

	  addingtext=new JMenuItem("Add text on image");
	  addingtext.setMnemonic(KeyEvent.VK_A);
	  addingtext.addActionListener(this);  

	  resize=new JMenuItem("Image resize");
	  resize.setMnemonic(KeyEvent.VK_R);
	  resize.addActionListener(this);
	 
	  compress=new JMenuItem("Image compression");
	  compress.setMnemonic(KeyEvent.VK_P);
	  compress.addActionListener(this);

	  rotate=new JMenuItem("Image rotation");
	  rotate.setMnemonic(KeyEvent.VK_T);
	  rotate.addActionListener(this);

	  transparent=new JMenuItem("Image transparency");
	  transparent.setMnemonic(KeyEvent.VK_T);
	  transparent.addActionListener(this);
	 
	  cancel=new JMenuItem("Cancel editing");
	  cancel.setMnemonic(KeyEvent.VK_L);
	  cancel.addActionListener(this);

	  editmenu.add(addingtext);
	  editmenu.add(bright);
	  editmenu.add(compress);
	  editmenu.add(resize);
	  editmenu.add(rotate);
	  editmenu.add(transparent);
	  editmenu.add(cancel);

	  mainmenu.add(menu);
	  mainmenu.add(editmenu);
	  setJMenuBar(mainmenu);
	 
	  setTitle("Image Editor");
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
	     setVisible(true); 

	  Choose = new JFileChooser();
	      FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "gif","bmp","png");
	      Choose.setFileFilter(filter);
	      Choose.setMultiSelectionEnabled(false);
	  enableSaving(false);
	  ima.requestFocus();
	  }
 public class ImgBrighten extends JFrame implements ChangeListener{

		 JSlider slider;
		
		
		ImgBrighten(){
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					dispose();
				}
			});
			
			Container cont=getContentPane();
			slider=new JSlider(-10,10,0);
			slider.setEnabled(false);
			slider.addChangeListener(this);
			cont.add(slider, BorderLayout.CENTER);
			slider.setEnabled(true);
			setTitle("Image Brightness");
			setPreferredSize(new Dimension(300,100));
			setVisible(true);
			pack();
			
			enabledSlider(false);
					}
		public void enabledSlider(boolean enabled){
			slider.setEnabled(enabled);
		}
		
		public void stateChanged(ChangeEvent e) {
			ima.setValue(slider.getValue()/10.0f);
		    ima.setActionSlided(true);   
		    ima.filterImage();
		    ima.repaint();
		    enableSaving(true);
			
		}
}

		public void actionPerformed(ActionEvent e){

	  JMenuItem source = (JMenuItem)(e.getSource());
	  if(source.getText().compareTo("Open...")==0)
	    {
	    setImage();
	    ima.repaint();
	     validate();
	      
	     }
 else if(source.getText().compareTo("Image brightness")==0)
	    {
	     
	    ImgBrighten ib=new ImgBrighten(); 
	    if(ImgArea.imgLoad)
	     ib.enabledSlider(true); 
	     }
}

 public void setImage(){
	  
	  int retValue = Choose.showOpenDialog(this);
	      if(retValue == JFileChooser.APPROVE_OPTION) {   
	   filename=Choose.getSelectedFile().toString();
	   ima.prepareImg(filename);
	   }
	           
	  }



	 
	 public void enableSaving(boolean f){
	  saveas.setEnabled(f);
	  save.setEnabled(f); 
	  
	  }

	} 
