package com.member.service.frame.core.sys;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ImageCodeMakerServlet extends HttpServlet {

	/************************���˼ά��λ���ֵ���֤��?***********************************************/
//	public ImageCodeMakerServlet() {
//		super();
//	}
//
//	public void destroy() {
//		super.destroy(); 
//	}
//
//	public void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// ��������ҳ�治����
//		response.setHeader("Pragma", "No-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//
//		// ����ͼƬ�Ŀ�Ⱥ͸߶�?
//		int width = 90, height = 40;
//		// ����һ��ͼ�����?
//		BufferedImage image = new BufferedImage(width, height,
//				BufferedImage.TYPE_INT_RGB);
//		// �õ�ͼ��Ļ�������?
//		Graphics g = image.createGraphics();
//
//		Random random = new Random();
//		// �������ɫ���ͼ�񱳾�
//		g.setColor(getRandColor(180, 250));
//		g.fillRect(0, 0, width, height);
//		for (int i = 0; i < 5; i++) {
//			g.setColor(getRandColor(50, 100));
//			int x = random.nextInt(width);
//			int y = random.nextInt(height);
//			g.drawOval(x, y, 4, 4);
//		}
//		// �������壬����׼���������?
//		g.setFont(new Font("", Font.PLAIN, 40));
//		// ������ַ�?
//		String sRand = "";
//		for (int i = 0; i < 4; i++) {
//			// ����ĸ������ַ�?
//			String rand = String.valueOf(random.nextInt(10));
//			sRand += rand;
//			// ��������ɫ
//			g.setColor(new Color(20 + random.nextInt(80), 20 + random
//					.nextInt(100), 20 + random.nextInt(90)));
//			// ��������ֻ���ͼ����?
//			g.drawString(rand, (17 + random.nextInt(3)) * i + 8, 34);
//
//			// ��ɸ�����?
//			for (int k = 0; k < 12; k++) {
//				int x = random.nextInt(width);
//				int y = random.nextInt(height);
//				int xl = random.nextInt(9);
//				int yl = random.nextInt(9);
//				g.drawLine(x, y, x + xl, y + yl);
//			}
//		}
//
//		// ����ɵ���������ַ�д��Session
//		request.getSession(false).setAttribute("randCode", sRand);
//		// ʹͼ����Ч
//		g.dispose();
//		// ���ͼ��ҳ��?
//		ImageIO.write(image, "JPEG", response.getOutputStream());
//	}
//
//	/**
//	 * Initialization of the servlet. <br>
//	 * 
//	 * @throws ServletException
//	 *             if an error occurs
//	 */
//	public void init() throws ServletException {
//		// Put your code here
//	}
//	/**
//	 * ����һ��������ɫ
//	 * 
//	 * @param fc
//	 *            ��ɫ������Сֵ
//	 * @param bc
//	 *            ��ɫ��������?
//	 * @return
//	 */
//	public Color getRandColor(int fc, int bc) {
//		Random random = new Random();
//		if (fc > 255){
//			fc = 255;
//		}
//		if (bc > 255){
//			bc = 255;
//		}
//		int r = fc + random.nextInt(bc - fc);
//		int g = fc + random.nextInt(bc - fc);
//		int b = fc + random.nextInt(bc - fc);
//		return new Color(r, g, b);
//	}
//
	/************************���˼ά��λ���ֺ���ĸ����֤��?***********************************************/
	//����ͼ����֤���л����ַ������?
	  private final Font mFont =
	    new Font("Arial Black", Font.PLAIN, 16);
	  //����ͼ����֤��Ĵ��?
	  private final int IMG_WIDTH = 80;
	  private final int IMG_HEIGTH = 18;
	  //����һ����ȡ�����ɫ�ķ���?
	  private Color getRandColor(int fc,int bc)
	  {
	    Random random = new Random();
	    if(fc > 255) fc = 255;
	    if(bc > 255) bc=255;
	    int r = fc + random.nextInt(bc - fc);
	    int g = fc + random.nextInt(bc - fc);
	    int b = fc + random.nextInt(bc - fc);
	    //�õ�������?
	    return new Color(r , g , b);
	  }
	  //��дservice��������ɶԿͻ��˵����?
	  public void service(HttpServletRequest request,
	    HttpServletResponse response) 
	    throws ServletException, IOException
	  {
	    //���ý�ֹ����
	    response.setHeader("Pragma","No-cache");
	    response.setHeader("Cache-Control","no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");
	    BufferedImage image = new BufferedImage
	      (IMG_WIDTH , IMG_HEIGTH , BufferedImage.TYPE_INT_RGB);
	    Graphics g = image.getGraphics();
	    Random random = new Random();
	    g.setColor(getRandColor(200 , 250));
	    //��䱳���?
	    g.fillRect(1, 1, IMG_WIDTH - 1, IMG_HEIGTH - 1);
	    //Ϊͼ����֤����Ʊ߿�?
	    g.setColor(new Color(102 , 102 , 102));
	    g.drawRect(0, 0, IMG_WIDTH - 1, IMG_HEIGTH - 1);
	    g.setColor(getRandColor(160,200));
	    //�����������?
	    for (int i = 0 ; i < 80 ; i++)
	    {
	      int x = random.nextInt(IMG_WIDTH - 1);
	      int y = random.nextInt(IMG_HEIGTH - 1);
	      int xl = random.nextInt(6) + 1;
	      int yl = random.nextInt(12) + 1;
	      g.drawLine(x , y , x + xl , y + yl);
	    }
	    g.setColor(getRandColor(160,200));
	    //�����������?
	    for (int i = 0 ; i < 80 ; i++)
	    {
	      int x = random.nextInt(IMG_WIDTH - 1);
	      int y = random.nextInt(IMG_HEIGTH - 1);
	      int xl = random.nextInt(12) + 1;
	      int yl = random.nextInt(6) + 1;
	      g.drawLine(x , y , x - xl , y - yl);
	    }
	    //���û����ַ������?
	    g.setFont(mFont);
	    //���ڱ���ϵͳ��ɵ�����ַ�
	    String sRand = "";
	    for (int i = 0 ; i < 4 ; i++)
	    {
	      String tmp = getRandomChar();
	      sRand += tmp;
	      //��ȡ������?
	      g.setColor(new Color(20 + random.nextInt(110)
	        ,20 + random.nextInt(110)
	        ,20 + random.nextInt(110)));
	      //��ͼƬ�ϻ���ϵͳ��ɵ�����ַ�
	      g.drawString(tmp , 15 * i + 10,15);
	    }
	    //��ȡHttpSesssion����
	    HttpSession session = request.getSession(true);
	    //������ַ����HttpSesssion������ 
	    session.setAttribute("randCode" , sRand);
	    g.dispose();
	    //������������ͼƬ
	    ImageIO.write(image, "JPEG", response.getOutputStream());
	  }
	  //�����ȡ����ַ���
	  private String getRandomChar()
	  {
	    //���һ��?��1��2���������?
	    int rand = (int)Math.round(Math.random() * 2);
	    long itmp = 0;
	    char ctmp = '\u0000';
	    switch (rand)
	    {
	      //��ɴ�д���?
	      case 1:
	        itmp = Math.round(Math.random() * 25 + 65);
	        ctmp = (char)itmp;
	        //����ִ�С�?
	        //return String.valueOf(ctmp);
	        //��ִ�С�?
	        return String.valueOf(ctmp).toLowerCase();
	      //���Сд���?
	      case 2:
	        itmp = Math.round(Math.random() * 25 + 97);
	        ctmp = (char)itmp;
	        return String.valueOf(ctmp);
	      //�������?
	      default :
	        itmp = Math.round(Math.random() * 9);
	        return  itmp + "";
	    }
	  }

}