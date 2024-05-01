package in.project.springbootmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

	@Bean
	public ValidatingMongoEventListener ValidatingMongoEventListner()
	{
		return new ValidatingMongoEventListener(Validator());
	}
	@Bean
	public LocalValidatorFactoryBean Validator()
	{
		return new LocalValidatorFactoryBean();
	}
	
	
}
