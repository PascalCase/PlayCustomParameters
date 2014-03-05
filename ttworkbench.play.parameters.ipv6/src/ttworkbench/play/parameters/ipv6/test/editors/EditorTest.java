package ttworkbench.play.parameters.ipv6.test.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import ttworkbench.play.parameters.ipv6.editors.IPv6Editor;
import ttworkbench.play.parameters.ipv6.editors.MacAddressEditor;

public class EditorTest {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell( display);
		shell.setLayout(new ColumnLayout());
		shell.setText( "Editor Test");

		new IPv6Editor().createControl( shell, SWT.NONE);
		new MacAddressEditor().createControl( shell, SWT.NONE);

		shell.open();

		// run the event loop as long as the window is open
		while (!shell.isDisposed()) {
			// read the next OS event queue and transfer it to a SWT event
			if (!display.readAndDispatch()) {
				// if there are currently no other OS event to process
				// sleep until the next OS event is available
				display.sleep();
			}
		}

		// disposes all associated windows and their components
		display.dispose();
	}
}