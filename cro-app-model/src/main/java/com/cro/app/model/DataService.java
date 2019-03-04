package com.cro.app.model;


import com.cro.app.model.dao.DisciplinaDAO;


/**
 * Classe abstrata que provê o acesso à camada de persistência
 * 
 * @author Ednilson Brito Lopes
 */
public abstract class DataService {

  /**
   * Singleton
   */
  private static DataService instance;

  /**
   * @return uma intância de {@link DataService}
   */
  public static DataService get() {
    if (instance == null) {
      instance = new DataServiceImpl();
    }
    return instance;
  }

  /**
   * @return uma instância de {@link DisciplinaDAO}
   */
  public abstract DisciplinaDAO getDisciplinaDAO();
}
