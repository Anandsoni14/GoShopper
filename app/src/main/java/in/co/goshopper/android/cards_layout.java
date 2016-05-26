package in.co.goshopper.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Navneet on 25-05-2016.
 */

public class cards_layout extends Activity {

    TextView productName;
    TextView dist;
    TextView price;
    TextView shop;
    ImageView product_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cards_layout);
        productName = (TextView)findViewById(R.id.productName);
        price = (TextView)findViewById(R.id.price);
        shop = (TextView)findViewById(R.id.shop);
        dist = (TextView)findViewById(R.id.dist);
        product_img = (ImageView)findViewById(R.id.productImg);

        productName.setText("Emma Wilson");
        price.setText("23 years old");
        shop.setText("2 old");
        dist.setText("2.3 Kms");
        product_img.setImageResource(R.drawable.img);
    }
}
