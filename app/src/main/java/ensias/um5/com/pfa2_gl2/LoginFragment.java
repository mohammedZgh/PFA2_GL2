package ensias.um5.com.pfa2_gl2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
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

import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    EditText userName;
    EditText password;
    Button loginButton;
    Button registerButton;
    Button forgotPassword;
    ProgressDialog loginProgress;
    private FirebaseAuth mAuth;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login,container,false);
        userName =(EditText) view.findViewById(R.id.input_email2);
        password =(EditText) view.findViewById(R.id.input_password1);
        loginButton = (Button) view.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClicked();
            }
        });
        return view;
    }

    private void onLoginButtonClicked() {
        if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
            Snackbar.make(loginButton, "YOU SHOULD FILL LOGIN AND PASSWORD", Snackbar.LENGTH_SHORT).show();
            return;
        }
        loginProgress = ProgressDialog.show(getActivity(), "", "logging In...");

        if (!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
            User user = new User(userName.getText().toString(),password.getText().toString());
        }

            }

    private void onLoginSuccess() {
    }
}
