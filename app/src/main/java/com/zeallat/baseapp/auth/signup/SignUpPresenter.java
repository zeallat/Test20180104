package com.zeallat.baseapp.auth.signup;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.zeallat.baseapp.auth.signin.SignInActivity;
import com.zeallat.baseapp.data.model.User;
import com.zeallat.baseapp.data.pref.PreferencesRepositoryImpl;
import com.zeallat.baseapp.data.source.BaseDataSource;
import com.zeallat.baseapp.data.source.UserRepository;

import static android.text.TextUtils.isEmpty;

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.View mView;
    private UserRepository mUserRepository = new UserRepository();

    public SignUpPresenter(SignUpContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onCreate() {

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
    public void onClickButtonSignUp() {
        String email = mView.getEmail();
        String password = mView.getPassword();
        String name = mView.getName();

        if (isEmpty(email)) {
            mView.showAlertDialog("이메일을 입력해주세요.");
            return;
        }
        if (isEmpty(password)) {
            mView.showAlertDialog("비밀번호를 입력해주세요.");
            return;
        }
        if (isEmpty(name)) {
            mView.showAlertDialog("이름을 입력해주세요.");
            return;
        }

        signUp(email, password, name);
    }

    private void signUp(String email, String password, String name) {
        mView.showProgressDialog("회원가입중");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Task<AuthResult> task) -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        User user = new User();
                        user.setName(name);
                        mUserRepository.create(firebaseUser.getUid(), user, new BaseDataSource.GetDataCallback<User>() {
                            @Override
                            public void onDataLoaded(User data) {
                                mView.dismissProgressDialog();
                                PreferencesRepositoryImpl.getInstance().setUser(data);
                                mView.showAlertDialog("회원가입이 완료되었습니다.", () -> {
                                    ActivityUtils.startActivity(SignInActivity.class);
                                    ActivityUtils.finishAllActivities();
                                    return null;
                                });
                            }

                            @Override
                            public void onDataNotAvailable() {
                                mView.dismissProgressDialog();
                            }
                        });
                    } else {
                        mView.dismissProgressDialog();
                        /**
                         * Exceptions:
                         * FirebaseAuthWeakPasswordException thrown if the password is not strong enough
                         * FirebaseAuthInvalidCredentialsException thrown if the email address is malformed
                         * FirebaseAuthUserCollisionException thrown if there already exists an account with the given email address
                         */
                        Exception exception = task.getException();
                        if (exception != null) {
                            if (exception instanceof FirebaseAuthWeakPasswordException) {
                                mView.showAlertDialog("비밀번호가 취약합니다. 조금 더 어려운 비밀번호를 입력해주세요.");
                            } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                mView.showAlertDialog("유효한 이메일을 입력해주세요.");
                            } else if (exception instanceof FirebaseAuthUserCollisionException) {
                                mView.showAlertDialog("이미 회원가입되어있는 이메일입니다.");
                            }
                        }
                    }
                });
    }
}
