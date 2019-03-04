package com.cro.app.model;


import com.cro.app.model.dao.AlunoDAO;
import com.cro.app.model.dao.DisciplinaDAO;
import com.cro.app.model.dao.ProfessorDAO;
import com.cro.app.model.dao.TurmaDAO;


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
   * @return uma instância de {@link AlunoDAO}
   */
  public abstract AlunoDAO getAlunoDAO();

  /**
   * @return uma instância de {@link DisciplinaDAO}
   */
  public abstract DisciplinaDAO getDisciplinaDAO();

  /**
   * @return uma instância de {@link ProfessorDAO}
   */
  public abstract ProfessorDAO getProfessorDAO();

  /**
   * @return uma instância de {@link TurmaDAO}
   */
  public abstract TurmaDAO getTurmaDAO();
}
