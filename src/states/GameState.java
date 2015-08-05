package states;
import java.awt.Graphics;

import entities.creatures.Player;
import gfx.Assets;
import tilegame.Game;


public class GameState extends State
{

	private Player player;
	
	public GameState(Game game)
	{
		super(game);
		player = new Player(game, 5, 5);
	}
	
	@Override
	public void tick() 
	{
		player.tick();
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.dirt, 0, 0, null);
		player.render(g);
		
	}

}
