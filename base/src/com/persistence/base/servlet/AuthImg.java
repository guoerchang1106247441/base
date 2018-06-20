package com.persistence.base.servlet;

import com.persistence.base.tool.Constains;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthImg extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private int width = 127;
	private int height = 36;
	private int codeCount = 6;
	private int x = 0;
	private int fontHeight;
	private int codeY;

	public void init()throws ServletException{
		String strWidth = getInitParameter("width");
		String strHeight = getInitParameter("height");
		String strCodeCount = getInitParameter("codeCount");
		try{
			if ((strWidth != null) && (strWidth.length() != 0)) {
				this.width = Integer.parseInt(strWidth);
			}
			if ((strHeight != null) && (strHeight.length() != 0)) {
				this.height = Integer.parseInt(strHeight);
			}
			if ((strCodeCount != null) && (strCodeCount.length() != 0)) {
				this.codeCount = Integer.parseInt(strCodeCount);
			}
		}catch (NumberFormatException e) {}
		this.x = (this.width / this.codeCount);
		this.fontHeight = (this.height - 2);
		this.codeY = (this.height - 2);
	}
  
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		BufferedImage buffImg = new BufferedImage(this.width, this.height, 1);

		Graphics2D g = buffImg.createGraphics();
		Random random = new Random();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.width, this.height);
    
		Font font = new Font("Fixedsys", 0, this.fontHeight);
		g.setFont(font);
		g.setColor(Color.gray);
		g.drawRect(0, 0, this.width - 1, this.height - 1);
		g.setColor(Color.gray);
		for (int i = 0; i < 15; i++){
			int x = random.nextInt(this.width);
			int y = random.nextInt(this.height);
			int xl = random.nextInt(8);
			int yl = random.nextInt(8);
			g.drawLine(x, y, x + xl, y + yl);
		}
		StringBuffer randomCode = new StringBuffer();
		int red = 0;int green = 0;int blue = 0;

		int[] number = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		char[] operator = { '+', '×' };

		int num1 = number[((int)(number.length * java.lang.Math.random()))];
		int num2 = number[((int)(number.length * java.lang.Math.random()))];
		char oper = operator[((int)(operator.length * java.lang.Math.random()))];
		int result;
		switch (oper){
			case '+': 
				result = num1 + num2; break;
			case '×': 
				if (num1 == 0) {
					do{
						num1 = number[((int)(number.length * java.lang.Math.random()))];
					} while (num1 == 0);
				}
				result = num1 * num2;
				break;
			default: 
				result = -1;
		}
		String expression = Integer.toString(num1) + Character.toString(oper) + "?=" + Integer.toString(result);
    
		red = random.nextInt(255);
		green = random.nextInt(255);
		blue = random.nextInt(255);
		g.setColor(new Color(red, green, blue));
		g.drawString(expression, 1 * this.x - 10, this.codeY);
    
		randomCode.append(num2);
		HttpSession session = req.getSession();
		session.setAttribute(Constains.LOGIN_RANDOM, randomCode.toString());
    
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0L);
		resp.setContentType("image/jpeg");
	    
		ServletOutputStream sos = resp.getOutputStream();
    

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(buffImg);
    
		sos.close();
	}
}



