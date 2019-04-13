package ensias.um5.com.pfa2_gl2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class SignupFragment extends Fragment {
    EditText userName;
    EditText Login;
    EditText password;
    Button SignButton;
    Button registerButton;
    Button forgotPassword;
    ProgressDialog loginProgress;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup,container,false);
        mAuth = FirebaseAuth.getInstance();
        userName = (EditText) view.findViewById(R.id.input_name);
        Login =(EditText) view.findViewById(R.id.input_email);
        password =(EditText) view.findViewById(R.id.input_password);
        SignButton = (Button) view.findViewById(R.id.btn_signup);
        SignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClicked();
            }
        });
        return view;
    }
    private void onLoginButtonClicked() {
        if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(Login.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
            Snackbar.make(SignButton, "YOU FORGOT NAME or LOGIN or PASSWORD", Snackbar.LENGTH_SHORT).show();
            return;
        }
        loginProgress = ProgressDialog.show(getActivity(), "", "Creation In...");
        if (!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(Login.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
            String email = Login.getText().toString();
            String password = this.password.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Snackbar.make(SignButton, "CRéé", Snackbar.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Snackbar.make(SignButton, "FAILED", Snackbar.LENGTH_SHORT).show();
                            } else {

                                Snackbar.make(SignButton, "FAILEDddd", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });

        }



    }

    }




