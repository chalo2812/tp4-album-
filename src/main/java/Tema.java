public class Tema {

    int nroTema;//NRO_TEMA` int(11) NOT NULL,
    int nroAlbum;//`NRO_ALBUM` int(11) NOT NULL,
    String descripcion;//`DESCRIPCION` varchar(30) NOT NULL,
    String duracion;//`DURACION` varchar(30) NOT NULL

    public Tema(int nroT, int nroA, String desc, String time){
        super();
        nroTema = nroT;
        nroAlbum = nroA;
        descripcion = desc;
        duracion = time;
    }

}
