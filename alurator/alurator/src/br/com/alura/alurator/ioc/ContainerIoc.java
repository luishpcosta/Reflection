package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoc {
	
	private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();
	
	public Object getInstancia(Class<?> tipoFonte) {
		
		Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);
		
		if(tipoDestino != null) {
			return getInstancia(tipoDestino);
		}
		
		Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());
		
		Optional<Constructor<?>> costrutorPadrao = construtores.filter(c -> c.getParameterCount() == 0).findFirst();
		
		try {
			
			if(costrutorPadrao.isPresent()) {
				Object instancia = costrutorPadrao.get().newInstance();
				
				return instancia;
				
			} else {
				
				Constructor<?> constructor = tipoFonte.getDeclaredConstructors()[0];
				
				List<Object> params = new ArrayList<>();
				for (Parameter param : constructor.getParameters()) {
					Class<?> tipoDoParametro = param.getType();
					
					params.add(getInstancia(tipoDoParametro));
					
				}
				
				return constructor.newInstance(params.toArray());
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			
			throw new RuntimeException(e);
		}
		
		
	}

	public <T, K extends T > void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		mapaDeTipos.put(tipoFonte, tipoDestino);
	}

}
