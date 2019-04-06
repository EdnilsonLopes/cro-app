package com.cro.app.model.util;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.cro.app.model.DataServiceImpl;


/**
 * Classe abstrata para o DAO. Classe pai de todos os DAOs da entidade
 * 
 * @author Ednilson Brito Lopes
 *
 * @param <T> tipo da entidade que será persistida
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends AbstractBasicEntity>
  implements DataAccessObject<T> {

  /**
   * Serial
   */
  private static final long serialVersionUID = 5683589371470519512L;

  /**
   * Fabrica de gerenciadores de entidades
   */
  private EntityManagerFactory emf;

  public EntityManagerFactory getEmf() {
    return emf;
  }

  public void setEmf(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public EntityManager getEm() {
    return em;
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }

  /**
   * Gerenciador de entidades
   */
  private EntityManager em;

  /**
   * Tipo da classe passada por parâmetro
   */
  private Class<T> type;

  public AbstractDAO() {
    this.emf = DataServiceImpl.getEMF();
  }

  public AbstractDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  /**
   * @return o tipo {@link Class} da entidade usada como parâmetro na classe
   */
  private Class<T> getType() {
    if (this.type == null) {
      this.type =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return type;
  }

  @Override
  public void insert(T obj) {
    em = getEmf().createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(obj);
      em.getTransaction().commit();
    }
    catch (Exception e) {
      em.getTransaction().rollback();
      throw new RuntimeException(e.getMessage());
    }
    finally {
      em.close();
    }
  }

  @Override
  public void update(T obj) {
    em = getEmf().createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(obj);
      em.getTransaction().commit();
    }
    catch (Exception e) {
      em.getTransaction().rollback();
      throw new RuntimeException(e.getMessage());
    }
    finally {
      em.close();
    }
  }

  @Override
  public void delete(T obj) {
    em = getEmf().createEntityManager();
    try {
      em.getTransaction().begin();
      em.remove(obj);
      em.getTransaction().commit();
    }
    catch (Exception e) {
      em.getTransaction().rollback();
      throw new RuntimeException(e.getMessage());
    }
    finally {
      em.close();
    }
  }

  @Override
  public T load(T obj) {
    try {
      return getEmf().createEntityManager().find(getType(), obj.getId());
    }
    finally {
    }
  }

  @Override
  public List<T> loadAll() {
    String q = "select i from " + getEntityName() + " i";
    Query query = getEmf().createEntityManager().createQuery(q);
    return query.getResultList();
  }

  private String getEntityName() {
    String temp = getType().getName().replace(".", "#");
    String[] array = temp.split("#");
    String nome = array[array.length - 1];
    return nome;
  }

}
