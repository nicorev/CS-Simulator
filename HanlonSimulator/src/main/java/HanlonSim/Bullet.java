package HanlonSim;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Bullet extends GameObject{
	private Handler handler;
	private int listPos;
	private int vel;
	private int damage = 50;
	private BufferedImage image;
	public Bullet(int x, int y, ID id, Handler handler, int dX, int dY, int vel, SpriteSheet ss, int damage) 
	{
		super(x, y, id, ss);
		this.handler = handler;
		this.velX = dX;
		this.velY = dY;
		this.vel = vel;
		this.damage = damage;
		if(id == ID.ENEMYPROJECTILE) image = ss.grabImage(14, 4, 32, 32);
		if(id == ID.PROJECTILE) image = ss.grabImage(13,4,32,32);
	}

	public void tick() {
		listPos = handler.object.indexOf(this);
		x += velX * vel;
		y += velY * vel;
		collision();
	}
	public void render(Graphics2D g) {
		g.drawImage(image,x-16,y-16,null);
		
	}
	private void collision()
	{
		if(this.getId() == ID.ENEMYPROJECTILE) {
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() != ID.PROJECTILE && tempObject.getId() != ID.TURRET && tempObject.getId() != ID.ENEMYPROJECTILE && tempObject.getId() != ID.LIGHT && tempObject.getId() != ID.GROUND)
			{
				if(hitBox().intersects(tempObject.hitBox()))
				{
					if(tempObject.getId() == ID.PLAYERS)
					{
					Player player = (Player) tempObject;
					player.damage("Ranged");
					}
					handler.object.remove(listPos);
					return;
				}
			}
		}
		}
		if(this.getId() == ID.PROJECTILE) {
			for(int i = 3; i < handler.object.size(); i++)
			{
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() != ID.PROJECTILE && tempObject.getId() != ID.ENEMYPROJECTILE && tempObject.getId() != ID.LIGHT&& tempObject.getId() != ID.GROUND)
				{
					if(hitBox().intersects(tempObject.hitBox()))
					{
						handler.object.remove(this);
						if(tempObject.getId() == ID.ENEMY)
						{
							if(tempObject.getHealth() - damage <= 0)
							handler.object.remove(i);
							else tempObject.damage(damage);
						}
						return;
					}
				}
			}
			}
	}
	@Override
	public Rectangle hitBox() {
		
		return new Rectangle(x,y,8,8);
	}
}
