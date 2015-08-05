package tilegame;

import gfx.Assets;
import input.KeyManager;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import states.GameState;
import states.MenuState;
import states.State;
import display.Display;


public class Game implements Runnable 
{

	private Display _display;
	public int width, height;
	public String title;
	
	private boolean _running = false;
	private Thread _thread;
	
	private BufferStrategy _bs;
	private Graphics _g;
	
	//States
	private State _gameState;
	private State _menuState;
	
	//Input
	private KeyManager _keyManager;
	
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		_keyManager = new KeyManager();
	}
	
	private void init(){
		_display = new Display(title, width, height);
		_display.getFrame().addKeyListener(_keyManager);
		Assets.init();
		
		_gameState = new GameState(this);
		_menuState = new MenuState(this);
		State.setState(_gameState);
	}
	
	private void tick(){
		_keyManager.tick();
		
		if(State.getState() != null)
		{
			State.getState().tick();
		}
	}
	
	private void render(){
		_bs = _display.getCanvas().getBufferStrategy();
		if(_bs == null)
		{
			_display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		_g = _bs.getDrawGraphics();
		
		//Clear Screen
		_g.clearRect(0, 0, width, height);
		//Draw Here!
		
		if(State.getState() != null)
		{
			State.getState().render(_g);
		}
		
		//End Drawing!
		_bs.show();
		_g.dispose();
	}
	
	public void run()
	{
		
		init();
		
		int fps = 60;
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
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
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
