package com.cro.app.model.entidade;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Entidade {@link Disciplina}
 * 
 * @author Ednilson Brito Lopes
 */
@Entity
@NamedQuery(name = "findAll", query = "select d from Disciplina d")
@SuppressWarnings("serial")
public class Disciplina
  extends AbstractBasicEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private int id;

  @Column(nullable = false, length = 100)
  @NotNull(message = "Preencha o campo de nome da disciplina.")
  @Size(min = 2, max = 100,
        message = "O nome da Disciplina não pode ter menos que 2 caracteres!")
  private String nome;

  @Column(nullable = true, length = 300)
  private String descricao;

  @ManyToMany(mappedBy = "disciplinas")
  private List<Professor> professores;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retorna o valor da propriedade nome.
   * @return {@link #nome}
   */
  public String getNome() {
    return nome;
  }

  /**
   * Configura o valor da propriedade nome.
   * @param nome atualiza {@link #nome}
   */
  public void setNome(String nome) {
    this.nome = nome;
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

  /**
   * Retorna o valor da propriedade professores.
   * @return {@link #professores}
   */
  public List<Professor> getProfessores() {
    return professores;
  }

  /**
   * Configura o valor da propriedade professores.
   * @param professores atualiza {@link #professores}
   */
  public void setProfessores(List<Professor> professores) {
    this.professores = professores;
  }

  @Override
  public String toString() {
    return nome;
  }

}
