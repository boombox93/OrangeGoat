package gfx;
import java.awt.image.BufferedImage;

//Any image, sound or piece of music in our game
public class Assets 
{
	public static BufferedImage player, dirt, grass, stone, wood, brick;
	private static final int width = 128, height = 128;
	
	public static void init()
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/download.png"));
		SpriteSheet textures = new SpriteSheet(ImageLoader.loadImage("Textures/2vx226g.png"));
		
		player = sheet.crop(17 , 133, 33, 51);
		dirt = textures.crop(0, 0, width, height);
		grass = textures.crop(width, 0, width, height);
		stone = textures.crop(3*width, 0, width, height);
		wood = textures.crop(width, height, width, height);
		brick = textures.crop(2*width, height, width, height);
		
		
		
	}
}
