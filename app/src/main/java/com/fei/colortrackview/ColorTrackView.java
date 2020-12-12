package com.fei.colortrackview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @ClassName: ColorTrackView
 * @Description: 滚动颜色文本
 * @Author: Fei
 * @CreateDate: 2020-12-11 21:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-12-11 21:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ColorTrackView extends AppCompatTextView {

    private Paint mOriginalPaint;//底色画笔
    private Paint mChangePaint;//滚动颜色画笔
    private Rect mBounds;//文本区域
    private Direction mCurrentDirection = Direction.LEFT_TO_RIGHT;//当前方向
    private float mCurrentProgress = 0f;//当前进度

    //方向枚举
    public enum Direction {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public ColorTrackView(Context context) {
        this(context, null);
    }

    public ColorTrackView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttr(context, attrs);
        initBounds();
    }

    private void initBounds() {
        mBounds = new Rect();
    }

    /**
     * 获取属性
     */
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackView);
        int originalColor = typedArray.getColor(R.styleable.ColorTrackView_originalColor, getTextColors().getDefaultColor());
        int changeColor = typedArray.getColor(R.styleable.ColorTrackView_changeColor, getTextColors().getDefaultColor());
        typedArray.recycle();

        initPaint(originalColor, changeColor);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        this.mOriginalPaint.setTextSize(getTextSize());
        this.mChangePaint.setTextSize(getTextSize());
    }

    /**
     * 初始化画笔
     */
    private void initPaint(int originalColor, int changeColor) {
        mOriginalPaint = getPaint(originalColor);
        mChangePaint = getPaint(changeColor);
    }

    /**
     * 获取画笔
     */
    private Paint getPaint(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);//防抖动
        paint.setTextSize(getTextSize());
        paint.setColor(color);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //需要改变颜色中介线
        int middle = (int) (mCurrentProgress * getWidth());
        //画原文本
        drawText(canvas, 0, getWidth(), mOriginalPaint);
        if (middle == 0) return;
        //从左至右
        if (mCurrentDirection == Direction.LEFT_TO_RIGHT) {
            //画需要改变颜色的文本
            drawText(canvas, 0, middle, mChangePaint);
        } else {
            //画需要改变颜色的文本
            drawText(canvas, getWidth() - middle, getWidth(), mChangePaint);
        }

    }

    /**
     * 画文本
     */
    private void drawText(Canvas canvas, int start, int end, Paint paint) {
        String text = getText().toString();
        canvas.save();
        canvas.clipRect(start, 0, end, getHeight());
        paint.getTextBounds(text, 0, text.length(), mBounds);
        int x = getWidth() / 2 - mBounds.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int y = getHeight() / 2 + dy;
        canvas.drawText(text, x, y, paint);
        canvas.restore();
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.mCurrentDirection = currentDirection;
    }

    public void setCurrentProgress(float currentprogress) {
        this.mCurrentProgress = currentprogress;
        invalidate();
    }

    public void setOriginaColor(@ColorInt int color) {
        this.mOriginalPaint.setColor(color);
    }

    public void setChangeColor(@ColorInt int color) {
        this.mChangePaint.setColor(color);
    }
}
