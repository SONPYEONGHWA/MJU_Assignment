package com.example.workdiary;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.lifecycle.MutableLiveData;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("android:text")
    public static void setText(TextView textview, MutableLiveData<String> text) {
        if (text == null) {
            textview.setText("");
        } else {
            textview.setText(text.getValue());
        }
    }
    @InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
    public static String getText(EditText editText) {
        return editText.getText().toString();
    }

    @androidx.databinding.BindingAdapter("textAttrChanged")
    public static void setTextWatcher(EditText editText, final InverseBindingListener textAttrChanged) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (textAttrChanged != null) {
                    textAttrChanged.onChange();

                }
            }
        });
    }
}
