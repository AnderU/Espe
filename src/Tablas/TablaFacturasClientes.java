package Tablas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;


import Clases.ConceptosC;
import Clases.DetalleComprasC;
import Clases.DetalleFacturasCliente;
import Clases.FacturasProveedoresTC;
import Clases.GeneroC;


public class TablaFacturasClientes extends AbstractTableModel {

    private Vector<DetalleFacturasCliente> conceptos;
    private Vector<String> nombresColumnas;

    public TablaFacturasClientes(Vector<DetalleFacturasCliente> conceptos,Vector<String> nombresColumnas) {

    	this.conceptos = new Vector<DetalleFacturasCliente>(conceptos);
        this.nombresColumnas= new Vector<String> (nombresColumnas);
        fireTableStructureChanged();
    }

    public void insertRow(DetalleFacturasCliente rowData) {
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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        DetalleFacturasCliente conceptos = this.conceptos.get(rowIndex);
        switch (columnIndex) {

            case 0:
                value = conceptos.getAlbaran();
                break;
            case 1:
            	if (!conceptos.getFecha().equals("NULL"))
            		value = conceptos.getFecha();
            	else
            		value="";
                break;
            case 2:
                value = conceptos.getGenero();
                break;
            case 3:
                value = conceptos.getCantidad();
                break;
            case 4:
            	value = conceptos.getPrecio();
            	break;
            case 5:
            	value = Double.toString(Double.parseDouble(conceptos.getCantidad())*
            			Double.parseDouble(conceptos.getPrecio()));
            	break; 
            case 6:
            	value = conceptos.getIdgenero();
            	break;
        }

        return value;

    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	DetalleFacturasCliente rowVector = (DetalleFacturasCliente) this.conceptos.elementAt(rowIndex);
    	 switch (columnIndex) {
    	 
    	 case 0:
        	 rowVector.setAlbaran((String) aValue);
             break;
         case 1:
        	 SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        	 rowVector.setFecha(formatoFecha.format(aValue));
             break;
         case 2: 
        	 
        	 rowVector.setGenero(((GeneroC)aValue).getGenero());
        	 rowVector.setIdgenero(Integer.toString((((GeneroC)aValue).getId())));
        	 break;
         case 3:
             rowVector.setCantidad((String) aValue);
             break;
         case 4:
        	 rowVector.setPrecio((String) aValue);
         	break;
     }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public DetalleFacturasCliente getUserAt(int row) {

        return conceptos.get(row);
    }
    
    public boolean isCellEditable(int row, int column) {
		
 	    return true;

 }

}