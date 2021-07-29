package com.example.playerdetails.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseViewModel extends ViewModel {

    public @NonNull
    <M> LiveData<M> liveData(Observable<M> observable) {
        final MutableLiveData<M> mutableLiveData = new MutableLiveData<>();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<M>() {
                    @Override
                    public void accept(M m) throws Exception {
                        mutableLiveData.setValue(m);
                    }
                });
        mutableLiveData.getValue();
        return mutableLiveData;
    }
}
