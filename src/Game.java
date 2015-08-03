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
	
	private Thread _thread; 
	
	//States
	private State gameState;
	@SuppressWarnings("unused")
	private State menuState;
	
	//Input
	private KeyManager _keyManager;
	
	//Constructor
	public Game(String title, int width, int height)
	{
		_width = width;
		_height = height;
		_title = title;
		_keyManager = new KeyManager();
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
		_display.getFrame().addKeyListener(_keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}
	
	private void tick()
	{
		_keyManager.tick();
		
		if(State.getState() != null)
		{
			State.getState().tick();
		}
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
		
		//Clear screen
		_g.clearRect(0, 0, _width, _height);
		
		//Draw
		
		if(State.getState() != null)
		{
			State.getState().render(_g);
		}
		
		/*
		 * _g.drawImage(Assets.grass, 0, 0, null);
		_g.drawImage(Assets.wood, 128, 0, null);
		_g.drawImage(Assets.brick, 0, 128, null);
		_g.drawImage(Assets.stone, 128, 128, null);
		_g.drawImage(Assets.dirt, 256, 0, null);
		_g.drawImage(Assets.dirt, 256, 128, null);
		_g.drawImage(Assets.player, 5, 5, null);
		 */
		
		//End draw
		_bs.show();
		_g.dispose();
	}
	
	@Override
	public void run() 
	{
		init();
		
		int fps = 60;
		
		//1 billion nanoseconds divided by 60 seconds
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(_running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			//If delta is greater than or equal to 1, we have to tick and render to achieve 60 fps
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			//1 second
			if(timer >= 1000000000)
			{
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager()
	{
		return _keyManager;
	}
	
	public synchronized void start()
	{
		if(_running)
		{
			return;
		}
		_running = true;
		_thread = new Thread(this);
		//Calls run method above
		_thread.start();
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
			_thread.join();
		} 
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
	}
}
