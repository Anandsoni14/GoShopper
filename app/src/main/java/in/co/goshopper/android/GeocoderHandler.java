package in.co.goshopper.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


/**
 * Created by Navneet on 26-05-2016.
 */

class GeocoderHandler extends Handler {

    TextView place;
    @Override
    public void handleMessage(Message message) {
        String locationAddress;
        switch (message.what) {
            case 1:
                Bundle bundle = message.getData();
                locationAddress = bundle.getString("address");
                break;
            default:
                locationAddress = null;
        }

        place.setText(locationAddress);
    }
}
