package com.cro.app.view.disciplina;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Disciplina;
import com.cro.app.view.util.AbstractViewLogic;
import com.vaadin.flow.component.UI;


/**
 * Lógica de visualização da disciplinas
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class DisciplinaViewLogic
  extends AbstractViewLogic<Disciplina> {


  public DisciplinaViewLogic(DisciplinaPage page) {
    super(page);
  }

  @Override
  public void navigate(String fragmentParameter) {
    UI.getCurrent().navigate(DisciplinaPage.class, fragmentParameter);
  }

  @Override
  public Disciplina createNewInstanceObject() {
    return new Disciplina();
  }

  @Override
  public Disciplina loadObject(Disciplina obj) {
    return DataService.get().getDisciplinaDAO().load(obj);
  }

}
