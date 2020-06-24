package adapters;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

public class Constantes {
    
    public static final String tCarreras="Carreras";
    public static final String ID="DNI";
    public static final String Nombre="Nombre";
    public static final String Apellido="Apellido";
    public static final String Telefono1="Telefono1";
    
    
    
   /* public static String Crear_Tabla_Clientes_Nuevos="Create Table Usuarios"+" "+
            Tabla_Clientes_Nuevos+"("+ID+" "+"int, "+Nombre+" text, "+Apellido+" text"+" text, "+Telefono1+" text)";
    */
    
    public static String insertar_en_Carreras=("insert into Carreras"+tCarreras+" (1, Facultad de Ing. Industrial, Lic. en Recursos Humanos, 4 años)");
    
    
}