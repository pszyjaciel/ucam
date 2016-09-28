// kompilacja
// c:\\progra~2\Java\\jdk1.6.0_30\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar;c:\\mb\\Ucam92\\classes\\bsh.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\\BeanShell.java 

package com.company.ucam.silk;

//Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//Source File Name:   BeanShell.java

import bsh.EvalError;
import bsh.Interpreter;
import bsh.util.JConsole;

import com.barco.ets.ucam.dtl.Ucamaction;
import com.barco.ets.ucam.hypertool.Ucamapp;
import javax.swing.JFrame;

public class BeanShell {

	public BeanShell() {
	}

	static void startBeanShellDesktop() {
		System.out.println("Starting BeanShell Desktop...");
		try {
			(new Interpreter()).eval("desktop()");
		} catch (EvalError evalerror) {
			System.err.println("Couldn't start desktop: " + evalerror);
		}
	}

	static void startBeanShellConsole() {
		System.out.println("Starting BeanShell Console...");
		JFrame jframe = new JFrame("BeanShell Console");
		JConsole jconsole = new JConsole();
		jframe.getContentPane().add(jconsole);
		jframe.pack();
		jframe.setLocation(200, 200);
		jframe.setSize(600, 400);
		jframe.validate();
		jframe.show();
		Interpreter interpreter = new Interpreter(jconsole);
		try {
			interpreter.eval("import com.barco.ets.ucam.hypertool.*");
		} catch (EvalError evalerror) {
			System.err.println("Couldn't eval: " + evalerror);
		}
		(new Thread(interpreter)).start();
	}

	static void execBeanShellScript() {
		System.out.println("Executing BeanShell Script...");
		Interpreter interpreter = new Interpreter();
		try {
			interpreter.set("Er", 0.5D);
			interpreter.set("width", 0.25D);
			interpreter.set("height", 0.14999999999999999D);
			interpreter.set("thickness", 0.01D);
			interpreter.source("symstrip.bsh");
			Double double1 = (Double) interpreter.get("z0");
			System.err.println("z0 = " + double1);
		} catch (EvalError evalerror) {
			System.err.println("Couldn't eval: " + evalerror);
		} catch (Exception exception) {
			System.err.println("Exception: " + exception);
			exception.printStackTrace();
		}
	}

	static {

		Ucamaction act = new Ucamaction() {
			public void action() {
				BeanShell.startBeanShellDesktop();
			}
		};
		Ucamapp.cO.add_pb("bshDesktop", "bshDesktop", "", null, "hypertool_menu", false);

		act = new Ucamaction() {
			public void action() {
				BeanShell.startBeanShellConsole();
			}
		};
		Ucamapp.cO.add_pb("bshConsole", "bshConsole", "", null, "hypertool_menu", false);

		act = new Ucamaction() {
			public void action() {
				BeanShell.execBeanShellScript();
			}
		};
		Ucamapp.cO.add_pb("bshScript", "bshScript", "", null, "hypertool_menu", false);

	}
}
