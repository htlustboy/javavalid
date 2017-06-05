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
 * ��֤��
 * 1.���ò�������֤��ͼƬ�Ŀ�ߣ��ַ������������߸���
 * 2.���û�������ȡ���ʣ����߿�-����ڲ�-��������-���ַ�
 * 3.�������Ļ������֤��浽session��
 * 4.ȡ��ͼƬ����
 * 5.�ر������
 * @author hutao
 *
 */
public class ValidUtil {
	
	private int width = 90; //���
	private int height = 20; //�߶�
	private int codeCount = 4; //����
	private int lingCount = 4; //������
	
	char[] codeSequence = {'A','B','C','D','E','F','G','H','I','J','K','L',
	                       'M','N','O','P','Q','R','S','T','U','V','W','X',
	                       'Y','Z','1','2','3','4','5','6','7','8','9'};
	
	/**
	 * ��ȡ��֤��
	 * @param time
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getCode(String time,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//���������
		Random r = new Random();
		//�洢��֤�����
		StringBuilder builderCode = new StringBuilder();
		//���廭��
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//�õ�����
		Graphics g = buffImg.getGraphics();
		//������ɫ�����߿�
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);
		//����ڲ�
		g.setColor(Color.white);
		g.fillRect(1,1,width-2,height-2);
		//���ø�����
		g.setColor(Color.GRAY);
		for(int i=0;i<lingCount;i++){
			g.drawLine(r.nextInt(width), r.nextInt(width), r.nextInt(width), r.nextInt(width));
		}
		//������֤��
		g.setColor(Color.green);
		//��������
		g.setFont(new Font("����", Font.BOLD|Font.ITALIC, 15));
		for(int i=0;i<codeCount;i++){
			char c = codeSequence[r.nextInt(codeSequence.length)];
			builderCode.append(c);
			g.drawString(c+"", 15*(i+1), 15);
		}
		//�������Ļ
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg,"png", sos);
		//���浽session
		HttpSession session = request.getSession();
		session.setAttribute("codeValidate", builderCode.toString());
		System.out.println(builderCode.toString());
		//��ֹͼ�񻺴�
		response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //�ر�sos
        sos.close();
	}
	
}
