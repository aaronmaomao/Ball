package com.ball.m;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ball.m.ui.commom.MyBrowser;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;

public class DesktopMain {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DesktopMain window = new DesktopMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		MyBrowser browser = new MyBrowser(shell, SWT.NONE, "file:///"+System.getProperty("user.dir")+"/resource/ui/ssq.html");

	}

}
