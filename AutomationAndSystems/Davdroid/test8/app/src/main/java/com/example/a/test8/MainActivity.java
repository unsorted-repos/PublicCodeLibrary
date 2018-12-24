package com.example.a.test8;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.NetworkErrorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccountList();
                removeAccounts(); //This should be sufficient to remove the accounts, but it does not do so.
                writeToFile("hi");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    public void writeToFile(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    // Global Variables
    public String AUTHORITY = "com.example.test8";
    public String ACCOUNT_TYPE = "at.bitfire.davdroid.address_book";
    public String ACCOUNT = "Address Book (P2:Own:Alg. Data Struc ng)";

    public void removeAccounts(){
        // Account Manager definition
        AccountManager accountManager = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);

        // loop through all accounts to remove them
        Account[] accounts = accountManager.getAccounts();
        int Temp = accounts.length;
        writeToFile(String.valueOf(Temp));
        //for (int index = 0; index < accounts.length; index++) {
        for (int index = 0; index < Temp; index++) {
            writeToFile(accounts[index].name);
            if (accounts[index].type.intern() == AUTHORITY) {
                //accountManager.removeAccount(accounts[index], null, null);
                accountManager.removeAccountExplicitly(accounts[index]);
            }
        }
    }

    //Source:https://stackoverflow.com/questions/22174259/pick-an-email-using-accountpicker-newchooseaccountintent
    public void getAccountList(){
        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.GET_ACCOUNTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.GET_ACCOUNTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
        String possibleEmail="";
        possibleEmail += "************* Get Registered Gmail Account *************\n\n";
        Account[] accounts = AccountManager.get(this).getAccountsByType("bitfire.at.davdroid");

        //Account systemAccount = new Account(mainAccount.getDisplayName(),                getResources().getString(R.string.account_type));


        for (Account account : accounts) {
            writeToFile("Call removal method with: "+account.name+" and type = "+account.type);
            AccountManager.get(this).removeAccount(account,null, null, null);
            deleteAccount(account.name,account.type);
            writeToFile(account.name);
            possibleEmail += " --> "+account.name+" : "+account.type+" , \n";
            possibleEmail += " \n\n";

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //New attempts
    //Source: http://www.javased.com/index.php?api=android.accounts.AccountManager
    /**
     * delete an account
     * @param name : account name
     * @param type : account type
     */
//    public void deleteAccount(String name,String type){
//        //AccountManager am=(AccountManager)mContext.getSystemService(Context.ACCOUNT_SERVICE);
//        AccountManager am = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);
//        Account mAccount=new Account(name,type);
//
//        //AccountManagerCallback<Bundle> bundleAccountManagerCallback = ;
//        //AccountManagerFuture<Bundle> accountManagerFuture = am.removeAccount(mAccount, null, AccountManagerCallback, null);
//         //am.removeAccount(mAccount, null, AccountManagerCallback, null);
//        //final AccountManagerFuture<Bundle> bundleAccountManagerFuture = am.removeAccount(mAccount, null, null, null);
//        am.removeAccount(mAccount, null, null, null);
//
//        //        am.removeAccount(mAccount,new AccountManagerCallback<Boolean>(){
////                    public void run(    AccountManagerFuture<Boolean> future){
////                        boolean failed=true;
////                        try {
////                            if (future.getResult() == true) {
////                                failed=false;
////                            }
////                        }
////                        catch (      OperationCanceledException e) {
////                        }
////                        catch (      IOException e) {
////                        }
////                        catch (      AuthenticatorException e) {
////                        }
////                    }
////                }
////                ,null);
//    }


    //Attempt 2:
    /**
     * delete an account
     * @param name : account name
     * @param type : account type
     */
    public void deleteAccount(String name,String type){
        //am =AccountManager.get(Context.ACCOUNT_SERVICE);
        AccountManager am = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);
        Account mAccount=new Account(name,type);
        am.removeAccount(mAccount,new AccountManagerCallback<Boolean>(){
                    public void run(    AccountManagerFuture<Boolean> future){
                        boolean failed=true;
                        try {
                            if (future.getResult() == true) {
                                failed=false;
                            }
                        }
                        catch (      OperationCanceledException e) {
                        }
                        catch (      IOException e) {
                        }
                        catch (      AuthenticatorException e) {
                        }
                    }
                }
                ,null);
    }

//    @Override
//    public Bundle getAccountRemovalAllowed(
//            AccountAuthenticatorResponse response, Account account)
//            throws NetworkErrorException {
//        final Bundle result = new Bundle();
//
//        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, true);
//
//        return result;
//    }
}
