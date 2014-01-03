
package Tablas;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import Clases.DetalleComprasC;
import Clases.GeneroC;



public class TablaDetalleCompra extends AbstractTableModel {

    private Vector<DetalleComprasC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaDetalleCompra(Vector<DetalleComprasC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<DetalleComprasC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(DetalleComprasC rowData) {
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        DetalleComprasC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                
            	value = conceptos.getGenero();

                break;
            case 1:
                value = conceptos.getCantidad();
                break;
            case 2:
                value = conceptos.getPrecio();
                break;
            case 3:
            	if (conceptos.getPrecio().equals("") || conceptos.getCantidad().equals(""))
            		value="";
            	else
            		value =Double.toString(Double.parseDouble(conceptos.getPrecio())*Double.parseDouble(conceptos.getCantidad()));
                break;
            case 4:
            	value= conceptos.getIdGenero();
                break;
        }

        return value;

    }
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
   /* @Override
    public Class<?> getColumnClass(int columnIndex) {
        return // Return the class that best represents the column...
    }
    */
    //Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	DetalleComprasC rowVector = (DetalleComprasC) this.conceptos.elementAt(rowIndex);
    	 switch (columnIndex) {

         case 0:
        	 rowVector.setGenero(((GeneroC)aValue).getGenero());
        	 rowVector.setIdGenero(Integer.toString(((GeneroC)aValue).getId()));
             break;
         case 1:
        	 rowVector.setCantidad((String) aValue);
             break;
         case 2:
        	 rowVector.setPrecio((String) aValue);
             break;
     }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }


    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public DetalleComprasC getUserAt(int row) {

        return conceptos.get(row);
    }
    
    public boolean isCellEditable(int row, int column) {
       if (column==3)
    	   return false;
       else
    	   return true;
    }
}