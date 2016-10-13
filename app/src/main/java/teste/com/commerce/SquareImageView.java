package teste.com.commerce;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class SquareImageView extends ImageView
{
    public SquareImageView(Context context) {
        super(context);
  //      initViews();
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initViews();
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        initViews();
    }
    

//    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//ZLog.t("wid="+widthMeasureSpec+" HEI="+heightMeasureSpec);        
		setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
       //	 setMeasuredDimension(300, 300); //Snap to width
    }
    
}
