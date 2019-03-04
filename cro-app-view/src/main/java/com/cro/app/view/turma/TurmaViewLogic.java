package com.cro.app.view.turma;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Turma;
import com.cro.app.view.util.AbstractViewLogic;
import com.vaadin.flow.component.UI;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class TurmaViewLogic
  extends AbstractViewLogic<Turma> {

  public TurmaViewLogic(TurmaPage view) {
    super(view);
  }

  @Override
  public void navigate(String fragmentParameter) {
    UI.getCurrent().navigate(TurmaPage.class, fragmentParameter);
  }

  @Override
  public Turma createNewInstanceObject() {
    return new Turma();
  }

  @Override
  public Turma loadObject(Turma obj) {
    return DataService.get().getTurmaDAO().load(obj);
  }

}
