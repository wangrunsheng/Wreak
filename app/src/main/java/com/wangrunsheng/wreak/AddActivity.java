package com.wangrunsheng.wreak;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by Russell on 2018/3/18.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Button mAddCancelBtn;
    private Button mAddCompleteBtn;
    private ImageView mCoverImageView;
    private EditText mInputBookName;
    private EditText mInputTotalPages;
    private EditText mInputEverydayPages;
    private TextView mCompleteTimeTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        mAddCancelBtn = (Button) findViewById(R.id.add_cancel_btn);
        mAddCancelBtn.setOnClickListener(this);
        mAddCompleteBtn = (Button) findViewById(R.id.add_complete_btn);
        mAddCompleteBtn.setOnClickListener(this);
        mCoverImageView = (ImageView) findViewById(R.id.add_book_cover_iv);
        mCoverImageView.setOnClickListener(this);
        mInputBookName = (EditText) findViewById(R.id.input_book_name);
        mInputTotalPages = (EditText) findViewById(R.id.input_total_pages);
        mInputEverydayPages = (EditText) findViewById(R.id.input_everyday_pages);
        mCompleteTimeTv = (TextView) findViewById(R.id.complete_time_tv);
        mCompleteTimeTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_cancel_btn:
                finish();
                break;
            case R.id.add_complete_btn:
                finish();
                break;
            case R.id.add_book_cover_iv:
                showFindCoverActivity();
                break;
            case R.id.complete_time_tv:
                showMaterialDateTimePicker();
                break;
        }
    }

    private void showMaterialDateTimePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                AddActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMinDate(now);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        mCompleteTimeTv.setText(date);
    }

    private void showFindCoverActivity() {
        Intent intent = new Intent(this, FindCoverActivity.class);
        startActivity(intent);
    }
}
