package com.cro.app.view.turma;

import com.cro.app.model.entidade.Aluno;
import com.cro.app.view.util.BeanGrid;

/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class AlunoTurmaGrid extends BeanGrid<Aluno> {

	public AlunoTurmaGrid() {
		addColumn(Aluno::getNome).setFlexGrow(1).setHeader("Nome").setSortable(true);
		addColumn(a -> getDateInFormat(a.getDataNascimento())).setFlexGrow(1).setHeader("Nascimento").setSortable(true);
	}

}
