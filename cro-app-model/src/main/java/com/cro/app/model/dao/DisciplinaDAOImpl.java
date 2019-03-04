package com.cro.app.model.dao;


import com.cro.app.model.entidade.Disciplina;
import com.cro.app.model.util.AbstractDAO;


/**
 * Implementação JPA para o DAO da entidade {@link Disciplina}
 * @author Ednilson Brito Lopes
 *
 */
public class DisciplinaDAOImpl
  extends AbstractDAO<Disciplina>
  implements DisciplinaDAO {

  /**
   * Serial
   */
  private static final long serialVersionUID = -2429201969155653980L;

  /**
   * Singleton
   */
  private static DisciplinaDAOImpl instance;

  private DisciplinaDAOImpl() {
  }

  public static DisciplinaDAOImpl getInstance() {
    if (instance == null) {
      instance = new DisciplinaDAOImpl();
    }
    return instance;
  }

}
