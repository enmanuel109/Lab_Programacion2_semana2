package lab_programacion2_semana2;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.lang.reflect.Method;

/**
 *
 * @author Cantarero
 */
public class EmpresaGUI extends JFrame {

    private List<Empleado> empleados = new ArrayList<>();

    //paneles
    private JPanel panelNavegacion, panelCentral;
    private CardLayout cardLayout;
    private JLabel lblEstado;

    //nombres de tarjetas
    private final String TAR_REGISTRAR = "RegistrarEmpleado";
    private final String TAR_HORAS = "RegistrarHoras";
    private final String TAR_VENTAS = "RegistrarVentas";
    private final String TAR_CONTRATO = "ActualizarContrato";
    private final String TAR_PAGO = "CalcularPago";
    private final String TAR_REPORTES = "Reportes";

    //botones de opcion
    private JButton btnRegistrar, btnHoras, btnVentas, btnContrato, btnPago, btnReportes;

    //subpaneles
    private PanelRegistroEmpleado panelRegistroEmpleado;
    private PanelRegistrarHoras panelRegistrarHoras;
    private PanelRegistrarVentas panelRegistrarVentas;
    private PanelActualizarContrato panelActualizarContrato;
    private PanelCalcularPago panelCalcularPago;
    private PanelReportes panelReportes;

    public EmpresaGUI() {
        super("EmpresaApp");
        initUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        //panel de botones superior
        panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        addButtonsToNav();

        lblEstado = new JLabel("REGISTRO DE EMPLEADO");
        lblEstado.setFont(lblEstado.getFont().deriveFont(Font.BOLD, 16f));

        JPanel panelTituloLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTituloLabel.add(lblEstado);

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.add(panelNavegacion, BorderLayout.NORTH);
        panelTitulo.add(panelTituloLabel, BorderLayout.SOUTH);

        add(panelTitulo, BorderLayout.NORTH);

        //panel central con tarjetas
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);

        panelRegistroEmpleado = new PanelRegistroEmpleado();
        panelRegistrarHoras = new PanelRegistrarHoras();
        panelRegistrarVentas = new PanelRegistrarVentas();
        panelActualizarContrato = new PanelActualizarContrato();
        panelCalcularPago = new PanelCalcularPago();
        panelReportes = new PanelReportes();

        panelCentral.add(panelRegistroEmpleado, TAR_REGISTRAR);
        panelCentral.add(panelRegistrarHoras, TAR_HORAS);
        panelCentral.add(panelRegistrarVentas, TAR_VENTAS);
        panelCentral.add(panelActualizarContrato, TAR_CONTRATO);
        panelCentral.add(panelCalcularPago, TAR_PAGO);
        panelCentral.add(panelReportes, TAR_REPORTES);

