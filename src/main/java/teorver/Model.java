// Model.java
package teorver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Model {
    public double[] generate(String func, double a, double b, int m) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        func = "var f = 1.0 * " + func;

        final int N = 10;
        double x[] = new double[N];
        double h[] = new double[N];
        double p[] = new double[N];
        double d = (b - a) / N;
        double sumH = 0.0;

        for (int i = 0; i < N; i++) {
            x[i] = a + (i + 1) * d;
            engine.put("x", x[i]);
            engine.eval(func);
            h[i] = (Double) engine.get("f");
            sumH += h[i];
        }

        for (int i = 0; i < N; i++) {
            p[i] = h[i] / sumH;
        }

        double xr[] = new double[m];

        for (int k = 0; k < m; k++) {
            double r = Math.random();
            int i = 0;
            while (r > p[i]) {
                r = r - p[i];
                i++;
            }
            xr[k] = x[i];
        }

        return xr;
    }
}
