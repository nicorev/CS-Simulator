package HanlonSim;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Enemy extends GameObject 
{
	private GameObject player;
	int chaseSpeed = 4;
	Handler handler;
	int listPos;
	private boolean yCol = false;
	private boolean xCol = false;
	BufferedImage enemyImage = null;
	private int health = 100;
	Enemy(int x, int y, ID id, GameObject player, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.player = player;
		this.handler = handler;
		velX = chaseSpeed;
		velY = chaseSpeed;
		enemyImage = ss.grabImage(13,2,32,32);
	}
	public void tick() {
		listPos = handler.object.lastIndexOf(this);

		collision();
		if(Math.abs(player.hitBox().getCenterX() - this.hitBox().getCenterX()) > 4)
		{
			if(player.getX()<=x) x -= velX;
			else x += velX;

		}
		if(Math.abs(player.hitBox().getCenterY() - this.hitBox().getCenterY()) > 4)
		{
			if(player.getY()<=y) y -= velY;
			else y += velY;
		}
	}
	public void damage(int x)
	{
		health -= x;
	}
	public int getHealth(){
		return health;
	}
	public void render(Graphics2D g) {
		g.drawImage(enemyImage,x,y,null);
	}
	private void collision()
	{
		for(int i = 0; i < handler.object.size() ; i++)
		{
			GameObject tempObject = handler.object.get(i);
			if((int)this.getDistance(player) > (int)tempObject.getDistance(player))
					{
						if(tempObject.getId() == ID.ENEMY && listPos != i)
							{
							if(this.collidesWith(tempObject))
							{
								if(tempObject.getX()>this.getX())
								{
									x = tempObject.getX() - 32;
								}
								if(tempObject.getX()<this.getX())
								{
									x = tempObject.getX() + 32;
								}
								if(tempObject.getY()>this.getY())
								{
									y = tempObject.getY() - 32;
								}
								if(tempObject.getY()<this.getY())
								{
									y = tempObject.getY() + 32;
								}

							}
				
							}
						if(tempObject.getId() == ID.BLOCK)
						{
							{
								if(!yCol && hitBox().intersection(tempObject.hitBox()).getWidth()  < hitBox().intersection(tempObject.hitBox()).getHeight() &&  this.collidesWith(tempObject))
								{
									velX  = 0;
									velY = chaseSpeed;
									xCol = true;
									return;
								}
								else if(!xCol && this.collidesWith(tempObject) && hitBox().intersection(tempObject.hitBox()).getWidth()  > hitBox().intersection(tempObject.hitBox()).getHeight())
								{
									velX  = chaseSpeed;
									velY = 0;
									yCol = true;
									return;
								}
								else if(!this.collidesWith(tempObject))
								{
									velX = chaseSpeed;
									velY = chaseSpeed;
									yCol = false;
									xCol = false;
								}
							}	
						}

					}
		}
	}
	public Rectangle hitBox() {

		return new Rectangle(x,y, 32, 32);
	}


}