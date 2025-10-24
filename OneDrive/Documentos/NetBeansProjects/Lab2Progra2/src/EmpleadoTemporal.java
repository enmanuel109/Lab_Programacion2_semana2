/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eduar
 */

import java.util.Calendar;
import javax.swing.ImageIcon;

public class EmpleadoTemporal extends Empleado{
    
    public Calendar fechafin;
    
    public EmpleadoTemporal(String codigo, String nombre, double salario, ImageIcon fotoEmpleado, Calendar fechafin){
        super( codigo, nombre, salario, fotoEmpleado);
        this.fechafin=Calendar.getInstance();
    }
    
    public double pagocond(){
        Calendar hoy= Calendar.getInstance();
        boolean hoypost= hoy.after(fechafin);
        
        if (hoypost) {
            return 0.0;
        }
        return salarioBase * horasTrabajadas;
    }
    
    public void actualizarfechafin(Calendar nuevafechafin){
        this.fechafin=nuevafechafin;
    }
    
    public String formatearFecha(Calendar c) {
    int año = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH) + 1; // +1 porque enero = 0
    int dia = c.get(Calendar.DAY_OF_MONTH);

    return año + "-" + mes + "-" + dia; 
}
    
    public String infoExtendida() {
        return "Empleado Temporal"+
                "Codigo de Empleado: "+codigo+
                "Nombre de Empleado: "+nombre+
                "Salario Base: "+salarioBase+
                "Horas Trabajadas: "+horasTrabajadas+
                "Fecha de contratacion: "+formatearFecha(fechaContratacion)+
                "Fecha fin: "+formatearFecha(fechafin);
                
                
    }
    
    
    
    
    
}
