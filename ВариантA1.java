package program;
/*@author StudentAltshulerIgor(ПМ-21(2))*/
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
class Vector
{
    int n;
    private final double[] координата;
    double длина=0;
    Vector(int n)
    {
        координата=new double[n];
        this.n=n;
    }
    double GetItem(int i)
    {
        return координата[i];
    }
    void SetItem(int i,double item)
    {
        координата[i]=item;
    }
    Vector inc()
    {
        Vector result=new Vector(n);
        for (int i=0; i<n; i++)
        {
            result.координата[i]=координата[i]+1;
        }
        return result;
    }
    Vector dec()
    {
        Vector result=new Vector(n);
        for (int i=0; i<n; i++)
        {
            result.координата[i]=координата[i]-1;
        }
        return result;
    }
    void Length()
    {
        for (int i=0; i<n; i++)
        {
            длина+=координата[i]*координата[i];
        }
        длина=Math.sqrt(длина);
    }
    Vector Addition(Vector v)
    {
        Vector result=new Vector(n);
        for (int i=0; i<n; i++)
        {
            result.координата[i]=координата[i]+v.координата[i];
        }
        return result;
    }
    Vector Substraction(Vector v)
    {
        Vector result=new Vector(n);
        for (int i=0; i<n; i++)
        {
            result.координата[i]=координата[i]-v.координата[i];
        }
        return result;
    } 
    double MultiplicationIntoScalar(Vector v)
    {
        double result=0;
        for (int i=0; i<n; i++)
        {
            result+=координата[i]*v.координата[i];
        }
        return result;
    }
    double Angle(double произведение,Vector v)
    {
        return Math.acos(произведение/(v.длина*длина));
    }
    @Override
    public String toString()
    {
        String res="(";
        for (int i=0; i<=n-2; i++)
        {
            res+=координата[i]+"; ";
        }
        res+=координата[n-1]+")";
        return res;
    }    
}
public class ВариантA1
{
    public static void main(String[] args)
    {
        try
        {
            int m,n;
            Vector[] вектор;
            try
            (
                FileReader числа=new FileReader("Input.txt");
                Scanner ввод=new Scanner(числа)
            )
            {
                m=ввод.nextInt();
                n=ввод.nextInt();
                if (n<1 || m<1)
                {
                    System.exit(1);
                }
                ввод.nextLine();
                вектор=new Vector[m];
                for (int j=0; j<m; j++)
                {
                    вектор[j]=new Vector(n);
                    for (int i=0; i<=n-2; i++)
                    {
                        вектор[j].SetItem(i,Double.parseDouble(ввод.next()));
                    }
                    вектор[j].SetItem(n-1,Double.parseDouble(ввод.nextLine()));
                }
            }
            out.println("Операции над векторами.");
            out.println("Унарные:");
            out.println("Индексирование:");
            Random index=new Random();
            for (int j=0; j<m; j++)
            {
                int[] выбор=new int[n];
                for (int k=0; k<n; k++)
                {
                    выбор[k]=k;
                }
                for (int k=n; k>0; k--)
                {
                    int индекс=index.nextInt(k);
                    out.println("["+j+"]"+"("+выбор[индекс]+")"+"="+вектор[j].GetItem(выбор[индекс]));
                    for (int i=индекс+1; i<k; i++)
                    {
                        выбор[i-1]=выбор[i];
                    }
                }
            }
            out.println("Инкремент:");
            for (int j=0; j<m; j++)
            {
                out.println("["+j+"]++ ="+вектор[j].inc());
            }
            out.println("Декремент:");
            for (int j=0; j<m; j++)
            {
                out.println("["+j+"]-- ="+вектор[j].dec());
            }
            out.println("Длина:");
            for (int j=0; j<m; j++)
            {
                вектор[j].Length();
                out.println("|"+j+"|="+вектор[j].длина);
            }
            out.println("Бинарные:");
            out.println("Сложение:");
            for (int j=0; j<m; j++)
            {
                for (int k=j; k<m; k++)
                {
                    out.println("["+j+"]"+"+"+"["+k+"]"+"="+вектор[j].Addition(вектор[k]));
                }
            }
            out.println("Вычитание:");
            for (int j=0; j<=m-2; j++)
            {
                for (int k=j+1; k<m; k++)
                {
                    out.println("["+j+"]"+"-"+"["+k+"]"+"="+вектор[j].Substraction(вектор[k]));
                }
            }
            double[][] угол=new double[m-1][];
            out.println("Скалярное произведение:");
            for (int j=0; j<=m-2; j++)
            {
                угол[j]=new double[m-j-1];
                for (int k=j+1; k<m; k++)
                {
                    double mult=вектор[j].MultiplicationIntoScalar(вектор[k]);
                    out.println("["+j+"]"+"*"+"["+k+"]"+"="+mult);
                    угол[j][m-k-1]=вектор[j].Angle(mult,вектор[k]);
                }
            }
            out.println("Угол:");
            for (int j=0; j<=m-2; j++)
            {
                for (int k=j+1; k<m; k++)
                {
                    out.println("["+j+"]"+"^"+"["+k+"]"+"="+угол[j][m-k-1]);
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(ВариантA1.class.getName()).log(Level.SEVERE,null,ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ВариантA1.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}