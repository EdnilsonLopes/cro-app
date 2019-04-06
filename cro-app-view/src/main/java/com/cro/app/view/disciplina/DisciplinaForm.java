package com.cro.app.view.disciplina;


import com.cro.app.model.entidade.Disciplina;
import com.cro.app.view.util.BeanForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


/**
 * Formulário para edição de uma Disciplina
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class DisciplinaForm
  extends BeanForm<Disciplina> {

  private VerticalLayout content;

  private TextField nomeField;
  private TextArea descricaoField;

  public DisciplinaForm() {
    content = new VerticalLayout();
    content.setSizeUndefined();
    add(content);

    nomeField = createTextField("Nome", "nome");
    content.add(nomeField);

    descricaoField = createTextArea("Descrição", "descricao");
    content.add(descricaoField);

    content.add(createButtonBar());
    inicializarBinder();
  }

}
