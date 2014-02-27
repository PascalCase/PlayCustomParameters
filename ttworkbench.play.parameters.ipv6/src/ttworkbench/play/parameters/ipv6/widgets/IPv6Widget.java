package ttworkbench.play.parameters.ipv6.widgets;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.testingtech.ttworkbench.ttman.parameters.api.IParameter;
import com.testingtech.ttworkbench.ttman.parameters.api.IParameterEditor;


public class IPv6Widget extends AbstractWidget {

	private static final String TITLE = "IPv6 Widget";
	private static final String DESCRIPTION = "";
	private static final Image IMAGE = null;
	
	private Composite editorContainer;
	
	public IPv6Widget() {
		super( TITLE, DESCRIPTION, IMAGE);
	}
	
	@Override
	public void update() {
	    // TODO better add editors incremental
		deleteParameterEditors();
		createParameterEditors();
		
	}
	
	private void deleteParameterEditors() {
		if ( editorContainer != null) {	
		 Control[] controls = editorContainer.getChildren();
			for (Control control : controls) {
			  control.dispose();	
			}
		}
	}

	private void createParameterEditors() {
		GridData gridData = new GridData( SWT.BEGINNING, SWT.FILL, true, true);
		GridLayout gridLayout = new GridLayout( 1, false);
		gridLayout.makeColumnsEqualWidth = false;
		if ( editorContainer != null) {	
			Set<IParameterEditor> editors = getEditors();
			for ( IParameterEditor editor : editors) {
				editor.createControl( editorContainer, gridData, gridLayout);
			}
			editorContainer.setSize( editorContainer.computeSize( SWT.DEFAULT, SWT.DEFAULT));
			editorContainer.layout();
		}
	}
	
	@Override
	public Control createControl(Composite theParent) {

		theParent.setLayout( new FillLayout(SWT.HORIZONTAL));

	    ScrolledComposite scrolledComposite = new ScrolledComposite( theParent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
	    editorContainer = new Composite( scrolledComposite, SWT.None);
		editorContainer.setLayout( new GridLayout( 1, false));
	    
        createParameterEditors();
        
	    scrolledComposite.setContent( editorContainer);
		
		return scrolledComposite;
	}

	@Override
	protected Set<IParameter> filterRelevantParameters(
			Set<IParameter> theParameters) {
		// TODO Auto-generated method stub
		return null;
	}


}
