package module.user.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 随机图片生成
 */
@WebServlet("/codeGenerateServlet.do")
public class CodeGenerateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer WIDTH = 160;
	private Integer HEIGHT = 30;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 创建一个图片区域
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2. 获得操作的对象
		Graphics2D g = image.createGraphics();
		// 3. 设置背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 4. 设置边框
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, WIDTH-2, 	HEIGHT-2);
		// 5. 画干扰线
		g.setColor(Color.BLACK);
		for(int i = 0; i < 20; i ++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
		// 6. 写随机数
		String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int x = 5;
		StringBuilder sbl = new StringBuilder();
		for(int i = 0; i < 4; i ++) {
			g.setColor(Color.red);
			g.setFont(new Font("", Font.BOLD, 20));
			// 设置旋转的弧度
			int degree = new Random().nextInt() % 30;
			// 随机取一个值
			String ch = str.charAt(new Random().nextInt(str.length()))+"";
			sbl.append(ch);
			// 旋转
			g.rotate(degree*Math.PI/180, x, 20);
			// 将字符打印上去
			g.drawString(ch, x, 20);
			// 反旋转回来
			g.rotate(-degree*Math.PI/180, x, 20);
			x += 30;
		}
		response.setContentType("image/jpeg;charset=UTF-8");
		// 记录生成的验证码，在登录和注册时验证
		HttpSession session = request.getSession(false);
		session.setAttribute("code", sbl.toString());
		// 告诉浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-catch");
		response.setHeader("Pragma", "no-catch");
		// 图片写给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
