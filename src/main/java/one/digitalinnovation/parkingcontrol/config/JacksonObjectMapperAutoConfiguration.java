package one.digitalinnovation.parkingcontrol.config;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class JacksonObjectMapperAutoConfiguration {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
		return new Jackson2ObjectMapperBuilderCustomizer() {

			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				final String dateTimeFormat = "dd/MM/yyyy HH:mm";
				
				jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL)
						.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
						.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			}
		};
	}
	
}
