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

  //  private DisciplinaPage page;

  public DisciplinaViewLogic(DisciplinaPage page) {
    super(page);
    //    this.page = page;
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

  //  public void init() {
  //    editDisciplina(null);
  //    // Hide and disable if not admin
  //    if (!AccessControlFactory.getInstance().createAccessControl().isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
  //      page.setNewDisciplinaEnabled(false);
  //    }
  //  }

  //  public void cancelDisciplina() {
  //    setFragmentParameter("");
  //    page.clearSelection();
  //  }

  //  public void enter(String disciplinaId) {
  //    if (disciplinaId != null && !disciplinaId.isEmpty()) {
  //      if (disciplinaId.equals("new")) {
  //        newDisciplina();
  //      }
  //      else {
  //        try {
  //          int pid = Integer.parseInt(disciplinaId);
  //          Disciplina disciplina = findDisciplina(pid);
  //          page.selectRow(disciplina);
  //        }
  //        catch (NumberFormatException e) {
  //        }
  //      }
  //    }
  //    else {
  //      page.showForm(false);
  //    }
  //  }

  //  private Disciplina findDisciplina(int disciplinaId) {
  //    Disciplina obj = new Disciplina();
  //    obj.setId(disciplinaId);
  //    return DataService.get().getDisciplinaDAO().load(obj);
  //  }

  //  public void saveDisciplina(Disciplina disciplina) {
  //    boolean newDisciplina = disciplina.isNewObject();
  //    page.clearSelection();
  //    page.updateDisciplina(disciplina);
  //    setFragmentParameter("");
  //    page.showSaveNotification(disciplina.getNome() +
  //      (newDisciplina? " criada": " atualizada"));
  //  }

  //  public void deleteDisciplina(Disciplina disciplina) {
  //    page.clearSelection();
  //    page.removeDisciplina(disciplina);
  //    setFragmentParameter("");
  //    page.showSaveNotification(disciplina.getNome() + " removida");
  //  }

  //  public void editDisciplina(Disciplina disciplina) {
  //    if (disciplina == null) {
  //      setFragmentParameter("");
  //    }
  //    else {
  //      setFragmentParameter(disciplina.getId() + "");
  //    }
  //    page.editDisciplina(disciplina);
  //  }

  //  private void setFragmentParameter(String disciplinaId) {
  //    String fragmentParameter;
  //    if (disciplinaId == null || disciplinaId.isEmpty()) {
  //      fragmentParameter = "";
  //    }
  //    else {
  //      fragmentParameter = disciplinaId;
  //    }
  //
  //    UI.getCurrent().navigate(DisciplinaPage.class, fragmentParameter);
  //  }

  //  public void newDisciplina() {
  //    page.clearSelection();
  //    setFragmentParameter("new");
  //    page.editDisciplina(new Disciplina());
  //  }

  //  public void rowSelected(Disciplina disciplina) {
  //    if (AccessControlFactory.getInstance().createAccessControl().isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
  //      editDisciplina(disciplina);
  //    }
  //  }

}
