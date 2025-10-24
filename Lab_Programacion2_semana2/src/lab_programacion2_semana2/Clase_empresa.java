/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana2;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cantarero
 */
public class Clase_empresa extends JFrame {

    public Clase_empresa() {
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
        Label_Registrar_empleado.setBounds(100, 20, 300, 100);
        Label_Registrar_empleado.setFont(new Font("Serif", Font.BOLD, 20));
        Label_Registrar_empleado.setForeground(Color.BLACK);
        almacen.add(Label_Registrar_empleado);

        JLabel Label_txt = new JLabel("Escriba el nombre del empleado:");
        Label_txt.setBounds(100, 50, 300, 100);
        Label_txt.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt.setForeground(Color.BLACK);
        almacen.add(Label_txt);
        
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(100, 110, 300, 30);
        almacen.add(txtNombre);

         JLabel Label_Registrar_empleado = new JLabel("Registrar Empleado");
        Label_Registrar_empleado.setBounds(100, 20, 300, 100);
        Label_Registrar_empleado.setFont(new Font("Serif", Font.BOLD, 20));
        Label_Registrar_empleado.setForeground(Color.BLACK);
        almacen.add(Label_Registrar_empleado);

        JLabel Label_txt = new JLabel("Escriba el nombre del empleado:");
        Label_txt.setBounds(100, 50, 300, 100);
        Label_txt.setFont(new Font("Serif", Font.BOLD, 15));
        Label_txt.setForeground(Color.BLACK);
        almacen.add(Label_txt);
        
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(100, 110, 300, 30);
        almacen.add(txtNombre);
        
        add(almacen);

        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Clase_empresa().setVisible(true);
        });
    }
}
