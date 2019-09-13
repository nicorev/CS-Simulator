package HanlonSim;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Player extends GameObject{
	Handler handler;
	private int speed = 7;
	private int shootDelay;
	private int delay;
	private BufferedImage playerImage;
	private double mX;
	private double mY;
	private double resX = 1280.0;
	private double resY = 800.0;
	private int spriteCount = 3;
	private int animationLimit = 10;
	private int animationDelay = 0;
	private int mousePosX;
	private int mousePosY;
	private int direction = 1; // 1 is up, 3 is right, 5 is down, 4 is left
	private Window window;
	private int health;
	private Game game;
	private boolean gameOver = false;
	private int damageTimer = 60;
	public Player(int x, int y, ID id, Handler handler, SpriteSheet ss, Window window, Game game)
	{
		super(x, y, id, ss);
		this.handler = handler;
		shootDelay = 15;
		playerImage = ss.grabImage(1, 1, 64, 64);
		this.window = window;
		this.game = game;
		health = game.playerHealth;
	}
	@Override
	public void tick() 
	{
		if(damageTimer > 0)
		{
			damageTimer --;
		}
		if(game.playerHealth <= 0) game.loadTitle();
		if(delay > 0) delay--;
		if(animationDelay > 0) animationDelay--;
		x += velX;
		y += velY;
		mousePosX = (int)((int) MouseInfo.getPointerInfo().getLocation().x - window.frame.getLocationOnScreen().x);
		mousePosY = (int)((int) MouseInfo.getPointerInfo().getLocation().y - window.frame.getLocationOnScreen().y);
		collision();
		double angle = Math.atan2(mY - mousePosY, mX - mousePosX);
		if(-.707 < angle && angle < .707)
			direction = 7;
		else if(angle>.707 && angle < 2.356)
			direction = 1;
		else if(angle < -2.356 || angle > 2.356)
			direction = 3;
		else if(angle < -.707 && angle > -2.356)
			direction = 5;
		if(handler.isUp())
			{
				velY = -speed;
				if(animationDelay == 0)
				{
					animationDelay = animationLimit;
					if(spriteCount >= 11)spriteCount = 1;
					else spriteCount+= 2;
					playerImage = ss.grabImage(spriteCount, direction, 64, 64);
				}
			}
		
		else if (!handler.isDown())
			{
				velY = 0;
				if(!handler.isUp() && !handler.isDown() && !handler.isLeft() && !handler.isRight())
				{
					playerImage = ss.grabImage(1, direction, 64, 64);
					spriteCount = 1;
					if(animationDelay != 0) animationDelay = 0;
				}
				
			}
		if(handler.isDown())
			{
				velY = speed;
				if(animationDelay == 0)
				{
					animationDelay = animationLimit;
					if(spriteCount >= 11)spriteCount = 1;
					else spriteCount+= 2;
					playerImage = ss.grabImage(spriteCount, direction, 64, 64);
				}
			}
		else if(!handler.isUp())
			{
				velY = 0;
				if(!handler.isUp() && !handler.isDown() && !handler.isLeft() && !handler.isRight())
				{
					playerImage = ss.grabImage(1, direction, 64, 64);
					spriteCount = 1;
					if(animationDelay != 0) animationDelay = 0;
				}
			}
		if(handler.isLeft())
			{
				velX = -speed;
				if(animationDelay == 0)
				{
					animationDelay = animationLimit;
					if(spriteCount >= 11)spriteCount = 1;
					else spriteCount+= 2;
					playerImage = ss.grabImage(spriteCount, direction, 64, 64);
				}
			}
		else if(!handler.isRight())
			{
				velX = 0;
				if(!handler.isUp() && !handler.isDown() && !handler.isLeft() && !handler.isRight())
				{
					playerImage = ss.grabImage(1, direction, 64, 64);
					spriteCount = 1;
					if(animationDelay != 0) animationDelay = 0;
				}
			}
		
		if(handler.isRight())
			{
				velX = speed;
				if(animationDelay == 0)
				{
					animationDelay = animationLimit;
					if(spriteCount >= 11)spriteCount = 1;
					else spriteCount+= 2;
					playerImage = ss.grabImage(spriteCount, direction, 64, 64);
				}
			}
		else if(!handler.isLeft())
			{
				velX = 0;
				if(!handler.isUp() && !handler.isDown() && !handler.isLeft() && !handler.isRight())
				{
					playerImage = ss.grabImage(1, direction, 64, 64);
					spriteCount = 1;
					if(animationDelay != 0) animationDelay = 0;
				}
			}
	}
	public void damage(String type)
	{
		if(damageTimer == 0)
		{
		damageTimer = 60;
		if(type.equals("Physical"))
		game.playerHealth -= 25;
		if(type.equals("Ranged"))
		game.playerHealth -= 10;
		}
	}
	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BLOCK || tempObject.getId() == ID.TURRET)
			{
				if(hitBox().intersects(tempObject.hitBox()))
				{
					x += velX * -1;
					y += velY * -1;
				}
			}
			if(tempObject.getId() == ID.CRATE){
				if(hitBox().intersects(tempObject.hitBox())) {
					handler.object.remove(((Pickup) tempObject).getListPos());
				}
			}
			if(tempObject.getId() == ID.ENEMY)
			{
				if(hitBox().intersects(tempObject.hitBox()))
				{
					this.damage("Physical");
				}
			}
			if(tempObject.getId() == ID.CRATE)
			{
				if(hitBox().intersects(tempObject.hitBox()))
				{
					this.health += 25;
				}
			}
		}
	}
	public void render(Graphics2D g) 
	{
		g.setColor(Color.GREEN);
		g.drawString("Health: " + String.valueOf(game.playerHealth),40,55);
		g.drawImage(playerImage,x,y,null);
	}
	@Override
	public Rectangle hitBox() 
	{
		return new Rectangle(x,y,64,64);
	}
	public void shoot(int xP, int yP)
	{
		if(delay == 0)
		{
		double angle = Math.atan2(mY - yP, mX - xP);
		if(-.707 < angle && angle < .707)
			handler.object.add(new Bullet((int)hitBox().getCenterX(), (int)hitBox().getCenterY(), ID.PROJECTILE, handler, -1, 0, 30, this.ss, 50));
		else if(angle>.707 && angle < 2.356)
			handler.object.add(new Bullet((int)hitBox().getCenterX(), (int)hitBox().getCenterY(), ID.PROJECTILE, handler, 0, -1, 30, this.ss, 50));
		else if(angle < -2.356 || angle > 2.356)
			handler.object.add(new Bullet((int)hitBox().getCenterX(), (int)hitBox().getCenterY(), ID.PROJECTILE, handler, 1, 0, 30, this.ss, 50));
		else if(angle < -.707 && angle > -2.356)
			handler.object.add(new Bullet((int)hitBox().getCenterX(), (int)hitBox().getCenterY(), ID.PROJECTILE, handler, 0, 1, 30, this.ss, 50));
		delay = shootDelay;
		}

	}
	public void scale(Window window)
	{
		mY = (double)(y * (window.frame.getHeight()/resY)) + 24;
		mX = (double)(x * (window.frame.getWidth()/resX)) + 16;
	}

}
