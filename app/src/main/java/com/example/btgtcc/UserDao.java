package com.example.btgtcc;

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static final String TAG = "CRUD table User";

    public static int insertUser(User mUser, Context mContext) {
        int vResponse = 0; // variável de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mUser.getUserName());
            mPreparedStatement.setString(2, mUser.getEmail());
            mPreparedStatement.setString(3, mUser.getPassword());

            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return vResponse; // 0 não fez insert, 1 fez insert com sucesso
    }

    public static int updateUser(User mUser, Context mContext) {
        int vResponse = 0; // variável de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mUser.getUserName());
            mPreparedStatement.setString(2, mUser.getEmail());
            mPreparedStatement.setString(3, mUser.getPassword());
            mPreparedStatement.setInt(4, mUser.getId());

            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return vResponse; // 0 não fez insert, 1 fez insert com sucesso
    }

    public static int deleteUser(User mUser, Context mContext) {
        int vResponse = 0; // variável de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "DELETE FROM usuario WHERE id = ?";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setInt(1, mUser.getId());

            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return vResponse; // 0 não fez insert, 1 fez insert com sucesso
    }

    public static List<User> listAllUsers(Context mContext) {
        List<User> mUserList = null;
        String mSql;

        try{
            mSql = "SELECT nome, email, senha FROM usuario ORDER BY name ASC";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            ResultSet mResultSet = mPreparedStatement.executeQuery();

            mUserList = new ArrayList<User>();

            while (mResultSet.next()) {
                mUserList.add(new User(
                        mResultSet.getString(2),
                        mResultSet.getString(3),
                        mResultSet.getString(4)
                ));

            }


        } catch (Exception e) {

            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return mUserList;

        }


//        Codigo Marcos
public static String authenticateUser(User mUser, Context mContext) {
    String mResponse = "";
//    String mSql = "SELECT id, nome , email, senha FROM usuario WHERE senha LIKE ? AND email LIKE ?";
    String mSql = "SELECT id, nome , email, senha FROM usuario WHERE senha=? AND email=?";
    try {
        PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

        mPreparedStatement.setString(1, mUser.getPassword());
        mPreparedStatement.setString(2, mUser.getEmail());

        ResultSet mResultSet = mPreparedStatement.executeQuery();

        while (mResultSet.next()) {
            mResponse = mResultSet.getString(2);
        }

    } catch (Exception e) {
        mResponse = "Exception";
        Log.e(TAG, e.getMessage());
        e.printStackTrace();
    }

    return mResponse;
}




















    //        Codigo do Joao
//    public static String authenticateUser(User mUser, Context mContext) {
//        String mResponse = "";
//        String mSql = "SELECT email, senha FROM usuario WHERE senha LIKE ? AND email LIKE ?";
//        try {
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            mPreparedStatement.setString(1, mUser.getPassword());
//            mPreparedStatement.setString(2, mUser.getEmail());
//
//            ResultSet mResultSet = mPreparedStatement.executeQuery();
//
//            while (mResultSet.next()) {
//                String senha = mResultSet.getString(1);
//                String email = mResultSet.getString(2);
//
//                mResponse = email + senha;
//            }
//
//        } catch (Exception e) {
//            mResponse = "Exception";
//            Log.e(TAG, e.getMessage());
//            e.printStackTrace();
//        }
//
//        return mResponse;
//    }

//    Codigo Charles
//    public static String authenticateUser(User mUser, Context mContext) {
//        String mResponse = "";
//        String mSql = "SELECT email, senha FROM usuario WHERE senha = ? AND email = ?";
//        try {
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            mPreparedStatement.setString(1, mUser.getPassword());
//            mPreparedStatement.setString(2, mUser.getEmail());
//
//            ResultSet mResultSet = mPreparedStatement.executeQuery();
//
//            if (mResultSet.next()) {
//                mResponse = "Authenticated"; // Se houver um resultado, o usuário está autenticado
//            } else {
//                mResponse = "Invalid"; // Se nenhum resultado for encontrado, o usuário não está autenticado
//            }
//
//        } catch (Exception e) {
//            mResponse = "Exception";
//            Log.e(TAG, e.getMessage());
//            e.printStackTrace();
//        }
//
//        return mResponse;
//    }

}