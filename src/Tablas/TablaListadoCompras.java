
package Tablas;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import Clases.ComprasC;
import Clases.ConceptosC;

public class TablaListadoCompras extends AbstractTableModel {

    private Vector<ComprasC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaListadoCompras(Vector<ComprasC> conceptos,Vector<String> nombresColumnas) {

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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        ComprasC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getProveedor();
                break;
            case 1:
                value = conceptos.getIva();
                break;
            case 2:
                value = conceptos.getImporte();
                break;
            case 3:
            	 value = conceptos.getId();
            	break;
            case 4:
            	value = conceptos.getImpuestos();
            	break;
            case 5:
            	value = conceptos.getIdproveedor();
            	break;
            	
        }

        return value;

    }
    
    @Override
    public String getColumnName(int index) {
        return this.nombresColumnas.get(index);
    }
    //Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	ComprasC rowVector = (ComprasC) this.conceptos.elementAt(rowIndex);
    	 switch (columnIndex) {

         case 0:
        	 rowVector.setProveedor((String) aValue);
             break;
         case 1:
        	 rowVector.setIva((String) aValue);
             break;
         case 2:
        	 rowVector.setImporte((String) aValue);
             break;
         	
     }
        
        fireTableCellUpdated(rowIndex, columnIndex);
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

