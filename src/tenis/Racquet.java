package tenis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private Game game;
	int x = 0;
	int xa = 0;
	int altura = 330; 
	public Racquet(Game gm) {
		this.game= gm;
	}
	public void move() {
		if(x+xa>0 && x+xa<game.getWidth()-60)x=x+xa;
	}
	public void paint (Graphics2D g2d) {
		g2d.setColor(Color.decode("#ffffff"));
		g2d.fillRect(x, altura, 60, 10);
	}
	public void Soltar() {
		xa=0;
	}
	public void pulsar(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) xa=-4;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) xa=4;
	}
	public Rectangle getBounds() {
		Rectangle rectangulo = new Rectangle(x,altura,60,10);
		return rectangulo;
	}
}
