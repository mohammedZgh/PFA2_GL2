package ensias.um5.com.pfa2_gl2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment implements View.OnClickListener {
    EditText userName;
    EditText Login;
    EditText password;
    Button SignButton;
    Button registerButton;
    Button forgotPassword;
    ProgressDialog loginProgress;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ProgressBar progressBar;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        userName = (EditText) view.findViewById(R.id.input_name);
        Login = (EditText) view.findViewById(R.id.input_email);
        password = (EditText) view.findViewById(R.id.input_password);
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
        String email = Login.getText().toString();
        String password = this.password.getText().toString();
        if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(Login.getText().toString()) || TextUtils.isEmpty(this.password.getText().toString())) {
            Snackbar.make(SignButton, "YOU FORGOT NAME or LOGIN or PASSWORD", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(Login.getText().toString()) && !TextUtils.isEmpty(this.password.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Snackbar.make(SignButton, "CREATED", Snackbar.LENGTH_SHORT).show();


                    } else {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                task.getException().getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                    }

                }
            });


        }
    }

    @Override
    public void onClick(View v) {

    }
}