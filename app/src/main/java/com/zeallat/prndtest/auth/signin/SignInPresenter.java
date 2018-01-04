package com.zeallat.prndtest.auth.signin;


import com.blankj.utilcode.util.ActivityUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zeallat.prndtest.auth.signup.SignUpActivity;
import com.zeallat.prndtest.data.model.User;
import com.zeallat.prndtest.data.pref.PreferencesRepositoryImpl;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.UserRepository;

import static android.text.TextUtils.isEmpty;

public class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View mView;
    private UserRepository mUserRepository = new UserRepository();

    public SignInPresenter(SignInContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        getUserInfo();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClickButtonSignIn() {
        String email = mView.getEmail();
        String password = mView.getPassword();
        signIn(email, password);
    }

    @Override
    public void onClickButtonSignUp() {
        ActivityUtils.startActivity(SignUpActivity.class);
    }

    private void signIn(String email, String password) {
        if (isEmpty(email)) {
            mView.showAlertDialog("이메일을 입력해주세요.");
            return;
        }
        if (isEmpty(password)) {
            mView.showAlertDialog("비밀번호를 입력해주세요.");
            return;
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mView.showAlertDialog("로그인되었습니다.");
                        getUserInfo();
                    } else {
                        mView.showAlertDialog("로그인에 실패했습니다.");
                    }

                });
    }

    private void getUserInfo() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) return;
        mView.showProgressDialog("자동 로그인중");
        mUserRepository.getData(firebaseUser.getUid(), new BaseDataSource.GetDataCallback<User>() {
            @Override
            public void onDataLoaded(User data) {
                mView.dismissProgressDialog();
                PreferencesRepositoryImpl.getInstance().setUser(data);
            }

            @Override
            public void onDataNotAvailable() {
                mView.dismissProgressDialog();
            }
        });
    }
}

