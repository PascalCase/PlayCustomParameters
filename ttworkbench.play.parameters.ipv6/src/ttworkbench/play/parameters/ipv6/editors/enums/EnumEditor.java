package ttworkbench.play.parameters.ipv6.editors.enums;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import ttworkbench.play.parameters.ipv6.customize.DefaultEditorLookAndBehaviour;
import ttworkbench.play.parameters.ipv6.customize.IValidatingEditorLookAndBehaviour;
import ttworkbench.play.parameters.ipv6.editors.ValidatingEditor;
import ttworkbench.play.parameters.ipv6.editors.verification.IVerifyingControl;
import ttworkbench.play.parameters.ipv6.editors.verification.widgets.VerifyingCombo;
import ttworkbench.play.parameters.ipv6.editors.verification.widgets.VerifyingRadio;
import ttworkbench.play.parameters.ipv6.valueproviders.EnumValueProvider;

import com.testingtech.muttcn.values.StringValue;

public class EnumEditor extends ValidatingEditor<StringValue> {

	private static final String TITLE = "Enum Editor";
	private static final String DESCRIPTION = "Enum Editor";

	private static final int ENUM_MAX_LENGTH = 20;
	private IVerifyingControl<?, StringValue> inputControl;

	private final EnumValueProvider enumValueProvider = new EnumValueProvider();

	private final EnumContextVerifier enumContextVerifier = new EnumContextVerifier();

	public EnumEditor() {
		super( TITLE, DESCRIPTION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IValidatingEditorLookAndBehaviour getDefaultLookAndBehaviour() {
		// TODO Auto-generated method stub
		return new DefaultEditorLookAndBehaviour();
	}

	@Override
	protected void createEditRow(Composite theContainer) {
		// TODO Auto-generated method stub
		Object[] layoutData = this.getLookAndBehaviour().getLayoutDataOfControls();

		CLabel comboLabel = new CLabel( theContainer, SWT.LEFT);
		comboLabel.setText( getParameter().getId());
		comboLabel.setLayoutData( layoutData[0]);

		if (isBoolean( enumValueProvider.getAvailableValues( this.getParameter()))) {
			createRadioGroup( theContainer, layoutData[0]);
		} else {
			createCombo( theContainer, layoutData[0]);
		}
	}

	private void createRadioGroup(Composite theContainer, Object theLayoutData) {
		// TODO Auto-generated method stub
		inputControl = new VerifyingRadio<StringValue>( getParameter(), theContainer, SWT.SHADOW_IN, enumContextVerifier);

		Set<StringValue> availableValues = enumValueProvider.getAvailableValues( this.getParameter());

		//Make a group for the Radio Buttons
		final Group enumRadioGroup = (Group) inputControl.getControl();
		enumRadioGroup.setLayout( new RowLayout( SWT.VERTICAL));

		//Create radio buttons according to the number of the available values
		for (StringValue value : availableValues) {
			final Button radio = new Button( enumRadioGroup, SWT.RADIO);
			radio.setText( value.getTheContent());
			//add listeners to the radion buttons
			radio.addSelectionListener( new SelectionAdapter() {
				public void widgetSelected(SelectionEvent theEvent) {
					if (radio.getSelection() == true) {
						System.out.println( "my current selection: " + radio.getText());
						getParameter().getValue().setTheContent(radio.getText());
					}
				}
			});
		}
	}

	private void createCombo(Composite theContainer, Object theLayoutData) {
		// TODO Auto-generated method stub
		inputControl = new VerifyingCombo<StringValue>( getParameter(), theContainer, SWT.READ_ONLY, enumContextVerifier);

		final Combo enumCombo = (Combo) inputControl.getControl();
		final Rectangle dimensions = new Rectangle( 50, 50, 200, 65);
		enumCombo.setBounds( dimensions);
		setWidthForText( enumCombo, ENUM_MAX_LENGTH);
		enumCombo.setTextLimit( ENUM_MAX_LENGTH);

		Set<StringValue> availableValues = enumValueProvider.getAvailableValues( this.getParameter());

		int index = 0;
		for (StringValue value : availableValues) {
			enumCombo.add( value.getTheContent().toString(), index);
			index++;
			System.out.println( value.getTheContent().toString());
		}

		enumCombo.addSelectionListener( new SelectionAdapter() {
				public void widgetSelected(SelectionEvent theEvent) {
					getParameter().getValue().setTheContent(enumCombo.getText());
				}
			});
	}

	private void setWidthForText(Combo theComboControl, int visibleChars) {
		// TODO Auto-generated method stub
		GC gc = new GC( theComboControl);
		int charWidth = gc.getFontMetrics().getAverageCharWidth();
		gc.dispose();

		int minWidth = visibleChars * charWidth;
		Object layout = theComboControl.getLayoutData();
		if (layout instanceof GridData)
			( (GridData) layout).minimumWidth = minWidth + 20;
		if (layout instanceof RowData)
			( (RowData) layout).width = minWidth + 20;
		else
			theComboControl.setSize( theComboControl.computeSize( minWidth + 20, SWT.DEFAULT));
	}

	private <T> boolean isBoolean(Set<T> values) {
		return ( values.size() < 3);
	}

	@Override
	public void reloadParameter() {
		// TODO Auto-generated method stub
		
	}

}