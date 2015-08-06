package states;
import java.awt.Graphics;

import entities.creatures.Player;
import tilegame.Game;
import tiles.Tile;
import worlds.World;



public class GameState extends State 
{
	
	private Player _player;
	private World _world;
	
	public GameState(Game game)
	{
		super(game);
		_player = new Player(game, 100, 100);
		_world = new World("Resources/worlds/world1.txt");
	}
	
	@Override
	public void tick() 
	{
		_world.tick();
		_player.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		_world.render(g);
		//Tile.tiles[0].render(g, 0, 0);
		_player.render(g);
	}

}
