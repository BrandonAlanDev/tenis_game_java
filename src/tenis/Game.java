package tenis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	int maxRecord = 0;
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tenis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(300,400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		game.setBackground(Color.decode("#4b0091"));
		while(true) {
			game.move();
			game.repaint();
			Thread.sleep(game.ball.dificultad());
		}
	}
	
	
	
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				racquet.Soltar();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				racquet.pulsar(e);
			}
		});
		setFocusable(true);
	}
	
	//Repintado de pantalla lo que se ve
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString(ball.contador(), 30, 30);
		g2d.drawString(ball.txtDificultad(), 150, 30);
	}
	public void move() {
		ball.move();
		racquet.move();
	}
	public void gameOver() {
		int puntos = Integer.parseInt(ball.contador());
		String mensaje="";
		if (puntos >maxRecord) {
			maxRecord=puntos;
			mensaje="Nuevo record!"+"\nPuntos conseguidos: "+ball.contador()+"\n¿Quieres reintentar?";
		}else {mensaje="Record anterior : "+String.valueOf(maxRecord)+"\nPuntos conseguidos: "+ball.contador()+"\n¿Quieres reintentar?";}
        int option = JOptionPane.showConfirmDialog(this, mensaje, "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        ball = new Ball(this);
        racquet = new Racquet(this);
        ball.contador = 0;
    }
}
