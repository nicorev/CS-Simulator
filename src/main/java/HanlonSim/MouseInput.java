package HanlonSim;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	private Handler handler;
	private Player player;
	private TitleScreen titleScreen;
	public MouseInput(Handler handler)
	{
		this.handler = handler;
	}
	public void mousePressed(MouseEvent e)
	{
		if(handler.object.size() > 2)
		{
		this.player = (Player)handler.object.get(1);
		int button = e.getButton();
		if(button == MouseEvent.BUTTON1)
		{
			player.shoot(e.getX(), e.getY());
		}
	}
	}
	public void mouseReleased(MouseEvent e)
	{
		if(handler.object.get(0).getId() == ID.TITLE)
		{
			this.titleScreen = (TitleScreen)handler.object.get(0);
			int button = e.getButton();
			if(button == MouseEvent.BUTTON1)
			{
				titleScreen.Click();
			}
		}
	}


}
