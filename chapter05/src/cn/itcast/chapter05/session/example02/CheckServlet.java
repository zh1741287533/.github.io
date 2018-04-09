 package cn.itcast.chapter05.session.example02;
 import java.io.*;

 import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 import java.awt.*;
 import java.awt.image.*;
 import javax.imageio.ImageIO;
 @WebServlet("/CheckServlet")
 public class CheckServlet extends HttpServlet
 {
 	private static int WIDTH = 60; //楠岃瘉鐮佸浘鐗囧搴�
 	private static int HEIGHT = 20; //楠岃瘉鐮佸浘鐗囬珮搴�
 public void doGet(HttpServletRequest request,HttpServletResponse response) 
 			throws ServletException,IOException{		
 		HttpSession session = request.getSession();
 		response.setContentType("image/jpeg");
 		ServletOutputStream sos = response.getOutputStream();
 		//璁剧疆娴忚鍣ㄤ笉瑕佺紦瀛樻鍥剧墖
 		response.setHeader("Pragma","No-cache");
 		response.setHeader("Cache-Control","no-cache");
 		response.setDateHeader("Expires", 0);
 		//鍒涘缓鍐呭瓨鍥捐薄骞惰幏寰楀叾鍥惧舰涓婁笅鏂�
 		BufferedImage image = 
 			new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
 		Graphics g = image.getGraphics();
 		//浜х敓闅忔満鐨勮璇佺爜
 		char [] rands = generateCheckCode();
 		//浜х敓鍥惧儚
 		drawBackground(g);
 		drawRands(g,rands);
 		//缁撴潫鍥惧儚鐨勭粯鍒惰繃绋嬶紝瀹屾垚鍥惧儚
 		g.dispose();
 		//灏嗗浘鍍忚緭鍑哄埌瀹㈡埛绔�
 		ByteArrayOutputStream bos = new ByteArrayOutputStream();
 		ImageIO.write(image, "JPEG", bos);
 		byte [] buf = bos.toByteArray();
 		response.setContentLength(buf.length);
 		//涓嬮潰鐨勮鍙ヤ篃鍙啓鎴愶細bos.writeTo(sos);
 		sos.write(buf);
 		bos.close();
 		sos.close();
 		//灏嗗綋鍓嶉獙璇佺爜瀛樺叆鍒癝ession涓�
 		session.setAttribute("check_code",new String(rands));
 		//鐩存帴浣跨敤涓嬮潰鐨勪唬鐮佸皢鏈夐棶棰橈紝Session瀵硅薄蹇呴』鍦ㄦ彁浜ゅ搷搴斿墠鑾峰緱
 	//request.getSession().setAttribute("check_code",new String(rands));
 	}
        //鐢熸垚涓�涓�4瀛楃鐨勯獙璇佺爜
 	private char [] generateCheckCode()
 	{
 		//瀹氫箟楠岃瘉鐮佺殑瀛楃琛�
 		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
 		char [] rands = new char[4];
 		for(int i=0; i<4; i++)
 		{
 			int rand = (int)(Math.random() * 36);
 			rands[i] = chars.charAt(rand);
 		}
 		return rands;
 	}
 	private void drawRands(Graphics g , char [] rands)
 	{
 		g.setColor(Color.BLACK);
 		g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));
 		//鍦ㄤ笉鍚岀殑楂樺害涓婅緭鍑洪獙璇佺爜鐨勬瘡涓瓧绗�		
 		g.drawString("" + rands[0],1,17);
 		g.drawString("" + rands[1],16,15);
 		g.drawString("" + rands[2],31,18);
 		g.drawString("" + rands[3],46,16);
 		System.out.println(rands);
 	}
 	private void drawBackground(Graphics g)
 	{
  		//鐢昏儗鏅�
 		g.setColor(new Color(0xDCDCDC));
 		g.fillRect(0, 0, WIDTH, HEIGHT);
 		//闅忔満浜х敓120涓共鎵扮偣
 		for(int i=0; i<120; i++)
 		{
 			int x = (int)(Math.random() * WIDTH);
 			int y = (int)(Math.random() * HEIGHT);
 			int red = (int)(Math.random() * 255);
 			int green = (int)(Math.random() * 255);
 			int blue = (int)(Math.random() * 255);
 			g.setColor(new Color(red,green,blue));		
 			g.drawOval(x,y,1,0);
 		}
 	}
 }
