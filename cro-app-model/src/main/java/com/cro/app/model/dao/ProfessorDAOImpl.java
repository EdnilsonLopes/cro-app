package com.cro.app.model.dao;


import com.cro.app.model.entidade.Professor;
import com.cro.app.model.util.AbstractDAO;


/**
 * 
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class ProfessorDAOImpl
  extends AbstractDAO<Professor>
  implements ProfessorDAO {

  /**
   * Singleton
   */
  private static ProfessorDAOImpl instance;

  private ProfessorDAOImpl() {

  }

  public static ProfessorDAOImpl getInstance() {
    if (instance == null) {
      instance = new ProfessorDAOImpl();
    }
    return instance;
  }

}
