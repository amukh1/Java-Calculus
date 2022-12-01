import java.util.function.Function;

class Main {
    public static void main(String[] args) {
        // *setup*
        Calculator calculus = new Calculator();

        // *prerequisites*
        Function<Double, Double> Area = r -> Math.PI * Math.pow(r, 2);
        Function<Double, Double> Radius = A -> Math.sqrt(A / Math.PI);

        // *limits*
        System.out.println("\nLimits:");
        double L = calculus.limit(4, Area);
        System.out.println(L);

        // *derivatives*
        System.out.println("\nDerivatives:");
        Function<Double, Double> dA_dt = calculus.derivative(Area);
        Function<Double, Double> dr_dt = calculus.derivative(Radius);
        System.out.println(dA_dt.apply(1.0));
        System.out.println(dr_dt.apply(1.0));

        // *implicit differenciation*
        // rate the radius is decreasng when it is 1.1 m^2 if the area is decreasing at
        // a rate of -0.25 m^2/s
        System.out.println("\nImplicit differentiation - Related rates:");
        Function<Double, Double> dx = calculus.relatedRate(-0.25, Area);
        System.out.println(dx.apply(1.1));

        // Newtons cooling law
        System.out.println("\nSir Issac Newtons Law of Cooling:");
        Function<Double, Double> T = calculus.newtonsCooling(1, -3.2, -4.1);
        System.out.println(T.apply(4.0));

        // Integrals
        System.out.println("\nIntegrals");
        Function<Double,Double> FN = x->2*x;
        double integ = calculus.integrate(0, 8, FN, 100);
        System.out.println(integ);
    }
}
