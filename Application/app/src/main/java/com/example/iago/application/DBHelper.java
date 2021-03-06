package com.example.iago.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import static java.lang.System.out;

public class DBHelper extends SQLiteOpenHelper {

    private Context contex;

    public static final String DATABASE_NAME = "TormentaDB.db";
    private SQLiteDatabase myDataBase;
    public static final String CHAR_TABLE_NAME = "personagens";
    public static final String CHAR_COLUMN_ID = "id";
    public static final String CHAR_COLUMN_NAME = "nome";
    public static final String CHAR_COLUMN_FOR = "força";
    public static final String CHAR_COLUMN_DES = "destreza";
    public static final String CHAR_COLUMN_CON = "constituicao";
    public static final String CHAR_COLUMN_INT = "inteligência";
    public static final String CHAR_COLUMN_SAB = "sabedoria";
    public static final String CHAR_COLUMN_CAR = "carisma";
    public static final String CHAR_COLUMN_RAÇA = "racaid";
    public static final String CHAR_COLUMN_NIVEL = "nivel";
    public static final String CHAR_COLUMN_FCLASS = "primclasse";

    public static final String CLASS_TABLE_NAME = "classes";
    public static final String CLASS_COLUMN_ID = "classid";
    public static final String CLASS_COLUMN_NAME = "classnome";
    public static final String CLASS_COLUMN_BBA = "bbaclasse";
    public static final String CLASS_COLUMN_PVS = "pvsclasse";
    public static final String CLASS_COLUMN_PERÍCIAS = "perclasse";
    public static final String CLASS_COLUMN_PRESTÍGIO = "prestígio";
    public static final String CLASS_COLUMN_NÍVEL = "nívelmax";
    public static final String CLASS_COLUMN_ORIGEM = "classorigem";
    public static final String CLASS_COLUMN_DESCRIPTION = "classdescricao";

    public static final String RACE_TABLE_NAME = "racas";
    public static final String RACE_COLUMN_ID = "racaid";
    public static final String RACE_COLUMN_NAME = "racanome";
    public static final String RACE_COLUMN_ORIGEM = "racaorigem";
    public static final String RACE_COLUMN_DESCRIPTION = "racadescricao";

    public static final String FEATPOINTS_TABLE_NAME = "pontostalento";
    public static final String FEATPOINTS_COMBAT = "pontostalentocombate";
    public static final String FEATPOINTS_MAGIC = "pontostalentomagia";
    public static final String FEATPOINTS_SKILL = "pontostalentopericia";
    public static final String FEATPOINTS_DIVINE = "pontostalentodivino";
    public static final String FEATPOINTS_TORMENTA = "pontostalentotormenta";
    public static final String FEATPOINTS_GENERAL = "pontostalentogeral";
    public static final String FEATPOINTS_CHARID = "pontostalentochar";

    public static final String FEAT_TABLE_NAME = "talentos";
    public static final String FEAT_COLUMN_ID = "talentoid";
    public static final String FEAT_COLUMN_NAME = "talentonome";
    public static final String FEAT_COLUMN_TYPE = "talentotipo";
    public static final String FEAT_COLUMN_REPEAT = "talentorep";
    public static final String FEAT_COLUMN_ACT = "talentoact";

    public static final String SKILL_TABLE_NAME = "pericias";
    public static final String SKILL_COLUMN_ID = "periciaid";
    public static final String SKILL_COLUMN_NAME = "pericianome";
    public static final String SKILL_COLUMN_TRAIN = "periciatr";
    public static final String SKILL_COLUMN_ARMOR = "periciapen";
    public static final String SKILL_COLUMN_ATT = "periciatt";

    public static final String WEAPON_TABLE_NAME = "armas";
    public static final String WEAPON_COLUMN_NAME = "armanome";
    public static final String WEAPON_COLUMN_ID = "armaid";
    public static final String WEAPON_COLUMN_TYPE = "armatipo";
    public static final String WEAPON_COLUMN_HAND = "armamao";
    public static final String WEAPON_COLUMN_DAMAGE = "armadano";
    public static final String WEAPON_COLUMN_CRITICAL = "armacrit";
    public static final String WEAPON_COLUMN_MULTIPLIER = "armamult";
    public static final String WEAPON_COLUMN_DTYPE = "armadtipo";
    public static final String WEAPON_COLUMN_DES = "armades";
    public static final String WEAPON_COLUMN_LONG = "armahaste";
    public static final String WEAPON_COLUMN_DISTANCE = "armadist";

    public static final String SPECWEAPON_TABLE_NAME = "armaespecifica";
    public static final String SPECWEAPON_COLUMN_NAME = "armaespecificanome";
    public static final String SPECWEAPON_COLUMN_MAGIC = "armaespecificamagia";
    public static final String SPECWEAPON_COLUMN_BASE = "armaespecificabase";
    public static final String SPECWEAPON_COLUMN_BONUS = "armaespecificabonus";
    public static final String SPECWEAPON_CHAR_ID = "armaespecificachar";

    public static final String SPECARMOR_TABLE_NAME = "armaduraespecifica";
    public static final String SPECARMOR_COLUMN_NAME = "armaduraespecificanome";
    public static final String SPECARMOR_COLUMN_MAGIC = "armaduraespecificamagia";
    public static final String SPECARMOR_COLUMN_BASE = "armaduraespecificabase";
    public static final String SPECARMOR_COLUMN_BONUS = "armaduraespecificabonus";
    public static final String SPECARMOR_CHAR_ID = "armaduraespecificachar";

    public static final String ARMOR_TABLE_NAME = "armaduras";
    public static final String ARMOR_COLUMN_NAME = "armaduranome";
    public static final String ARMOR_COLUMN_ID = "armaduraid";
    public static final String ARMOR_COLUMN_TYPE = "armaduratipo";
    public static final String ARMOR_COLUMN_CA = "armaduraca";
    public static final String ARMOR_COLUMN_BMD = "armadurabmd";
    public static final String ARMOR_COLUMN_PA = "armadurapen";

