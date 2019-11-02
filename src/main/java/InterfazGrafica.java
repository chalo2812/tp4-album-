import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfazGrafica implements ActionListener, WindowListener {

    private JFrame frame;
    private JButton jb1, jb2, jb3, jb4, jb5,jb1Salir;
    private JComboBox jcbAlbum, jcbArtista;
    private JLabel jl1, jl2;
    private JTable jt;
    private DefaultTableModel dtm;
    private JPanel jpBotones, jpAlbum, jpArtista, jpTema;
    private Conexion con;

    public InterfazGrafica() {
        try {
            con = new Conexion();
            crearPrincipal();
        } catch (Exception e1) {
            crearVentaSalida();
        }
    }

    public void crearPrincipal(){
        
        frame = new JFrame("Tp de TP4");
        jpBotones = new JPanel();
        jb1 = new JButton("Consulta");
        jb1.addActionListener((ActionListener) this);
        jb2 = new JButton("Modificacion");
        jb2.setEnabled(false);
        jb3 = new JButton("Baja");
        jb3.setEnabled(false);
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
        String[] encabezado = {"NRO_TEMA", "NRO_ALBUM", "NRO_ARTISTA", "DESCRIPCION", "DURACION"};
        dtm.setColumnIdentifiers(encabezado);
        jt.setSize(400,200);
        jpTema = new JPanel();
        jpTema.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpTema.add(new JScrollPane(jt));
        jpTema.setVisible(false);
        jcbAlbum.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {    
                jt.removeAll();
                jcbArtista.setSelectedIndex(0);
                int i=0;
                if (dtm.getDataVector().size()>0){
                    while (i < dtm.getDataVector().size())
                        dtm.removeRow(0);
                }
                JComboBox obj = (JComboBox) e.getSource();
                try {
                    String filtro = obj.getSelectedItem().toString();
                    if (!filtro.equals("Seleccionar")) {
                        int selecciono = Integer.parseInt(filtro.split("-")[0].trim());
                        ResultSet lista = con.obtenerTemaByIdAlbum(selecciono);
                        while (lista.next()) {
                            dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getInt(3),lista.getString(4), lista.getString(5)});
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
                //jcbAlbum.setSelectedIndex(0);
                int i=0;
                if (dtm.getDataVector().size()>0) {
                    while (i < dtm.getDataVector().size()){
                        dtm.removeRow(0);
                    }
                }
                try {
                    JComboBox obj = (JComboBox) e.getSource();
                    String filtro = obj.getSelectedItem().toString();
                    if (!filtro.equals("Seleccionar")) {
                        int selecciono = Integer.parseInt(filtro.split("-")[0].trim());
                        ResultSet lista = con.obtenerTemaByArtista(selecciono);
                        while (lista.next()){
                            dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getString(3),lista.getString(4),lista.getString(5)});
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
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(500,630);
        frame.setLocation(50,50);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void crearVentaSalida(){
        frame = new JFrame("Tp de TP4");
        JLabel jl = new JLabel();
        jl.setText("Error con la BD");
        jb1Salir = new JButton("Salir");
        jb1Salir.addActionListener((ActionListener) this);
        frame.add(jl);
        frame.add(jb1Salir);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLocation(50,50);
        frame.setSize(100,200);
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
            jb5.setVisible(true);
        } else if (ae.getSource().equals(jb5)){
            jt.removeAll();
            try {
                int i=0;
                if (dtm.getDataVector().size()>0){
                    while (i < dtm.getDataVector().size()){
                        dtm.removeRow(0);
                    }
                }
                int idArtista = jcbArtista.getSelectedIndex();
                int idAlbum = jcbAlbum.getSelectedIndex();
                ResultSet lista = null;
                if (idArtista != 0) {
                    if (idAlbum != 0)
                        lista = con.obtenerTemaByArtistaAndAlbum(idArtista, idAlbum);
                    else
                        lista = con.obtenerTemaByArtista(idArtista);
                } else if (idAlbum != 0) {
                    lista = con.obtenerTemaByIdAlbum(idAlbum);
                }
                while (lista != null && lista.next()){
                    dtm.insertRow(i, new Object[]{lista.getInt(1),lista.getInt(2),lista.getInt(3),lista.getString(4),lista.getString(5)});
                    i++;
                }
            } catch (Exception ex) {
                System.out.println("Error cargarTema: Excepcion " + ex.getMessage());
            }
            jt.setVisible(true);
        } else if (ae.getSource().equals(jb1Salir)){
            frame.setVisible(false);
            frame.dispose ();
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