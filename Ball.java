import java.awt.Image;
import javax.swing.ImageIcon;
//TODO Transform the code to be used safely in a concurrent context.  
public class Ball {
       //TODO  Find an archive named Ball.png 
	private String Ball = "Ball.png"; 

	private double x,y,dx,dy;
	private double v,fi;
	private Image image;
	private final int IMG_TAM_X,IMG_TAM_Y;

	public Ball() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(Ball));
		image = ii.getImage();
		
		//TODO Depend of image size
		IMG_TAM_X = ii.getIconWidth();
		IMG_TAM_Y = ii.getIconHeight();

		
		x = Billiards.Width/4-16;
		y = Billiards.Height/2-16;
		v = 5;
		fi =  Math.random() * Math.PI * 2;
	}

	public void move() {
		v = v*Math.exp(-v/1000);
		dx = v*Math.cos(fi);
		dy = v*Math.sin(fi);
		if (Math.abs(dx) < 1 && Math.abs(dy) < 1) {
			dx = 0;
			dy = 0;
		}
		x += dx;   
		y += dy;
		
		
		//TODO Check postcondition
		assert Math.abs(x - Board.RIGHTBOARD) > Math
				.abs(dx) : "Ves esto porque una bola se salio por el lado de la derecha";
		assert Math.abs(y - Board.BOTTOMBOARD) > Math.abs(dy) : "Ves esto porque una bola se salio por abajo";
		assert Math.abs(x + 32 - Board.LEFTBOARD) > Math
				.abs(dx) : "Ves esto porque una bola se salio por el lado de la Izquierda";
		assert Math.abs(y + 32 - Board.TOPBOARD) > Math.abs(dy) : "Ves esto porque una bola se salio por arriba";
	}

	public void reflect() {
		if (Math.abs(x + IMG_TAM_X - Board.RIGHTBOARD) <  Math.abs(dx)) {
			fi = Math.PI - fi;
		}
		if (Math.abs(y + IMG_TAM_Y - Board.BOTTOMBOARD) <  Math.abs(dy)) {
			fi = - fi;
		}
		if (Math.abs(x - IMG_TAM_X - Board.LEFTBOARD) <  Math.abs(dx)) {
			fi = Math.PI - fi;
		}
		if (Math.abs(y - IMG_TAM_Y - Board.TOPBOARD) <  Math.abs(dy)) {
			fi = - fi;
		}

		//TODO Check postcondition
		assert Math.abs(x  - Board.RIGHTBOARD) >  Math.abs(dx):"Ves esto porque una bola se salio por el lado de la derecha";
		assert Math.abs(y  - Board.BOTTOMBOARD) >  Math.abs(dy):"Ves esto porque una bola se salio por abajo";
		assert Math.abs(x + 32 - Board.LEFTBOARD) >  Math.abs(dx):"Ves esto porque una bola se salio por el lado de la Izquierda";
		assert Math.abs(y + 32 - Board.TOPBOARD) >  Math.abs(dy):"Ves esto porque una bola se salio por arriba";

	}

	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public double getFi() {
		return fi;
	}

	public double getdr() {
		return Math.sqrt(dx*dx+dy*dy);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

}

