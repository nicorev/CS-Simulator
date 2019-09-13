package HanlonSim;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.MouseInfo;
import java.util.ArrayList;
public class TitleScreen extends GameObject{

    private BufferedImage backGround;
    private BufferedImage startGameUp;
    private BufferedImage startGameDown;
    private BufferedImage startGame;
    private BufferedImage exitGameUp;
    private BufferedImage exitGameDown;
    private BufferedImage exitGame;
    private Game game;
    private ArrayList<GameState> levelList;
    private int mousePosX;
    private int mousePosY;
    private Window window;
    public TitleScreen(int x, int y, ID id, SpriteSheet ss, Window window, Game game, ArrayList<GameState> levelList)
    {
        super(x,y,id,ss);
        BufferedImageLoader loader = new BufferedImageLoader();
        backGround = loader.loadImage("/images/TitleScreen.png");
        startGameUp = loader.loadImage("/images/StartGameUp.png");
        startGameDown = loader.loadImage("/images/StartGameDown.png");
        exitGameUp = loader.loadImage("/images/ExitUp.png");
        exitGameDown = loader.loadImage("/images/ExitDown.png");
        this.window = window;
        startGame = startGameUp;
        this.game = game;
        this.levelList = levelList;
    }
    public void tick()
    {
        mousePosX = (int)((int) MouseInfo.getPointerInfo().getLocation().x - window.frame.getLocationOnScreen().x);
        mousePosY = (int)((int) MouseInfo.getPointerInfo().getLocation().y - window.frame.getLocationOnScreen().y);
        if(mousePosX > 278 && mousePosX < 971 && mousePosY > 325 && mousePosY < 465)
        startGame = startGameDown;
        else
        startGame = startGameUp;
        if(mousePosX > 511 && mousePosX < 762 && mousePosY > 554 && mousePosY < 683)
        exitGame = exitGameDown;
        else
        exitGame = exitGameUp;
    }
    public void render(Graphics2D g)
    {
        g.drawImage(backGround,0,0,null);
        g.drawImage(startGame,278,295,null);
        g.drawImage(exitGame,511,524,null);
    }
    public Rectangle hitBox()
    {
        return null;
    }
    public void Click()
    {
        if(mousePosX > 278 && mousePosX < 971 && mousePosY > 325 && mousePosY < 465)
        game.loadLevel(levelList.get(0).getLevel(), levelList.get(0).getLight());
        if(mousePosX > 511 && mousePosX < 762 && mousePosY > 554 && mousePosY < 683)
        window.frame.dispose();
    }
}