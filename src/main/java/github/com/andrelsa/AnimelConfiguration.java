package github.com.andrelsa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimelConfiguration {
	
	@Bean("cachorro")
	public Animal cachorro() {
		return new Animal() {
			@Override
			public void makeNoise() {
				System.out.println("Au Au");
			}
		};
	}
	
	@Bean("gato")
	public Animal gato() {
		return new Animal() {
			@Override
			public void makeNoise() {
				System.out.println("Miau");
			}
		};
	}
	
}
