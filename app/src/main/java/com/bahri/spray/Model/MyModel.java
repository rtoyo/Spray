package com.bahri.spray.Model;


import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bahri.spray.Controller.CurrentMediaFragment;
import com.bahri.spray.Controller.LoginActivity;
import com.bahri.spray.Controller.MainTabActivity;
import com.bahri.spray.Controller.SignUpActivity;
import com.bahri.spray.Controller.SprayFragment;
import com.bahri.spray.SprayUser;

import java.util.ArrayList;

/**
 * Created by user on 19/03/2015.
 */
public class MyModel {
    public static ArrayList<SprayUser> discoverdUsers = new ArrayList<SprayUser>();
    public static ArrayList<Integer> discoverdUsersIDSLocalArray = new ArrayList<Integer>();
    private static MyModel ourInstance = new MyModel();
    private ModelInterface model;
    public static MyModel getInstance() {
        return ourInstance;
    }


    SprayUser localUser = null;
    private MyModel() {
        model = new ParseModel();
    }

    public void addCloseUser(SprayUser user) {
        model.addCloseUser(user);
        discoverdUsers.add(user);
    }

    public SprayUser getCurrentUser() {
        if (localUser == null){
            localUser = model.getCurrentUser();
        }
        return localUser;
    }

    public interface GetSprayUserCallback{
        public void done(SprayUser user);
    }
    public void getBTUserDetails(String macAddress, final GetSprayUserCallback callback) {
        model.getBTUserDetails(macAddress,callback);
    }

    public interface getUserImageCllback{
        public void done(Bitmap image);
    }
    public void getUserImage(final SprayUser user, final getUserImageCllback callback) {
        class AsynchGet extends AsyncTask<String,String,String> {
            Bitmap image;
            @Override
            protected String doInBackground(String... strings) {
                image = model.getUserBitmap(user.getUserID());
                return null;
            }

            @Override
            protected void onPostExecute(String arg){
                callback.done(image);
            }
        }
        AsynchGet async = new AsynchGet();
        async.execute("");
    }

    public interface ModelInterface
    {
        public  void LoginToSpray(String userName, String password);
        public  void LogOutFromSpray();
        public void SignUpToSpray(String userName, String password,String email,Bitmap userImage);
        public boolean IsParseUserConnect();
        public int UserCounter();
        public void setSignActivity(SignUpActivity signActivity);
        public void setLoginActivity(LoginActivity loginActivity);
        public  void setMainTabActivity(MainTabActivity mainTabActivity);
        public void updateRelationsInServer(Integer id);
        public void deleteRelations();
        public void getCloseUsers();
        public void sendImageToUsers(ArrayList<String> usersTosendTo,Bitmap bitmap);
        public void setCurrentMediaFragment(CurrentMediaFragment currentMediaFragment);
        public void getCurrentFiles();
        //gps
        public void updateLocation(double latitude,double longitude);
        public void getCloseUsersByGPS();
        //Wifi
        public void updateWifi(String bssid);
        public void getCloseUsersConnectedToSameWifi(String bssid);
        //bluetooth
        public void updateBluetoothMACAddress(String macAddress);
        public void getCloseUserByBluetooth(String macAddress);

        void getBTUserDetails(String macAddress, GetSprayUserCallback callback);

        void addCloseUser(SprayUser user);

        SprayUser getCurrentUser();
        public Bitmap getUserBitmap(String userId);
    }

    public  void LoginToSpray(String userName, String password){
        model.LoginToSpray(userName, password);
    }

    public  void LogOutFromSpray(){
        model.LogOutFromSpray();
    }

    public void SignUpToSpray(String userName, String password,String email,Bitmap userImage){
        model.SignUpToSpray(userName,password,email,userImage);
    }

    public void setSprayFragment(SprayFragment fragment)
    {
    }
    public void setCurrentMediaFragment(CurrentMediaFragment currentMediaFragment)
    {
        model.setCurrentMediaFragment(currentMediaFragment);
    }

    public boolean IsParseUserConnect(){
        return  model.IsParseUserConnect();
    }
    public int UserCounter(){ return  model.UserCounter();}
    public void setSignInActivity(SignUpActivity activity){model.setSignActivity(activity);}
    public void setLoginActivity(LoginActivity activity){model.setLoginActivity(activity);}
    public  void setMainTabActivity(MainTabActivity mainTabActivity){model.setMainTabActivity(mainTabActivity);}
    public  void deleteRelations(){
        model.deleteRelations();}
    public void updateRelationsInServer(Integer id)
    {
        model.updateRelationsInServer(id);
    }
    public void getCloseUsers(){model.getCloseUsers();}
    public void sendImageToUsers(ArrayList<String> usersTosendTo,Bitmap bitmap)
    {
        model.sendImageToUsers(usersTosendTo,bitmap);
    }
    public void getCurrentFiles()
    {
        model.getCurrentFiles();
    }
    //gps
    public void updateLocation(double latitude,double longitude)
    {
        model.updateLocation(latitude,longitude);
    }
    public void getCloseUsersByGPS()
    {
        model.getCloseUsersByGPS();
    }
    //wifi
    public void updateWifi(String bssid)
    {
        model.updateWifi(bssid);
    }
    public void getCloseUsersConectedToSameWifi(String bssid)
    {
        model.getCloseUsersConnectedToSameWifi(bssid);
    }
    public void updateBluetoothMACAddress(String macAddress)
    {
        model.updateBluetoothMACAddress(macAddress);
    }
    public void getCloseUsersByBluetooth(String macAddress)
    {
        model.getCloseUserByBluetooth(macAddress);
    }
}
