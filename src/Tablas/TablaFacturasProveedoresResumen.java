
package Tablas;

import java.text.SimpleDateFormat;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;


import Clases.ConceptosC;
import Clases.DetalleComprasC;
import Clases.FacturasProveedoresC;
import Clases.FacturasProveedoresTC;
import Clases.GeneroC;


public class TablaFacturasProveedoresResumen extends AbstractTableModel {

    private Vector<FacturasProveedoresC> conceptos;
    private Vector<String> nombresColumnas;

    public TablaFacturasProveedoresResumen(Vector<FacturasProveedoresC> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<FacturasProveedoresC>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(FacturasProveedoresC rowData) {
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
        FacturasProveedoresC conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getFecha();
                break;
            case 1:
                value = conceptos.getProveedor();
                break;
            case 2:
                value = conceptos.getnFactura();
                break;
            case 3:
                value = conceptos.getFechaPago();
            	break;
            case 4:
            	value = conceptos.getIva();
            	break;
            case 5:
                value =conceptos.getImpuestos();
                break;
            case 6:
                value =conceptos.getTotal();
                break;
            case 7:
                value =conceptos.getIdFactProv();
                break;  
            case 8:
                value =conceptos.getIdProveedor();
                break;
            case 9:
                value =conceptos.getIdFormaPago();
                break;  
            case 10:
                value =conceptos.getObservaciones();
                break;  
        }

        return value;

    }
    
        
    

    public FacturasProveedoresC getUserAt(int row) {

        return conceptos.get(row);
    }
    
    public boolean isCellEditable(int row, int column) {
    		
     	    return false;

     }

}