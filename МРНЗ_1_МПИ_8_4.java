package program;
/*@author StudentAltshulerIgor(ПМ-31(2))*/
public class МРНЗ_1_МПИ_8_4
{
    private static final int M=32;
    private static final double H=1.0/M;
    private static double s,t=0;
    private static final double[] Y=new double[M+1];
    static double[] A=new double[M+1];
    static double[][] x=new double[M+1][M+1];
    public static void main(String[] args)
    {
        System.out.print(t+" "+y(t)+" "+x(t)+" ");
        int n=0;
        Norm2(n);
        Norm3(n);
        Norm1();
        t=0;
        for (int i=1; i<=M; i++)
        {
            t+=H;
            Y[i]=(int)(y(t)*1000+0.5)*0.001;
        }
        double α=0.8,ε=0.0015;
        do
        {
            n++;
            /*if (n%2==1)
            {
                α=4.4;
            }
            else
            {
                α=0.8;
            }*/
            t=0;
            for (int i=1; i<=M; i++)
            {
                t+=H;
                s=0;
                A[i]=0;
                for (int j=1; j<=M; j++)
                {
                    s+=H;
                    A[i]+=K(t,s)*x[n-1][j];
                }
                A[i]*=H;
                x[n][i]=x[n-1][i]+α*(Y[i]-A[i]);
            }
            t=H*n;
            System.out.print(t+" "+y(t)+" "+x(t)+" ");
        }
        while (Norm2(n)>0.001 && Norm3(n)>0.001 && Norm1()>ε);
        System.out.println();
        System.out.println(""+n);
        for (int i=0; i<=M; i++)
        {
            System.out.println(""+x[n][i]);
        }
    }
    static double x(double t)
    {
        return t*(1-t);
    }
    static double y(double t)
    {
        return t*(t-1)*(t*(t-1)-1)/12.0;
    }
    static double K(double t,double s)
    {
        if (s<t)
        {
            return s*(1-t);
        }
        else
        {
            return t*(1-s);
        }
    }
    static double Norm1()
    {
        double norm=0;
        for (int i=1; i<=M; i++)
        {
            norm+=(A[i]-Y[i])*(A[i]-Y[i]);
        }
        norm=Math.sqrt(norm*H);
        System.out.println(""+norm);
        return norm;
    }
    static double Norm2(int n)
    {
        double norm=0;
        for (int i=1; i<=M; i++)
        {
            norm+=x[n][i]*x[n][i];
        }
        norm=Math.sqrt(norm*H);
        System.out.print(" "+norm);
        return norm;
    }
    static double Norm3(int n)
    {
        double norm=0;
        t=0;
        for (int i=1; i<=M; i++)
        {
            t+=H;
            norm+=(x(t)-x[n][i])*(x(t)-x[n][i]);
        }
        norm=Math.sqrt(norm*H);
        System.out.println(" "+norm);
        return norm;
    }
}