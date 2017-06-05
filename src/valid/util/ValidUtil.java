package valid.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码
 * 1.设置参数，验证码图片的宽高，字符个数，混淆线个数
 * 2.设置画布，获取画笔，画边框-填充内部-画干扰线-画字符
 * 3.输出到屏幕，把验证码存到session中
 * 4.取消图片缓存
 * 5.关闭输出流
 * @author hutao
 *
 */
public class ValidUtil {
	
	private int width = 90; //宽度
	private int height = 20; //高度
	private int codeCount = 4; //个数
	private int lingCount = 4; //混淆线
	
	char[] codeSequence = {'A','B','C','D','E','F','G','H','I','J','K','L',
	                       'M','N','O','P','Q','R','S','T','U','V','W','X',
	                       'Y','Z','1','2','3','4','5','6','7','8','9'};
	
	/**
	 * 获取验证码
	 * @param time
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getCode(String time,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//定义随机数
		Random r = new Random();
		//存储验证码的类
		StringBuilder builderCode = new StringBuilder();
		//定义画布
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//得到画笔
		Graphics g = buffImg.getGraphics();
		//设置颜色，画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);
		//填充内部
		g.setColor(Color.white);
		g.fillRect(1,1,width-2,height-2);
		//设置干扰线
		g.setColor(Color.GRAY);
		for(int i=0;i<lingCount;i++){
			g.drawLine(r.nextInt(width), r.nextInt(width), r.nextInt(width), r.nextInt(width));
		}
		//设置验证码
		g.setColor(Color.green);
		//设置字体
		g.setFont(new Font("宋体", Font.BOLD|Font.ITALIC, 15));
		for(int i=0;i<codeCount;i++){
			char c = codeSequence[r.nextInt(codeSequence.length)];
			builderCode.append(c);
			g.drawString(c+"", 15*(i+1), 15);
		}
		//输出到屏幕
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg,"png", sos);
		//保存到session
		HttpSession session = request.getSession();
		session.setAttribute("codeValidate", builderCode.toString());
		System.out.println(builderCode.toString());
		//禁止图像缓存
		response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //关闭sos
        sos.close();
	}
	
}