    public static final String PREREQ_TABLE_NAME = "prerequisitos";
    public static final String PREREQ_COLUMN_FID = "prereqtalid";
    public static final String PREREQ_COLUMN_TYPE = "prereqtype";
    public static final String PREREQ_COLUMN_EXTRA = "prereqesp";
    public static final String PREREQ_COLUMN_VALUE = "prereqval";

    public static final String CHARCLASSE_REL_NAME = "relaccharclass";
    public static final String CHARCLASSE_CHAR_ID = "relaccharclasschar";
    public static final String CHARCLASSE_CLASS_ID = "relaccharclassclass";
    public static final String CHARCLASSE_NÍVEL = "relaccharclassnvl";

    public static final String CHARSKILL_REL_NAME = "relaccharskill";
    public static final String CHARSKILL_CHAR_ID = "relaccharskillchar";
    public static final String CHARSKILL_SKILL_ID = "relaccharskillskill";

    public static final String CHARFEAT_REL_NAME = "relaccharfeat";
    public static final String CHARFEAT_CHAR_ID = "relaccharfeatchar";
    public static final String CHARFEAT_FEAT_ID = "relaccharfeatfeat";
    public static final String CHARFEAT_TIMES = "relaccharfeatnum";
    public static final String CHARFEAT_AUX = "relaccharfeataux";

    public static final String ABILITY_TABLE_NAME = "habilidades";
    public static final String ABILITY_COLUMN_ID = "habilidadeid";
    public static final String ABILITY_COLUMN_NAME = "habilidadenome";
    public static final String ABILITY_COLUMN_VALUE = "habilidadevalor";

    public static final String CHARABILITY_REL_NAME = "relaccharab";
    public static final String CHARABILITY_CHAR_ID = "relaccharabchar";
    public static final String CHARABILITY_SKILL_ID = "relaccharabab";
    public static final String CHARABILITY_AUX = "relaccharabaux";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        contex = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        FileReader file = null;

        db.execSQL(
                "create table " + RACE_TABLE_NAME +
                        "(racaid integer primary key, racanome text, racaorigem text, racadescricao text)"
        );
        db.execSQL(
                "create table " + CHAR_TABLE_NAME +
                        "(id integer primary key, nome text, força integer, destreza integer, constituicao integer, inteligência integer, sabedoria integer, carisma integer, racaid integer, primclasse integer, nivel integer, foreign key(racaid) references racas(racaid), foreign key(primclasse) references classes(classid))"
        );
        db.execSQL(
                "create table " + CLASS_TABLE_NAME +
                        "(classid integer primary key, classnome text, bbaclasse integer, pvsclasse integer, perclasse integer, prestígio boolean, nívelmax integer, classorigem text, classdescricao text)"
        );
        db.execSQL(
                "create table " + CHARCLASSE_REL_NAME +
                        "(relaccharclasschar integer, relaccharclassclass integer, " + CHARCLASSE_NÍVEL + " integer, foreign key(relaccharclasschar) references personagens(id), foreign key(relaccharclassclass) references classes(classid))"
        );
        db.execSQL(
                "create table " + FEAT_TABLE_NAME +
                        "(talentoid integer primary key, talentonome text, talentorep boolean, talentoact boolean)"
        );
        db.execSQL(
                "create table " + SKILL_TABLE_NAME +
                        "(periciaid integer primary key, pericianome text, periciatr boolean, periciapen boolean, periciatt integer)"
        );

        db.execSQL(
                "create table " + ABILITY_TABLE_NAME +
                        "(habilidadeid integer primary key, habilidadenome text, habilidadevalor integer)"
        );

        db.execSQL(
                "create table " + WEAPON_TABLE_NAME +
                        "(armaid integer primary key, armanome text, armatipo integer, armamao integer, armadano text, armamult integer, armacrit integer, armadtipo integer, armades integer, armalong integer, armadist integer)"
        );

        db.execSQL(
                "create table " + SPECWEAPON_TABLE_NAME +
                        "(armaespecificanome text, armaespecificamagia text, armaespecificabonus integer,armaespecificabase integer, armaespecificachar integer, foreign key(armaespecificabase) references armas(armaid), foreign key(armaespecificachar) references personagens(id))"
        );

        db.execSQL(
                "create table " + SPECARMOR_TABLE_NAME +
                        "(armaduraespecificanome text, armaduraespecificamagia text, armaduraespecificabonus integer,armaduraespecificabase integer, armaduraespecificachar integer, foreign key(armaduraespecificabase) references armaduras(armaduraid), foreign key(armaduraespecificachar) references personagens(id))"
        );

        db.execSQL(
                "create table " + ARMOR_TABLE_NAME +
                        "(armaduraid integer primary key, armaduranome text, armaduratipo integer, armaduraca integer, armadurabmd text, armadurapen integer)"
        );

        db.execSQL(
                "create table " + CHARFEAT_REL_NAME +
                        "(relaccharfeatchar integer, relaccharfeatfeat integer, " + CHARFEAT_TIMES + " integer, " + CHARFEAT_AUX + " integer, foreign key(relaccharfeatchar) references personagens(id), foreign key(relaccharfeatfeat) references talentos(talentoid))"
        );
        db.execSQL(
                "create table " + PREREQ_TABLE_NAME +
                        "(prereqtalid integer, prereqtype text, prereqesp text, prereqval integer, foreign key(prereqtalid) references talentos(talentoid))");

        db.execSQL(
                "create table " + CHARSKILL_REL_NAME +
                        "(relaccharskillchar integer, relaccharskillskill integer, foreign key(relaccharskillchar) references personagens(id), foreign key(relaccharskillskill) references pericias(periciaid))"
        );

