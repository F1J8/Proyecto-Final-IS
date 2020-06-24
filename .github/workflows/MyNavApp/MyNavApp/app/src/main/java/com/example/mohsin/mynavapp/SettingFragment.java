package com.example.mohsin.mynavapp;


import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import adapters.SQLiteOpenHelper;


public class SettingFragment extends Fragment {

    public  String NOMBRE_DIRECTORIO = "PDF SSU", NOMBRE_DOCUMENTO = "Reporte_Carreras.pdf",
                   NOMBRE_DOCUMENTO1 = "Reporte_Ingreso.pdf",
                   NOMBRE_DOCUMENTO2 = "Reporte_Transferencia.pdf",
                   NOMBRE_DOCUMENTO3 = "Reporte_Reingreso.pdf",
                   NOMBRE_DOCUMENTO4 = "Reporte_5to.pdf",
                   NOMBRE_DOCUMENTO5 = "Reporte_6to.pdf",
                   NOMBRE_DOCUMENTO6 = "Reporte_Estudiantes.pdf";


    private Button bt5,bt6, btR, btT, btI, btC,btE;
    View vista;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista =  inflater.inflate(R.layout.fragment_setting, container, false);

        /*bt5 = vista.findViewById(R.id.Prosp5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_5();
            }
        });

        bt6 = vista.findViewById(R.id.Prosp6);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_6();
            }
        });

        btE = vista.findViewById(R.id.Estudiantes);
        btE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_E();
            }
        });

        btC = vista.findViewById(R.id.Carreras);
        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_C();
            }
        });

        btI = vista.findViewById(R.id.P_Ingreso);
        btI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_I();
            }
        });

        btT = vista.findViewById(R.id.ProspUT);
        btT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_T();
            }
        });

        btR = vista.findViewById(R.id.ProspUR);
        btR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarPDF_R();
            }
        });*/
        return vista;
    }
    public void GenerarPDF_C() {
try{

    // Permisos
    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                1000);

    }
    // Genera el documento
    crearPDF_Carreras();

    Toast.makeText(getContext(), "SE CREO EL PDF de las Carreras", Toast.LENGTH_LONG).show();

} catch (Exception e) {
    e.printStackTrace();
}
    }

    public void GenerarPDF_I() {
    try{

        // Permisos
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);

        }
        // Genera el documento

        crearPDF_Ingreso();

        Toast.makeText(getContext(), "SE CREO EL PDF de Primer Ingreso", Toast.LENGTH_LONG).show();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }

    public void GenerarPDF_E() {

       try{
           // Permisos
           if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                   PackageManager.PERMISSION_GRANTED &&
                   ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                           PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                       1000);

           }
           // Genera el documento


           crearPDF_Estudiante();

           Toast.makeText(getContext(), "SE CREO EL PDF de Estudiantes Regulares", Toast.LENGTH_LONG).show();
       } catch (Exception e) {
           e.printStackTrace();
       }

    }

    public void GenerarPDF_5() {
try{

    // Permisos
    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                1000);

    }
    // Genera el documento

    crearPDF_5to();

    Toast.makeText(getContext(), "SE CREO EL PDF de Prospectos de 5to", Toast.LENGTH_LONG).show();

} catch (Exception e) {
    e.printStackTrace();
}
    }


    public void GenerarPDF_6() {
try {

    // Permisos
    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                1000);

    }
    // Genera el documento

    crearPDF_6to();

    Toast.makeText(getContext(), "SE CREO EL PDF de Prospectos de 6to", Toast.LENGTH_LONG).show();

} catch (Exception e) {
    e.printStackTrace();
}
    }


    public void GenerarPDF_R() {
    try{
        // Permisos
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);

        }
        // Genera el documento

        crearPDF_Reingreso();

        Toast.makeText(getContext(), "SE CREO EL PDF de Prospectos por Reingreso", Toast.LENGTH_LONG).show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


    public void GenerarPDF_T() {
        try {

            // Permisos
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                        1000);

            }
            // Genera el documento

            crearPDF_Transferencia();
            Toast.makeText(getContext(), "SE CREO EL PDF de Prospectos por Transferencia", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/**Duplicar*/
    public void crearPDF_Carreras() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();

        try {
            File file = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            // documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_Carreras() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_Carreras() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Carreras";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("ID")) != null) {
                dbString += "\n";
                dbString += "ID: ";
                dbString += Cur.getInt(Cur.getColumnIndex("ID"));
                dbString += "\n";
                dbString += "Nombre de la Carrera: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Facultad: ";
                dbString += Cur.getString(Cur.getColumnIndex("Facultad"));
                dbString += "\n";
                dbString += "Duracion: ";
                dbString += Cur.getString(Cur.getColumnIndex("Duracion"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }


/**Duplicar*/
    public void crearPDF_Ingreso() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();
        try {
            File file = crearFichero(NOMBRE_DOCUMENTO1);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            // documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_Ingreso() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_Ingreso() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Estudiantes_Primer_Ingreso";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("DNI")) != null) {
                dbString += "\n";
                dbString += "Cedula: ";
                dbString += Cur.getString(Cur.getColumnIndex("DNI"));
                dbString += "\n";
                dbString += "Nombre del Eestudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Apellido del Estudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Apellido"));
                dbString += "\n";
                dbString += "Telefono 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono1"));
                dbString += "\n";
                dbString += "Telefono 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono2"));
                dbString += "\n";
                dbString += "Correo: ";
                dbString += Cur.getString(Cur.getColumnIndex("Correo"));
                dbString += "\n";
                dbString += "Nombre del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombrePadre"));
                dbString += "\n";
                dbString += "Apellido del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoPadre"));
                dbString += "\n";
                dbString += "Nombre de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombreMadre"));
                dbString += "\n";
                dbString += "Apellido de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoMadre"));
                dbString += "\n";
                dbString += "Opcion de Carrera 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera1"));
                dbString += "\n";
                dbString += "Opcion de Carrera 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera2"));
                dbString += "\n";
                dbString += "Opcion de Carrera 3: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera3"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }

