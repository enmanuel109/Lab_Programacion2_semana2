/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cantarero
 */
public class EmpresaGUI extends JFrame {

    public EmpresaGUI() {
        setTitle("Gestión de Empleados - Empresa");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(null); // Usaremos posicionamiento absoluto (setBounds)
        JPanel almacen = new JPanel();
        almacen.setLayout(null);
        almacen.setBounds(0, 0, 800, 800);
        almacen.setBackground(Color.LIGHT_GRAY);

        JLabel Label_Registrar_empleado = new JLabel("Registrar Empleado");
        Label_Registrar_empleado.setBounds(70, 20, 300, 100);
        Label_Registrar_empleado.setFont(new Font("Serif", Font.BOLD, 20));
        Label_Registrar_empleado.setForeground(Color.BLACK);
        almacen.add(Label_Registrar_empleado);

        JLabel Label_txt_nombre = new JLabel("Escriba el nombre del empleado:");
        Label_txt_nombre.setBounds(70, 50, 220, 100);
        Label_txt_nombre.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt_nombre.setForeground(Color.BLACK);
        almacen.add(Label_txt_nombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(70, 110, 220, 30);
        almacen.add(txtNombre);

        JLabel Label_txt_codigo = new JLabel("Escriba el código del empleado:");
        Label_txt_codigo.setBounds(400, 50, 200, 100);
        Label_txt_codigo.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt_codigo.setForeground(Color.BLACK);
        almacen.add(Label_txt_codigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(400, 110, 220, 30);
        almacen.add(txtCodigo);

        JLabel Label_txt_Fecha_ini = new JLabel("Escriba la fecha de contratación del empleado:");
        Label_txt_Fecha_ini.setBounds(70, 100, 350, 100);
        Label_txt_Fecha_ini.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt_Fecha_ini.setForeground(Color.BLACK);
        almacen.add(Label_txt_Fecha_ini);

        JTextField txtFecha = new JTextField();
        txtFecha.setBounds(70, 160, 220, 30);
        almacen.add(txtFecha);

        JLabel Label_txt_Salario_base = new JLabel("Escriba el salario base del empleado:");
        Label_txt_Salario_base.setBounds(400, 100, 300, 100);
        Label_txt_Salario_base.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt_Salario_base.setForeground(Color.BLACK);
        almacen.add(Label_txt_Salario_base);

        JTextField txtsalariobase = new JTextField();
        txtsalariobase.setBounds(400, 160, 220, 30);
        almacen.add(txtsalariobase);

        JLabel Label_txt_Horas_trb = new JLabel("Escriba las horas trabajadas del empleado:");
        Label_txt_Horas_trb.setBounds(70, 150, 350, 100);
        Label_txt_Horas_trb.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt_Horas_trb.setForeground(Color.BLACK);
        almacen.add(Label_txt_Horas_trb);

        JTextField txtHorastrb = new JTextField();
        txtHorastrb.setBounds(70, 210, 220, 30);
        almacen.add(txtHorastrb);

        File file = new File(rutaDeLaImgit agen);
        Image imagen = ImageIO.read(file);

        add(almacen);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmpresaGUI().setVisible(true);
        });
    }
}
