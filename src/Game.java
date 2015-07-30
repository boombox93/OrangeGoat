import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


//This is the main class
public class Game implements Runnable
{
	
	private Display _display;
	private String _title;
	private int _width;
	private int _height;
	private boolean _running = false;
	private BufferStrategy _bs;
	private Graphics _g;
	
	private Thread thread; 
	
	//Constructor
	public Game(String title, int width, int height)
	{
		_width = width;
		_height = height;
		_title = title;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	public String getTitle()
	{
		return _title;
	}
	
	public void setTitle(String title)
	{
		//*******SHOULD NOT NEED TO USE THIS*******
		_title = title;
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

	private void init()
	{
		_display = new Display(_title, _width, _height);
	}
	
	private void tick()
	{
		
	}
	
	//Draws to window
	private void render()
	{
		_bs = _display.getCanvas().getBufferStrategy();
		if(_bs == null)
		{
			_display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		_g = _bs.getDrawGraphics();
		
		//Draws rectangle on screen(x, y, width, height)
		//_g.fillRect(0, 0, _width, _height);
		
		//Clear screen
		_g.clearRect(0, 0, _width, _height);
		
		//Draw
		
		//_g.drawRect(10, 50, 50, 50);
		_g.setColor(Color.red);
		_g.fillRect(10, 50, 50, 50);
		
		//End draw
		_bs.show();
		_g.dispose();
	}
	
	@Override
	public void run() 
	{
		init();
		
		while(_running)
		{
			tick();
			render();
		}
		
		stop();
		
	}
	
	public synchronized void start()
	{
		if(_running)
		{
			return;
		}
		_running = true;
		thread = new Thread(this);
		//Calls run method above
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!_running)
		{
			return;
		}
		
		_running = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
	}
}
