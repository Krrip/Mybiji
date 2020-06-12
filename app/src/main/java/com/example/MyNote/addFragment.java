package com.example.MyNote;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class addFragment extends Fragment {
private Button buttonSubmit;
private EditText editTextEnglish;
private WordViewModel wordViewModel;

    public addFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonSubmit = requireActivity().findViewById(R.id.buttonSubmit);
        editTextEnglish = requireActivity().findViewById(R.id.editTextEnglish);
        wordViewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        buttonSubmit.setEnabled(false);
        editTextEnglish.requestFocus();
        InputMethodManager imm =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);//自动弹出键盘
        imm.showSoftInput(editTextEnglish,0);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
String english = editTextEnglish.getText().toString().trim();
buttonSubmit.setEnabled(!english.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editTextEnglish.addTextChangedListener(textWatcher);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String english = editTextEnglish.getText().toString().trim();
                Word word = new Word(english,null);
                wordViewModel.insertWords(word);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
                InputMethodManager imm =(InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);//自动弹出键盘
imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });
    }
}
