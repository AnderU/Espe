package Tablas;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;


import Clases.ConceptosC;
import Clases.FacturasProveedoresTC;


public class TablaFacturasProveedores extends AbstractTableModel {

    private Vector<FacturasProveedoresTC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaFacturasProveedores(Vector<FacturasProveedoresTC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<FacturasProveedoresTC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(FacturasProveedoresTC rowData) {
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
        FacturasProveedoresTC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getIdGenero();
                break;
            case 1:
                value = conceptos.getIdGenero();
                break;
            case 2:
                value = conceptos.getCantidad();
                break;
            case 3:
                value = conceptos.getPrecio();
                break;
            case 4:
            	value = conceptos.getSubtotal();
            	break;
            case 5:
            	value = conceptos.getIva();
            	break;
            case 6:
                value = "incluido";
                break;
            	
        }

        return value;

    }
    


    public FacturasProveedoresTC getUserAt(int row) {

        return conceptos.get(row);
    }

}