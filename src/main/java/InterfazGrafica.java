import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;

public class InterfazGrafica {

    JFrame frame;
    JButton jb1, jb2, jb3;

    public InterfazGrafica(){
        frame = new JFrame("Tp de TP4");
        jb1 = new JButton("Consulta");
        jb2 = new JButton("Modificación");
        jb3 = new JButton("Baja");
        frame.setLayout(new FlowLayout());
        frame.setSize(400,400);
        frame.setLocation(50,50);

        frame.add(jb1);
        frame.add(jb2);
        frame.add(jb3);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
