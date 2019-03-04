package com.cro.app.model.util;


import java.io.Serializable;


/**
 * Classe abstrata base para todas as entidades da aplicação
 * 
 * @author Ednilson Brito Lopes
 *
 * @param <T>
 *            tipo da chave composta da entidade
 */
public abstract class AbstractBasicEntity<T extends Serializable>
  implements Serializable {

  /**
   * Serial
   */
  private static final long serialVersionUID = 6471602696219599194L;

  /**
   * @return o id da entidade
   */
  public abstract Integer getId();

  /**
   * @param id
   *            atualiza o valor do id
   */
  public abstract void setId(Integer id);

  /**
   * Verifica se é um novo objeto
   * 
   * @return {@link Boolean}
   */
  public Boolean isNewObject() {
    return getId() == null || getId() == -1 || getId() == 0;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof AbstractBasicEntity)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    AbstractBasicEntity<T> other = (AbstractBasicEntity<T>) obj;
    if (this.getId().equals(other)) {
      return true;
    }
    return false;
  }

}
