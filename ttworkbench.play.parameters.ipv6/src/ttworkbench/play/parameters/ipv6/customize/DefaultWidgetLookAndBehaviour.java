package ttworkbench.play.parameters.ipv6.customize;

public class DefaultWidgetLookAndBehaviour implements IWidgetLookAndBehaviour {

	@Override
	public IValidatingEditorLookAndBehaviour getEditorLookAndBehaviour() {
		return new IntegerEditorLookAndBehaviour();
	}

	@Override
	public IMessagePanelLookAndBehaviour getMessaagePanelLookAndBehaviour() {
		return getEditorLookAndBehaviour().getMessaagePanelLookAndBehaviour();
	}

}
