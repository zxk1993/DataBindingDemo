package zxk.demo.databindingdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by zxk on 2018/8/3.
 * Description:
 */
public class NumberEditView extends View implements View.OnKeyListener {

    //输入法管理
    private InputMethodManager input;
    private static final int MAX_LENGTH = 10;
    private String num = "";
    private Paint mPaint;
    private boolean isKeyboardShow = false;
    private OnNumChangeListener onNumChangeListener;

    public NumberEditView(Context context) {
        this(context, null);
    }

    public NumberEditView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        input = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        this.setOnKeyListener(this);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int min = 300;

        if (widthSpecMode == MeasureSpec.AT_MOST) {
            widthSpecSize = Math.min(widthSpecSize, min);
        }

        if (heightSpecMode == MeasureSpec.AT_MOST) {
            heightSpecSize = Math.min(heightSpecSize, min);
        }

        widthSpecSize = widthSpecSize + getPaddingLeft() + getPaddingRight();
        heightSpecSize = heightSpecSize + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        if (!num.equals("")) {
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int y = (int) (getHeight() / 2 - fontMetrics.top / 2 - fontMetrics.bottom / 2);
            canvas.drawText(num, getPaddingLeft(), y, mPaint);
        }

        //第一次画
        if (!isKeyboardShow) {
            showKeyBoard();
        }
    }

    interface OnNumChangeListener {
        void onNumChange();
    }

    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener) {
        this.onNumChangeListener = onNumChangeListener;
    }

    public String getNum() {
        return num == null ? "" : num;
    }

    public void setNum(String num) {
        this.num = num;

        if (onNumChangeListener != null) {
            onNumChangeListener.onNumChange();
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {//点击弹出键盘
            showKeyBoard();
            return true;
        }
        return super.onTouchEvent(event);
    }

    //防止切到桌面键盘还在
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus) {
            hideKeyBoard();
        }
    }

    //显示键盘
    public void showKeyBoard() {
        requestFocus();
        if (input != null) {
            isKeyboardShow = true;
            input.showSoftInput(this, InputMethodManager.SHOW_FORCED);
        }
    }

    //隐藏键盘
    public void hideKeyBoard() {
        if (input != null) {
            isKeyboardShow = false;
            input.hideSoftInputFromWindow(this.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;
        return new NumberInputConnection(this, false);
    }

    class NumberInputConnection extends BaseInputConnection {

        public NumberInputConnection(View targetView, boolean fullEditor) {
            super(targetView, fullEditor);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            return super.commitText(text, newCursorPosition);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if (beforeLength == 1 && afterLength == 0) {
                return super.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && super.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }
            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.isShiftPressed()) {//处理*#等键
                return false;
            }
            if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {//只处理数字
                if (num.length() < MAX_LENGTH) {
                    num += keyCode - 7;
                    setNum(num);
                }
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (num.length() > 0) {//不为空时，删除最后一个数字
                    num = num.substring(0, num.length() - 1);
                    setNum(num);
                }
                return true;
            }
        }
        return false;
    }
}