        add(panelCentral, BorderLayout.CENTER);
    }

    private void addButtonsToNav() {
        btnRegistrar = new JButton("Registrar Empleado");
        btnHoras = new JButton("Registrar Horas");
        btnVentas = new JButton("Registrar Ventas");
        btnContrato = new JButton("Actualizar Contrato");
        btnPago = new JButton("Calcular Pago");
        btnReportes = new JButton("Generar Reportes");

        JButton[] botones = {btnRegistrar, btnHoras, btnVentas, btnContrato, btnPago, btnReportes};

        for (JButton b : botones) {
            b.addActionListener(e -> {
                if (b == btnRegistrar) { cardLayout.show(panelCentral, TAR_REGISTRAR); lblEstado.setText("REGISTRO DE EMPLEADO"); }
                else if (b == btnHoras) { cardLayout.show(panelCentral, TAR_HORAS); lblEstado.setText("REGISTRO DE HORAS"); }
                else if (b == btnVentas) { cardLayout.show(panelCentral, TAR_VENTAS); lblEstado.setText("REGISTRO DE VENTAS"); }
                else if (b == btnContrato) { cardLayout.show(panelCentral, TAR_CONTRATO); lblEstado.setText("ACTUALIZAR FECHA DE FIN DE CONTRATO"); }
                else if (b == btnPago) { cardLayout.show(panelCentral, TAR_PAGO); lblEstado.setText("CALCULO DE PAGO"); }
                else if (b == btnReportes) { cardLayout.show(panelCentral, TAR_REPORTES); lblEstado.setText("REPORTES"); }
            });
            panelNavegacion.add(b);
        }
    }

    //panel registro
    public class PanelRegistroEmpleado extends JPanel {
        JTextField txtCodigo, txtNombre, txtSalarioBase, txtComision;
        JDateChooser dcFechaContratacion, dcFechaFin;
        JLabel lblCodigo, lblNombre, lblSalario, lblFoto, lblTipo, lblFechaFin, lblComision;
        JButton btnSeleccionarFoto, btnRegistrar, btnLimpiar;
        JComboBox<String> cbTipoEmpleado;
        private JPanel panelFechaContratacion, panelFechaFin;

        public PanelRegistroEmpleado() {
            setLayout(new BorderLayout(6,6));
            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(4,4,4,4);
            gbc.anchor = GridBagConstraints.WEST;
            int row = 0;

            txtCodigo = new JTextField(8);
            lblCodigo = new JLabel("Código:");
            putLabel(form, gbc, row++, lblCodigo, txtCodigo);

            txtNombre = new JTextField(12);
            lblNombre = new JLabel("Nombre:");
            putLabel(form, gbc, row++, lblNombre, txtNombre);

            dcFechaContratacion = new JDateChooser();
            dcFechaContratacion.setDateFormatString("yyyy-MM-dd");
            JTextField editorContratacion = (JTextField) dcFechaContratacion.getDateEditor().getUiComponent();
            editorContratacion.setEditable(false); editorContratacion.setForeground(Color.BLACK);
            panelFechaContratacion = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            panelFechaContratacion.add(dcFechaContratacion.getCalendarButton());
            panelFechaContratacion.add(editorContratacion);
            putLabel(form, gbc, row++, new JLabel("Fecha de contratación:"), panelFechaContratacion);

            txtSalarioBase = new JTextField(8);
            lblSalario = new JLabel("Salario base:");
            putLabel(form, gbc, row++, lblSalario, txtSalarioBase);

            // --- FOTO ---
            lblFoto = new JLabel();
            lblFoto.setPreferredSize(new Dimension(100,100));
            lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
            lblFoto.setText("Sin imagen");

            btnSeleccionarFoto = new JButton("Seleccionar imagen");

            JPanel fotoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,4,0));
            fotoPanel.add(lblFoto);
            fotoPanel.add(btnSeleccionarFoto);
            putLabel(form, gbc, row++, new JLabel("Foto:"), fotoPanel);

            btnSeleccionarFoto.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Seleccionar foto de empleado");
                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Imágenes (*.jpg, *.png, *.jpeg)", "jpg", "png", "jpeg"));
                int result = chooser.showOpenDialog(this);
                if(result == JFileChooser.APPROVE_OPTION){
                    java.io.File file = chooser.getSelectedFile();
                    ImageIcon original = new ImageIcon(file.getAbsolutePath());
                    Image img = original.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                    ImageIcon scaled = new ImageIcon(img);
                    lblFoto.setIcon(scaled);
                    lblFoto.setText("");
                    lblFoto.putClientProperty("fotoSeleccionada", original);
                }
            });

            cbTipoEmpleado = new JComboBox<>(new String[]{"Estándar","Temporal","Ventas"});
            lblTipo = new JLabel("Tipo de empleado:");
            putLabel(form, gbc, row++, lblTipo, cbTipoEmpleado);

            dcFechaFin = new JDateChooser(); dcFechaFin.setDateFormatString("yyyy-MM-dd");
            JTextField editorFechaFin = (JTextField) dcFechaFin.getDateEditor().getUiComponent();
            editorFechaFin.setEditable(false); editorFechaFin.setForeground(Color.BLACK);
            panelFechaFin = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            panelFechaFin.add(dcFechaFin.getCalendarButton()); panelFechaFin.add(editorFechaFin);
            lblFechaFin = new JLabel("Fecha fin (temporal):");
            putLabel(form, gbc, row++, lblFechaFin, panelFechaFin);

            txtComision = new JTextField(4); txtComision.setText("5"); txtComision.setEditable(false);
            lblComision = new JLabel("Tasa de comisión (%):");
            putLabel(form, gbc, row++, lblComision, txtComision);

            setCamposVisibles(false,false);
            cbTipoEmpleado.addActionListener(e -> {
                String tipo = (String) cbTipoEmpleado.getSelectedItem();
                setCamposVisibles("Temporal".equals(tipo), "Ventas".equals(tipo));
                revalidate(); repaint();
            });

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            btnRegistrar = new JButton("Registrar");
            btnLimpiar = new JButton("Limpiar campos");
            botones.add(btnRegistrar); botones.add(btnLimpiar);

            add(form, BorderLayout.CENTER); add(botones, BorderLayout.SOUTH);

            //listeners-----------------------
            btnRegistrar.addActionListener(e -> {
                String codigo = txtCodigo.getText().trim();
                String nombre = txtNombre.getText().trim();
                double salario;
                try { salario = Double.parseDouble(txtSalarioBase.getText()); }
                catch(Exception ex) { JOptionPane.showMessageDialog(this,"Salario inválido"); return; }

                ImageIcon foto = (ImageIcon) lblFoto.getClientProperty("fotoSeleccionada");
                String tipo = (String) cbTipoEmpleado.getSelectedItem();
                Empleado emp = null;

                if(tipo.equals("Estándar")) emp = new Empleado(codigo,nombre,salario,foto);
                else if(tipo.equals("Temporal")){
                    Date fFin = dcFechaFin.getDate();
                    if(fFin==null){ JOptionPane.showMessageDialog(this,"Fecha fin requerida"); return; }
                    Calendar calFin = Calendar.getInstance(); calFin.setTime(fFin);
                    emp = new EmpleadoTemporal(codigo,nombre,salario,foto,calFin);
                } else if(tipo.equals("Ventas")){
                    double tasa;
                    try{tasa = Double.parseDouble(txtComision.getText());} catch(Exception ex){JOptionPane.showMessageDialog(this,"Comisión inválida"); return;}
                    emp = new EmpleadoVentas(codigo,nombre,salario,foto,tasa);
                }

                for(Empleado e1: empleados){
                    if(e1.getCodigo().equals(codigo)){
                        JOptionPane.showMessageDialog(this,"Código ya existe"); return;
                    }
                }

                empleados.add(emp);
                JOptionPane.showMessageDialog(this,"Empleado registrado correctamente");
            });

            btnLimpiar.addActionListener(e -> {
                txtCodigo.setText(""); txtNombre.setText(""); txtSalarioBase.setText("");
                txtComision.setText("5"); dcFechaFin.setDate(null);
                lblFoto.setIcon(null); lblFoto.setText("Sin imagen");
                lblFoto.putClientProperty("fotoSeleccionada", null);
            });
        }

        private void putLabel(JPanel panel, GridBagConstraints gbc, int row, JLabel label, Component comp){
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0;
            panel.add(label,gbc);
            gbc.gridx=1; gbc.weightx=1; gbc.fill=GridBagConstraints.HORIZONTAL;
            panel.add(comp,gbc);
            gbc.fill=GridBagConstraints.NONE;
        }

        private void setCamposVisibles(boolean mostrarFechaFin, boolean mostrarComision){
            lblFechaFin.setVisible(mostrarFechaFin); panelFechaFin.setVisible(mostrarFechaFin);
            lblComision.setVisible(mostrarComision); txtComision.setVisible(mostrarComision);
        }
    }

    //registrar horas 
    public class PanelRegistrarHoras extends JPanel {
        JTextField txtCodigo, txtHoras;
        JButton btnRegistrarHoras, btnLimpiar;

        public PanelRegistrarHoras(){
            setLayout(new BorderLayout(6,6));
            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets=new Insets(4,4,4,4); gbc.anchor=GridBagConstraints.WEST; gbc.fill=GridBagConstraints.HORIZONTAL;
            int row=0;

            JLabel lblCodigo = new JLabel("Código del empleado:");
            txtCodigo = new JTextField(8);
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0; form.add(lblCodigo,gbc);
            gbc.gridx=1; gbc.weightx=1; form.add(txtCodigo,gbc); row++;

            JLabel lblHoras = new JLabel("Horas trabajadas:");
            txtHoras = new JTextField(5);
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0; form.add(lblHoras,gbc);
            gbc.gridx=1; gbc.weightx=1; form.add(txtHoras,gbc); row++;

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            btnRegistrarHoras = new JButton("Registrar Horas");
            btnLimpiar = new JButton("Limpiar");
            botones.add(btnRegistrarHoras); botones.add(btnLimpiar);

            add(form,BorderLayout.CENTER); add(botones,BorderLayout.SOUTH);

            btnRegistrarHoras.addActionListener(e -> {
                String codigo = txtCodigo.getText().trim();
                double horas;
                try{horas = Double.parseDouble(txtHoras.getText());} catch(Exception ex){JOptionPane.showMessageDialog(this,"Horas inválidas"); return;}
                Empleado emp = null;
                for(Empleado e1: empleados){ if(e1.getCodigo().equals(codigo)){ emp=e1; break; } }
                if(emp==null){ JOptionPane.showMessageDialog(this,"Empleado no encontrado"); return; }
                emp.registrarHoras(horas);
                JOptionPane.showMessageDialog(this,"Horas registradas");
            });

            btnLimpiar.addActionListener(e -> { txtCodigo.setText(""); txtHoras.setText(""); });
        }
    }

    //registrar ventas
    public class PanelRegistrarVentas extends JPanel {
        JTextField txtCodigo, txtMonto;
        JButton btnRegistrarVenta;

        public PanelRegistrarVentas(){
            setLayout(new BorderLayout(6,6));
            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets=new Insets(4,4,4,4); gbc.anchor=GridBagConstraints.WEST; gbc.fill=GridBagConstraints.HORIZONTAL;
            int row=0;

            JLabel lblCodigo = new JLabel("Código del empleado:");
            txtCodigo = new JTextField(8);
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0; form.add(lblCodigo,gbc);
            gbc.gridx=1; gbc.weightx=1; form.add(txtCodigo,gbc); row++;

            JLabel lblMonto = new JLabel("Monto de venta:");
            txtMonto = new JTextField(8);
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0; form.add(lblMonto,gbc);
            gbc.gridx=1; gbc.weightx=1; form.add(txtMonto,gbc); row++;

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            btnRegistrarVenta = new JButton("Registrar Venta");
            botones.add(btnRegistrarVenta);

            add(form,BorderLayout.CENTER); add(botones,BorderLayout.SOUTH);

            btnRegistrarVenta.addActionListener(e -> {
                String codigo = txtCodigo.getText().trim();
                double monto;
                try{monto=Double.parseDouble(txtMonto.getText());} catch(NumberFormatException ex){JOptionPane.showMessageDialog(this,"Monto inválido"); return;}
                Empleado emp = null;
                for(Empleado e1: empleados){ if(e1.getCodigo().equals(codigo)){ emp=e1; break; } }
                if(emp==null){ JOptionPane.showMessageDialog(this,"Empleado no encontrado"); return; }
                if(emp instanceof EmpleadoVentas empleadoVentas){
                    empleadoVentas.registroDeVentas(monto);
                    JOptionPane.showMessageDialog(this,"Venta registrada");
                } else { JOptionPane.showMessageDialog(this,"Empleado no es de tipo ventas"); }
            });
        }
    }

    //actualizar contrato
    public class PanelActualizarContrato extends JPanel {
        JTextField txtCodigo;
        JDateChooser dcNuevaFechaFin;
        JButton btnActualizar;

        public PanelActualizarContrato(){
            setLayout(new BorderLayout(6,6));
            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets=new Insets(4,4,4,4); gbc.anchor=GridBagConstraints.WEST; gbc.fill=GridBagConstraints.HORIZONTAL;
            int row=0;

            JLabel lblCodigo = new JLabel("Código del empleado:");
            txtCodigo = new JTextField(8);
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0; form.add(lblCodigo,gbc);
            gbc.gridx=1; gbc.weightx=1; form.add(txtCodigo,gbc); row++;

            JLabel lblFecha = new JLabel("Nueva fecha de fin:");
            dcNuevaFechaFin = new JDateChooser(new Date()); dcNuevaFechaFin.setDateFormatString("yyyy-MM-dd");
            JTextFieldDateEditor editor = (JTextFieldDateEditor) dcNuevaFechaFin.getDateEditor();
            editor.setEditable(false); editor.setForeground(Color.BLACK);
            JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            panelFecha.add(dcNuevaFechaFin.getCalendarButton()); panelFecha.add(editor.getUiComponent());
            gbc.gridx=0; gbc.gridy=row; gbc.weightx=0; form.add(lblFecha,gbc);
            gbc.gridx=1; gbc.weightx=1; form.add(panelFecha,gbc); row++;

            btnActualizar = new JButton("Actualizar");
            JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            botones.add(btnActualizar);

            add(form,BorderLayout.CENTER); add(botones,BorderLayout.SOUTH);

            btnActualizar.addActionListener(e -> {
                String codigo = txtCodigo.getText().trim();
                Calendar cal = Calendar.getInstance(); cal.setTime(dcNuevaFechaFin.getDate());
                Empleado emp = null;
                for(Empleado e1: empleados){ if(e1.getCodigo().equals(codigo)){ emp=e1; break; } }
                if(emp==null){ JOptionPane.showMessageDialog(this,"Empleado no encontrado"); return; }
                if(emp instanceof EmpleadoTemporal empleadoTemporal){
                    empleadoTemporal.actualizarfechafin(cal);
                    JOptionPane.showMessageDialog(this,"Fecha actualizada correctamente");
                } else { JOptionPane.showMessageDialog(this,"Empleado no es temporal"); }
            });
        }
    }

    //calcular pago
    public class PanelCalcularPago extends JPanel {
        JTextField txtCodigo;
        JButton btnCalcularPago;
        JTextArea salida;

        public PanelCalcularPago(){
            setLayout(new BorderLayout(6,6));
            JPanel top = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets=new Insets(4,4,4,4); gbc.anchor=GridBagConstraints.WEST; gbc.fill=GridBagConstraints.HORIZONTAL;

            JLabel lblCodigo = new JLabel("Código del empleado:");
            txtCodigo = new JTextField(8);
            gbc.gridx=0; gbc.gridy=0; gbc.weightx=0; top.add(lblCodigo,gbc);
            gbc.gridx=1; gbc.weightx=1; top.add(txtCodigo,gbc);

            btnCalcularPago = new JButton("Calcular Pago");
            gbc.gridx=2; gbc.weightx=0; top.add(btnCalcularPago,gbc);

            salida = new JTextArea(6,40); salida.setEditable(false); salida.setBorder(BorderFactory.createTitledBorder("Resultado"));

            add(top,BorderLayout.NORTH); add(new JScrollPane(salida),BorderLayout.CENTER);

            btnCalcularPago.addActionListener(e -> {
                String codigo = txtCodigo.getText().trim();
                Empleado emp = null;
                for(Empleado e1: empleados){ if(e1.getCodigo().equals(codigo)){ emp=e1; break; } }
                if(emp==null){ salida.setText("Empleado no encontrado"); return; }
                double pago = emp.calcularPago();
                salida.setText("Empleado: " + emp.getNombre() + "\nPago mensual: $" + pago);
            });
        }
    }

    //reportes (ahora con miniaturas)
    public class PanelReportes extends JPanel {
        JTable table;
        DefaultTableModel tableModel;
        JLabel lblTotales;
        JButton btnActualizar;

        PanelReportes(){
            setLayout(new BorderLayout(6,6));
            String[] cols = {"Foto","Código","Nombre","Tipo","Salario","Horas","Comisión"};
            tableModel = new DefaultTableModel(cols,0){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if(columnIndex == 0) return ImageIcon.class; // columna foto
                    if(columnIndex == 4 || columnIndex == 6) return Double.class; // salario, comisión
                    if(columnIndex == 5) return Double.class; // horas
                    return Object.class;
                }
                @Override
                public boolean isCellEditable(int row, int column) { return false; }
            };
            table = new JTable(tableModel);
            table.setRowHeight(64);
            add(new JScrollPane(table),BorderLayout.CENTER);

            JPanel lateral = new JPanel(); lateral.setLayout(new BoxLayout(lateral,BoxLayout.Y_AXIS));
            lblTotales = new JLabel("Totales: Std=0  Temp=0  Ventas=0  Total=0"); lateral.add(lblTotales);
            lateral.add(Box.createVerticalStrut(10));
            btnActualizar = new JButton("Actualizar Lista"); lateral.add(btnActualizar);
            lateral.add(Box.createVerticalGlue());
            add(lateral,BorderLayout.EAST);

            btnActualizar.addActionListener(e -> {
                tableModel.setRowCount(0);
                int totStd=0, totTemp=0, totVentas=0;
                for(Empleado emp: empleados){
                    String tipo="Estándar";
                    double comision = 0;
                    // detectar tipo
                    if(emp instanceof EmpleadoTemporal) tipo="Temporal";
                    if(emp instanceof EmpleadoVentas) tipo="Ventas";

                    // intentar obtener comisión (primero metodo calcularComision(), luego getComision())
                    if(emp instanceof EmpleadoVentas){
                        try {
                            Method mCalc = emp.getClass().getMethod("calcularComision");
                            Object res = mCalc.invoke(emp);
                            if(res instanceof Number) comision = ((Number)res).doubleValue();
                        } catch (Exception ex) {
                            // si no existe calcularComision, probar getComision
                            try {
                                Method mGet = emp.getClass().getMethod("getComision");
                                Object res2 = mGet.invoke(emp);
                                if(res2 instanceof Number) comision = ((Number)res2).doubleValue();
                            } catch (Exception ex2) {
                                // si tampoco existe, dejar 0
                                comision = 0;
                            }
                        }
                    }

                    // obtener horas trabajadas (intentar getHorasTrabajadas)
                    double horas = 0;
                    try {
                        Method mHoras = emp.getClass().getMethod("getHorasTrabajadas");
                        Object rh = mHoras.invoke(emp);
                        if(rh instanceof Number) horas = ((Number)rh).doubleValue();
                    } catch (Exception ignored) { /* si no existe, queda 0 */ }

                    // obtener la foto mediante reflexión (soporta getFotoEmpleado() o getFoto())
                    ImageIcon fotoIcon = fetchFoto(emp);
                    ImageIcon thumb = null;
                    if(fotoIcon != null){
                        Image img = fotoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        thumb = new ImageIcon(img);
                    } else {
                        // placeholder gris con texto N/A
                        java.awt.image.BufferedImage bi = new BufferedImage(60,60, BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = bi.createGraphics();
                        g.setColor(Color.LIGHT_GRAY); g.fillRect(0,0,60,60);
                        g.setColor(Color.DARK_GRAY); g.drawString("N/A", 18, 34);
                        g.dispose();
                        thumb = new ImageIcon(bi);
                    }

                    // calcular pago (usar método existente)
                    double pago = 0;
                    try {
                        Method mPago = emp.getClass().getMethod("calcularPago");
                        Object rp = mPago.invoke(emp);
                        if(rp instanceof Number) pago = ((Number)rp).doubleValue();
                    } catch (Exception ex) {
                        // fallback a 0 si algo falla
                        pago = 0;
                    }

                    // agregar fila: Foto, Código, Nombre, Tipo, Salario(pago), Horas, Comisión
                    tableModel.addRow(new Object[]{ thumb, emp.getCodigo(), emp.getNombre(), tipo, pago, horas, comision });

                    // contadores
                    if(tipo.equals("Estándar")) totStd++;
                    if(tipo.equals("Temporal")) totTemp++;
                    if(tipo.equals("Ventas")) totVentas++;
                }
                lblTotales.setText("Totales: Std="+totStd+" Temp="+totTemp+" Ventas="+totVentas+" Total="+empleados.size());
            });
        }

        /**
         * Intenta obtener un ImageIcon desde la instancia de Empleado
         * Soporta métodos: getFotoEmpleado(), getFoto()
         */
        private ImageIcon fetchFoto(Empleado emp){
            try {
                Method m1 = emp.getClass().getMethod("getFotoEmpleado");
                Object res = m1.invoke(emp);
                if(res instanceof ImageIcon) return (ImageIcon) res;
            } catch (Exception ignored) {}
            try {
                Method m2 = emp.getClass().getMethod("getFoto");
                Object res2 = m2.invoke(emp);
                if(res2 instanceof ImageIcon) return (ImageIcon) res2;
            } catch (Exception ignored) {}
            return null;
        }
    }


    //main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmpresaGUI().setVisible(true));
    }
}
