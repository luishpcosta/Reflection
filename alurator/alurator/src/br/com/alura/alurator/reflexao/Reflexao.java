package br.com.alura.alurator.reflexao;

public class Reflexao {

	public ManipuladorClasse refleteClasse(String fqn) {
		Class<?> Clazz = getClasse(fqn);
		return new ManipuladorClasse(Clazz);
	}

	public Class<?> getClasse(String fqn) {
		try {
			Class<?> Clazz = Class.forName(fqn);
			return Clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
