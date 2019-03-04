package com.cro.app.model.entidade;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cro.app.model.enumerator.SerieEnum;
import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Entidade {@link Turma}
 * 
 * @author Ednilson Brito Lopes
 */
@Entity
@SuppressWarnings("serial")
public class Turma
  extends AbstractBasicEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Integer id;

  @Column(nullable = false, length = 2)
  @NotNull
  @Size(min = 1,
        message = "Preencha este campo. Ex.: \"UN\", \"A\", \"B\".")
  private String nome;

  @Column(length = 300)
  private String descricao;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @NotNull(message = "Selecione a série.")
  private SerieEnum serie;

  @OneToMany(mappedBy = "turma")
  private List<Aluno> alunos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_professor", referencedColumnName = "id")
  private Professor professor;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
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
   * Retorna o valor da propriedade serie.
   * @return {@link #serie}
   */
  public SerieEnum getSerie() {
    return serie;
  }

  /**
   * Configura o valor da propriedade serie.
   * @param serie atualiza {@link #serie}
   */
  public void setSerie(SerieEnum serie) {
    this.serie = serie;
  }

  /**
   * Retorna o valor da propriedade alunos.
   * @return {@link #alunos}
   */
  public List<Aluno> getAlunos() {
    return alunos;
  }

  /**
   * Configura o valor da propriedade alunos.
   * @param alunos atualiza {@link #alunos}
   */
  public void setAlunos(List<Aluno> alunos) {
    this.alunos = alunos;
  }

  /**
   * Retorna o valor da propriedade professor.
   * @return {@link #professor}
   */
  public Professor getProfessor() {
    return professor;
  }

  /**
   * Configura o valor da propriedade professor.
   * @param professor atualiza {@link #professor}
   */
  public void setProfessor(Professor professor) {
    this.professor = professor;
  }

  public String getNomeCompleto() {
    return getSerie() + " " + getNome();
  }

  @Override
  public String toString() {
    return getNomeCompleto();
  }

}
