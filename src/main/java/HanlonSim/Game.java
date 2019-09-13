package HanlonSim;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
public class Game extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Thread thread;
	public Handler handler;
	private SpriteSheet ss;
	private BufferedImage level = null;
	private BufferedImage spriteSheet = null;
	private BufferedImage floor = null; // do not delete
	private Window window;
	private BufferedImage image;
	private BufferedImage lightMap;
	private BufferedImage lightImage;
	private Graphics2D g;
	private	Graphics2D g2;
	private Graphics2D gLight;
	private GameStateManager gsm;
	public int playerHealth;
	Game(BufferedImage levelMap, BufferedImage lightImage)
	{
		playerHealth = 100;
		window = new Window(1280,800,"Game", this);
		start();
		handler = new Handler();
		BufferedImageLoader loader = new BufferedImageLoader();
		level  = levelMap;
		this.lightImage = lightImage;
		spriteSheet = loader.loadImage("/images/spritesheet.png");
		ss = new SpriteSheet(spriteSheet);
		floor = ss.grabImage(14, 1, 32, 32);
		this.gsm = gsm = new GameStateManager();// do not delete
		loadTitle();
	}
	public void run()
	{
		image = new BufferedImage(1280,800,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		lightMap = new BufferedImage(1280,800,BufferedImage.TYPE_INT_ARGB);
		gLight = (Graphics2D) lightMap.getGraphics();
		this.addMouseListener(new MouseInput(handler));
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		while(isRunning)
		{
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			render();
			bufferRender();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
			
		}
		stop();
		
	}
	public void tick()
	{
			handler.tick();
	}
	public void render()
	{
		handler.render(g,window);
		g.drawImage(lightMap,0,0,null);
	}
	public void bufferRender()
	{
		g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	//title screen load
	public void loadTitle()
	{
		handler.object.clear();
		handler.addObject(new TitleScreen(0,0,ID.TITLE,null,this.window,this,gsm.gameStates));
	}
	//level load
	public void loadLevel(BufferedImage image,BufferedImage lightLayout)
	{
		handler.object.clear();
		lightHandler lHandler = new lightHandler(lightLayout);
		gLight.setColor(new Color(0,0,0,255));
		gLight.fillRect(0,0,1280,800);
		lHandler.renderLight(gLight);
		handler.addObject(new Floor(0,0,ID.GROUND,null));
		int w = image.getWidth();
		int h = image.getHeight();
		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if(blue == 255 && green == 0 && red ==0) handler.addObject(new Player(x*32, y*32, ID.PLAYERS, handler, this.ss,this.window, this));
			}
		}
		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if(green == 255 && blue == 0 && red == 0) handler.addObject(new Block(x*32, y*32, ID.BLOCK, this.ss));
				if(red == 255 && green == 0 && blue == 0) handler.addObject(new Enemy(x*32,y*32,ID.ENEMY, handler.object.get(1), handler, this.ss));
				if(red == 255 && green == 255 && blue == 0) handler.addObject(new Pickup(x*32,y*32,ID.CRATE,handler, this.ss));
				if(red == 255 && green == 0 && blue == 255) handler.addObject(new Turret(x*32, y*32,ID.TURRET, handler,"smart", this.ss));
				if(green == 0 && blue == 100) handler.addObject(new switchBlock(x*32, y*32, ID.SWITCH, this.ss, gsm.gameStates, handler, red, this));
			}
		}
	
	}
	private void start()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop()
	{
		isRunning = false;
		try
		{
			thread.join();
		} catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) handler.setUp(true);
		if (key == KeyEvent.VK_S) handler.setDown(true);
		if (key == KeyEvent.VK_A) handler.setLeft(true);
		if (key == KeyEvent.VK_D) handler.setRight(true);
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) handler.setUp(false);
		if (key == KeyEvent.VK_S) handler.setDown(false);
		if (key == KeyEvent.VK_A) handler.setLeft(false);
		if (key == KeyEvent.VK_D) handler.setRight(false);

	}
	public void keyTyped(KeyEvent e) {
		
	}
	public static void main(String args[])
	{
		GameStateManager gsm = new GameStateManager();
		new Game(gsm.getLevel(0),gsm.getLight(0));
	}
}