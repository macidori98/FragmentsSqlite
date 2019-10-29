package com.example.fragmentssqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentssqlite.Database.DatabaseHelper;
import com.example.fragmentssqlite.Database.Model.Hobby;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_addHobby, btn_viewHobbies;
    private DatabaseHelper db;
    private EditText et_hobby;
    private TextView tv_hobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);

        db = new DatabaseHelper(this);
        btn_addHobby = findViewById(R.id.btn_addHobby);
        btn_viewHobbies = findViewById(R.id.btn_viewHobby);
        et_hobby = findViewById(R.id.et_newHobby);
        tv_hobby = findViewById(R.id.tv_addHobby);

        btn_addHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Hobby> hobbies = new ArrayList<>();
                hobbies = db.getAllHobbies();
                boolean was = false;

                for (int i = 0; i < hobbies.size(); ++i){
                    if (hobbies.get(i).getHobby().equals(et_hobby.getText().toString())) {
                        was = true;
                        Toast.makeText(getApplicationContext(), R.string.fail, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if (!was && !et_hobby.getText().toString().equals("")){
                    db.insertHobby(et_hobby.getText().toString());
                    Toast.makeText(getApplicationContext(), R.string.added, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.enter_text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_viewHobbies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_hobby.setVisibility(View.INVISIBLE);
                et_hobby.setVisibility(View.INVISIBLE);
                btn_addHobby.setVisibility(View.INVISIBLE);
                btn_viewHobbies.setVisibility(View.INVISIBLE);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.my_frame_layout, new ListFragment(), ListFragment.TAG);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        tv_hobby.setVisibility(View.VISIBLE);
        et_hobby.setVisibility(View.VISIBLE);
        btn_viewHobbies.setVisibility(View.VISIBLE);
        btn_addHobby.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }
}
