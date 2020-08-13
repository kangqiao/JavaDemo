//package com.zp.demo.instrument;
//
//import com.sun.tools.attach.AgentInitializationException;
//import com.sun.tools.attach.AgentLoadException;
//import com.sun.tools.attach.AttachNotSupportedException;
//import com.sun.tools.attach.VirtualMachine;
//
//import java.io.IOException;
//
//public class Attacher {
//
//    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
//        //传入目标 JVM pid
//        VirtualMachine vm = VirtualMachine.attach("11145");
//        vm.loadAgent("build/classes/java/main/");
//    }
//}
