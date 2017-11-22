package org.yan.base.oauth.resources;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Created by yanshuai on 17/5/17.
 */
public class CustomOAuth2ExceptionJackson2Serializer extends StdSerializer<CustomOAuth2Exception> {

	public CustomOAuth2ExceptionJackson2Serializer() {
		super(CustomOAuth2Exception.class);
	}

	@Override
	public void serialize(CustomOAuth2Exception value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeObject(value.getResponseData());
	}
}
