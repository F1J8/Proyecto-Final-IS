package adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import Prospectos.ProspectosU;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    public static String TABLA_CLIENTES = "clientes", TABLA_ACTIVOS = "activos",
            TABLA_PROMOCIONES="promociones", TABLA_PROVEEDORES="proveedores", TABLA_SERVICIOS="servicios";


    /**Constructor*/

    public SQLiteOpenHelper(@Nullable Context context, @Nullable String name,  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        /**Creacion de la tabla Clientes*/
        db.execSQL("create table "+ TABLA_CLIENTES +" (ced_cli text primary key, nom_cli text, ape_cli text, " +
                "tel1_cli text, tel2_cli text, correo_cli text, res_cli text, trab_cli text)");

        /**Creacion de la tabla Activos*/
        db.execSQL("create table "+ TABLA_ACTIVOS +" (cod text primary key, tipo text, marca text, modelo text, " +
                "precio double, fecha_compra date)");

        /**Creacion de la tabla Promociones*/
        db.execSQL("create table "+ TABLA_PROMOCIONES +" (cod text primary key, tipo text, titulo text, duracion text, ubic_inicial text, " +
                "ubic_final text, fecha text, precio double, idioma text)");

        /**Creacion de la tabla Proveedores*/
        db.execSQL("create table "+ TABLA_PROVEEDORES +" (cod text primary key, tipo text, nombre text, correo text, telefono text)");

        /**Creacion de la tabla Servicios*/
        db.execSQL("create table "+ TABLA_SERVICIOS +" (cod text primary key, tipo text, nombre text, precio double)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLA_ACTIVOS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLA_PROMOCIONES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLA_PROVEEDORES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLA_SERVICIOS);

        onCreate(db);
    }

    /**Mostrar datos de Tabla Promociones */
    public Cursor ViewDataPromociones(){
        SQLiteDatabase BaseDeDatos = this.getReadableDatabase();
        String query = "Select * From "+ TABLA_PROMOCIONES;
        Cursor cursor = BaseDeDatos.rawQuery(query, null);

        return cursor;
    }
    /**Mostrar datos de Tabla Servicios */
    public Cursor ViewDataServicios(){
        SQLiteDatabase BaseDeDatos = this.getReadableDatabase();
        String query = "Select * From "+ TABLA_SERVICIOS;
        Cursor cursor = BaseDeDatos.rawQuery(query, null);

        return cursor;
    }
    /*public ArrayList<String> ShowC(){

    ArrayList<String> list = new ArrayList<>();
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("select nombre from "+tablaCarreras,null);
        if(cursor.moveToFirst()){
        do{
            list.add(cursor.getString(0));
        }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
        }
*/


}
