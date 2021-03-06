package com.cro.app.model.util;


import java.io.Serializable;


/**
 * Classe abstrata base para todas as entidades da aplicação
 * 
 * @author Ednilson Brito Lopes
 */
public abstract class AbstractBasicEntity
  implements Serializable {

  /**
   * Serial
   */
  private static final long serialVersionUID = 6471602696219599194L;

  /**
   * @return o id da entidade
   */
  public abstract int getId();

  /**
   * @param id
   *            atualiza o valor do id
   */
  public abstract void setId(int id);

  /**
   * Verifica se é um novo objeto
   * 
   * @return {@link Boolean}
   */
  public Boolean isNewObject() {
    return getId() == -1 || getId() == 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof AbstractBasicEntity)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    AbstractBasicEntity other = (AbstractBasicEntity) obj;
    if (this.getId() == other.getId()) {
      return true;
    }
    return false;
  }

}
