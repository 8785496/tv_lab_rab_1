package teorver;

import javax.script.*;

public class Main {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        System.out.println("Введите функцию плотности:");

        // evaluate JavaScript code
        try {
            engine.eval("print('hello');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
