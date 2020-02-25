package pruebas;
 import java.awt.Color;
 import java.awt.Frame;
 import java.awt.Graphics;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;
 
 public class Pelotas20 extends Frame{
     int contador=0;
 
     public Pelotas20(){
         super("Viruz Blog: 20 Pelotas");
         setSize(600,600);
         setBackground(Color.WHITE);
         setVisible(true);
         
         this.addWindowListener(new WindowAdapter(){
             @Override
             public void windowClosing(WindowEvent e){
                 System.exit(0);
             }
         });
         
         this.addMouseListener(new MouseAdapter(){
             @Override
             public void mousePressed(MouseEvent e){
                 if(contador<=20){
                     Graphics g = getGraphics();
                     Bola b = new Bola(g);
                     b.start();
                 }
                 contador++;
             }
         });
     }
     
     private class Bola extends Thread{
         int x,y,diametro,xInc,yInc;
         Graphics g;
 
         public Bola(Graphics g){
             x=(int)(Math.random()*250+1);
             y=(int)(Math.random()*250+1);
             diametro=10;
             xInc=5;
             yInc=2;
             this.g=g;
         }
        
         @Override
         public void run(){
            g.setColor(Color.BLUE);
            Color pelota;
            pelota = (new Color((int)(Math.random()*256),
                           (int)(Math.random()*256),
                           (int)(Math.random()*256)));
             for(;;){
                 g.setColor(Color.WHITE);
                 g.fillOval(x, y, diametro, diametro);
                 g.setColor(pelota);
                 if(x+xInc <= 0 ){
                     xInc = -xInc;
                 }
                 if(x+xInc >= 600){
                     xInc = -xInc;
                 }
                 if(y+yInc <=0 ){
                     yInc = -yInc;
                 }
                 if(y+yInc >= 600){
                     yInc = -yInc;
                 }
                 x+=xInc;
                 y+=yInc;
                 g.fillOval(x,y,diametro,diametro);
                 
                 try{
                     Thread.sleep(10);
                 }catch(InterruptedException e){
                     System.err.println("Error de excepcion:" + e);
                 }
             }
         }
     }
	 
	 public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Pelotas20();
			}
		});
	}
 }