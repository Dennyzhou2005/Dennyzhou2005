package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class GameObject {
    private Image image;
    private double x, y;
    protected int diameter;

    public GameObject(String imagePath, int diameter) {
        this.image = new ImageIcon(imagePath).getImage();
        this.diameter = diameter;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //启用抗锯齿渲染提示，使得绘制图像更平滑。


        int drawX = (int) (x - diameter / 2);
        int drawY = (int) (y - diameter / 2);

        // 创建一个圆形的剪裁区域
        Shape clip = new Ellipse2D.Double(drawX, drawY, diameter, diameter);
        g2d.setClip(clip);
        //setClip将Graphics2D的剪裁区域设置为这个圆形。

        // 在剪裁区域内绘制图像
        g2d.drawImage(image, drawX, drawY, diameter, diameter, null);

        // 如果你需要在圆形周围绘制一个边框，取消注释以下代码
        // g2d.setColor(Color.BLACK);
        // g2d.drawOval(drawX, drawY, diameter, diameter);
    }

    // getter....setter

    public boolean isCollision(GameObject object1, GameObject object2) {
        return false;
    }
}
