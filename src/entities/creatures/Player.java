package entities.creatures;

import gfx.Assets;

import java.awt.Graphics;

import tilegame.Game;

public class Player extends Creature
{
	
	private Game _game;

	public Player(Game game, float x, float y) 
	{
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		_game = game;
		
	}

	@Override
	public void tick()
	{
		getInput();
		move();
	}
	
	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if(_game.getKeyManager().up)
		{
			yMove = -speed;
			
		}
		if(_game.getKeyManager().down)
		{
			yMove = speed;
			
		}
		if(_game.getKeyManager().left)
		{
			xMove = -speed;
			
		}
		if(_game.getKeyManager().right)
		{
			xMove = speed;
			
		}
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
		
	}

}