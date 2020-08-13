package com.zp.demo.java8feature;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn 一个 javascript 引擎。
 *
 * 从JDK 1.8开始，Nashorn取代Rhino(JDK 1.6, JDK1.7)成为Java的嵌入式JavaScript引擎。Nashorn完全支持ECMAScript 5.1规范以及一些扩展。它使用基于JSR 292的新语言特性，其中包含在JDK 7中引入的 invokedynamic，将JavaScript编译成Java字节码。
 *
 * 与先前的Rhino实现相比，这带来了2到10倍的性能提升。
 *
 * jjs
 * jjs是个基于Nashorn引擎的命令行工具。它接受一些JavaScript源代码为参数，并且执行这些源代码。
 *
 * 例如，我们创建一个具有如下内容的sample.js文件：
 */
//https://www.runoob.com/java/java8-nashorn-javascript.html
public class TestNashornJavaScript {

    public static void main(String[] args) {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Runoob";
        Integer result = null;

        try {
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");
            //result = (Integer) nashorn.eval("1 / 0");
        }catch(ScriptException e){
            System.out.println("执行脚本错误: "+ e.getMessage());
        }

        System.out.println(result.toString());
    }
}
