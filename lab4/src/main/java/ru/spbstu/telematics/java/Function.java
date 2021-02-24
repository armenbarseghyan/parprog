package ru.spbstu.telematics.java;

public class Function implements FunctionInterface{
    private final double[][] coefficients;

    Function(double [][] arr){
        this.coefficients = arr;
    }

    @Override
    public double f(Vector x){
        double [] res = new double[x.getSize()];
        for (int i = 0; i < x.getSize(); i++){
            res[i]=0;
        }
        for (int i = 0; i < x.getSize(); i++) {
            for (int j = 0; j < x.getSize(); j++) {
                    res[i] += x.getX()[j] * coefficients[j][i];
            }
        }
        double result = 0;
        for (int i = 0; i < x.getSize(); i++)
            result += res[i]*x.getX()[i];
        return result;
    }

    @Override
    public double f(double[] x){
        double [] res = new double[x.length];
        for (int i = 0; i < x.length; i++){
            res[i]=0;
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                res[i] += x[j] * coefficients[j][i];
            }
        }
        double result = 0;
        for (int i = 0; i < x.length; i++)
            result += res[i]*x[i];
        return result;
    }
    int getSize(){
        return coefficients.length;
    }
    void printValues(){
        for (double[] y: coefficients) {
            for (double x : y) {
                System.out.print(x + "\t");
            }
            System.out.println();
        }
    }
}
