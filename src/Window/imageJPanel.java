package Window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class imageJPanel extends JPanel {
	
	BufferedImage image ;
	
	public void setImage( BufferedImage image ) {
		if( image != null) {
			this.image = image ;
			Dimension dim = new Dimension( image.getWidth() , image.getHeight() ) ;
			this.setSize( dim ) ;
			this.setPreferredSize( dim ) ;
			this.repaint() ;			
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if ( image != null) {
			g.drawImage( image , 0 , 0 , null ) ;			
		}else {
			g.fillRect( 0 , 0 ,  this.getWidth() + 500 , this.getHeight() +500 ) ;
		}
	}

}
