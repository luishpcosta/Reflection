package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorContrutor {

	private Constructor<?> constructor;

	public ManipuladorContrutor(Constructor<?> constructor) {
		this.constructor = constructor;
		
	}

	public Object invocar() {
		
		try {
			return this.constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e ) {
			e.printStackTrace();
			throw new RuntimeException(e.getTargetException());
		}
	}
	
	

}
