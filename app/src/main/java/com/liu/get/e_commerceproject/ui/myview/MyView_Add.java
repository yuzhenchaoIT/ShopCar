package com.liu.get.e_commerceproject.ui.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liu.get.e_commerceproject.R;



public class MyView_Add extends LinearLayout {
    private Button my_add;
    private TextView my_nums;
    private Button my_sub;
    int nums=1;

    public void setNums(int numb){
        nums = numb;
        my_nums.setText(""+nums);
    }

    public int getNums(){
        return nums;
    }

    public MyView_Add(Context context) {
        this(context, null);
    }

    public MyView_Add(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView_Add(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = inflate(context, R.layout.my_view_, this);

        my_add = view.findViewById(R.id.my_add);
        my_nums = view.findViewById(R.id.my_nums);
        my_sub = view.findViewById(R.id.my_sub);

        my_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nums++;
                my_nums.setText(""+nums);
                mNumChanger.getNumS_(nums);
            }
        });
        my_sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nums>1){
                    nums--;
                    my_nums.setText(""+nums);
                    mNumChanger.getNumS_(nums);
                }
            }
        });
    }

    public void setNumChanger(NumChanger numChanger) {
        mNumChanger = numChanger;
    }

    NumChanger mNumChanger;
    public interface NumChanger{
        void getNumS_(int num);
    }
}
