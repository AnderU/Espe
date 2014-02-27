package Tablas;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import Clases.FacturasClientesC;





public class TablaFacturasClientesResumen extends AbstractTableModel {

    private Vector<FacturasClientesC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaFacturasClientesResumen(Vector<FacturasClientesC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<FacturasClientesC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(FacturasClientesC rowData) {
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
        FacturasClientesC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getFecha();
                break;
            case 1:
                value = conceptos.getCliente();
                break;
            case 2:
                value = conceptos.getnFactura();
                break;
            case 3:
                value = conceptos.getFechaCobro();
            	break;
            case 4:
            	value = conceptos.getIva();
            	break;
            case 5:
                value =conceptos.getTotal();
                break;
            case 6:
                value =conceptos.getIdFactCli();
                break;  
            case 7:
                value =conceptos.getIdCliente();
                break;
            case 8:
                value =conceptos.getIdFormaCobro();
                break;  
            case 9:
                value =conceptos.getObservaciones();
                break;  
        }

        return value;

    }
    
        
    

    public FacturasClientesC getUserAt(int row) {

        return conceptos.get(row);
    }
    
    public boolean isCellEditable(int row, int column) {
    		
     	    return false;

     }

}