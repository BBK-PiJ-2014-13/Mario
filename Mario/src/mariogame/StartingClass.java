package mariogame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	private MainActor mainActor;
	private Image image, character;
	private URL base;
	private Graphics second;
	
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		addKeyListener(this);
		
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}


		// Image Setups
		character = getImage(base, "data/character.png");
	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
		mainActor = new MainActor();
	}

	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void run() {
		while (true) {
			mainActor.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}


		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);


		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(character, mainActor.centerX - 20, mainActor.centerY - 20, this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;

		case KeyEvent.VK_DOWN:
			break;

		case KeyEvent.VK_LEFT:
			mainActor.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			break;

		case KeyEvent.VK_SPACE:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_SPACE:
			break;

		}
	}

}
