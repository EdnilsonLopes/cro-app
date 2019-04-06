package com.cro.app.view.sala;


import com.cro.app.model.entidade.Sala;
import com.cro.app.view.util.BeanForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


/**
 * Formulário para edição de {@link Sala}
 * @author Ednilson Brito Lopes
 */
public class SalaForm
  extends BeanForm<Sala> {

  /**
   * Serial
   */
  private static final long serialVersionUID = 5819271025378880094L;

  private TextField numeroField;

  private TextArea descricaoField;

  public SalaForm() {
    VerticalLayout content = new VerticalLayout();
    content.setSizeUndefined();
    add(content);
    content.add(createNumeroEdit());
    content.add(createDescricaoEdit());
    content.add(createButtonBar());
    inicializarBinder();
  }

  private Component createNumeroEdit() {
    numeroField = createTextField("Número", "numero");
    numeroField.setPattern("[0-9]*");
    numeroField.setWidth("50%");
    numeroField.setMaxLength(10);
    return numeroField;
  }

  private Component createDescricaoEdit() {
    descricaoField = createTextArea("Descrição", "descricao");
    descricaoField.setWidth("100%");
    descricaoField.setMaxLength(200);
    return descricaoField;
  }
}
