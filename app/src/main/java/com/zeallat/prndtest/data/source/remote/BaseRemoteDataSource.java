package com.zeallat.prndtest.data.source.remote;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.zeallat.prndtest.data.model.BaseModel;
import com.zeallat.prndtest.data.source.BaseDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public abstract class BaseRemoteDataSource<T extends BaseModel> implements BaseDataSource<T> {

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference(getPath());
    private List<Pair<Query, ValueEventListener>> mQueryValueEventListenerList = new ArrayList<>();
    private final Class<T> mTypeParameterClass;

    public BaseRemoteDataSource(Class<T> typeParameterClass) {
        mTypeParameterClass = typeParameterClass;
    }

    @Override
    abstract public String getPath();

    @Override
    public void getList(@NonNull String groupId, boolean isRequestRealTime, @NonNull GetDataListCallback<T> callback) {
        Query query = isEmpty(groupId) ? mDatabaseReference : mDatabaseReference.child(groupId);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<T> datas = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    T data = snapshot.getValue(mTypeParameterClass);
                    snapshot.getValue();
                    if (data != null) data.setId(snapshot.getKey());
                    datas.add(data);
                }
                callback.onDataListLoaded(datas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable();
            }
        };

        addEventListener(query, valueEventListener, isRequestRealTime);
    }

    @Override
    public void getList(boolean isRequestRealTime, @NonNull GetDataListCallback<T> callback) {
        getList("", isRequestRealTime, callback);
    }

    @Override
    public void getList(@NonNull GetDataListCallback<T> callback) {
        getList("", false, callback);
    }

    @Override
    public void getData(@NonNull String id, boolean isRequestRealTime, @NonNull GetDataCallback<T> callback) {
        Query query = mDatabaseReference.child(id);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                T data = dataSnapshot.getValue(mTypeParameterClass);
                if (data != null) data.setId(dataSnapshot.getKey());
                callback.onDataLoaded(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable();
            }
        };

        addEventListener(query, valueEventListener, isRequestRealTime);
    }

    @Override
    public void getData(@NonNull String id, @NonNull GetDataCallback<T> callback) {
        getData(id, false, callback);
    }

    @Override
    public void create(@NonNull T data, @NonNull GetDataCallback<T> callback) {
        String id = mDatabaseReference.push().getKey();
        create(id, data, callback);
    }

    @Override
    public void create(@NonNull String id, @NonNull T data, @NonNull GetDataCallback<T> callback) {
        mDatabaseReference.child(id).setValue(data).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                getData(id, callback);
            } else {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void update(@NonNull String id, @NonNull Map<String, Object> updates, @NonNull GetDataCallback<T> callback) {
        mDatabaseReference.child(id).updateChildren(updates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                getData(id, callback);
            } else {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void delete(@NonNull String id, @NonNull ResponseCallback callback) {
        mDatabaseReference.child(id).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) callback.onComplete();
            else callback.onFailure();
        });
    }

    private void addEventListener(Query query, ValueEventListener valueEventListener, boolean isRequestRealTime) {
        if (isRequestRealTime) {
            query.addValueEventListener(valueEventListener);
            mQueryValueEventListenerList.add(Pair.create(query, valueEventListener));
        } else {
            query.addListenerForSingleValueEvent(valueEventListener);
        }
    }

    @Override
    public void removeEventListeners() {
        for (Pair<Query, ValueEventListener> queryValueEventListenerPair : mQueryValueEventListenerList) {
            if (queryValueEventListenerPair.first != null && queryValueEventListenerPair.second != null) {
                queryValueEventListenerPair.first.removeEventListener(queryValueEventListenerPair.second);
            }
        }
        mQueryValueEventListenerList.clear();
    }
}
