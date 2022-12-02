
# Java-Calculus

This is a Java library for calculus. It is a work in progress.

<!-- Checklist with all the features -->

## Features
- [x] Limits
- [x] Derivatives
- [x] Implicit Differentiation / Related Rates
- [x] Sir Issac Newton's Law of Cooling
- [x] Integration
- [x] Linear Approximation
- [x] Maclaurin Series++
- [x] Taylor Polynomials
- [x] Taylor Series
- [ ] Limited Taylor Series (Unknown Taylor Polynomials) (Soon!)
- [ ] Maxima/Minima (Soon!)

## Usage

# Limits

```java
Calculator calculus = new Calculator();
System.out.println(calculus.limit(4,fn)); // 4: Limit at, fn: function;
```

# Derivatives
```java
Calculator calculus = new Calculator();
System.out.println(calculus.derivative(fn).apply(4)); // fn -> fn
```

# Implicit / Related Rates
Ex. A circular sheet of ice is melting at a rate of 0.25 m^2/s. At what rate is the radius decreasing at?
```java
// *prerequisites*
Function<Double, Double> Area = r -> Math.PI * Math.pow(r, 2);

Function<Double, Double> dx = calculus.relatedRate(-0.25, Area); // constant rate, fn
System.out.println(dx.apply(1.0)); // -ans
```

# Cooling law
```java
Function<Double, Double> T = calculus.newtonsCooling(k, initialTempature, ambientTempature); // k == decay constant
System.out.println(T.apply(4.0)); // Tempature 4 seconds in.
```

# Integrals
```java
Calculator calculus = new Calculator();
System.out.println(a, b, fn, subdivisions);
```

# Linear Approximation
```java
Calculator calculus = new Calculator();
Function<Double,Double> f = x -> Math.pow(x, 1/4.0);
double appx = calculus.linearApprox(f, 16, 15);
System.out.println(appx);
System.out.println(f.apply(15.0));
System.out.println("% Error: " + ((appx - f.apply(15.0)) / f.apply(15.0) * 100)+"%");
```
# Taylor Polynomials
```java
Calculator calculus = new Calculator();
System.out.println(calculus.taylorPoly(fn, n, a, x));
```

# Taylor Series
```java
Calculator calculus = new Calculator();
Function <Double,Double> sinAppx = calculus.taylorSeries(Math::sin, 5, 0);
System.out.println(sinAppx.apply(0.5));
System.out.println(Math.sin(0.5));
System.out.println("% Error: " + (Math.abs(sinAppx.apply(0.5) - Math.sin(0.5)) / Math.sin(0.5) * 100)+"%");
```
# Deriving a Taylor Series from a limited number of derivatives
```java
Calculator calculus = new Calculator();
double[] d = {0, 1, 0, -1}; // sin derivatives
System.out.println(calculus.deriveTaylorSeries(d, 0).apply(1.0)); // approximation of the sinx function
```

# Overall:
```java
import java.util.function.Function;
// Programmed by amukh1#9613
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
        System.out.println(dA_dt.apply(1.0));
        System.out.println(calculus.nthDerivativeAt(2, dA_dt, 4));

        // *implicit differenciation*
        // rate the radius is decreasng when it is 1.1 m^2 if the area is decreasing at
        // a rate of -0.25 m^2/s
        System.out.println("\nImplicit differentiation - Related rates:");
        Function<Double, Double> dx = calculus.relatedRate(-0.62, Area);
        System.out.println(dx.apply(1.0));

        // Newtons cooling law
        System.out.println("\nSir Issac Newtons Law of Cooling:");
        Function<Double, Double> T = calculus.newtonsCooling(1, -3.2, -4.1);
        System.out.println(T.apply(4.0));

        // Integrals
        System.out.println("\nIntegrals");
        Function<Double,Double> Fn = x->2*x;
        double integ = calculus.integrate(0, 8, Fn, 100);
        System.out.println(integ);

        // Linear Approximation
        System.out.println("\nLinear Approximation");
        Function<Double,Double> f = x -> Math.pow(x, 1/4.0);
        double appx = calculus.linearApprox(f, 16, 15);
        System.out.println(appx);
        System.out.println(f.apply(15.0));
        System.out.println("% Error: " + ((appx - f.apply(15.0)) / f.apply(15.0) * 100)+"%");

        // Todo: *Taylor Series*
        System.out.println("\nTaylor Series:");
        Function <Double,Double> sinAppx = calculus.taylorSeries(Math::sin, 5, 0);
        System.out.println(sinAppx.apply(0.5));
        System.out.println(Math.sin(0.5));
        System.out.println("% Error: " + (Math.abs(sinAppx.apply(0.5) - Math.sin(0.5)) / Math.sin(0.5) * 100)+"%");

      System.out.println("\nUnknown Taylor Series:");
      double[] d = {0, 1, 0, -1}; // sin derivatives
      System.out.println(calculus.deriveTaylorSeries(d, 0).apply(1.0)); // approximation of the sinx function
    }
}
```
