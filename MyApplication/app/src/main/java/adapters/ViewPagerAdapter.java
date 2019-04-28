package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by ashraf on 3/19/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Bitmap> images;

    public ViewPagerAdapter(Context context,List<Bitmap> images) {
        this.images=images;
        this.context=context;
    }

    @Override
    public int getCount() {
        return images!=null?images.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView= new ImageView(context);
        container.addView(imageView);
        imageView.setImageBitmap(images.get(position));
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
