package com.example.ssendhil.ease_e;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

class Model extends Observable {
    // Create static instance of this mModel
    private static final Model ourInstance = new Model();

    static Model getInstance() {
        return ourInstance;
    }

    // Private Variables
    private String phoneNo;
    private String message;
    private static Context context;

    // Model constructor
    Model() {
        phoneNo = "some-num";
        message = "http://khalsami.dev.fast.sheridanc.on.ca/ease-e_Sample.txt";
    }

    /**
     * Sends sms message to default phoneNo
     */
    public void sendSMSMessage() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(context.getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.d("Message", "Some error");
        }
        setChanged();
        notifyObservers();
    }

    // Getter and Setter methods for context, phoneNo and message
    public void setContext(Context context) {
        this.context = context;
        setChanged();
        notifyObservers();
    }

    public Context getContext() {
        return context;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        setChanged();
        notifyObservers();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        setChanged();
        notifyObservers();
    }

    // OBSERVER PATTERN METHODS
    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}

