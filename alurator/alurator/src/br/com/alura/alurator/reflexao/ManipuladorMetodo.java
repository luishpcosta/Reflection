package br.com.alura.alurator.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorMetodo {

	private Object instancia;
	private Method metodo;
	private Map<String, Object> params;
	private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;


	public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
		this.instancia = instancia;
		this.metodo = metodo;
		this.params = params;
	}	
	
	public Object invocar() {
		try {
			List<Object> parametros = new ArrayList<>();
			Stream.of(metodo.getParameters()).forEach(p -> parametros.add(params.get(p.getName())));
			return metodo.invoke(this.instancia, parametros.toArray());
		} catch (IllegalAccessException | IllegalArgumentException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {

			// tratamento especial e customizado da exceção.
			if (tratamentoExcecao != null) {
				return tratamentoExcecao.apply(metodo, e);
			}

			e.printStackTrace();
			throw new RuntimeException("Erro no método!", e.getTargetException());
		}
	}


	public ManipuladorMetodo comTratamentoDeExcecao(BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
		this.tratamentoExcecao = tratamentoExcecao;
		return this;
	}

}
