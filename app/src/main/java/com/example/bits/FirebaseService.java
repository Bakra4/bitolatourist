package com.example.bits;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseService {
    public static FirebaseAuth getFirebaseInstance() {
        return FirebaseAuth.getInstance();
    }
}
