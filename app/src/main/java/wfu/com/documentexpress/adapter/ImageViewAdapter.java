package wfu.com.documentexpress.adapter;

/**
 * Created by Lenovo on 2016/4/20.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import wfu.com.documentexpress.R;

public class ImageViewAdapter extends BaseAdapter {
    private List<Bitmap> image;
    private boolean isChice[];
    private Context context;

    public ImageViewAdapter(List<Bitmap> image, Context context,boolean[] isChice) {
        this.image = image;
        Log.i("hck", image.size()+"lenght");
        this.isChice = isChice;

        this.context = context;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public Object getItem(int arg0) {
        return image.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        View view = arg1;
        GetView getView=null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.imageitem, null);
            getView = new GetView();
            getView.imageView=(ImageView) view.findViewById(R.id.image_item);
            view.setTag(getView);
        } else {
            getView = (GetView) view.getTag();
        }
        getView.imageView.setImageDrawable(getView(arg0));

        return view;
    }

    static class GetView {
        ImageView imageView;
    }


    //主要就是下面的代码了
    private LayerDrawable getView(int post) {
        Bitmap bitmap =  image.get(post);
        Bitmap bitmap2=null;
        LayerDrawable la=null;
        if (isChice[post]== true){
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.check_yes);
        }
        if (bitmap2!=null) {
            Drawable[] array = new Drawable[2];
            array[0] = new BitmapDrawable(bitmap);
            array[1] = new BitmapDrawable(bitmap2);
            la= new LayerDrawable(array);
            la.setLayerInset(0, 0, 0, 0, 0);   //第几张图离各边的间距
            la.setLayerInset(1, 0, 150, 150, 0);
        }
        else {
            Drawable[] array = new Drawable[1];
            array[0] = new BitmapDrawable(bitmap);
            la= new LayerDrawable(array);
            la.setLayerInset(0, 0, 0, 0, 0);
        }
        return la; // 返回叠加后的图
    }
    public void chiceState(int post)
    {
        isChice[post]=isChice[post]==true?false:true;
        this.notifyDataSetChanged();
    }
}

