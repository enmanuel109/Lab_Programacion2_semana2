/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana2;

import javax.swing.ImageIcon;
import java.util.Calendar;

/**
 *
 * @author mavasquez
 */
public class EmpleadoVentas extends Empleado {

    private double tasaComision;
    private double ventasMensuales[];

    public EmpleadoVentas(String codigo, String nombre, double salario, ImageIcon foto, double tasaComision) {
        super(codigo, nombre, salario, foto);
        this.tasaComision = tasaComision;
        ventasMensuales = new double[12];
    }

    public void registroDeVentas(double monto) {
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        double ventasAntes = ventasMensuales[mesActual];
        double nuevasVentas = ventasAntes + monto;
        ventasMensuales[mesActual] = nuevasVentas;
    }

    public double calculoDeComision() {
        double comisionMes = 0;
        int mes = Calendar.getInstance().get(Calendar.MONTH);
        double ventas = ventasMensuales[mes];
        if (ventas != 0) {
            comisionMes = ventas * (tasaComision / 100);
        }
        return comisionMes;
    }

    @Override
    public double calcularPago() {
        double pagoPorHoras = (horasTrabajadas / 160.0) * salarioBase;
        double comisionMes = calculoDeComision();
        double pagoFinal = pagoPorHoras + comisionMes;

        return pagoFinal;
    }

    public double totalVentasAnuales() {
        double total = 0;
        for (int i = 0; i < ventasMensuales.length; i++) {
            total = total + ventasMensuales[i];
        }
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " |  Ventas Anuales: $" + totalVentasAnuales();
    }
}
