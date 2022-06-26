package com.example.androiddevkotlin.advanced.coroutine.flow.overview

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class FireStoreUserEventDataSrc(
    private val firestore: FirebaseFirestore
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUserEvents(): Flow<UserEvent> = callbackFlow {
        var documentReference: DocumentReference? = null
        try {
            // init

        } catch (e: Throwable) {
            // flow consumers will stop collecting and the coroutine will resume
            close(e)
        }

        val subscription = documentReference?.addSnapshotListener { snapshot, _ ->

            // Sends events to the flow! Consumers will get the new events
            try {
                trySend(snapshot!!.getEvent())
            } catch (e: Throwable) {

            }
        }

        // The callback inside awaitClose will be executed when the flow is
        // either closed or cancelled.
        awaitClose {
            // clear resource
        }
    }
}


fun DocumentSnapshot.getEvent(): UserEvent {
    return UserEvent()
}

class UserEvent {

}
