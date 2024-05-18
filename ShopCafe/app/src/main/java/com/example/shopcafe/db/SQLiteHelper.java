package com.example.shopcafe.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shopcafe.model.Bill;
import com.example.shopcafe.model.Product;
import com.example.shopcafe.model.User;
import com.example.shopcafe.model.UserDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbbtlandroid";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE users("+
                "username TEXT PRIMARY KEY,"+
                "password TEXT)";
        db.execSQL(sql);

        String sql1 = "CREATE TABLE profile("+
                "name TEXT,"+
                "email TEXT PRIMARY KEY,"+
                "contact TEXT,"+
                "birthdate TEXT,"+
                "password TEXT)";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE products("+
                "id int PRIMARY KEY,"+
                "urlImg TEXT,"+
                "productName TEXT,"+
                "description TEXT,"+
                "brand TEXT,"+
                "productPrice float,"+
                "num int)";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE bills("+
                "id TEXT PRIMARY KEY,"+
                "name TEXT,"+
                "phone TEXT,"+
                "dateOrder TEXT,"+
                "billProduct TEXT,"+
                "address TEXT,"+
                "totalPrices int)";
        db.execSQL(sql3);
//        onUpgrade(db,3,4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onCreate(db);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();

        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("users",null,null,null,null,null,null);
        while (rs!=null && rs.moveToNext()){
            String username = rs.getString(0);
            String password = rs.getString(1);
            list.add(new User(username,password));
        }
        return list;
    }

    //add 1 user
    public long addUser(User u){
        ContentValues values = new ContentValues();
        values.put("username",u.getUsername());
        values.put("password",u.getPassword());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        System.out.println("da add user"+u.getUsername()+" thanh cong");
        return sqLiteDatabase.insert("users", null, values);
    }
    public long addProfile(UserDetail u){
        ContentValues values = new ContentValues();
        values.put("name",u.getName());
        values.put("email",u.getEmail());
        values.put("contact",u.getContact());
        values.put("birthdate",u.getBirthdate());
        values.put("password",u.getPassword());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("profile", null, values);
    }

    //delete
    public int delete(String username){
        String whereClause = "username= ?";
        String[] whereArgs = {username};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("users",whereClause,whereArgs);
    }


    public boolean checkUserSigup(String username){
        List<User> list = getAllUser();
        for(User x:list){
            if(x.getUsername().equalsIgnoreCase(username)){
                return false;
            }
        }
        return true;
    }

    public boolean checkUser(User u){
        List<User> list = getAllUser();
        for(User x:list){
            if(x.getUsername().equalsIgnoreCase(u.getUsername()) && x.getPassword().equalsIgnoreCase(u.getPassword())){
                return true;
            }
        }
        return false;
    }
    public User getUserByUserName(String username) {
        String[] projection = {
                "username",
                "password",
        };

        String selection = "username LIKE ?";
        String[] selectionArgs = { "%" + username + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        // Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.query(
                "users", projection, selection, selectionArgs, null, null, null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            String userName = cursor.getString(0);
            String password = cursor.getString(1);
            user = new User();
            user.setUsername(userName);
            user.setPassword(password);
        }

        // Đóng Cursor và SQLiteDatabase
        if (cursor != null) {
            cursor.close();
        }
        sqLiteDatabase.close();

        return user;
    }
    public UserDetail getProfileByUserName(String username) {
        String[] projection = {
                "name",
                "email",
                "contact",
                "birthdate",
                "password",
        };

        String selection = "email LIKE ?";
        String[] selectionArgs = { "%" + username + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        // Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.query(
                "profile", projection, selection, selectionArgs, null, null, null
        );

        UserDetail user = null;
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            String contact = cursor.getString(2);
            String birthdate = cursor.getString(3);
            String password = cursor.getString(4);
            user = new UserDetail();
            user.setName(name);
            user.setEmail(email);
            user.setContact(contact);
            user.setBirthdate(birthdate);
            user.setPassword(password);
        }

        // Đóng Cursor và SQLiteDatabase
        if (cursor != null) {
            cursor.close();
        }
        sqLiteDatabase.close();

        return user;
    }
    /// Product
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();

        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("products", null, null, null, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String urlImg = rs.getString(1);
            String productName = rs.getString(2);
            String description = rs.getString(3);
            String brand = rs.getString(4);
            double productPrice = rs.getDouble(5);
            int num = rs.getInt(6);
            list.add(new Product(id,urlImg,productName,description,brand,productPrice,num));
        }
        return list;
    }
    public long addProduct(Product u){
        ContentValues values = new ContentValues();
        values.put("id",u.getId());
        values.put("urlImg",u.getUrlImg());
        values.put("productName",u.getProductName());
        values.put("description",u.getDescription());
        values.put("brand",u.getBrand());
        values.put("productPrice",u.getProductPrice());
        values.put("num",u.getNumProduct());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("products", null, values);
    }

    ///Bill
    public List<Bill> getAllBill() {
        List<Bill> list = new ArrayList<>();

        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("bills", null, null, null, null, null, null);
        while (rs != null && rs.moveToNext()) {
            String id = rs.getString(0);
            String name = rs.getString(1);
            String phone = rs.getString(2);
            String dateOrder = rs.getString(3);
            String billProduct = rs.getString(4);
            String address = rs.getString(5);
            int totalPrices = rs.getInt(6);
            list.add(new Bill(id,name,phone,dateOrder,billProduct,address,totalPrices));
        }
        return list;
    }
    public long addBill(Bill u){
        ContentValues values = new ContentValues();
        values.put("id",u.getId());
        values.put("name",u.getName());
        values.put("phone",u.getPhone());
        values.put("dateOrder",u.getDateOrder());
        values.put("billProduct",u.getBillProduct());
        values.put("address",u.getAddress());
        values.put("totalPrices",u.getTotalPrices());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("bills", null, values);
    }
    public void deleteAllRowsFromTable(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }
}
