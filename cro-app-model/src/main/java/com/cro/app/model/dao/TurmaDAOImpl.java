package com.cro.app.model.dao;


import com.cro.app.model.entidade.Turma;
import com.cro.app.model.util.AbstractDAO;


/**
 * Implementação JPA para o DAO de {@link Turma}
 * @author Ednilson Brito Lopes
 *
 */
@SuppressWarnings("serial")
public class TurmaDAOImpl
  extends AbstractDAO<Turma>
  implements TurmaDAO {

  /**
   * Singleton
   */
  private static TurmaDAOImpl instance;

  private TurmaDAOImpl() {

  }

  public static TurmaDAOImpl getInstance() {
    if (instance == null) {
      instance = new TurmaDAOImpl();
    }
    return instance;
  }
}