/**Duplicar*/
    public void crearPDF_Estudiante() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();
        try {
            File file = crearFichero(NOMBRE_DOCUMENTO6);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            // documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_Estudiante() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_Estudiante() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Estudiantes_Primer_Ingreso";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("DNI")) != null) {
                dbString += "\n";
                dbString += "Cedula: ";
                dbString += Cur.getString(Cur.getColumnIndex("DNI"));
                dbString += "\n";
                dbString += "Nombre del Eestudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Apellido del Estudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Apellido"));
                dbString += "\n";
                dbString += "Telefono 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono1"));
                dbString += "\n";
                dbString += "Telefono 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono2"));
                dbString += "\n";
                dbString += "Correo: ";
                dbString += Cur.getString(Cur.getColumnIndex("Correo"));
                dbString += "\n";
                dbString += "Nombre del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombrePadre"));
                dbString += "\n";
                dbString += "Apellido del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoPadre"));
                dbString += "\n";
                dbString += "Nombre de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombreMadre"));
                dbString += "\n";
                dbString += "Apellido de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoMadre"));
                dbString += "\n";
                dbString += "Opcion de Carrera 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera1"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }


    /**Duplicar*/
    public void crearPDF_6to() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();
        try {
            File file = crearFichero(NOMBRE_DOCUMENTO5);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            // documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_6to() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_6to() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Prospectos_6";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("DNI")) != null) {
                dbString += "\n";
                dbString += "Cedula: ";
                dbString += Cur.getString(Cur.getColumnIndex("DNI"));
                dbString += "\n";
                dbString += "Nombre del Eestudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Apellido del Estudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Apellido"));
                dbString += "\n";
                dbString += "Telefono 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono1"));
                dbString += "\n";
                dbString += "Telefono 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono2"));
                dbString += "\n";
                dbString += "Correo: ";
                dbString += Cur.getString(Cur.getColumnIndex("Correo"));
                dbString += "\n";
                dbString += "Nombre del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombrePadre"));
                dbString += "\n";
                dbString += "Apellido del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoPadre"));
                dbString += "\n";
                dbString += "Nombre de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombreMadre"));
                dbString += "\n";
                dbString += "Apellido de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoMadre"));
                dbString += "\n";
                dbString += "Opcion de Carrera 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera1"));
                dbString += "\n";
                dbString += "Opcion de Carrera 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera2"));
                dbString += "\n";
                dbString += "Opcion de Carrera 3: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera3"));
                dbString += "\n";
                dbString += "Colegio: ";
                dbString += Cur.getString(Cur.getColumnIndex("Colegio"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }


    /**Duplicar*/
    public void crearPDF_5to() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();
        try {
            File file = crearFichero(NOMBRE_DOCUMENTO4);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            // documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_5to() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_5to() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Prospectos_6";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("DNI")) != null) {
                dbString += "\n";
                dbString += "Cedula: ";
                dbString += Cur.getString(Cur.getColumnIndex("DNI"));
                dbString += "\n";
                dbString += "Nombre del Eestudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Apellido del Estudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Apellido"));
                dbString += "\n";
                dbString += "Telefono 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono1"));
                dbString += "\n";
                dbString += "Telefono 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono2"));
                dbString += "\n";
                dbString += "Correo: ";
                dbString += Cur.getString(Cur.getColumnIndex("Correo"));
                dbString += "\n";
                dbString += "Nombre del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombrePadre"));
                dbString += "\n";
                dbString += "Apellido del Padre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoPadre"));
                dbString += "\n";
                dbString += "Nombre de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("NombreMadre"));
                dbString += "\n";
                dbString += "Apellido de la Madre: ";
                dbString += Cur.getString(Cur.getColumnIndex("ApellidoMadre"));
                dbString += "\n";
                dbString += "Opcion de Carrera 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera1"));
                dbString += "\n";
                dbString += "Opcion de Carrera 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera2"));
                dbString += "\n";
                dbString += "Opcion de Carrera 3: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera3"));
                dbString += "\n";
                dbString += "Colegio: ";
                dbString += Cur.getString(Cur.getColumnIndex("Colegio"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }

    /**Duplicar*/
    public void crearPDF_Reingreso() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();
        try {
            File file = crearFichero(NOMBRE_DOCUMENTO3);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            // documento.add(new Paragraph("TABLA \n\n"));
            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_Reingreso() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_Reingreso() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Prospectos_U_Reingreso";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("DNI")) != null) {
                dbString += "\n";
                dbString += "Cedula: ";
                dbString += Cur.getString(Cur.getColumnIndex("DNI"));
                dbString += "\n";
                dbString += "Nombre del Eestudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Apellido del Estudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Apellido"));
                dbString += "\n";
                dbString += "Telefono 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono1"));
                dbString += "\n";
                dbString += "Telefono 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono2"));
                dbString += "\n";
                dbString += "Correo: ";
                dbString += Cur.getString(Cur.getColumnIndex("Correo"));
                dbString += "\n";
                dbString += "Opcion de Carrera: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera"));
                dbString += "\n";
                dbString += "Universidad: ";
                dbString += Cur.getString(Cur.getColumnIndex("Universidad"));
                dbString += "\n";
                dbString += "Nivel: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nivel"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }

    /**Duplicar*/
    public void crearPDF_Transferencia() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getReadableDatabase();
        Document documento = new Document();
        try {
            File file = crearFichero(NOMBRE_DOCUMENTO2);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();

            documento.add(new Paragraph("\t" + "\tRegistro de Base de Datos" + "\n\n"));
            documento.add(new Paragraph(GenerarString_Transferencia() + "\n\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            documento.close();
        }

    }
    /**Duplicar*/
    public String GenerarString_Transferencia() {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        String dbString = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        String Query = "SELECT * FROM Prospectos_U_Transferencia";
        Cursor Cur = db.rawQuery(Query, null);
        Cur.moveToFirst();
        while (!Cur.isAfterLast()) {
            if (Cur.getString(Cur.getColumnIndex("DNI")) != null) {
                dbString += "\n";
                dbString += "Cedula: ";
                dbString += Cur.getString(Cur.getColumnIndex("DNI"));
                dbString += "\n";
                dbString += "Nombre del Eestudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Nombre"));
                dbString += "\n";
                dbString += "Apellido del Estudiante: ";
                dbString += Cur.getString(Cur.getColumnIndex("Apellido"));
                dbString += "\n";
                dbString += "Telefono 1: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono1"));
                dbString += "\n";
                dbString += "Telefono 2: ";
                dbString += Cur.getString(Cur.getColumnIndex("Telefono2"));
                dbString += "\n";
                dbString += "Correo: ";
                dbString += Cur.getString(Cur.getColumnIndex("Correo"));
                dbString += "\n";
                dbString += "Universidad: ";
                dbString += Cur.getString(Cur.getColumnIndex("Universidad"));
                dbString += "\n";
                dbString += "Carrera Anterior: ";
                dbString += Cur.getString(Cur.getColumnIndex("Carrera"));
                dbString += "\n";
                dbString += "Opcion de Carrera: ";
                dbString += Cur.getString(Cur.getColumnIndex("CarreraN"));
                dbString += "\n";
                dbString += "\n";
            }
            Cur.moveToNext();
        }
        db.close();
        return dbString;
    }


    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();
        File fichero = null;
        if (ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }
        return fichero;
    }

    public File getRuta() {
        File ruta = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NOMBRE_DIRECTORIO);

            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                        return null;
                    }
                }
            }
        }
        return ruta;
    }

}
