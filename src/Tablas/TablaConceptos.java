package Tablas;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import Clases.ConceptosC;


public class TablaConceptos extends AbstractTableModel {

    private Vector<ConceptosC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaConceptos(Vector<ConceptosC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<ConceptosC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(ConceptosC rowData) {
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        ConceptosC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getConcepto();
                break;
            case 1:
                value = conceptos.getPatron();
                break;
            case 2:
                value = conceptos.getTipo();
                break;
            case 3:
                value = conceptos.getGrupo();
                break;
            case 4:
            	value = conceptos.getIdTipo();
            	break;
            case 5:
            	value = conceptos.getIdGrupo();
            	break;
            case 6:
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
    	ConceptosC rowVector = (ConceptosC) this.conceptos.elementAt(rowIndex);
    	 switch (columnIndex) {

         case 0:
        	 rowVector.setConcepto((String) aValue);
             break;
         case 1:
        	 rowVector.setPatron((String) aValue);
             break;
         case 2:
        	 rowVector.setTipo((String) aValue);
             break;
         case 3:
        	 rowVector.setGrupo((String) aValue);
             break;
         	
     }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }


    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public ConceptosC getUserAt(int row) {

        return conceptos.get(row);
    }

}