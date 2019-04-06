package com.cro.app.model.dao;


import com.cro.app.model.entidade.Sala;
import com.cro.app.model.util.AbstractDAO;


/**
 * Imlementação JPA para o DAO de {@link Sala}
 * @author Ednilson Brito Lopes
 */
public class SalaDAOImpl
  extends AbstractDAO<Sala>
  implements SalaDAO {

  /**
   * Serial
   */
  private static final long serialVersionUID = 3591512017686306729L;

  /**
   * Singleton
   */
  private static SalaDAOImpl instance;

  private SalaDAOImpl() {

  }

  /**
   * @return uma instância de {@link SalaDAOImpl}
   */
  public static SalaDAOImpl getInstance() {
    if (instance == null) {
      instance = new SalaDAOImpl();
    }
    return instance;
  }

}
