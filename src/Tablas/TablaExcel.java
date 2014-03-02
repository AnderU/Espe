
package Tablas;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import Clases.DetalleComprasC;
import Clases.ExcelC;
import Clases.GeneroC;



public class TablaExcel extends AbstractTableModel {

    private Vector<ExcelC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaExcel(Vector<ExcelC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<ExcelC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(ExcelC rowData) {
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
        ExcelC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {
        
        case 0:
        	value = conceptos.getFecha();
            break;
        case 1:
        	value = conceptos.getConcepto();
            break;
        case 2:
            value = conceptos.getImporte();
            break;
            
        }

        return value;

    }
    @Override
    public String getColumnName(int index) {
        return this.nombresColumnas.get(index);
    }

   /* @Override
    public Class<?> getColumnClass(int columnIndex) {
        return // Return the class that best represents the column...
    }
    */
    //Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	ExcelC rowVector = (ExcelC) this.conceptos.elementAt(rowIndex);
    	 switch (columnIndex) {

         case 0:
        	 rowVector.setFecha((String)aValue);
             break;
         case 1:
        	 rowVector.setConcepto((String)aValue);
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
    public ExcelC getUserAt(int row) {

        return conceptos.get(row);
    }
    
    public boolean isCellEditable(int row, int column) {
       if (column==3)
    	   return false;
       else
    	   return true;
    }
}