package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;

public class ManipuladorClasse {
	
	
	private Class<?> clazz;

	public ManipuladorClasse(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	
	public ManipuladorContrutor getConstrutorPadrao() {
		try {
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			return new ManipuladorContrutor(constructor);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	public ManipuladorObjeto criaInstancia() {
		Object instancia = getConstrutorPadrao().invocar();
		return new ManipuladorObjeto(instancia);
	}

}