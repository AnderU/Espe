
package Tablas;

import java.util.Date;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import Clases.ComprasC;
import Clases.ConceptosC;

public class TablaListadoComprasResumen extends AbstractTableModel {

    private Vector<ComprasC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaListadoComprasResumen(Vector<ComprasC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<ComprasC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(ComprasC rowData) {
    	conceptos.addElement(rowData);
        fireTableRowsInserted(conceptos.size(),conceptos.size());
    }
    
    public void removeRow(int row) {
    	conceptos.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }
    
    
    @Override
    public int getRowCount() {
        return conceptos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        ComprasC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
            	value = conceptos.getFecha();
                break;
            case 1:
            	value = conceptos.getProveedor();
                break;
            case 2:
            	value = conceptos.getImporte();
                break;
            case 3:
            	value = conceptos.getIva();
            	break;
            case 4:
            	value = conceptos.getImpuestos();
            	break;
            case 5:
            	value = (1+(Double.parseDouble(conceptos.getIva())/100))*(1+(Double.parseDouble(conceptos.getImpuestos())/100))*Double.parseDouble(conceptos.getImporte());
            	break;
            case 6:
            	value = conceptos.getIdproveedor();
            	break;
            case 7:
            	value = conceptos.getId();
            	break;
            	
        }

        return value;

    }
    
    @Override
    public String getColumnName(int index) {
        return this.nombresColumnas.get(index);
    }


    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public ComprasC getUserAt(int row) {

        return conceptos.get(row);
    }

}

