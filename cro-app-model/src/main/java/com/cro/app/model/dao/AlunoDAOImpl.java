package com.cro.app.model.dao;


import com.cro.app.model.entidade.Aluno;
import com.cro.app.model.util.AbstractDAO;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class AlunoDAOImpl
  extends AbstractDAO<Aluno>
  implements AlunoDAO {

  /**
   * Singleton
   */
  private static AlunoDAOImpl instance;

  private AlunoDAOImpl() {
  }

  public static AlunoDAOImpl getInstance() {
    if (instance == null) {
      instance = new AlunoDAOImpl();
    }
    return instance;
  }

}
