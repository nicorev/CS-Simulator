package HanlonSim;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Turret extends GameObject{
	private Handler handler;
	private int timer = 0;
	private String type;
	private Player player;
	BufferedImage turretImage = null;
	public Turret(int x, int y, ID id, Handler handler, String type, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.type = type;
		player = (Player) handler.object.get(1);
		turretImage = ss.grabImage(13, 5, 64, 64);
	}

	public void tick() {
		if (type.equals("dumb"))
		{
			timer++;
			if(timer == 50)
			{
			handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 1,0, 20, this.ss, 30));
			handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 0,1, 20, this.ss, 30));
			handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, -1,0, 20, this.ss, 30));
			handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 0,-1, 20, this.ss, 30));
			timer = 0;
			}
		}
		if(type.equals("smart"))
		{
			if(timer == 0)
			{
				if(Math.abs(player.hitBox().getCenterY() - this.hitBox().getCenterY()) < 5)
				{
					if(player.hitBox().getCenterX() > this.hitBox().getCenterX()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 1,0, 15, this.ss, 30));
					if(player.hitBox().getCenterX() < this.hitBox().getCenterX()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, -1,0, 15, this.ss, 30));
					timer++;
				}
				if(Math.abs(player.hitBox().getCenterX() - this.hitBox().getCenterX()) < 5)
				{
					if(player.hitBox().getCenterY() > this.hitBox().getCenterY()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 0,1, 15, this.ss, 30));
					if(player.hitBox().getCenterY() < this.hitBox().getCenterY()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 0,-1, 15, this.ss, 30));
					timer++;
				}
			}
			if(timer != 0)
			{
				timer++;
				if(Math.abs(player.hitBox().getCenterY() - this.hitBox().getCenterY()) < 5 && timer >= 15)
				{
					if(player.hitBox().getCenterX() > this.hitBox().getCenterX()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 1,0, 15, this.ss, 30));
					if(player.hitBox().getCenterX() < this.hitBox().getCenterX()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, -1,0, 15, this.ss, 30));
					timer = 0;
				}
				if(Math.abs(player.hitBox().getCenterX() - this.hitBox().getCenterX()) < 5 && timer >= 15)
				{
					if(player.hitBox().getCenterY() > this.hitBox().getCenterY()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 0,1, 15, this.ss, 30));
					if(player.hitBox().getCenterY() < this.hitBox().getCenterY()) handler.object.add(handler.object.indexOf(this)-1, new Bullet((int)this.hitBox().getCenterX(),(int) this.hitBox().getCenterY(), ID.ENEMYPROJECTILE, handler, 0,-1, 15, this.ss, 30));
					timer = 0;
				}
			}
		}
		
	}


	public void render(Graphics2D g) {
		g.drawImage(turretImage,x,y,null);
		
	} 

	@Override
	public Rectangle hitBox() {

		return new Rectangle(x,y,64,64);
	}
		
}
