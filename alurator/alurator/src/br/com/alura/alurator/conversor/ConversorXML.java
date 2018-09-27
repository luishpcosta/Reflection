package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

import br.com.alura.alurator.conversor.anotacao.NomeTagXml;

public class ConversorXML {

	public String converte(Object retorno)  {
		try {
			
		Class<? extends Object> classe = retorno.getClass();
		StringBuilder xmlBuilder = new StringBuilder(); 
		
		if(retorno instanceof Collection) {
			Collection<?> colecao = (Collection<?>) retorno;
			xmlBuilder.append("<lista>");
			
			for (Object o : colecao) {
				String xml = converte(o);
				xmlBuilder.append(xml);
			}
			
			xmlBuilder.append("</lista>");
			
		} else {
			
			NomeTagXml anotacao = classe.getDeclaredAnnotation(NomeTagXml.class);
			String nomeClasse = anotacao == null ? classe.getName() : anotacao.value();
			
			xmlBuilder.append("<"+nomeClasse+">");
			for (Field atributo : classe.getDeclaredFields()) {
				atributo.setAccessible(true);
				
				NomeTagXml anotacaoTributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
				String nomeAtributo = anotacaoTributo == null ? atributo.getName() : anotacaoTributo.value();
				
					xmlBuilder.append("<"+nomeAtributo + ">" + atributo.get(retorno) + "</" + nomeAtributo + ">");
			}
			xmlBuilder.append("</"+nomeClasse+">");
			}
		return xmlBuilder.toString();
		
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na geração do XML");
		}
	}

}
