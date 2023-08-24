package com.example.project_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ArrayAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private Frag5 frag5;

    private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<CharSequence> adapterSpinner2;
    private Switch switchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);  // Initialize the bottomNavigationView first

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_shopping) {
                    setFrag(0);
                } else if (itemId == R.id.action_community) {
                    setFrag(1);
                } else if (itemId == R.id.action_home) {
                    setFrag(2);
                } else if (itemId == R.id.action_sell) {
                    setFrag(3);
                } else if (itemId == R.id.action_hire) {
                    setFrag(4);
                }
                return true;
            }
        });


        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        frag5 = new Frag5();
        setFrag(2); //what fragment is going to be your first fragment
        bottomNavigationView.setSelectedItemId(R.id.action_home);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        switchButton = findViewById(R.id.switchButton);

        setupSpinner(spinner1, R.array.major);
        setupSpinner(spinner2, R.array.default_minor);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int arrayId;
                switch (position) {
                    case 1: arrayId = R.array.minor_for_major1; break;
                    case 2: arrayId = R.array.minor_for_major2; break;
                    case 3: arrayId = R.array.minor_for_major3; break;
                    default: arrayId = R.array.default_minor; break;
                }
                setupSpinner(spinner2, arrayId);
                updateSpinner3BasedOnSelections();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateSpinner3BasedOnSelections();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle switch logic here
        });

    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame, frag5);
                ft.commit();
                break;
        }
    }

    private void setupSpinner(Spinner spinner, int arrayResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void updateSpinner3BasedOnSelections() {
        String selectedMajor = spinner1.getSelectedItem().toString();
        String selectedMinor = spinner2.getSelectedItem().toString();
        int arrayId;

        switch (selectedMajor) {
            case "Major1":
                switch (selectedMinor) {
                    case "Minor1A":
                        arrayId = R.array.franchise_for_major1_minor1A;
                        break;
                    case "Minor1B":
                        arrayId = R.array.franchise_for_major1_minor1B;
                        break;
                    case "Minor1C":
                        arrayId = R.array.franchise_for_major1_minor1C;
                        break;
                    default:
                        arrayId = R.array.default_franchise_array;
                        break;
                }
                break;
            case "Major2":
                switch (selectedMinor) {
                    case "Minor2A":
                        arrayId = R.array.franchise_for_major2_minor2A;
                        break;
                    case "Minor2B":
                        arrayId = R.array.franchise_for_major2_minor2B;
                        break;
                    case "Minor2C":
                        arrayId = R.array.franchise_for_major2_minor2C;
                        break;
                    default:
                        arrayId = R.array.default_franchise_array;
                        break;
                }
                break;
            case "Major3":
                switch (selectedMinor) {
                    case "Minor3A":
                        arrayId = R.array.franchise_for_major3_minor3A;
                        break;
                    case "Minor3B":
                        arrayId = R.array.franchise_for_major3_minor3B;
                        break;
                    case "Minor3C":
                        arrayId = R.array.franchise_for_major3_minor3C;
                        break;
                    default:
                        arrayId = R.array.default_franchise_array;
                        break;
                }
                break;
            default:
                arrayId = R.array.default_franchise_array;
                break;
        }

        setupSpinner(spinner3, arrayId);
    }

}
