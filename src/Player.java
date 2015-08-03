
import java.awt.Graphics;

public class Player extends Creature
{
	
	private Game _game;

	public Player(Game game, float x, float y) 
	{
		super(x, y);
		_game = game;
		
	}

	@Override
	public void tick()
	{
		if(_game.getKeyManager().up)
		{
			y -= 3;
		}
		if(_game.getKeyManager().down)
		{
			y += 3;
		}
		if(_game.getKeyManager().left)
		{
			x -= 3;
		}
		if(_game.getKeyManager().right)
		{
			x += 3;
		}
		
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.player, (int) x, (int) y, null);
		
	}

}