package ru.vasiliyplatonov.homework6.shell.table.formatter;

import org.springframework.shell.table.CellMatcher;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Service;

@Service
public class ListTypeCellMatcher implements CellMatcher {

	@Override
	public boolean matches(int row, int column, TableModel model) {


		return false;
	}
}