        db.execSQL(
                "create table " + CHARABILITY_REL_NAME +
                        "(relaccharabchar integer, relaccharabab integer, relaccharabaux integer, foreign key(relaccharabchar) references personagens(id), foreign key(relaccharabab) references habilidades(habilidadeid))"
        );
        db.execSQL(
                "create table " + FEATPOINTS_TABLE_NAME +
                        "(pontostalentogeral integer, pontostalentocombate integer, pontostalentomagia integer, pontostalentopericia integer, pontostalentodivino integer, pontostalentotormenta integer, pontostalentochar integer, foreign key(pontostalentochar) references personagens(id))"
        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS personagens");
        db.execSQL("DROP TABLE IF EXISTS classes");
        db.execSQL("DROP TABLE IF EXISTS racas");
        db.execSQL("DROP TABLE IF EXISTS talentos");

        onCreate(db);
    }

    public boolean copyDataBase() throws IOException {
        InputStream myInput = contex.getAssets().open(DATABASE_NAME);
        String outFileName = "/data/data/com.example.iago.application/databases/" + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        return true;
    }

    public boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = "/data/data/com.example.iago.application/databases/" + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    public void inicializaDB() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = "/data/data/com.example.iago.application/databases/" + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public int insertPersonagem(String name, int força, int destreza, int constituição, int inteligência, int sabedoria, int carisma, int raçaid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHAR_COLUMN_NAME, name);
        contentValues.put(CHAR_COLUMN_FOR, força);
        contentValues.put(CHAR_COLUMN_DES, destreza);
        contentValues.put(CHAR_COLUMN_CON, constituição);
        contentValues.put(CHAR_COLUMN_INT, inteligência);
        contentValues.put(CHAR_COLUMN_SAB, sabedoria);
        contentValues.put(CHAR_COLUMN_CAR, carisma);
        contentValues.put(CHAR_COLUMN_RAÇA, raçaid);
        contentValues.put(CHAR_COLUMN_NIVEL, 0);
        db.insert(CHAR_TABLE_NAME, null, contentValues);
        contentValues = new ContentValues();
        Cursor res = db.rawQuery("select " + CHAR_COLUMN_ID + " from personagens where " + CHAR_COLUMN_NAME + " LIKE '" + name + "'", null);
        int charid = 0;
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                charid = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_ID));
                res.moveToNext();
            }
        }
        contentValues.put(FEATPOINTS_CHARID, charid);
        contentValues.put(FEATPOINTS_COMBAT, 0);
        contentValues.put(FEATPOINTS_MAGIC, 0);
        contentValues.put(FEATPOINTS_SKILL, 0);
        contentValues.put(FEATPOINTS_DIVINE, 0);
        contentValues.put(FEATPOINTS_TORMENTA, 0);
        contentValues.put(FEATPOINTS_GENERAL, 0);
        db.insert(FEATPOINTS_TABLE_NAME, null, contentValues);

        return charid;
    }

    public boolean insertClasse(String name, int bba, int pvs, int pericias, boolean prestígio, int nivelmax, String origem, String descrição) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_COLUMN_NAME, name);
        contentValues.put(CLASS_COLUMN_BBA, bba);
        contentValues.put(CLASS_COLUMN_PVS, pvs);
        contentValues.put(CLASS_COLUMN_PERÍCIAS, pericias);
        contentValues.put(CLASS_COLUMN_PRESTÍGIO, prestígio);
        contentValues.put(CLASS_COLUMN_NÍVEL, nivelmax);
        contentValues.put(CLASS_COLUMN_ORIGEM, origem);
        contentValues.put(CLASS_COLUMN_DESCRIPTION, descrição);
        out.println(contentValues);
        db.insert(CLASS_TABLE_NAME, null, contentValues);
        return true;
    }

    public int getClassLevel(int charid, int classid) {
        SQLiteDatabase db = this.getReadableDatabase();
        int level = 0;
        Cursor res = db.rawQuery("select " + CHARCLASSE_NÍVEL + " from " + CHARCLASSE_REL_NAME + " where " + CHARCLASSE_CLASS_ID + "=" + classid + " and " + CHARCLASSE_CHAR_ID + "=" + charid + "", null);

        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                level = res.getInt(res.getColumnIndex(DBHelper.CHARCLASSE_NÍVEL));
                res.moveToNext();
            }
        }

        return level;
    }

    public boolean insertRaça(String name, String origem, String descricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RACE_COLUMN_NAME, name);
        contentValues.put(RACE_COLUMN_ORIGEM, origem);
        contentValues.put(RACE_COLUMN_DESCRIPTION, descricao);
        out.println(contentValues);
        db.insert(RACE_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String table, int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        if (table == "personagem")
            res = db.rawQuery("select * from personagens where id=" + id + "", null);
        else if (table == "classe")
            res = db.rawQuery("select * from classes where classid=" + id + "", null);
        else if (table == "raca")
            res = db.rawQuery("select * from racas where racaid=" + id + "", null);
        else if (table == "talento")
            res = db.rawQuery("select * from talentos where talentoid=" + id + "", null);
        else if (table == "pericia")
            res = db.rawQuery("select * from pericias where periciaid=" + id + "", null);
        else if (table == "armadura")
            res = db.rawQuery("select * from armaduras where armaduraid=" + id + "", null);
        else if (table == "arma")
            res = db.rawQuery("select * from armas where armaid=" + id + "", null);
        return res;
    }

    public boolean levelUp(int charid, int classid) {
        SQLiteDatabase db = this.getReadableDatabase();
        int level = getClassLevel(charid, classid) + 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHARCLASSE_CLASS_ID, classid);
        contentValues.put(CHARCLASSE_CHAR_ID, charid);
        contentValues.put(CHARCLASSE_NÍVEL, level);
        Cursor res = null;
        if (level > 1)
            db.update(CHARCLASSE_REL_NAME, contentValues, CHARCLASSE_CLASS_ID + " = ? and " + CHARCLASSE_CHAR_ID + " = ?", new String[]{Integer.toString(classid), Integer.toString(charid)});
        else
            db.insert(CHARCLASSE_REL_NAME, null, contentValues);
        contentValues = new ContentValues();
        res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            level = res.getInt(res.getColumnIndex("nivel")) + 1;
            res.moveToNext();
        }
        contentValues.put(CHAR_COLUMN_NIVEL, level);
        if (level == 1)
            contentValues.put(CHAR_COLUMN_FCLASS, classid);
        db.update(CHAR_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(charid)});
        if (level % 2 == 1)
            featPointChange("Geral", charid, 1);
        return true;
    }

    public boolean abilityGet(int charid, int abilityid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = hasAbility(charid, abilityid);
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHARABILITY_CHAR_ID, charid);
        contentValues.put(CHARABILITY_SKILL_ID, abilityid);
        if (res != null) {
            contentValues.put(CHARABILITY_AUX, res.getInt(res.getColumnIndex(DBHelper.CHARABILITY_AUX)) + 1);
            db.update(CHARABILITY_REL_NAME, contentValues, CHARABILITY_SKILL_ID + " = ? and " + CHARABILITY_CHAR_ID + " = ?", new String[]{Integer.toString(abilityid), Integer.toString(charid)});
        } else {
            contentValues.put(CHARABILITY_AUX, 1);
            db.insert(CHARABILITY_REL_NAME, null, contentValues);
        }
        return true;
    }

    public Cursor hasFeat(int charid, int featid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CHARFEAT_REL_NAME + " where " + CHARFEAT_CHAR_ID + "=" + charid + " and " + CHARFEAT_FEAT_ID + "=" + featid + "", null);
        res.moveToFirst();
        if (res.getCount() > 0)
            return res;
        else
            return null;
    }

    public Cursor hasAbility(int charid, int abilityid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CHARABILITY_REL_NAME + " where " + CHARABILITY_CHAR_ID + "=" + charid + " and " + CHARABILITY_SKILL_ID + "=" + abilityid + "", null);
        res.moveToFirst();
        if (res.getCount() > 0)
            return res;
        else
            return null;
    }

    public Cursor hasSkill(int charid, int skillid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CHARSKILL_REL_NAME + " where " + CHARSKILL_CHAR_ID + "=" + charid + " and " + CHARSKILL_SKILL_ID + "=" + skillid + "", null);
        res.moveToFirst();
        if (res.getCount() > 0)
            return res;
        else
            return null;
    }

    public Cursor hasArmor(int charid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SPECARMOR_TABLE_NAME + " where " + SPECARMOR_CHAR_ID + "=" + charid + "", null);
        res.moveToFirst();
        if (res.getCount() > 0)
            return res;
        else
            return null;
    }

    public String getName(String type, int id) {
        String coluna = "", nome = "";
        boolean error = false;
        Cursor res = getData(type, id);
        res.moveToFirst();
        if (type == "talento")
            coluna = "talentonome";
        else if (type == "pericia")
            coluna = "pericianome";
        else if (type == "classe")
            coluna = "classnome";
        else if (type == "raca")
            coluna = "racanome";
        else if (type == "arma")
            coluna = "armanome";
        else if (type == "armadura")
            coluna = "armaduranome";
        else
            error = true;
        if (!error) {
            while (res.isAfterLast() == false) {
                nome = res.getString(res.getColumnIndex(coluna));
                res.moveToNext();
            }
            return nome;

        } else
            return "";
    }

    public String getDescr(String nome, String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        String desc;
        ArrayList<String> saida = new ArrayList<String>();
        if (table == "classe") {
            res = db.rawQuery("select classdescricao from classes where " + DBHelper.CLASS_COLUMN_NAME + " LIKE '" + nome + "'", null);
            if (res != null) {
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    saida.add(res.getString(res.getColumnIndex(DBHelper.CLASS_COLUMN_DESCRIPTION)));
                    res.moveToNext();
                }
                desc = saida.toString().substring(1, saida.toString().length() - 1);
                return desc;
            } else {
                return "Nada encontrado";
            }
        } else if (table == "raca") {
            res = db.rawQuery("select racadescricao from racas where " + DBHelper.RACE_COLUMN_NAME + " LIKE '" + nome + "'", null);
            if (res != null) {
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    saida.add(res.getString(res.getColumnIndex(DBHelper.RACE_COLUMN_DESCRIPTION)));
                    res.moveToNext();
                }
                desc = saida.toString().substring(1, saida.toString().length() - 1);
                return desc;
            } else {
                return "Nada encontrado";
            }
        }

        return "Tabela não encontrada";

    }

    public int mod(int charid, String atributo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from personagens where id=" + charid + "", null);
        Integer value;
        res.moveToFirst();
        if (atributo == "FOR")
            value = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_FOR));
        else if (atributo == "DES")
            value = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_DES));
        else if (atributo == "CON")
            value = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_CON));
        else if (atributo == "INT")
            value = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_INT));
        else if (atributo == "SAB")
            value = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_SAB));
        else if (atributo == "CAR")
            value = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_CAR));
        else
            value = 0;
        return (int) (value - 10) / 2;
    }

    public int CA(int charid) {
        int CA = 10;
        int aux = 0, aux2 = mod(charid, "DES");
        Cursor res = hasArmor(charid);
        if (res == null) {
            CA += aux2;
            if (hasFeat(charid, 18) != null)
                CA += mod(charid, "CON");
        } else {
            res = getData("armadura", res.getInt(res.getColumnIndex(SPECARMOR_COLUMN_BASE)));
            aux = res.getInt(res.getColumnIndex(ARMOR_COLUMN_BMD));
            if (aux < aux2)
                CA += aux;
            else
                CA += aux2;
        }
        res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux = (int) (res.getInt(res.getColumnIndex("nivel")) / 2);
            res.moveToNext();
        }
        CA += aux;
        if (hasFeat(charid, 33) != null)
            CA++;
        if (hasFeat(charid, 17) != null)
            CA++;
        return CA;
    }

    public int Fort(int charid) {
        int aux = 0, fort = 0;
        Cursor res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux = (int) (res.getInt(res.getColumnIndex("nivel")) / 2);
            res.moveToNext();
        }
        fort += aux;
        fort += mod(charid, "CON");
        res = hasFeat(charid, 130);
        if (res != null)
            fort += 2 * res.getInt(res.getColumnIndex(CHARFEAT_TIMES));
        return fort;
    }

    public int Ref(int charid) {
        int aux = 0, ref = 0;
        Cursor res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux = (int) (res.getInt(res.getColumnIndex("nivel")) / 2);
            res.moveToNext();
        }
        ref += aux;
        ref += mod(charid, "DES");
        res = hasFeat(charid, 133);
        if (res != null)
            ref += 2 * res.getInt(res.getColumnIndex(CHARFEAT_TIMES));
        return ref;
    }

    public int Von(int charid) {
        int aux = 0, von = 0;
        Cursor res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux = (int) (res.getInt(res.getColumnIndex("nivel")) / 2);
            res.moveToNext();
        }
        von += aux;
        von += mod(charid, "SAB");
        res = hasFeat(charid, 133);
        if (res != null)
            von += 2 * res.getInt(res.getColumnIndex(CHARFEAT_TIMES));
        return von;
    }

    public int BBA(int charid) {
        int BBA = 0;
        int BBAtype = 0;
        int nível;
        Cursor res;
        for (int i = 1; i < numberOfRows("classe"); i++) {
            nível = getClassLevel(charid, i);
            if (nível > 0) {
                res = getData("classe", i);
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    BBAtype = res.getInt(res.getColumnIndex("bbaclasse"));
                    res.moveToNext();
                }
                if (BBAtype == 1)
                    BBA += (int) (nível / 2);
                else if (BBAtype == 2)
                    BBA += (int) (3 * nível / 4);
                else if (BBAtype == 3)
                    BBA += nível;
            }
        }
        return BBA;
    }

    public int PVs(int charid) {
        int PVs = 0;
        int aux1 = 0, aux2 = 0, nível;
        Cursor res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux1 = (res.getInt(res.getColumnIndex("nivel")));
            aux2 = res.getInt(res.getColumnIndex("primclasse"));
            res.moveToNext();
        }
        PVs += aux1 * mod(charid, "CON");
        res = getData("classe", aux2);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux1 = res.getInt(res.getColumnIndex("pvsclasse"));
            res.moveToNext();
        }
        PVs += aux1 * 3;
        for (int i = 1; i < numberOfRows("classe"); i++) {
            nível = getClassLevel(charid, i);
            if (nível > 0) {
                res = getData("classe", i);
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    aux1 = res.getInt(res.getColumnIndex("pvsclasse"));
                    res.moveToNext();
                }
                PVs += aux1;
            }
        }
        return PVs;
    }

    public int BonusAtaque(String nomearma) {
        SQLiteDatabase db = this.getReadableDatabase();
        int charid = 0, bonus = 0, aux1 = 0, aux2 = 0;
        Cursor res = db.rawQuery("select * from armaespecifica where armaespecificanome=" + nomearma + "", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            charid = res.getInt(res.getColumnIndex(SPECWEAPON_CHAR_ID));
            aux1 = res.getInt(res.getColumnIndex(SPECWEAPON_COLUMN_BONUS));
            aux2 = res.getInt(res.getColumnIndex(SPECWEAPON_COLUMN_BASE));
            res.moveToNext();
        }
        bonus += BBA(charid);
        bonus += aux1;
        res = getData("arma", aux2);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux1 = res.getInt(res.getColumnIndex(WEAPON_COLUMN_HAND));
            aux2 = res.getInt(res.getColumnIndex(WEAPON_COLUMN_DES));
            res.moveToNext();
        }
        if ((aux1 == 4) || ((hasFeat(charid, 2) != null) && (aux2 > 0))) {
            bonus += mod(charid, "DES");
        } else
            bonus += mod(charid, "FOR");
        return bonus;
    }

    public String BonusDano(String nomearma) {
        SQLiteDatabase db = this.getReadableDatabase();
        int charid = 0, aux1 = 0, aux2 = 0, bonus = 0;
        String damage = "";
        Cursor res = db.rawQuery("select * from armaespecifica where armaespecificanome=" + nomearma + "", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            charid = res.getInt(res.getColumnIndex(SPECWEAPON_CHAR_ID));
            aux1 = res.getInt(res.getColumnIndex(SPECWEAPON_COLUMN_BONUS));
            aux2 = res.getInt(res.getColumnIndex(SPECWEAPON_COLUMN_BASE));
            res.moveToNext();
        }
        bonus += aux1;
        res = getData("arma", aux2);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux1 = res.getInt(res.getColumnIndex(WEAPON_COLUMN_HAND));
            aux2 = res.getInt(res.getColumnIndex(WEAPON_COLUMN_DES));
            damage = res.getString(res.getColumnIndex(WEAPON_COLUMN_DAMAGE));
            res.moveToNext();
        }
        if ((aux1 == 3) && (hasFeat(charid, 39) != null)) {
            bonus += 2 * mod(charid, "FOR");
        } else if ((aux1 == 4) && (hasFeat(charid, 49) != null)) {
            bonus += mod(charid, "DES");
        }
        if ((aux2 > 0) && (hasFeat(charid, 15) != null)) {
            bonus += mod(charid, "INT");
        }
        damage += "+" + Integer.toString(bonus);
        return damage;
    }

    public String Crítico(int armaid) {
        int margem = 1, mult = 1;
        String result = "";
        Cursor res = getData("arma", armaid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            margem = res.getInt(res.getColumnIndex(WEAPON_COLUMN_CRITICAL));
            mult = res.getInt(res.getColumnIndex(WEAPON_COLUMN_CRITICAL));
            res.moveToNext();
        }
        if (margem == 3)
            result += "18-20";
        else if (margem == 2)
            result += "19-20";
        if ((margem > 1) && (mult != 1))
            result += "/";
        if (mult == 2)
            result += "x3";
        else if (mult == 3)
            result += "x4";
        else if (mult == 0)
            result += "esp";
        else if ((mult == 1) && (margem == 1))
            result = "x2";
        return result;


    }

    public int numberOfRows(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = 0;
        if (table == "personagem")
            numRows = (int) DatabaseUtils.queryNumEntries(db, CHAR_TABLE_NAME);
        else if (table == "classe")
            numRows = (int) DatabaseUtils.queryNumEntries(db, CLASS_TABLE_NAME);
        else if (table == "raca")
            numRows = (int) DatabaseUtils.queryNumEntries(db, RACE_TABLE_NAME);
        else if (table == "talento")
            numRows = (int) DatabaseUtils.queryNumEntries(db, FEAT_TABLE_NAME);
        else if (table == "pericia")
            numRows = (int) DatabaseUtils.queryNumEntries(db, SKILL_TABLE_NAME);
        return numRows;
    }

    public int periciaBonus(int charid, int periciaid) {
        String att = "";
        int aux1 = 0, bonus = 0, train = 0, pen = 0;
        Cursor res = getData("pericia", periciaid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            train = res.getInt(res.getColumnIndex(SKILL_COLUMN_TRAIN));
            pen = res.getInt(res.getColumnIndex(SKILL_COLUMN_ARMOR));
            att = res.getString(res.getColumnIndex(SKILL_COLUMN_ATT));
            res.moveToNext();
        }
        bonus += mod(charid, att);

        if (pen > 0) {
            res = hasArmor(charid);
            if (res != null) {
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    aux1 = res.getInt(res.getColumnIndex(SPECARMOR_COLUMN_BASE));
                    res.moveToNext();
                }
                res = getData("armadura", aux1);
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    aux1 = res.getInt(res.getColumnIndex(ARMOR_COLUMN_PA));
                    res.moveToNext();
                }
            }
            bonus = bonus - aux1;
        }
        res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux1 = res.getInt(res.getColumnIndex(CHAR_COLUMN_NIVEL));
            res.moveToNext();
        }
        if (hasSkill(charid, periciaid) != null)
            bonus += aux1 + 3;
        else if (train == 0)
            bonus += aux1 / 2;
        else
            bonus = 0;
        return bonus;
    }

    public int pontosPericia(int charid) {
        SQLiteDatabase db = this.getReadableDatabase();
        int aux1 = 0, raca = 0, classe = 0, pontos = 0;
        Cursor res = getData("personagem", charid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            raca = res.getInt(res.getColumnIndex(CHAR_COLUMN_RAÇA));
            classe = res.getInt(res.getColumnIndex(CHAR_COLUMN_FCLASS));
            res.moveToNext();
        }
        if (raca == 5)
            pontos += 2;
        res = getData("classe", classe);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            aux1 = res.getColumnIndex(CLASS_COLUMN_PERÍCIAS);
            res.moveToNext();
        }
        pontos += aux1;
        pontos += mod(charid, "INT");
        res = db.rawQuery("select * from " + CHARSKILL_REL_NAME + "", null);
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getInt(res.getColumnIndex(CHARSKILL_CHAR_ID)) == charid)
                    pontos--;
                res.moveToNext();
            }
        }
        return pontos;

    }

    public boolean raceUpdate(int charid) {
        Cursor res = getData("personagem", charid);
        res.moveToFirst();
        int racaid = 0;
        while (res.isAfterLast() == false) {
            racaid = res.getInt(res.getColumnIndex(CHAR_COLUMN_RAÇA));
            res.moveToNext();
        }
        if (racaid == 1) {
            attChange(charid, "CON", 4);
            attChange(charid, "SAB", 2);
            attChange(charid, "DES", -2);
        } else if (racaid == 2) {
            attChange(charid, "DES", 4);
            attChange(charid, "INT", 2);
            attChange(charid, "CON", -2);
        } else if (racaid == 3) {
            attChange(charid, "DES", 4);
            attChange(charid, "CON", 2);
            attChange(charid, "CHA", -2);
        } else if (racaid == 4) {
            attChange(charid, "DES", 4);
            attChange(charid, "CHA", 2);
            attChange(charid, "FOR", -2);
        } else if (racaid == 7) {
            attChange(charid, "FOR", 4);
            attChange(charid, "CON", 2);
            attChange(charid, "CHA", -2);
        } else if (racaid == 8) {
            attChange(charid, "CHA", 4);
            attChange(charid, "INT", 2);
            attChange(charid, "SAB", -2);
        }
        return true;
    }

    public boolean AbilityTable(int charid, int classid, int level) {
        if (classid == 1) {
            if ((level % 4 == 0) || (level == 1))
                abilityGet(charid, 1);
            if (level == 1)
                abilityGet(charid, 2);
            if (level == 2)
                abilityGet(charid, 3);
            if (level % 6 == 3)
                abilityGet(charid, 4);
            if (level == 5)
                abilityGet(charid, 5);
            if ((level > 6) && (level % 3 == 1))
                abilityGet(charid, 6);
            if (level == 11)
                abilityGet(charid, 7);
            if (level == 14)
                abilityGet(charid, 8);
            if (level == 17)
                abilityGet(charid, 9);
            if (level == 20)
                abilityGet(charid, 10);
        } else if (classid == 2) {
            if ((level % 2 == 1) || (level == 2))
                abilityGet(charid, 12);
            if (level == 1)
                abilityGet(charid, 11);
        } else if (classid == 3) {
            if ((level % 2 == 1) || (level == 2))
                abilityGet(charid, 14);
            if (level == 1)
                abilityGet(charid, 13);
        } else if (classid == 5) {
            if ((level % 5 == 0) || (level == 1))
                abilityGet(charid, 24);
        }
        return true;
    }

    public boolean updatePersonagem(Integer id, String name, int força, int destreza, int constituição, int inteligência, int sabedoria, int carisma) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHAR_COLUMN_NAME, name);
        contentValues.put(CHAR_COLUMN_FOR, força);
        contentValues.put(CHAR_COLUMN_DES, destreza);
        contentValues.put(CHAR_COLUMN_CON, constituição);
        contentValues.put(CHAR_COLUMN_INT, inteligência);
        contentValues.put(CHAR_COLUMN_SAB, sabedoria);
        contentValues.put(CHAR_COLUMN_CAR, carisma);
        db.update(CHAR_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public boolean attChange(int charid, String att, int val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor res = getData("Personagem", charid);

        res = getData("personagem", charid);
        res.moveToFirst();
        int valAtr = 0;
        if (att == "FOR") {
            while (res.isAfterLast() == false) {
                valAtr = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_FOR));
                res.moveToNext();
            }
            contentValues.put(CHAR_COLUMN_FOR, valAtr + val);
        } else if (att == "DES") {
            while (res.isAfterLast() == false) {
                valAtr = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_DES));
                res.moveToNext();
            }
            contentValues.put(CHAR_COLUMN_DES, valAtr + val);
        } else if (att == "CON") {
            while (res.isAfterLast() == false) {
                valAtr = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_CON));
                res.moveToNext();
            }
            contentValues.put(CHAR_COLUMN_CON, valAtr + val);
        } else if (att == "INT") {
            while (res.isAfterLast() == false) {
                valAtr = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_INT));
                res.moveToNext();
            }
            contentValues.put(CHAR_COLUMN_INT, valAtr + val);
        } else if (att == "SAB") {
            while (res.isAfterLast() == false) {
                valAtr = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_SAB));
                res.moveToNext();
            }
            contentValues.put(CHAR_COLUMN_SAB, valAtr + val);
        } else if (att == "CAR") {
            while (res.isAfterLast() == false) {
                valAtr = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_CAR));
                res.moveToNext();
            }
            contentValues.put(CHAR_COLUMN_CAR, valAtr + val);
        }
        String[] aux = {Integer.toString(charid)};
        db.update(CHAR_TABLE_NAME, contentValues, "id = ? ", aux);
        return true;
    }

    public Integer deletePersonagem(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CHAR_TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAll(String table) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        String column = null;
        if (table == "personagens") {
            res = db.rawQuery("select * from personagens", null);
            column = CHAR_COLUMN_NAME;
        } else if (table == "classes") {
            res = db.rawQuery("select * from classes", null);
            column = CLASS_COLUMN_NAME;
        } else if (table == "racas") {
            res = db.rawQuery("select * from racas", null);
            column = RACE_COLUMN_NAME;
        } else if (table == "pericias") {
            res = db.rawQuery("select * from pericias", null);
            column = SKILL_COLUMN_NAME;
        } else if (table == "talentos") {
            res = db.rawQuery("select * from talentos", null);
            column = FEAT_COLUMN_NAME;
        } else if (table == "habilidades") {
            res = db.rawQuery("select * from habilidades", null);
            column = ABILITY_COLUMN_NAME;
        }
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(column)));
                res.moveToNext();
            }
        }
        return array_list;
    }

    public ArrayList<String> getAllId(String table) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        String column = null;
        if (table == "personagens") {
            res = db.rawQuery("select * from personagens", null);
            column = CHAR_COLUMN_ID;
        } else if (table == "classes") {
            res = db.rawQuery("select * from classes", null);
            column = CLASS_COLUMN_ID;
        } else if (table == "racas") {
            res = db.rawQuery("select * from racas", null);
            column = RACE_COLUMN_ID;
        } else if (table == "pericias") {
            res = db.rawQuery("select * from pericias", null);
            column = SKILL_COLUMN_ID;
        } else if (table == "talentos") {
            res = db.rawQuery("select * from talentos", null);
            column = FEAT_COLUMN_ID;
        } else if (table == "habilidades") {
            res = db.rawQuery("select * from habilidades", null);
            column = ABILITY_COLUMN_ID;
        }
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(column)));
                res.moveToNext();
            }
        }
        return array_list;
    }

    public ArrayList<String> getAllFeat(int charid, String table) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String column = null;
        Cursor res = db.rawQuery("select * from talentos where " + FEAT_COLUMN_TYPE + " LIKE '" + table + "'", null);
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                column = res.getString(res.getColumnIndex(FEAT_COLUMN_ID));
                if (hasFeat(charid, Integer.parseInt(column)) == null)
                    array_list.add(res.getString(res.getColumnIndex(FEAT_COLUMN_ID)));
                res.moveToNext();
            }
        }
        return array_list;
    }

    public String getPrereq(int featid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String type, extra, value, prereq = "";
        Cursor res = db.rawQuery("select * from " + PREREQ_TABLE_NAME + " where " + PREREQ_COLUMN_FID + "=" + featid + "", null);
        if ((res != null) && (res.getCount() > 0)) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                type = res.getString(res.getColumnIndex(PREREQ_COLUMN_TYPE));
                extra = res.getString(res.getColumnIndex(PREREQ_COLUMN_EXTRA));
                value = res.getString(res.getColumnIndex(PREREQ_COLUMN_VALUE));
                if (type == "Atributo") {
                    prereq += extra + " " + value + "; ";
                } else if (type == "Talento") {
                    prereq += getName("Talento", Integer.parseInt(value)) + "; ";
                } else if (type == "BBA") {
                    prereq += "BBA " + value + "; ";
                } else if (type == "Classe") {
                    prereq += extra + " de " + value + " nível; ";
                } else if ((type == "Perícia") && (value == "1")) {
                    prereq += "Treinado em " + extra + "; ";
                }
                res.moveToNext();
            }
        }
        return prereq;
    }

    public boolean featPrereq(int charid, int featid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String featype = "", type, esp;
        boolean answer = true;
        int val;
        int[] value = new int[6];
        Cursor res = getData("talento", featid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            featype = res.getString(res.getColumnIndex(FEAT_COLUMN_TYPE));
            res.moveToNext();
        }
        int pontos = featPoints(featype, charid);
        if ((pontos > 0) || (featPoints("Geral", charid) > 0)) {
            res = db.rawQuery("select * from " + CHAR_TABLE_NAME + " where " + CHAR_COLUMN_ID + " = " + charid + "", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                value[0] = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_FOR));
                value[1] = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_DES));
                value[2] = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_CON));
                value[3] = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_INT));
                value[4] = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_SAB));
                value[5] = res.getInt(res.getColumnIndex(DBHelper.CHAR_COLUMN_CAR));
                res.moveToNext();
            }
            res = db.rawQuery("select * from " + PREREQ_TABLE_NAME + " where " + PREREQ_COLUMN_FID + "=" + featid + "", null);
            if (res != null) {
                res.moveToFirst();
                while (res.isAfterLast() == false) {
                    type = res.getString(res.getColumnIndex(PREREQ_COLUMN_TYPE));
                    esp = res.getString(res.getColumnIndex(PREREQ_COLUMN_EXTRA));
                    val = res.getInt(res.getColumnIndex(PREREQ_COLUMN_VALUE));
                    if (type == "Atributo") {

                        if (esp == "FOR")
                            if (value[0] < val)
                                answer = false;
                            else ;
                        else if (esp == "DES")
                            if (value[1] < val)
                                answer = false;
                            else ;
                        else if (esp == "CON")
                            if (value[2] < val)
                                answer = false;
                            else ;
                        else if (esp == "INT")
                            if (value[3] < val)
                                answer = false;
                            else ;
                        else if (esp == "SAB")
                            if (value[4] < val)
                                answer = false;
                            else ;
                        else if (esp == "CAR")
                            if (value[5] < val)
                                answer = false;
                            else ;
                    } else if (type == "Talento") {
                        if (hasFeat(charid, val) == null)
                            answer = false;
                    } else if (type == "BBA") {
                        if (BBA(charid) < val)
                            answer = false;
                    }
                    res.moveToNext();
                }
            }
        } else
            answer = false;
        return answer;
    }

    public boolean featBuy(int charid, int featid, int aux) {
        SQLiteDatabase db = this.getReadableDatabase();
        String type = "";
        int auxi = 0;
        Cursor res = hasFeat(charid, featid);
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHARFEAT_CHAR_ID, charid);
        contentValues.put(CHARFEAT_FEAT_ID, charid);
        contentValues.put(CHARFEAT_AUX, aux);
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                auxi = res.getInt(res.getColumnIndex(DBHelper.CHARFEAT_TIMES)) + 1;
                res.moveToNext();
            }
            contentValues.put(CHARFEAT_TIMES, auxi);
            db.update(CHARFEAT_REL_NAME, contentValues, CHARFEAT_FEAT_ID + " = ? and " + CHARFEAT_CHAR_ID + " = ?", new String[]{Integer.toString(featid), Integer.toString(charid)});
        } else {
            contentValues.put(CHARFEAT_TIMES, 1);
            db.insert(CHARFEAT_REL_NAME, null, contentValues);
        }
        res = getData("talento", featid);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            type = res.getString(res.getColumnIndex(FEAT_COLUMN_TYPE));
            res.moveToNext();
        }
        if (featPoints(type, charid) > 0)
            featPointChange(type, charid, -1);
        else
            featPointChange("Geral", charid, -1);
        return true;
    }

    public int featPoints(String feat, int charid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String type = "";
        if (feat == "Combate")
            type = FEATPOINTS_COMBAT;
        else if (feat == "Magia")
            type = FEATPOINTS_MAGIC;
        else if (feat == "Perícia")
            type = FEATPOINTS_SKILL;
        else if (feat == "Poder Concedido")
            type = FEATPOINTS_DIVINE;
        else if (feat == "Tormenta")
            type = FEATPOINTS_TORMENTA;
        else
            type = FEATPOINTS_GENERAL;
        Cursor res = db.rawQuery("select * from " + FEATPOINTS_TABLE_NAME + " where " + FEATPOINTS_CHARID + "=" + charid + "", null);
        res.moveToFirst();
        int answer = 0;
        while (res.isAfterLast() == false) {
            answer = res.getInt(res.getColumnIndex(type));
            res.moveToNext();
        }
        return answer;
    }

    public boolean featPointChange(String feat, int charid, int value) {
        SQLiteDatabase db = this.getReadableDatabase();
        String type = "";
        if (feat == "Combate")
            type = FEATPOINTS_COMBAT;
        else if (feat == "Magia")
            type = FEATPOINTS_MAGIC;
        else if (feat == "Perícia")
            type = FEATPOINTS_SKILL;
        else if (feat == "Poder Concedido")
            type = FEATPOINTS_DIVINE;
        else if (feat == "Tormenta")
            type = FEATPOINTS_TORMENTA;
        else
            type = FEATPOINTS_GENERAL;
        int points = featPoints(feat, charid) + value;
        ContentValues contentValues = new ContentValues();
        contentValues.put(type, points);
        db.update(FEATPOINTS_TABLE_NAME, contentValues, FEATPOINTS_CHARID + " = ?", new String[]{Integer.toString(charid)});
        return true;
    }

    public ArrayList<String> getAllWeaponsId(int charid) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        int column = 0;
        Cursor res = db.rawQuery("select * from armas", null);
        if (res != null) {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                    column = res.getInt(res.getColumnIndex(WEAPON_COLUMN_TYPE));
                    if ((column == 1)&&(hasFeat(charid,68)!=null))
                        array_list.add(res.getString(res.getColumnIndex(FEAT_COLUMN_ID)));
                    else if ((column == 2)&&(hasFeat(charid,69)!=null))
                        array_list.add(res.getString(res.getColumnIndex(FEAT_COLUMN_ID)));
                    else if ((column == 3)&&(hasFeat(charid,70)!=null))
                        array_list.add(res.getString(res.getColumnIndex(FEAT_COLUMN_ID)));
                res.moveToNext();
            }
        }
        return array_list;
    }

}