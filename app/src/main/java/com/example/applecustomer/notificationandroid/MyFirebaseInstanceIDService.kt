package com.example.applecustomer.notificationandroid

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {


    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        sendRegistrationToServer(refreshedToken)
    }

    private fun sendRegistrationToServer(token: String?) {
        // Add custom implementation, as needed.
    }
}


//For sending http req
//url : https://fcm.googleapis.com/fcm/send
/*
body :
"data": {
    "name": "himanshu",
    "address": "abc",
    "id":"123"
},

//firebase id token
"to" : "ezpg8K9h7jk:APA91bEF8YOVetKAi5CRtCiYaT3WkYjJ8oSulb189AdfdbI7Lxi9stIUYvLFM__MyXSAt0GJtp_OgmoHuP1bQytdKOBXNE4EeaVrXOo8Psmms73Du4zdtVZm6Tsw8vltA2jkF8wAPMIz"
}

Header

Authorization : key=server-key
content-type : app/json
*/
