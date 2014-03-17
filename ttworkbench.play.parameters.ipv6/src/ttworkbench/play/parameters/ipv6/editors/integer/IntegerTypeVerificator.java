package ttworkbench.play.parameters.ipv6.editors.integer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import ttworkbench.play.parameters.ipv6.components.messaging.data.MessageRecord;
import ttworkbench.play.parameters.ipv6.editors.verification.Verificator;
import ttworkbench.play.parameters.ipv6.editors.verification.VerifyResult;

import com.testingtech.ttworkbench.ttman.parameters.validation.ErrorKind;

public class IntegerTypeVerificator implements Verificator<String> {

  private boolean isValueAnInteger( String theValue) {
		try {
		  new BigInteger( theValue);
		  return true;
		} catch ( NumberFormatException e) {
			return false;
		}
	}
	
	@Override
	public VerifyResult<String> verify(String theInput, Object... theParams) {
		boolean verified = isValueAnInteger( theInput);
		
		MessageRecord inputRejectedWarning = new MessageRecord( "invalid_input_warning", String.format( "Input of \"%s\" rejected.", theInput), ErrorKind.warning); 
		MessageRecord acceptedValuesInfo = new MessageRecord( "valid_chars_info", "Only integer values accepted.", ErrorKind.info); 
		List<MessageRecord> messages = Arrays.asList( inputRejectedWarning, acceptedValuesInfo); 
		return new VerifyResult<String>( theInput, verified, messages);
	}
	
}