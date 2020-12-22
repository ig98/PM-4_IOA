package program;
/*@author StudentAltshulerIgor(ПМ-41(2))*/
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Расчёт_характеристик_и_анимация_движения_шара_в_воздухе extends javax.swing.JFrame
{
    private static Integer D=null,ρ=null,v_0=null,α=null;
    public Расчёт_характеристик_и_анимация_движения_шара_в_воздухе()
    {
        initComponents();
    }
    @Override
    public void paint(Graphics animation)
    {
        animation.clearRect(9,36,1169,936);
        animation.setColor(Color.green);
        animation.fillPolygon(new int[]{9,1069,1169,109},new int[]{936,936,836,836},4);
        animation.setColor(Color.lightGray);
        int x0=99,y0=886;
        animation.drawLine(59,y0,1119,y0);
        animation.setColor(Color.darkGray);
        animation.fillOval(x0,874,12,12);
        System.out.println("Ускорение свободного падения g=9,806 м/с^2;");
        System.out.println("введите по одному в строке 4 натуральных числа:");
        System.out.println("размер тела - диаметр шара (в см),");
        System.out.println("плотность тела - плотность шара (в кг/м^3),");
        System.out.println("начальная скорость V - модуль скорости (в см/с) движения шара,");
        System.out.println("острый угол α - угол (в градусах) между направлением бросания шара и горизонтом.");
        while (D==null || ρ==null || v_0==null || α==null)
        {
            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Расчёт_характеристик_и_анимация_движения_шара_в_воздухе.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        final double g=980.6,k=1.0,m=D*D*D*(Math.PI/6.0E6)*ρ,a=α/180.0*Math.PI;
        double Y=0.0,v_0x=Math.cos(a)*v_0,T=0.0;
        int X=0;
        do
        {
            animation.setColor(new Color(120,42,14));
            int current_x=x0+X,current_y=874-(int)(Y+0.5);
            animation.fillOval(current_x,current_y,12,12);
            X++;
            double t=-Math.log1p(-X*k/(m*v_0x))*m/k;
            T=(t-T)*1.0E9;
            Y=m*g*(X/v_0x-t)/k+Math.tan(a)*X;
            int next_x=current_x+1,next_y=874-(int)(Y+0.5);
            animation.drawLine(current_x+6,current_y+6,next_x+6,next_y+6);
            animation.setColor(Color.darkGray);
            try 
            {
                Thread.sleep((int)T/1000000,(int)T%1000000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Расчёт_характеристик_и_анимация_движения_шара_в_воздухе.class.getName()).log(Level.SEVERE,null,ex);
            }
            animation.fillOval(next_x,next_y,12,12);
            T=t;
        }
        while(Y>0.0);
        System.out.println("Горизонтальная дальность полёта шара - "+X+" см,");
        System.out.println("время его полёта - "+T+" с.");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) throws IOException
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Расчёт_характеристик_и_анимация_движения_шара_в_воздухе.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
        }
        java.awt.EventQueue.invokeLater
        (
            ()->
            {
                new Расчёт_характеристик_и_анимация_движения_шара_в_воздухе().setVisible(true);
            }
        );
        try (BufferedReader ввод=new BufferedReader(new InputStreamReader(System.in)))
        {
            D=Integer.parseInt(ввод.readLine());
            ρ=Integer.parseInt(ввод.readLine());
            v_0=Integer.parseInt(ввод.readLine());
            α=Integer.parseInt(ввод.readLine());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}