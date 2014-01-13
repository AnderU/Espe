package Tablas;

import java.text.SimpleDateFormat;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;


import Clases.MovimientosC;


public class TablaMovimientos extends AbstractTableModel {

    private Vector<MovimientosC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaMovimientos(Vector<MovimientosC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<MovimientosC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(MovimientosC rowData) {
    	conceptos.addElement(rowData);
        fireTableRowsInserted(conceptos.size(),conceptos.size());
    }
    
    public void removeRow(int row) {
    	conceptos.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }
    
    @Override
    public String getColumnName(int index) {
        return this.nombresColumnas.get(index);
    }
    @Override
    public int getRowCount() {
        return conceptos.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        MovimientosC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
            	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                value = formatoFecha.format(conceptos.getFecha());
                break;
            case 1:
            	SimpleDateFormat formatoFechaPC = new SimpleDateFormat("dd-MM-yyyy");
                value = formatoFechaPC.format(conceptos.getFechaPC());
                break;
            case 2:
                value = conceptos.getIdProvCli();
                break;
            case 3:
                value = conceptos.getImporte();
                break;
            case 4:
            	value = conceptos.getnFactura();
            	break;
            case 5:
            	value = conceptos.getObservaciones();
            	break;
        }

        return value;

    }

    public MovimientosC getUserAt(int row) {

        return conceptos.get(row);
    }
    

}