package com.cro.app.model.entidade;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;

import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Enitade {@link Nota}. Aqui é a ligação de alunos com disciplinas
 * @author Ednilson Brito Lopes
 *
 */
@Entity
@SuppressWarnings("serial")
public class Nota
  extends AbstractBasicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private int id;

  @Column(nullable = false)
  @DecimalMax(value = "10.00")
  private BigDecimal valor;

  @OneToOne
  @JoinColumn(name = "id_disciplina", referencedColumnName = "id")
  private Disciplina disciplina;

  @OneToOne
  @JoinColumn(name = "id_aluno", referencedColumnName = "id")
  private Aluno aluno;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

}
