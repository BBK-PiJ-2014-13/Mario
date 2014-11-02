package mariogame;

public class MainActor {
	
	// Constants
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 382;
	
	public int centerX = 100;
	public int centerY = GROUND;
	public boolean jumped = false;
	public boolean movingLeft = false;
	public boolean movingRight = false;

	private Background bg1 = StartingClass.bg1;
	private Background bg2 = StartingClass.bg2;
	
	public int speedX = 0;
	public int speedY = 1;

	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX <= 0) {
			bg1.speedX = 0;
			bg2.speedX = 0;
		}
		if (speedX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.speedX = -MOVESPEED;
			bg1.speedX = -MOVESPEED;
		}
		
		// Updates Y Position
		centerY += speedY;

		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		} else {
			centerY += speedY;
		}

		// Handles Jumping
		if (jumped == true) {
			speedY += 1;

			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				speedY = 0;
				jumped = false;
			}

		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 20) {
			centerX = 21;
		}
	}

	public void moveRight() {
		speedX = MOVESPEED;
	}

	public void moveLeft() {
		speedX = -MOVESPEED;
	}
	
	public void stopRight() {
		movingRight = false;
		stop();
	}
	
	public void stopLeft() {
		movingLeft = false;
		stop();
	}

	public void stop() {
		if (movingRight == false && movingLeft == false) {
			speedX = 0;
		}
		if (movingRight == false && movingLeft == true) {
			moveLeft();
		}
		if (movingRight == true && movingLeft == false) {
			moveRight();
		}
	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}
}
