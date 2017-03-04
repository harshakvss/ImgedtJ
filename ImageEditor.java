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

////start the ImgArea class
//The ImgArea class acts as a drawing area of the Image Editor program

class ImgArea extends Canvas{ 

  Image Img;
  BufferedImage BufferedImg;
  BufferedImage bufimg; 
  BufferedImage bufimg1; 
  float e;
  float radian;
  Dimension ds;
  int mX;
  int mY;
  int x;
  int y;
  static boolean imgLoad;
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
  public ImgArea(){

   
  
	  addMouseListener(new mousexy()); //hanlding mouse event of Canvas class
	 
	   

	   try{
	    rb=new Robot(); 
	   }catch(AWTException e){}

	   ds=getToolkit().getScreenSize();    
	   mX=(int)ds.getWidth()/2; 
	   mY=(int)ds.getHeight()/2;
	   
	  }
	  
	  public void paint(Graphics g){
	   Graphics2D g2d=(Graphics2D)g;   //create Graphics2D object  
	   if(imgLoad){

		    
		    if(actionSlided || actionResized || actionTransparent || actionRotated || drawn ){
		     x=mX-bufimg.getWidth()/2;
		     y=mY-bufimg.getHeight()/2;
		     g2d.translate(x,y);  
		     g2d.drawImage(bufimg,0,0,null); 
		     
		     }
	 
		    else{
	     
	   
	     x=mX-BufferedImg.getWidth()/2;
	     y=mY-BufferedImg.getHeight()/2;
	     g2d.translate(x,y); 
	     g2d.drawImage(BufferedImg,0,0,null); 
	     
		    }}
	   g2d.dispose(); 
	   
	  }

	  class mousexy extends MouseAdapter{
	   
	   public void mousePressed(MouseEvent e){
	  Color color=rb.getPixelColor(e.getX(),e.getY()); //get the color at the clicked point
   	 try{    
    	setColor(color); //take the color at the clicked point for later use
   	 if(actionDraw){ //add text to the update image
   	  if(actionSlided || actionResized || actionTransparent || actionRotated || drawn)
     	 addTextToImage(e.getX()-x,e.getY()-y, bimg);
    	 else  //add text to the original image
      	addTextToImage(e.getX()-x,e.getY()-y, orBufferedImage);
     	 
	   		}
	      } catch(Exception ie){}
	   					} 
	  				}
	 //The addTextToImage method adds the text specified by the user to the image
 public void addTextToImage(int x,int y, BufferedImage img){
  //create a blanck buffered image
  BufferedImage bi=(BufferedImage)createImage(img.getWidth(),img.getHeight());
  //create the Graphics2D object from the buffered image   
  Graphics2D  g2d=(Graphics2D)bi.createGraphics();
  //Set the font of drawing pen
  g2d.setFont(new Font(fontName,Font.BOLD,fontSize));
  //Set the pen color
  g2d.setPaint(colorTextDraw);
  //Draw the image on the blank buffered image
  g2d.drawImage(img,0,0,null);
  //Draw the text on the buffered image
  g2d.drawString(textToDraw,x,y);
  //update the image
  bimg=bi;
  //there is a text drawing on the image
  drawn=true;
  //clean the Graphics2D object    
  g2d.dispose();
  //redisplay the update image so the text is displayed on the image now
  repaint(); 
  }
	  
 public void prepareImg(String filename){
   
   try{
  
   mt=new MediaTracker(this);    
   Img=Toolkit.getDefaultToolkit().getImage(filename); 
   mt.addImage(Img,0);
    mt.waitForID(0); 
    
   int width=Img.getWidth(null);
   int height=Img.getHeight(null);
   
   BufferedImg=createBufferedImageFromImage(Img,width,height,false);
   
   bufimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
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
	 ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null); //create ConvolveOp to encapsulate the kernel
	 bufimg= new BufferedImage(BufferedImg.getWidth(),BufferedImg.getHeight(),BufferedImage.TYPE_INT_RGB);
	 cop.filter(BufferedImg,bufimg); 
	 
	  
 }
 public void setValue(float value){ 
  e=value;
 }

 public void setActionSlided(boolean value ){ 
  actionSlided=value;
 }
}

public class ImageEditor{
	  
	 public static void main(String args[]){
	       Main m=new Main();
	   
	 }
	}
 
