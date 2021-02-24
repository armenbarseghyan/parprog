package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

import java.time.Instant;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public void testApp()
    {

        FunctionInterface f = new Function(new double[][]{{1,0}, {0,1}});
        GradientDescent descentMinimizer = new GradientDescent(2);
        Vector startVector = new Vector(new double[]{2, 2});
        double expectedF = 0.0;
        Vector res = new Vector(2);
        descentMinimizer.minimize(
                f,
                startVector,
                1e-2,
                1e-2,
                res);
        Assert.assertEquals(expectedF, f.f(res), 21e-2);
    }
}
