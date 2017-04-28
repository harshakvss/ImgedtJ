import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.awt.color.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;
import javax.imageio.stream.*;

// The image area class starts here
//The ImgArea class acts as a drawing area of the Image Editor program

class ImgArea extends Canvas{ 

  Image Img;
  BufferedImage BufferedImg;
  BufferedImage bufimg; 
  BufferedImage bufimg1; 
  float e;
  float radian;
  Dimension Ds;
  int mx;
  int my;
  int x;
  int y;
  static boolean imgLoaded;
  boolean actionSlided;
  boolean actionResized;
  boolean actionCompressed;
  boolean actionTransparent;
  boolean actionRotated;
  boolean actionDraw;
  boolean drawn;
  MediaTracker mt;
  static Color c;
  Color colorTextDraw;
  Robot rb;
  boolean dirHor;
  String imgFileName;
  String fontName;
  int fontSize;
  String textToDraw;
  public ImgArea()
  {
	  addMouseListener(new mousexy()); // Event handler, notified whenever you change the state of mouse
	 
	   try{
	    rb=new Robot(); // creating a robot object
	   }catch(AWTException e){}  // Exception part and catching

	   ds=getToolkit().getScreenSize();    // This will get the screen size
	   mx=(int)ds.getWidth()/2; 		// half of the screen width
	   my=(int)ds.getHeight()/2;		// half of the screen height
	   
	  }
	  
	  public void paint(Graphics g){
	   Graphics2D g2d=(Graphics2D)g;   // This will create graphics 2D object
	   if(imgLoaded){ 		   // This will draw the updated image 

		    
		    if(actionSlided || actionResized || actionTransparent || actionRotated || drawn ){
		     x=mx-bufimg.getWidth()/2;
		     y=mx-bufimg.getHeight()/2;
		     g2d.translate(x,y);  
		     g2d.drawImage(bufimg,0,0,null); 
		     
		     }
	 
		    else{		  // Will draw the original unchanged image
	     
	   
	     x=mx-BufferedImg.getWidth()/2;
	     y=my-BufferedImg.getHeight()/2;
	     g2d.translate(x,y); 
	     g2d.drawImage(BufferedImg,0,0,null); 
	     
		    }}
	   g2d.dispose(); 		// this will dispose the graphics 2d image
	   
	  }

	  class mousexy extends MouseAdapter{
	   
	   public void mousePressed(MouseEvent e){
 	try{    
    	setColor(color);    //take the color at the clicked point for later use
    	if(actionDraw){     //will add text to the updated image
     	if(actionSlided || actionResized || actionTransparent || actionRotated || drawn)
      	addTextToImage(e.getX()-x,e.getY()-y, bimg);
     	else               //will add text to the original image
      	addTextToImage(e.getX()-x,e.getY()-y, orBufferedImage);
     }

    }catch(Exception ie){}
	
 public void prepareImg(String filename){
   
   try{
  
   mt=new MediaTracker(this);    
   Img=Toolkit.getDefaultToolkit().getImage(filename); 
   mt.addImage(Img,0);
    mt.waitForID(0); 
    
   int width=Img.getWidth(NULL);
   int height=Img.getHeight(NULL);
   
   BufferedImg=createBufferedImageFromImage(Img,width,height,false);
   
   bimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
   imgLoad=true;
   }catch(Exception e){System.exit(-1);}
  }
 public BufferedImage createBufferedImageFromImage(Image image, int width, int height, boolean tran)
   { BufferedImage dest ;
  if(tran) 
       dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  else
   dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       Graphics2D g2 = dest.createGraphics();
       g2.drawImage(image, 0, 0, null);
       g2.dispose();
       return dest;
   }
 

 public void filterImage(){
	 float[] elements={0.0f, 1.0f, 0.0f, -1.0f,e,1.0f,0.0f,0.0f,0.0f};
	 Kernel kernel=new Kernel(3,3,elements);//create kernel object to encapsulate the elements array
	 ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, NULL); //create ConvolveOp to encapsulate the kernel
	 bimg= new BufferedImage(BufferedImg.getWidth(),BufferedImg.getHeight(),BufferedImage.TYPE_INT_RGB);
	 cop.filter(BufferedImg,bufimg); 
	 
	  
 }
 public void setValue(float value){ 			//this method is invoked when the user makes change to the  image slider
  e=value;
 }

 public void setActionSlided(boolean value ){ 		//Set a boolean value the actionSlided variable 
  actionSlided=value;
 }
}

public class ImageEditor{
	  
	 public static void main(String args[]){
	       Main m=new Main();
	   
	 }
	}
 
