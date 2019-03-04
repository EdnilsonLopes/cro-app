package com.cro.app.model;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cro.app.model.dao.DisciplinaDAO;
import com.cro.app.model.dao.DisciplinaDAOImpl;


/**
 * Implementação da camada de serviço. Esta acessa a camada de persistência
 * 
 * @author Ednilson Brito Lopes
 */
public class DataServiceImpl
  extends DataService {

  /**
   * Fábrica gerenciadora de entidades
   */
  private static EntityManagerFactory emf;

  /**
   * @return uma instância de {@link EntityManagerFactory} o campo {@link #emf}
   */
  public static EntityManagerFactory getEMF() {
    if (emf == null) {
      emf = Persistence.createEntityManagerFactory("cro-app");
    }
    return emf;
  }

  @Override
  public DisciplinaDAO getDisciplinaDAO() {
    return DisciplinaDAOImpl.getInstance();
  }

}
