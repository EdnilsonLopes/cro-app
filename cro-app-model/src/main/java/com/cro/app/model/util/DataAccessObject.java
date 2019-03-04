package com.cro.app.model.util;

import java.io.Serializable;
import java.util.List;

/**
 * Interface para os DAOs da aplicação
 * 
 * @author Ednilson Brito Lopes
 * @param <T>
 *            Tipo da entidade que será persistida
 */
public interface DataAccessObject<T extends AbstractBasicEntity<?>> extends Serializable {

	/**
	 * Insere um novo objeto no banco de dados
	 * 
	 * @param obj
	 *            que será inserido
	 */
	public void insert(T obj);

	/**
	 * Atualiza os valores do objeto no banco de dados
	 * 
	 * @param obj
	 *            que será atualizado
	 */
	public void update(T obj);

	/**
	 * Deleta o objeto no banco de dados
	 * 
	 * @param obj
	 *            que será deletado
	 */
	public void delete(T obj);

	/**
	 * Carrega um objeto da entidade {@link T}
	 * 
	 * @param obj
	 *            usado para filtrar
	 * @return um objeto da entidade {@link T}
	 */
	public T load(T obj);

	/**
	 * Carrega todos os objetos da entidade {@link T}
	 * 
	 * @return uma lista de objetos da entidade {@link T}
	 */
	public List<T> loadAll();

}
