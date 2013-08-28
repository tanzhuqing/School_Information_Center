package cn.heu.hmps.util.identifycode;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class CheckNumberServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	// private static final String CONTENT_TYPE = "text/html; charset=GBK";
	/*private static int WIDTH = 93;*/
	private static int WIDTH = 63;
	private static int HEIGHT = 25;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// response.setContentType(CONTENT_TYPE);
		response.setContentType("image/jpeg");
		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "on-cache");
		response.setDateHeader("Expires", 0);
		// 获取流对象向客户端写入图片
		ServletOutputStream sos = response.getOutputStream();

		// 在内存中创建一副图片,宽60像素,高20像素,图片背景色为黑色
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 从图片中取得图形上下文,g相当于是一只画笔
		Graphics g = bufferedImage.getGraphics();

		g.setFont(new Font(null, Font.ITALIC, 18));
//		String value = this.getCheckCode().toUpperCase();
		String value = this.getCheckCode().toLowerCase();
		// 将验证码放入session
		request.getSession().removeAttribute("checkNumber");
		request.getSession().setAttribute("checkNumber", value);

		// 绘制背景,加上干扰点
		this.drawBackgound(g);
		// 绘制验证码
		this.drawRands(g, value);
		// 结束图像绘制过程,完成图像
		g.dispose();

		// 将内存中的图像编码成jpeg格式的图片写入到流对象中
		javax.imageio.ImageIO.write(bufferedImage, "JPEG", sos);
		sos.close();
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

	/**
	 * 取得验证码
	 * 
	 * @return String
	 */
	private String getCheckCode()
	{
		// 定义验证码的字符表
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 4; i++)
		{
			int rand = r.nextInt(36);
			sb.append(chars.charAt(rand));
		}

		return sb.toString();
	}

	/**
	 * 将产生的验证码绘制到图片上
	 * 
	 * @param g
	 *            Graphics
	 * @param value
	 *            String
	 */
	private void drawRands(Graphics g, String value)
	{
		// 画笔为黑色
		g.setColor(Color.BLACK);
		// 字体加粗斜体
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
		// 在不同的高度上输出验证码的每个字符
		Random r = new Random();
		g.drawString("" + value.charAt(0), 1, 17);
		g.drawString("" + value.charAt(1), 16, 15);
		g.drawString("" + value.charAt(2), 31, 18);
		g.drawString("" + value.charAt(3), 46, 16);
		/*g.drawString("" + value.charAt(4), 61, 15);
		g.drawString("" + value.charAt(5), 76, 18);*/
	}

	private void drawBackgound(Graphics g)
	{
		// 画背景,0x(数字零)表示16进制,DCDCDC表示颜色
		g.setColor(new Color(0xDCDCDC));
		// 给图片填充颜色
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 随机产生120个干扰点
		for (int i = 0; i < 120; i++)
		{
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			// 绘制干扰点
			g.drawOval(x, y, 1, 0);
		}
	}
}
