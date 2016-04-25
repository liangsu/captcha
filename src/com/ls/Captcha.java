package com.ls;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.AttributedString;

import javax.imageio.ImageIO;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.SimpleTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

public class Captcha {

	public static void main(String[] args) throws IOException {
		String randomWord = new String("0123456789abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ");
		RandomWordGenerator randomWordGenerator = new RandomWordGenerator(randomWord);
		
		RandomFontGenerator fontGenerator = new RandomFontGenerator(20, 20);
		
//		Color colorGray = new Color(200, 255, 200);
//		Color colorGreen = new Color(110, 120, 200);
//		GradientBackgroundGenerator background = new GradientBackgroundGenerator(62, 22, colorGray, colorGreen);
		
		CustomBackgroundGenerator background = new CustomBackgroundGenerator();
		
		Color colorFont = new Color(250, 250, 60);
		SimpleTextPaster textPaster = new SimpleTextPaster(4, 4, colorFont);
		
		ComposedWordToImage word2image = new ComposedWordToImage(fontGenerator, background, textPaster);
		
		GimpyFactory factory = new GimpyFactory(randomWordGenerator, word2image);
		GimpyFactory[] factories = new GimpyFactory[]{factory};
		GenericCaptchaEngine captchaEngine = new GenericCaptchaEngine(factories);
		
		GenericManageableCaptchaService captchaService = new GenericManageableCaptchaService(captchaEngine, 300, 20000, 20000);
		
		BufferedImage bufferedImage = (BufferedImage) captchaService.getChallengeForID("1234");
		writeImage(bufferedImage);
	}
	
	/**
	 * дͼƬ
	 * @param bufferedImage
	 * @throws IOException
	 */
	public static void writeImage(BufferedImage bufferedImage) throws IOException{
		ByteArrayOutputStream bs = new ByteArrayOutputStream();  
		ImageIO.createImageOutputStream(bs);
		ImageIO.write(bufferedImage, "png", bs);
		ByteArrayInputStream is = new ByteArrayInputStream(bs.toByteArray());
		
		OutputStream os = new FileOutputStream("C:\\Users\\warhorse\\Desktop\\a.png");
		int len = 0;
		byte[] buff = new byte[1024];
		while( (len = is.read(buff)) != -1){
			os.write(buff, 0, len); 
		}
		os.close();
		is.close();
	}
	
}
