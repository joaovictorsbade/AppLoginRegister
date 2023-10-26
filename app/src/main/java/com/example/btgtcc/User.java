package com.example.btgtcc;

public class User {

    public static final String TAG = "User Entity";

    private String mUserName;


    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mEmail='" + mEmail +
                '}';
    }

//Login
    public User( String password, String email) {
        mPassword = password;
        mEmail = email;
    }

//    Cadastro
    public User(String userName, String password, String email) {
        mUserName = userName;
        mPassword = password;
        mEmail = email;
    }



    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public long getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(long createDate) {
        mCreateDate = createDate;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public String getResetPasswordOtp() {
        return mResetPasswordOtp;
    }

    public void setResetPasswordOtp(String resetPasswordOtp) {
        mResetPasswordOtp = resetPasswordOtp;
    }

    public long getResetPasswordCreatedAt() {
        return mResetPasswordCreatedAt;
    }

    public void setResetPasswordCreatedAt(long resetPasswordCreatedAt) {
        mResetPasswordCreatedAt = resetPasswordCreatedAt;
    }


    private int mId;
    private String mPassword;
    private String mEmail;
    private long mCreateDate;
    private String mApiKey;
    private String mResetPasswordOtp;
    private long mResetPasswordCreatedAt;



}