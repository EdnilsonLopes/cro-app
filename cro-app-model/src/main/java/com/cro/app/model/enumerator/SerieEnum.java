package com.cro.app.model.enumerator;


import java.util.Arrays;
import java.util.List;


/**
 * Enumerator para Serie
 * 
 * @author Ednilson Brito Lopes
 */
public enum SerieEnum {
  PRE_JARDIM("Pré Jardim"), JARDIM_UM("Jardim I"), JARDIM_DOIS(
    "Jardim II"), PRIMEIRO_ANO_F(
      "1º Ano Ensino Fundamental"), SEGUNDO_ANO_F(
        "2º Ano Ensino Fundamental"), TERCEIRO_ANO_F(
          "3º Ano Ensino Fundamental"), QUARTO_ANO_F(
            "4º Ano Ensino Fundamental"), QUINTO_ANO_F(
              "5º Ano Ensino Fundamental"), SEXTO_ANO_F(
                "6º Ano Ensino Fundamental"), SETIMO_ANO_F(
                  "7º Ano Ensino Fundamental"), OITAVO_ANO_F(
                    "8º Ano Ensino Fundamental"), NONO_ANO_F(
                      "9º Ano Ensino Fundamental"), PRIMEIRO_ANO_M(
                        "1º Ano Ensino Médio"), SEGUNDO_ANO_M(
                          "2º Ano Ensino Médio"), TERCEIRO_ANO_M(
                            "3º Ano Ensino Médio");

  private String descricao;

  private SerieEnum(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return descricao;
  }

  public static List<SerieEnum> getValues() {
    return Arrays.asList(SerieEnum.values());
  }
}
