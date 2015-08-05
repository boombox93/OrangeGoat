package worlds;

import java.awt.Graphics;

import tiles.Tile;

public class World 
{
	private int _width;
	private int _height;
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
		_width = 5;
		_height = 5;
		_tiles = new int[_width][_height];
		
		for(int i = 0; i < _width; i++)
		{
			for(int j = 0; j < _height; j++)
			{
				_tiles[i][j] = 2;
			}
		}
	}
}
