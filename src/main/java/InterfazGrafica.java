import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InterfazGrafica implements ActionListener, WindowListener {

    JFrame frame;
    JButton jb1, jb2, jb3, jb4;
    JComboBox jcb;
    JLabel jl1,jl2;
    JTextField jtf;
    JTable jt;
    DefaultTableModel dtm;
    JPanel jpBotones, jpAlbum,jpArtista, jpTema;

    public InterfazGrafica() {

        frame = new JFrame("Tp de TP4");
        jpBotones = new JPanel();

        jb1 = new JButton("Consulta");
        jb1.addActionListener((ActionListener) this);
        jb2 = new JButton("Modificación");
        jb2.setEnabled(false);
        jb3 = new JButton("Baja");
        jb3.setEnabled(false);
        jb4 = new JButton("Aceptar");
        jb4.setEnabled(false);
        jpBotones.add(jb1);
        jpBotones.add(jb2);
        jpBotones.add(jb3);

        jl1 = new JLabel("Album");
        jl1.setVisible(false);
        jcb = new JComboBox();
        jcb.add(alimentarCombo());
        jcb.setEditable(false);
        jcb.setVisible(false);
        jpAlbum = new JPanel();
        jpAlbum.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpAlbum.add(jl1);
        jpAlbum.add(jcb);

        jl2 = new JLabel("Artitsta");
        jl2.setVisible(false);
        jtf = new JTextField();
        jtf.setVisible(false);
        jpArtista = new JPanel();
        jpArtista.setLayout(new FlowLayout(FlowLayout.CENTER));

        jpArtista.add(jl2);
        jpArtista.add(jtf);

        dtm = new DefaultTableModel();
        jt = new JTable(dtm);
        String[] encabezado = {"NRO_TEMA","NRO_ALBUM", "DURACION", "DESCRIPCION"};



/*
        JTable table = new JTable(data, columnNames);
        INSERT INTO TEMA VALUES ('Tratar de estar mejor',4,,1);
       */
        dtm.addRow(encabezado);
        JScrollPane jsp = new JScrollPane(jt);
        /*JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);*/

        jpTema = new JPanel();
        jpTema.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpTema.add(jsp);

        frame.add(jpBotones);
        frame.add(jpAlbum);
        frame.add(jpArtista);
        frame.add(jpTema);
        frame.add(jb4);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(600,600);
        frame.setLocation(50,50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private List alimentarCombo() {
        List theList = new List();
        return theList;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(jb1)){
            jpAlbum.setVisible(true);
            jpArtista.setVisible(true);
            jpTema.setVisible(true);
            jb4.setEnabled(true);
        } else if (ae.getSource().equals(jcb)) {
            jt.setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}