package com.closerbuy.vendor.presentation.utils

import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Created by Vijay on 18/4/20.
 */

//thread safe singleton class
object RxBus {

    val publisherURI = PublishSubject.create<Map<String, String>>()
    val publisherHorDataPos = PublishSubject.create<String>()
    val publisherError = PublishSubject.create<Boolean>()
    val publisherListWorkDetails = BehaviorSubject.create<ArrayList<String>>()

    fun publishUri(event: Map<String, String>) {
        publisherURI.onNext(event)
    }

    fun listenUri(): PublishSubject<Map<String, String>> {
        return publisherURI
    }

    fun publishAdapterPos(event: String) {
        publisherHorDataPos.onNext(event)
    }

    fun listenAdapterPos(): PublishSubject<String> {
        return publisherHorDataPos
    }

    fun publishWorkDetails(event: ArrayList<String>) {
        publisherListWorkDetails.onNext(event)
    }

    fun listenWorkDetails(): BehaviorSubject<ArrayList<String>> {
        return publisherListWorkDetails
    }

    fun publishError(event: Boolean) {
        publisherError.onNext(event)
    }

    fun listenError(): PublishSubject<Boolean> {
        return publisherError
    }

}