
//This is the main class
public class Game 
{
	
	private Display display;
	private int _width;
	private int _height;
	
	//Constructor
	public Game(String title, int width, int height)
	{
		display = new Display(title, width, height);
		_width = width;
		_height = height;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	public void setWidth(int width)
	{
		//*******SHOULD NOT NEED TO USE THIS*******
		_width = width;
	}
	
	public void setHeight(int height)
	{
		//*******SHOULD NOT NEED TO USE THIS*******
		_height = height;
	}
}
