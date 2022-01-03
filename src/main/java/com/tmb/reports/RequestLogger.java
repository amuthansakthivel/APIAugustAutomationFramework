package com.tmb.reports;

import java.io.PrintStream;
import java.io.StringWriter;

public final class RequestLogger {

    private RequestLogger(){}

    private static ThreadLocal<StringWriter> sw = new ThreadLocal<>();
    private static ThreadLocal<PrintStream> ps = new ThreadLocal<>();

    public static void setWriter(StringWriter stringwriter){
        sw.set(stringwriter);
    }
    public static StringWriter getWriter(){
        return sw.get();
    }

    public static void setPrintStream(PrintStream printStream){
        ps.set(printStream);
    }
    public static PrintStream getPrintStream(){
        return ps.get();
    }
    public static void unload(){
        sw.remove();
        ps.remove();
    }




}
