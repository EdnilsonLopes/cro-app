package com.cro.app.model.entidade;


import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.cro.app.model.enumerator.SitucacaoMatriculaEnum;
import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Entidade {@link Aluno}
 * @author Ednilson Brito Lopes
 */
@Entity
@SuppressWarnings("serial")
public class Aluno
  extends AbstractBasicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private int id;

  @Column(nullable = false, length = 100)
  @NotNull(message = "Digite o nome do aluno.")
  private String nome;

  @Column(nullable = false, length = 11)
  @NotNull(message = "O CPF deve ser informado.")
  private String cpf;

  @Column(length = 100)
  private String nomePai;

  @Column(nullable = false, length = 100)
  @NotNull(message = "Digite o nome da mãe.")
  private String nomeMae;

  @Column(length = 15)
  private String telefone;

  @Column(length = 15)
  private String telefone2;

  @Column(nullable = false, length = 9)
  @NotNull(message = "Digite o CEP")
  private String cep;

  @Column(nullable = false, length = 2)
  @NotNull(message = "Campo Obrigatório")
  private String uf;

  @Column(nullable = false, length = 100)
  @NotNull(message = "Digite a cidade.")
  private String cidade;

  @Column(nullable = false, length = 100)
  @NotNull(message = "Digite o bairro.")
  private String bairro;

  @Column(nullable = false, length = 150)
  @NotNull(message = "Campo Obrigatório")
  private String logradouro;

  @Column(length = 150)
  private String complemento;

  @Column(length = 10)
  private String numeroEndereco;

  @Temporal(TemporalType.DATE)
  @Column
  private Date dataNacimento;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_turma", referencedColumnName = "id")
  private Turma turma;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SitucacaoMatriculaEnum situacaoMatricula;

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
   * Retorna o valor da propriedade nomePai.
   * @return {@link #nomePai}
   */
  public String getNomePai() {
    return nomePai;
  }

  /**
   * Configura o valor da propriedade nomePai.
   * @param nomePai atualiza {@link #nomePai}
   */
  public void setNomePai(String nomePai) {
    this.nomePai = nomePai;
  }

  /**
   * Retorna o valor da propriedade nomeMae.
   * @return {@link #nomeMae}
   */
  public String getNomeMae() {
    return nomeMae;
  }

  /**
   * Configura o valor da propriedade nomeMae.
   * @param nomeMae atualiza {@link #nomeMae}
   */
  public void setNomeMae(String nomeMae) {
    this.nomeMae = nomeMae;
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
  public String getNumeroEndereco() {
    return numeroEndereco;
  }

  /**
   * Configura o valor da propriedade numeroEndereco.
   * @param numeroEndereco atualiza {@link #numeroEndereco}
   */
  public void setNumeroEndereco(String numeroEndereco) {
    this.numeroEndereco = numeroEndereco;
  }

  /**
   * Retorna o valor da propriedade turma.
   * @return {@link #turma}
   */
  public Turma getTurma() {
    return turma;
  }

  /**
   * Configura o valor da propriedade turma.
   * @param turma atualiza {@link #turma}
   */
  public void setTurma(Turma turma) {
    this.turma = turma;
  }

  /**
   * Retorna o valor da propriedade situacaoMatricula.
   * @return {@link #situacaoMatricula}
   */
  public SitucacaoMatriculaEnum getSituacaoMatricula() {
    return situacaoMatricula;
  }

  /**
   * Configura o valor da propriedade situacaoMatricula.
   * @param situacaoMatricula atualiza {@link #situacaoMatricula}
   */
  public void setSituacaoMatricula(SitucacaoMatriculaEnum situacaoMatricula) {
    this.situacaoMatricula = situacaoMatricula;
  }

  /**
   * Retorna o valor da propriedade dataNacimento.
   * @return {@link #dataNacimento}
   */
  public Date getDataNacimento() {
    return dataNacimento;
  }

  /**
   * Configura o valor da propriedade dataNacimento.
   * @param dataNacimento atualiza {@link #dataNacimento}
   */
  public void setDataNacimento(Date dataNacimento) {
    this.dataNacimento = dataNacimento;
  }

  /**
   * Retorna o valor da propriedade cpf.
   * @return {@link #cpf}
   */
  public String getCpf() {
    return cpf;
  }

  /**
   * Configura o valor da propriedade cpf.
   * @param cpf atualiza {@link #cpf}
   */
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  /**
   * Retorna o valor da propriedade uf.
   * @return {@link #uf}
   */
  public String getUf() {
    return uf;
  }

  /**
   * Configura o valor da propriedade uf.
   * @param uf atualiza {@link #uf}
   */
  public void setUf(String uf) {
    this.uf = uf;
  }

  /**
   * Retorna o valor da propriedade logradouro.
   * @return {@link #logradouro}
   */
  public String getLogradouro() {
    return logradouro;
  }

  /**
   * Configura o valor da propriedade logradouro.
   * @param logradouro atualiza {@link #logradouro}
   */
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

}
