import java.util.function.Function;

class Calculator {
    double infinitesimal = 0.0000000001;

    public double derivativeAt(Function<Double, Double> f, double w) {
        return (f.apply(w + infinitesimal) - f.apply(w)) / infinitesimal;
    }

    public Function derivative(Function<Double, Double> f) {
        Function<Double, Double> fn = x -> (f.apply(x + infinitesimal) - f.apply(x)) / infinitesimal;
        return fn;
    }

    // implicit differenciation function:
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

    public double linearAprox(Function<Double,Double> fn, double k, double u) {
        Function<Double,Double> appx = x-> fn.apply(k) + (derivativeAt(fn, k) * (x-k));
        return appx.apply(u);
    }
}