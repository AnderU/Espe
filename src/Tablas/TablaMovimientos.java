package Tablas;

import java.text.SimpleDateFormat;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;


import Clases.MovimientosC;


public class TablaMovimientos extends AbstractTableModel {

    private Vector<MovimientosC> movimientos;
    private Vector<String> nombresColumnas;

    public TablaMovimientos(Vector<MovimientosC> movimientos,Vector<String> nombresColumnas) {

    	this.movimientos = new Vector<MovimientosC>(movimientos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(MovimientosC rowData) {
    	movimientos.addElement(rowData);
        fireTableRowsInserted(movimientos.size(),movimientos.size());
    }
    
    public void removeRow(int row) {
    	movimientos.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }
    
    @Override
    public String getColumnName(int index) {
        return this.nombresColumnas.get(index);
    }
    @Override
    public int getRowCount() {
        return movimientos.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        MovimientosC movimientos = this.movimientos.get(rowIndex);
        switch (columnIndex) {

            case 0:
            	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                value = formatoFecha.format(movimientos.getFecha());
                break;
            case 1:
            	SimpleDateFormat formatoFechaPC = new SimpleDateFormat("dd-MM-yyyy");
                value = formatoFechaPC.format(movimientos.getFechaPC());
                break;
            case 2:
                value = movimientos.getIdProvCli();
                break;
            case 3:
                value = movimientos.getImporte();
                break;
            case 4:
            	value = movimientos.getnFactura();
            	break;
            case 5:
            	value = movimientos.getObservaciones();
            	break;
            case 6:
            	value = movimientos.getIdFact();
            	break;
        }

        return value;

    }

    public MovimientosC getUserAt(int row) {

        return movimientos.get(row);
    }
    

}