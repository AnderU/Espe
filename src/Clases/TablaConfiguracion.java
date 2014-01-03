package Clases;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;


public class TablaConfiguracion extends AbstractTableModel {

    private Vector<ConfiguracionC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaConfiguracion(Vector<ConfiguracionC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<ConfiguracionC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(ConfiguracionC rowData) {
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
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        ConfiguracionC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getNombre();
                break;
            case 1:
                value = conceptos.getValor();
                break;
            case 2:
                value = conceptos.getId();
                break;
        }

        return value;

    }

   /* @Override
    public Class<?> getColumnClass(int columnIndex) {
        return // Return the class that best represents the column...
    }
    */
    //Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	ConfiguracionC rowVector = (ConfiguracionC) this.conceptos.elementAt(rowIndex);
    	 switch (columnIndex) {

         case 0:
        	 rowVector.setNombre((String) aValue);
             break;
         case 1:
        	 rowVector.setValor((String) aValue);
             break;
         case 2:
        	 rowVector.setId((String) aValue);
             break;
     }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }


    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public ConfiguracionC getUserAt(int row) {

        return conceptos.get(row);
    }
    
    public boolean isCellEditable(int row, int column) {
        return true;
    }

}