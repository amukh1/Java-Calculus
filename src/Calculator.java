import java.util.function.Function;
// Programmed by amukh1#9613
class Calculator {
    double infinitesimal = 0.00001;

    public double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public double derivativeAt(Function<Double, Double> f, double w) {
        return (f.apply(w + infinitesimal) - f.apply(w)) / infinitesimal;
    }

    // ex: nthDerivativeAt(2, f, 4) = f''(4)
    public double nthDerivativeAt(int n, Function<Double, Double> f, double w) {
        Function <Double,Double> ff = f;
        for(int i = 1; i <= n; i++) {
            ff = derivative(ff);
        }
        return ff.apply(w);
    }

    public Function derivative(Function<Double, Double> fx) {
        Function<Double, Double> fn = x -> ((fx.apply(x + infinitesimal) - fx.apply(x)) / infinitesimal);
        return fn;
    }

    // implicit differentiation function:
    public Function relatedRate(double independentRate, Function<Double, Double> formula) {
        Function<Double, Double> rate = r -> independentRate / (derivativeAt(formula, r));
        return rate;
    }

    public Function newtonsCooling(double k, double initTemp, double ambientTemp) {
        Function<Double, Double> T = t -> ambientTemp + (initTemp - ambientTemp) * Math.exp(-1 * k * t);
        return T;
    }

    public double limit(double a, Function<Double, Double> f) {
        return f.apply(a - infinitesimal) + ((f.apply(a + infinitesimal) - f.apply(a - infinitesimal))/2);
    }

    public double integrate(double a, double b, Function<Double,Double> fn, double sub) {
        double retV = 0.0;
        double dx = (b-a)/sub;
        double currentX = a + dx / 2;
        for (double i = 0; i < sub; i++) {
            var currentY = fn.apply(currentX);
            retV += dx * currentY;
            currentX += dx;
        }
        return retV;
    }

    public double linearApprox(Function<Double,Double> fn, double k, double u) {
        Function<Double,Double> appx = x-> fn.apply(k) + (derivativeAt(fn, k) * (x-k));
        return appx.apply(u);
    }

    // Todo: add Taylor series
    public Function taylorSeries(Function<Double,Double> fn, double n, double a) {
        Function<Double,Double> appx = x-> fn.apply(a)/factorial(0) + summation(0, n, i-> taylorPoly(fn, (int) Math.round(i), a, x));
        return appx;
    }

    public double taylorPoly(Function<Double,Double> fn, int n, double a, double x) {
        return nthDerivativeAt(n, fn, a)*Math.pow(x-a, n) / factorial(n);
    }

    public double taylorPolyGiven(double[] fn, double n, double a, Double x) {
        return fn[(int) Math.round(n)]*Math.pow(x-a, n) / factorial(n);
    }

    public Function deriveTaylorSeries(double[] derivs, double a) {
        Function<Double,Double> appx = x1->derivs[0]/factorial(0) + summation(1, derivs.length, i->taylorPolyGiven(derivs, i, a, x1));
        return appx;
    }

    // Todo: add Summation

    public double summation(double a, double b, Function<Double,Double> fn) {
        double retV = 0.0;
        for (double i = a; i < b; i++) {
            retV += fn.apply(i);
        }
        return retV;
    }

    public long factorial(double num) {
        long factorial = 1;
        for (int i = 1; i <= num; ++i) {
            // factorial = factorial * i;
            factorial *= i;
        }
        return factorial;
    }
}