/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana2;

import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 *
 * @author ljmc2
 */
public class Empleado {

    protected String codigo;
    protected String nombre;
    protected Calendar fechaContratacion;
    protected double salarioBase;
    protected double horasTrabajadas;
    protected ImageIcon fotoEmpleado;

    public Empleado(String codigo, String nombre, double salario, ImageIcon foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        salarioBase = salario;
        fotoEmpleado = foto;
        fechaContratacion = Calendar.getInstance();
        horasTrabajadas = 0;
    }

    public void registrarHoras(double horas) {
        horasTrabajadas += horas;
    }

    public double calcularPago() {
        double pago = (horasTrabajadas / 160.0) * salarioBase;
        return pago - (pago * 0.035);//deduccion 3.5%
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Fecha: +" + fechaContratacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public ImageIcon getFoto() {
        return fotoEmpleado;
    }
}
