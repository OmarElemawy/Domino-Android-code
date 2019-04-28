package com.example.ashraf.domino;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView2;
    Queue<Integer> right;
    Queue<Integer> left;
    List<Integer> images;
    private Bitmap bitmap;
    private Random random;
    List<ImageView> bottom;
    private List<ImageView> top;
    private View view_bottom;
    private View view_top;
    boolean player;
    View top_view;
    View bottom_view;
    List<ImageView> top_open_images;
    List<ImageView> bottom_open_images;
    HashMap<Integer, String> map;
    private int d1 = 0;
    private int d2 = 0;
    private int d3 = 0;
    private int d4 = 0;

    private int s1 = 10;
    private int s2 = 10;
    private int s3 = 10;
    private int s4 = 10;
    private int s5 = 10;
    private int s6 = 10;
    private int s7 = 10;
    private int s8 = 10;
    private int b = 0;
    private int f1 = 0;
    private int f2 = 0;
    private boolean select = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue_of_right();
        queue_of_left();
        the_images();
        top_open_images = new ArrayList<>();
        bottom_open_images = new ArrayList<>();

        top_open_images.add((ImageView) findViewById(R.id.top_9));
        top_open_images.add((ImageView) findViewById(R.id.top_10));
        top_open_images.add((ImageView) findViewById(R.id.top_11));
        top_open_images.add((ImageView) findViewById(R.id.top_12));

        bottom_open_images.add((ImageView) findViewById(R.id.bottom_9));
        bottom_open_images.add((ImageView) findViewById(R.id.bottom_10));
        bottom_open_images.add((ImageView) findViewById(R.id.bottom_11));
        bottom_open_images.add((ImageView) findViewById(R.id.bottom_12));

        player = true;
        random = new Random();
        top_view = findViewById(R.id.top_row);
        bottom_view = findViewById(R.id.bottom_row);
        List<Integer> arr_bottom = new ArrayList<>();
        List<Integer> arr_top = new ArrayList<>();
        bottom = new ArrayList<>();
        top = new ArrayList<>();
        top_image();
        bottom_image();
        for (int i = 0; i < 7; i++) {
            int s = random.nextInt(images.size());
            Integer integer = images.remove(s);
            arr_bottom.add(integer);
            ImageView imageView = bottom.get(i);
            imageView.setImageResource(integer);
            imageView.setTag(integer);
        }
        for (int i = 0; i < 7; i++) {
            int s = random.nextInt(images.size());
            Integer integer = images.remove(s);
            arr_top.add(integer);
            ImageView imageView = top.get(i);
            imageView.setImageResource(integer);
            imageView.setTag(integer);
        }

    }


    public void click_top(View view) {
        if (player) {

            if (select) {
                this.view_top = view;
                view.setSelected(true);
                view.setAlpha(0.5f);
                select = false;
                Integer tag = (Integer) view_top.getTag();
                String tag1 = map.get(tag);
                String[] split = tag1.split("_");
                d1 = Integer.parseInt(split[0]);
                d2 = Integer.parseInt(split[1]);
            }
        }
    }

    public void click_bottom(View view) {

        if (!player) {

            if (select) {
                this.view_bottom = view;
                view.setSelected(true);
                view.setAlpha(0.5f);
                select = false;
                Integer tag = (Integer) view_top.getTag();
                String tag1 = map.get(tag);
                String[] split = tag1.split("_");
                d3 = Integer.parseInt(split[0]);
                d4 = Integer.parseInt(split[1]);
            }
        }

    }

    public void Right_top(View view) {

        Integer tag = (Integer) view_top.getTag();
        String tag1 = map.get(tag);
        String[] split = tag1.split("_");
        s1 = Integer.parseInt(split[0]);
        s2 = Integer.parseInt(split[1]);
        Log.i("ooooo", "s1 " + s1 + " " + "s2 " + s2);
        bitmap = ((BitmapDrawable) ((ImageView) view_top).getDrawable()).getBitmap();

        if (f1 == 1 || f1 == 0) {

            if (s6 == 10 && s5 == 10 || s6 == s1) {
                if (player) {
                    myclick(right, view_top);
                    player = false;
                    select = true;
                    b = 1;
                    f1 = 1;
                }

            } else if (s6 == s2) {
                if (player) {
                    imageView2 = findViewById(right.remove());
                    imageView2.setImageBitmap(bitmap);
                    imageView2.animate().rotation(-90).setDuration(0).translationYBy(-4);
                    ((ImageView) view_top).setImageBitmap(null);
                    player = false;
                    select = true;
                    b = 1;
                    f1 = 2;
                }
            } else {
                view_top.setAlpha(1);
                view_top.setSelected(false);
                select = true;
                b = 1;


            }
        } else {
            if (s6 == 10 && s5 == 10 || s5 == s1) {
                if (player) {
                    myclick(right, view_top);
                    player = false;
                    select = true;
                    b = 1;
                    f1 = 1;
                }
            } else if (s5 == s2) {
                if (player) {
                    imageView2 = findViewById(right.remove());
                    imageView2.setImageBitmap(bitmap);
                    imageView2.animate().rotation(-90).setDuration(0).translationYBy(-4);
                    ((ImageView) view_top).setImageBitmap(null);
                    player = false;
                    select = true;
                    b = 1;
                    f1 = 2;
                }
            } else {
                view_top.setAlpha(1);
                view_top.setSelected(false);
                select = true;
                b = 1;

            }
        }

    }




    public void Left_top(View view) {
        Integer tag = (Integer) view_top.getTag();
        String tag1 = map.get(tag);
        String[] split = tag1.split("_");
        s3 = Integer.parseInt(split[0]);
        s4 = Integer.parseInt(split[1]);
        Log.i("ooooo", "s3 " + s3 + " " + "s4 " + s4);


        if (f2 == 1||f2==0) {

            if (s7 == 10 && s8 == 10 || s7 == s3) {
                if (player) {
                    myclick(left, view_top);
                    imageView2.animate().rotation(-180);
                    player = false;
                    select = true;
                    b = 2;
                    f2=1;
                }

            }else if (s7==s4){
                if (player) {
                    imageView2 = findViewById(left.remove());
                    imageView2.setImageBitmap(bitmap);
                    imageView2.animate().rotation(90).setDuration(0).translationYBy(-4);
                    ((ImageView) view_top).setImageBitmap(null);
                    player = false;
                    select = true;
                    b=2;
                    f2=2;
                }
            }
            else{
                view_top.setAlpha(1);
                view_top.setSelected(false);
                select = true;
                b=2;


            }
        } else {
            if (s7 == 10 && s8 == 10 || s8 == s4) {
                if (player) {
                    myclick(left, view_top);
                    player = false;
                    select = true;
                    b = 2;
                    f2=1;
                }
            } else if (s8 == s3) {
                if (player) {
                    imageView2 = findViewById(left.remove());
                    imageView2.setImageBitmap(bitmap);
                    imageView2.animate().rotation(-90).setDuration(0).translationYBy(-4);
                    ((ImageView) view_top).setImageBitmap(null);
                    player = false;
                    select = true;
                    b=2;
                    f2=2;
                }
            } else {
                view_top.setAlpha(1);
                view_top.setSelected(false);
                select = true;
                b=1;

            }
        }

    }

    public void Right_bottom(View view) {
        Integer tag = (Integer) view_bottom.getTag();
        String tag1 = map.get(tag);
        String[] split = tag1.split("_");
        s5 = Integer.parseInt(split[0]);
        s6 = Integer.parseInt(split[1]);
        Log.i("ooooo", "s5 " + s5 + " " + "s6 " + s6);
        bitmap = ((BitmapDrawable) ((ImageView) view_bottom).getDrawable()).getBitmap();

        if (s2 == s5) {
            if (!player) {
                myclick(right, view_bottom);
                player = true;
                select = true;
                b = 3;
                f1 = 1;
            }
        } else if (s2 == s6) {
            if (!player) {
                imageView2 = findViewById(right.remove());
                imageView2.setImageBitmap(bitmap);
                imageView2.animate().rotation(-90).setDuration(0).translationYBy(-4);
                ((ImageView) view_bottom).setImageBitmap(null);
                player = true;
                select = true;
                b = 3;
                f1 = 2;
            }
        } else {
            view_bottom.setAlpha(1);
            view_bottom.setSelected(false);
            select = true;
            b = 3;

        }
    }



    public void Left_bottom(View view) {
        Integer tag = (Integer) view_bottom.getTag();
        String tag1 = map.get(tag);
        String[] split = tag1.split("_");
        s7 = Integer.parseInt(split[0]);
        s8 = Integer.parseInt(split[1]);
        Log.i("ooooo", "s7 " + s7 + " " + "s8 " + s8);
        bitmap = ((BitmapDrawable) ((ImageView) view_bottom).getDrawable()).getBitmap();

        if (s1 == s8) {
            if (!player) {
                myclick(left, view_bottom);
                player = true;
                select = true;
                b = 4;
                f2 = 1;
            }
        } else if (s1 == s7) {

            if (!player) {
                imageView2 = findViewById(left.remove());
                imageView2.setImageBitmap(bitmap);
                imageView2.animate().rotation(-90).setDuration(0).translationYBy(-4);
                ((ImageView) view_bottom).setImageBitmap(null);
                player = true;
                select = true;
                b = 4;
                f2 = 2;
            }
        } else {
            view_bottom.setAlpha(1);
            view_bottom.setSelected(false);
            select = true;

            b = 4;
        }
    }




    public void click(View view) {

        if (((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap() != null) {
            ((ImageView) view).setImageBitmap(null);
            int s = random.nextInt(images.size());
            Integer integer = images.remove(s);

            if (player) {
                ImageView imageView = top_open_images.remove(0);
                imageView.setTag(integer);
                top.add(imageView);
                imageView.setImageResource(integer);
                imageView.setAlpha(1f);
                imageView.setSelected(false);
            } else {
                ImageView remove = bottom_open_images.remove(0);
                remove.setTag(integer);
                bottom.add(remove);
                remove.setImageResource(integer);
                remove.setAlpha(1f);
                remove.setSelected(false);
            }
        }
    }


    private void queue_of_right() {
        right = new ArrayDeque<>();
        right.add(R.id.right_1_);
        right.add(R.id.right_2_);
        right.add(R.id.right_3_);
        right.add(R.id.right_4_);
        right.add(R.id.right_5_);
        right.add(R.id.right_6_);
        right.add(R.id.right_7_);
        right.add(R.id.right_8_);
        right.add(R.id.right_9_);
        right.add(R.id.right_10_);
        right.add(R.id.right_11_);
        right.add(R.id.right_12_);
        right.add(R.id.right_13_);
        right.add(R.id.right_14_);
        right.add(R.id.right_15_);
        right.add(R.id.right_16_);
        right.add(R.id.right_17_);
        right.add(R.id.right_18_);
        right.add(R.id.right_19_);
        right.add(R.id.right_20_);
        right.add(R.id.right_21_);
        right.add(R.id.right_22_);
        right.add(R.id.right_23_);
        right.add(R.id.right_24_);
        right.add(R.id.right_25_);
        right.add(R.id.right_26_);
    }

    private void queue_of_left() {
        left = new ArrayDeque<>();
        left.add(R.id.left_2_);
        left.add(R.id.left_3_);
        left.add(R.id.left_4_);
        left.add(R.id.left_5_);
        left.add(R.id.left_6_);
        left.add(R.id.left_7_);
        left.add(R.id.left_8_);
        left.add(R.id.left_9_);
        left.add(R.id.left_10_);
        left.add(R.id.left_11_);
        left.add(R.id.left_12_);
        left.add(R.id.left_13_);
        left.add(R.id.left_14_);
        left.add(R.id.left_15_);
        left.add(R.id.left_16_);
        left.add(R.id.left_17_);
        left.add(R.id.left_18_);
        left.add(R.id.left_19_);
        left.add(R.id.left_20_);
        left.add(R.id.left_21_);
        left.add(R.id.left_22_);
        left.add(R.id.left_23_);
        left.add(R.id.left_24_);
        left.add(R.id.left_25_);
        left.add(R.id.left_26_);
    }

    private void the_images() {
        images = new ArrayList<>();
        map = new HashMap<>();


        imageView2 = findViewById(R.id.bottom_1);

        images.add(R.drawable.do_0_0);
        images.add(R.drawable.do_0_1);
        images.add(R.drawable.do_0_2);
        images.add(R.drawable.do_0_3);
        images.add(R.drawable.do_0_4);
        images.add(R.drawable.do_0_5);
        images.add(R.drawable.do_0_6);
        images.add(R.drawable.do_1_1);
        images.add(R.drawable.do_1_2);
        images.add(R.drawable.do_1_3);
        images.add(R.drawable.do_1_4);
        images.add(R.drawable.do_1_5);
        images.add(R.drawable.do_1_6);
        images.add(R.drawable.do_2_2);
        images.add(R.drawable.do_2_3);
        images.add(R.drawable.do_2_4);
        images.add(R.drawable.do_2_5);
        images.add(R.drawable.do_2_6);
        images.add(R.drawable.do_3_3);
        images.add(R.drawable.do_3_4);
        images.add(R.drawable.do_3_5);
        images.add(R.drawable.do_3_6);
        images.add(R.drawable.do_4_4);
        images.add(R.drawable.do_4_5);
        images.add(R.drawable.do_4_6);
        images.add(R.drawable.do_5_5);
        images.add(R.drawable.do_5_6);
        images.add(R.drawable.do_6_6);


        map.put(images.get(0), "0_0");
        map.put(images.get(1), "0_1");
        map.put(images.get(2), "0_2");
        map.put(images.get(3), "0_3");
        map.put(images.get(4), "0_4");
        map.put(images.get(5), "0_5");
        map.put(images.get(6), "0_6");
        map.put(images.get(7), "1_1");
        map.put(images.get(8), "1_2");
        map.put(images.get(9), "1_3");
        map.put(images.get(10), "1_4");
        map.put(images.get(11), "1_5");
        map.put(images.get(12), "1_6");
        map.put(images.get(13), "2_2");
        map.put(images.get(14), "2_3");
        map.put(images.get(15), "2_4");
        map.put(images.get(16), "2_5");
        map.put(images.get(17), "2_6");
        map.put(images.get(18), "3_3");
        map.put(images.get(19), "3_4");
        map.put(images.get(20), "3_5");
        map.put(images.get(21), "3_6");
        map.put(images.get(22), "4_4");
        map.put(images.get(23), "4_5");
        map.put(images.get(24), "4_6");
        map.put(images.get(25), "5_5");
        map.put(images.get(26), "5_6");
        map.put(images.get(27), "6_6");
    }

    private void bottom_image() {
        bottom.add((ImageView) findViewById(R.id.bottom_1));
        bottom.add((ImageView) findViewById(R.id.bottom_2));
        bottom.add((ImageView) findViewById(R.id.bottom_3));
        bottom.add((ImageView) findViewById(R.id.bottom_4));
        bottom.add((ImageView) findViewById(R.id.bottom_5));
        bottom.add((ImageView) findViewById(R.id.bottom_6));
        bottom.add((ImageView) findViewById(R.id.bottom_7));
        bottom.add((ImageView) findViewById(R.id.bottom_8));
        bottom.add((ImageView) findViewById(R.id.bottom_9));
        bottom.add((ImageView) findViewById(R.id.bottom_10));
        bottom.add((ImageView) findViewById(R.id.bottom_11));
        bottom.add((ImageView) findViewById(R.id.bottom_12));

    }

    private void top_image() {
        top.add((ImageView) findViewById(R.id.top_1));
        top.add((ImageView) findViewById(R.id.top_2));
        top.add((ImageView) findViewById(R.id.top_3));
        top.add((ImageView) findViewById(R.id.top_4));
        top.add((ImageView) findViewById(R.id.top_5));
        top.add((ImageView) findViewById(R.id.top_6));
        top.add((ImageView) findViewById(R.id.top_7));
        top.add((ImageView) findViewById(R.id.top_8));
        top.add((ImageView) findViewById(R.id.top_9));
        top.add((ImageView) findViewById(R.id.top_10));
        top.add((ImageView) findViewById(R.id.top_11));
        top.add((ImageView) findViewById(R.id.top_12));
    }

    private void myclick(Queue<Integer> right1, View view_top1) {

        if (view_top1.isSelected()) {

            bitmap = ((BitmapDrawable) ((ImageView) view_top1).getDrawable()).getBitmap();

            if (bitmap != null) {

                if (view_top1 != null) {

                    Integer tag2 = (Integer) view_top1.getTag();
                    String integer = map.get(tag2);
                    Log.i("ooooooo", "" + integer);

                    int id = view_top1.getId();

                    top_open_images.add((ImageView) findViewById(id));
                    top.remove(findViewById(id));

                    if (right1.size() == 26) {
                        imageView2 = findViewById(right1.remove());
                        imageView2.setImageBitmap(bitmap);


                        ((ImageView) view_top1).setImageBitmap(null);
                    } else if (right1.size() == 22 || right1.size() == 14 || right1.size() == 6) {
                        imageView2 = findViewById(right1.remove());
                        imageView2.setImageBitmap(bitmap);
                        ((ImageView) view_top1).setImageBitmap(null);
                    } else {
                        imageView2 = findViewById(right1.remove());
                        imageView2.setImageBitmap(bitmap);
                        imageView2.animate().rotation(90).setDuration(0).translationYBy(-4);
                        ((ImageView) view_top1).setImageBitmap(null);
                    }
                }


            }


        }
    }

}


