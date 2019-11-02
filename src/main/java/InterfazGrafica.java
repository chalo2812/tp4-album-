import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfazGrafica implements ActionListener, WindowListener {

    private JFrame frame;
    private JButton jb1, jb2, jb3, jb4, jb5;
    private JComboBox jcbAlbum, jcbArtista;
    private JLabel jl1,jl2;
    private JTable jt;
    private DefaultTableModel dtm;
    private JPanel jpBotones, jpAlbum,jpArtista, jpTema;
    private Conexion con = new Conexion();

    public InterfazGrafica() {
        frame = new JFrame("Tp de TP4");
        jpBotones = new JPanel();
        jb1 = new JButton("Consulta");
        jb1.addActionListener((ActionListener) this);
        jb2 = new JButton("Modificacion");
        jb2.setEnabled(false);
        jb3 = new JButton("Baja");
        jb3.setEnabled(false);
        jb4 = new JButton("Aceptar");
        jb4.setVisible(false);
        jpBotones.add(jb1);
        jpBotones.add(jb2);
        jpBotones.add(jb3);
        jl1 = new JLabel("Album");
        jl1.setVisible(true);
        jcbAlbum = new JComboBox();
        try {
            ResultSet lista = con.obtenerAlbum();
            jcbAlbum.addItem("Seleccionar");
            while (lista.next())
                jcbAlbum.addItem(lista.getInt("NRO_ALBUM") + " - "+ lista.getString("NOMBRE_ALBUM"));
        } catch (SQLException e) {
            System.out.println("Excepcion al cargar el album" + e.getCause());
        }
        jcbAlbum.setEditable(true);
        jcbAlbum.setVisible(true);
        jpAlbum = new JPanel();
        jpAlbum.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpAlbum.add(jl1);
        jpAlbum.add(jcbAlbum);
        jpAlbum.setVisible(false);
        jl2 = new JLabel("Artista");
        jl2.setVisible(true);
        jcbArtista = new JComboBox();
        try {
            ResultSet lista = con.obtenerArtista();
            jcbArtista.addItem("Seleccionar");
            while (lista.next())
                jcbArtista.addItem(lista.getInt("NRO_ARTISTA") + " - "+ lista.getString("NOMBREARTISTA"));
        } catch (SQLException e) {
            System.out.println("Excepcion al cargar el album" + e.getCause());
        }
        jcbArtista.setEditable(true);
        jcbArtista.setVisible(true);
        jb5 = new JButton("Buscar");
        jb5.setVisible(false);
        jb5.addActionListener(this);
        jpArtista = new JPanel();
        jpArtista.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpArtista.add(jl2);
        jpArtista.add(jcbArtista);
        jpArtista.add(jb5);
        jpArtista.setVisible(false);
        dtm = new DefaultTableModel();
        jt = new JTable(dtm);
        String[] encabezado = {"NRO_TEMA","NRO_ALBUM", "DESCRIPCION", "DURACION"};
        dtm.setColumnIdentifiers(encabezado);
        jt.setSize(400,200);
        jpTema = new JPanel();
        jpTema.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpTema.add(new JScrollPane(jt));
        jpTema.setVisible(false);
        jcbAlbum.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                jt.removeAll();
                if (dtm.getDataVector().size()>0){
                    for (int i=0; i < dtm.getDataVector().size(); i++)
                        dtm.removeRow(i);
                }
                JComboBox obj = (JComboBox) e.getSource();
                try {
                    String filtro = obj.getSelectedItem().toString();
                    if (!filtro.equals("Seleccionar")) {
                        int selecciono = Integer.parseInt(filtro.split("-")[0].trim());
                        ResultSet lista = con.obtenerTemaByIdAlbum(selecciono);
                        int i = 0;
                        while (lista.next()){
                            dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getString(3),lista.getString(4)});
                            i++;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error cargarTema: Excepcion " + ex.getMessage());
                }
                jt.setVisible(true);
            }
        });
        jcbArtista.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                jt.removeAll();
                if (dtm.getDataVector().size()>0){
                    for (int i=0; i < dtm.getDataVector().size(); i++)
                        dtm.removeRow(i);
                }
                JComboBox obj = (JComboBox) e.getSource();
                try {
                    String filtro = obj.getSelectedItem().toString();
                    if (!filtro.equals("Seleccionar")) {
                        int selecciono = Integer.parseInt(filtro.split("-")[0].trim());
                        ResultSet lista = con.obtenerTemaByIdAlbum(selecciono);
                        int i = 0;
                        while (lista.next()){
                            dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getString(3),lista.getString(4)});
                            i++;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error cargarTema: Excepcion " + ex.getMessage());
                }
                jt.setVisible(true);
            }
        });
        frame.add(jpBotones);
        frame.add(jpAlbum);
        frame.add(jpArtista);
        frame.add(jpTema);
        frame.add(jb4);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(500,630);
        frame.setLocation(50,50);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(jb1)){
            jpAlbum.setVisible(true);
            jpArtista.setVisible(true);
            jpTema.setVisible(true);
            jb4.setVisible(true);
            jb5.setVisible(true);
        } else if (ae.getSource().equals(jcbAlbum)) {
            jt.removeAll();
            try {
                ResultSet lista = con.obtenerTema();
                int i = 0;
                while (lista.next()){
                    dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getString(3),lista.getString(4)});
                    i++;
                }
            } catch (Exception ex) {
                System.out.println("Error cargarTema: Excepcion " + ex.getMessage());
            }
            jt.setVisible(true);
        } else if (ae.getSource().equals(jb5)){
            jt.removeAll();
            try {
                if (dtm.getDataVector().size()>0){
                    for (int i=0; i < dtm.getDataVector().size(); i++)
                        dtm.removeRow(i);
                }
                int id = jcbArtista.getSelectedIndex();
                ResultSet lista = con.obtenerTemaByArtista(id);
                int i = 0;
                while (lista.next()){
                    dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getString(3),lista.getString(4)});
                    i++;
                }
            } catch (Exception ex) {
                System.out.println("Error cargarTema: Excepcion " + ex.getMessage());
            }
            jt.setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) { }

    @Override
    public void windowClosing(WindowEvent windowEvent) { }

    @Override
    public void windowClosed(WindowEvent windowEvent) { }

    @Override
    public void windowIconified(WindowEvent windowEvent) { }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) { }

    @Override
    public void windowActivated(WindowEvent windowEvent) { }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) { }

}