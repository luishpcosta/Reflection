package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Field;

import br.com.alura.alurator.playground.modelo.Produto;

public class TesteManipulaAtributo {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object produto = new Produto("Produto 1", 20.0, "Marca 1");
		Class<?> classe = produto.getClass();
		
		
	//	System.out.println(classe.getField("id"));
		
		String nameClasse = classe.getTypeName();
		
		String[] split = nameClasse.split("\\.");
		
		
		nameClasse =  split[split.length -1];
		
		StringBuilder xml = new StringBuilder(); 
		xml.append("<"+nameClasse+">");
		for (Field atributo : classe.getDeclaredFields()) {
			atributo.setAccessible(true);
			xml.append("<"+atributo.getName() + ">" + atributo.get(produto) + "</" + atributo.getName() + ">");
		}
		xml.append("</"+nameClasse+">");
		System.out.println(xml.toString());
	}

}
