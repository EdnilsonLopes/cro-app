package com.cro.app.model.entidade;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Entidade {@link Professor}
 * @author Ednilson Brito Lopes
 */
@Entity
@SuppressWarnings("serial")
public class Professor
  extends AbstractBasicEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  @NotNull(message = "Preencha o campo de nome.")
  @Size(min = 2,
        message = "O nome não pode ter menos que 2 caracteres!")
  private String nome;

  @Column(length = 150)
  @NotNull(message = "Preencha o campo de formação")
  @Size(min = 2,
        message = "O nome não pode ter menos que 2 caracteres!")
  private String formacao;

  @Temporal(TemporalType.DATE)
  @Column
  private Date dataNascimento;

  @Column(length = 15)
  private String telefone;

  @Column(length = 15)
  private String telefone2;

  @Column(nullable = false, length = 9)
  @NotNull(message = "Digite o CEP")
  private String cep = "";

  @Column(nullable = false, length = 100)
  @NotNull(message = "Digite a cidade.")
  private String cidade = "";

  @Column(nullable = false, length = 100)
  @NotNull(message = "Digite o bairro.")
  private String bairro = "";

  @Column(length = 150)
  private String complemento;

  @Column(length = 10)
  private Integer numeroEndereco;

  @OneToMany(mappedBy = "professor")
  private List<Turma> turmas;

  @ManyToMany
  @JoinTable(name = "disciplina_professor",
             joinColumns = @JoinColumn(name = "id_professor",
                                       referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "id_disciplina",
                                              referencedColumnName = "id"))
  private Set<Disciplina> disciplinas;

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
   * Retorna o valor da propriedade telefone.
   * @return {@link #telefone}
   */
  public String getTelefone() {
    return telefone;
  }

  /**
   * Configura o valor da propriedade telefone.
   * @param telefone atualiza {@link #telefone}
   */
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  /**
   * Retorna o valor da propriedade telefone2.
   * @return {@link #telefone2}
   */
  public String getTelefone2() {
    return telefone2;
  }

  /**
   * Configura o valor da propriedade telefone2.
   * @param telefone2 atualiza {@link #telefone2}
   */
  public void setTelefone2(String telefone2) {
    this.telefone2 = telefone2;
  }

  /**
   * Retorna o valor da propriedade cep.
   * @return {@link #cep}
   */
  public String getCep() {
    return cep;
  }

  /**
   * Configura o valor da propriedade cep.
   * @param cep atualiza {@link #cep}
   */
  public void setCep(String cep) {
    this.cep = cep;
  }

  /**
   * Retorna o valor da propriedade cidade.
   * @return {@link #cidade}
   */
  public String getCidade() {
    return cidade;
  }

  /**
   * Configura o valor da propriedade cidade.
   * @param cidade atualiza {@link #cidade}
   */
  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  /**
   * Retorna o valor da propriedade bairro.
   * @return {@link #bairro}
   */
  public String getBairro() {
    return bairro;
  }

  /**
   * Configura o valor da propriedade bairro.
   * @param bairro atualiza {@link #bairro}
   */
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  /**
   * Retorna o valor da propriedade complemento.
   * @return {@link #complemento}
   */
  public String getComplemento() {
    return complemento;
  }

  /**
   * Configura o valor da propriedade complemento.
   * @param complemento atualiza {@link #complemento}
   */
  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  /**
   * Retorna o valor da propriedade numeroEndereco.
   * @return {@link #numeroEndereco}
   */
  public Integer getNumeroEndereco() {
    return numeroEndereco;
  }

  /**
   * Configura o valor da propriedade numeroEndereco.
   * @param numeroEndereco atualiza {@link #numeroEndereco}
   */
  public void setNumeroEndereco(Integer numeroEndereco) {
    this.numeroEndereco = numeroEndereco;
  }

  /**
   * Retorna o valor da propriedade turmas.
   * @return {@link #turmas}
   */
  public List<Turma> getTurmas() {
    return turmas;
  }

  /**
   * Configura o valor da propriedade turmas.
   * @param turmas atualiza {@link #turmas}
   */
  public void setTurmas(List<Turma> turmas) {
    this.turmas = turmas;
  }

  /**
   * Retorna o valor da propriedade disciplinas.
   * @return {@link #disciplinas}
   */
  public Set<Disciplina> getDisciplinas() {
    return disciplinas;
  }

  /**
   * Configura o valor da propriedade disciplinas.
   * @param disciplinas atualiza {@link #disciplinas}
   */
  public void setDisciplinas(Set<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }

  public void addTurma(Turma turma) {
    turma.setProfessor(this);
    getTurmas().add(turma);
  }

  public void removeTurma(Turma turma) {
    turma.setProfessor(null);
    getTurmas().remove(turma);
  }

  public void addDisciplina(Disciplina disciplina) {
    getDisciplinas().add(disciplina);
    disciplina.getProfessores().add(this);
  }

  public void removeDisciplina(Disciplina disciplina) {
    getDisciplinas().remove(disciplina);
    disciplina.getProfessores().remove(this);
  }

  @Override
  public String toString() {
    return nome;
  }

  /**
   * Retorna o valor da propriedade formacao.
   * @return {@link #formacao}
   */
  public String getFormacao() {
    return formacao;
  }

  /**
   * Configura o valor da propriedade formacao.
   * @param formacao atualiza {@link #formacao}
   */
  public void setFormacao(String formacao) {
    this.formacao = formacao;
  }

  /**
   * Retorna o valor da propriedade dataNascimento.
   * @return {@link #dataNascimento}
   */
  public Date getDataNascimento() {
    return dataNascimento;
  }

  /**
   * Configura o valor da propriedade dataNascimento.
   * @param dataNascimento atualiza {@link #dataNascimento}
   */
  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

}
