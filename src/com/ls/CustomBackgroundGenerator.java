package com.ls;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

import javax.imageio.ImageIO;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;

public class CustomBackgroundGenerator implements BackgroundGenerator{
	
	private int imageHeight;
	private int imageWidth;
	
	public CustomBackgroundGenerator() {
		this(62, 22);
	}
	
	public CustomBackgroundGenerator(int imageHeight, int imageWidth) {
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
	}

	@Override
	public BufferedImage getBackground() {
		BufferedImage bufferedImage = null;
		
		try {
			//1.获取背景图片文件
			Random r = new Random();
			int i = r.nextInt(59);
			String name = "captcha_bg_"+i+".jpg";
			
			String filePath = this.getClass().getClassLoader().getResource("com/ls/captcha/"+name).getPath();
			filePath = URLDecoder.decode(filePath,"utf-8");
			
			File imageFile = new File(filePath);
			bufferedImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bufferedImage;
	}

	@Override
	public int getImageHeight() {
		return imageHeight;
	}

	@Override
	public int getImageWidth() {
		return imageWidth;
	}

//	public static void main(String[] args) {
//		CustomBackgroundGenerator g = new CustomBackgroundGenerator();
//		g.getBackground();
//	}
}
