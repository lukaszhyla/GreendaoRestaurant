package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(4,"com.lhyla.myapplication.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try{
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addTables(Schema schema){
        Entity product = schema.addEntity("Product");
        product.addIdProperty().primaryKey().autoincrement();
        product.addStringProperty("name");
        product.addFloatProperty("price");
    }
}
