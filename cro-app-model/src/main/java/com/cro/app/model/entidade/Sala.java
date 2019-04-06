package com.cro.app.model.entidade;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Entidade {@link Salas}
 * @author Ednilson Brito Lopes
 */
@Entity
public class Sala
  extends AbstractBasicEntity {

  /**
   * Serial
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true, nullable = false, length = 10)
  @NotNull(message = "O Número da sala deve ser informado!")
  private String numero;

  @Column(length = 200)
  private String descricao;

  /**
   * Retorna o valor da propriedade id.
   * @return {@link #id}
   */
  public int getId() {
    return id;
  }

  /**
   * Configura o valor da propriedade id.
   * @param id atualiza {@link #id}
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retorna o valor da propriedade numero.
   * @return {@link #numero}
   */
  public String getNumero() {
    return numero;
  }

  /**
   * Configura o valor da propriedade numero.
   * @param numero atualiza {@link #numero}
   */
  public void setNumero(String numero) {
    this.numero = numero;
  }

  /**
   * Retorna o valor da propriedade descricao.
   * @return {@link #descricao}
   */
  public String getDescricao() {
    return descricao;
  }

  /**
   * Configura o valor da propriedade descricao.
   * @param descricao atualiza {@link #descricao}
   */
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return numero;
  }
  
}
