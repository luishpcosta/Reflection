package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import br.com.alura.alurator.playground.controle.SubControle;

public class TesteInstanciaObjetoCorretamente {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// url -> /controle/lista

		Class<SubControle> subControleClasse1 = SubControle.class;

		Class<?> subControleClasse2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");

		Class<?> ControleClasse1 = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		Constructor<SubControle> constructorSubControle = subControleClasse1.getConstructor();
		
		SubControle newInstance = constructorSubControle.newInstance();
		
		System.out.println(newInstance);

	}

}
	