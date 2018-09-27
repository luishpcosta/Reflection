package br.com.alura.alurator.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;
	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> parametros) {

		Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());

		Method metodoSelecionado = metodos
				.filter(metodo -> metodo.getName().equals(nomeMetodo)
						&& metodo.getParameterCount() == parametros.values().size()
						&& Stream.of(metodo.getParameters())
								.allMatch(parametro -> parametros.keySet().contains(parametro.getName())
										&& parametros.get(parametro.getName()).getClass().equals(parametro.getType())))
				.findFirst().orElseThrow(() -> new RuntimeException(" metodo não encontrado"));

		return new ManipuladorMetodo(this.instancia, metodoSelecionado, parametros);

	}

}
