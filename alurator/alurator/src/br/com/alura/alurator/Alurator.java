package br.com.alura.alurator;

import java.util.Map;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.ioc.ContainerIoc;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.ManipuladorObjeto;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator<K> {

	private String pacoteBase;
	
	private ContainerIoc container;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
		this.container = new ContainerIoc();
	}
	
	

	public Object executa(String url) {

		Request request = new Request(url);

		String nomeController = request.getNomeControle();
		String NomeMetodo = request.getNomeMetodo();
		Map<String, Object> params = request.getQueryParams();

		
		Class<?> classeControle = new Reflexao().getClasse(pacoteBase + nomeController);
		Object instanciaControle = container.getInstancia(classeControle);
		Object retorno = new ManipuladorObjeto(instanciaControle)
								.getMetodo(NomeMetodo, params)
								.comTratamentoDeExcecao((metodo, ex) -> {
				                	System.out.println("Erro no método " + metodo.getName() + " da classe " 
				                            + metodo.getDeclaringClass().getName() + ".\n\n");
				                	throw new RuntimeException("Erro no método!");
				                })
								.invocar();

		System.out.println(retorno);
		
		retorno = new ConversorXML().converte(retorno);

		return retorno;

	}


	public<T, K extends T > void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		container.registra(tipoFonte, tipoDestino);
	}
}
