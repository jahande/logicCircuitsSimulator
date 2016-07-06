package ir.sharif.ce.javaClass.logicCircuit.utils;

import static java.lang.Math.random;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class Utils {
	
	private Utils(){
	}
	public static Color getRandomColor()
	{
		return new Color((int)(random()*255),(int)(random()*255),(int)(random()*255));
	}
	public static Font getResizedFont(Font f,  String text,int width,int height) {
		int sizeW =  (int)(width * 1.8 / text.length());
		int sizeH = (int)(height*1.9);
		if(sizeH<sizeW){
			sizeW=sizeH;
		}
		if(sizeW>50)
		{sizeW=50;}
		return new Font(f.getFontName(),f.getStyle(),sizeW);
	}
}
