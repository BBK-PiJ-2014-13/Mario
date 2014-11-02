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
	private Image image, character, background;
	private URL base;
	private Graphics second;
	public static Background bg1, bg2;

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
		background = getImage(base, "data/background.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		mainActor = new MainActor();
		Thread thread = new Thread(this);
		thread.start();
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
			bg1.update();
			bg2.update();
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
		g.drawImage(background, bg1.bgX, bg2.bgY, this);
		g.drawImage(background, bg2.bgX, bg2.bgY, this);
		g.drawImage(character, mainActor.centerX - 20, mainActor.centerY - 20, this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Move down");
			break;

		case KeyEvent.VK_LEFT:
			mainActor.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			mainActor.moveRight();
			break;

		case KeyEvent.VK_SPACE:
			mainActor.jump();
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Stop moving down");
			break;

		case KeyEvent.VK_LEFT:
			mainActor.stop();
			break;

		case KeyEvent.VK_RIGHT:
			mainActor.stop();
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Stop jumping");
			break;

		}

	}

}
