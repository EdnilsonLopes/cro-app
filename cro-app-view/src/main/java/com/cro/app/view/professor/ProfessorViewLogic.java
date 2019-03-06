package com.cro.app.view.professor;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Professor;
import com.cro.app.view.util.AbstractListView;
import com.cro.app.view.util.AbstractViewLogic;
import com.vaadin.flow.component.UI;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class ProfessorViewLogic
  extends AbstractViewLogic<Professor> {

  public ProfessorViewLogic(AbstractListView<Professor> view) {
    super(view);
  }

  @Override
  public void navigate(String fragmentParameter) {
    UI.getCurrent().navigate(ProfessorPage.class, fragmentParameter);
  }

  @Override
  public Professor createNewInstanceObject() {
    return new Professor();
  }

  @Override
  public Professor loadObject(Professor obj) {
    return DataService.get().getProfessorDAO().load(obj);
  }

}
