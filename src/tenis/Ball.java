package tenis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	int x=0;
	int y=0;
	int xa=1;
	int ya=1;
	int contador = 0;
	int dificultad =8; // a menor numero mayor dificultad minimo 3 como maxima dificultad
	int txtDificultad=1;
	private Game game;
	
	public Ball(Game gm) {
		this.game = gm;
	}
	
	public void paint(Graphics2D g) {
		g.setColor(Color.decode("#ffffff"));
		g.fillOval(x, y, 30, 30);
	}
	
	public void move() {
		if(x+xa<0) xa=2;
		if(x+xa>game.getWidth()-30) xa=-2;
		if(y+ya<0) ya=2;
		if(y+ya>game.getHeight()-30) game.gameOver();
		if(colision()) ya=-2;
		x=x+xa;
		y=y+ya;
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,30,30);
	}
	public boolean colision() {
		if(game.racquet.getBounds().intersects(this.getBounds())) {
			contador ++;
			if (contador%5 == 0 && dificultad>3) {dificultad--;}
			return true;
		}
		return false;
	}
	public String contador () {
		return String.valueOf(contador);
	}
	public String txtDificultad() {
		String dif = "Dificultad : "+String.valueOf((9-dificultad));
		return dif;
	}
	public int dificultad() {
		return this.dificultad;
	}
}
