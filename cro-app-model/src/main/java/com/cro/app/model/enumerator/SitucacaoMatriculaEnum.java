package com.cro.app.model.enumerator;


import java.util.Arrays;
import java.util.List;


/**
 * Enumerator para Situação de Matrícula
 * 
 * @author Ednilson Brito Lopes
 */
public enum SitucacaoMatriculaEnum {
  MATRICULADO("Matriculado"), NAO_MATRICULADO(
    "Não Matriculado"), PRE_MATRICULADO("Pré Matriculado");

  private String descricao;

  private SitucacaoMatriculaEnum(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public static List<SitucacaoMatriculaEnum> getValues() {
    return Arrays.asList(SitucacaoMatriculaEnum.values());
  }

  @Override
  public String toString() {
    return descricao;
  }

}
