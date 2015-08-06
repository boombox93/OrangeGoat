package worlds;

import java.awt.Graphics;

import tiles.Tile;
import utils.Utils;

public class World 
{
	private int _width;
	private int _height;
	private int _spawnX;
	private int _spawnY;
	
	private int[][] _tiles;
	
	public World(String path)
	{
		loadWorld(path);
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		for(int j = 0; j < _height; j++)
		{
			for(int i = 0; i < _width; i++)
			{
				getTile(i, j).render(g, i*Tile.TILEWIDTH, j*Tile.TILEHEIGHT);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		Tile t = Tile.tiles[_tiles[x][y]];
		
		if(t == null)
		{
			return Tile.dirtTile;
		}
		return t;
	}
	
	private void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		_width = Utils.parseInt(tokens[0]);
		_height = Utils.parseInt(tokens[1]);
		_spawnX = Utils.parseInt(tokens[2]);
		_spawnY = Utils.parseInt(tokens[3]);
		
		_tiles = new int[_width][_height];
		
		for(int j = 0; j < _height; j++)
		{
			for(int i = 0; i < _width; i++)
			{
				_tiles[i][j] = Utils.parseInt(tokens[(i + j * _width) + 4]);
			}
		}
	
	
				
		
	}
}
