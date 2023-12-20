package tdtu.edu.project_ghn.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import tdtu.edu.project_ghn.MainActivity;
import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.view.activity.CustomerCreateOrderActivity3;
import tdtu.edu.project_ghn.view.activity.DetailProductActivity;
import tdtu.edu.project_ghn.view.activity.GoogleMapActivity;

public class CreateOrderFragment extends Fragment {
    private View mView;
    private Button btnCheapService, btnFastService, btnMotorbike,
            btnTricycles, btnNextToDesProduct;
    private MaterialButton spaceChoseService, spaceChoseTransport;
    private ImageButton btnMap;
    private EditText txtAddress;
    private TextView txtShopAddress, txtTime;
    private String chosenService = "cheap", chosenTransport="bike";
    public CreateOrderFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_create_order, container, false);
        initUI();
        initListener();
        return mView;
    }

    private void initListener() {
        spaceChoseService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionService();
            }
        });

        spaceChoseTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionTransport();
            }
        });

        btnNextToDesProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailProductActivity.class);
                intent.putExtra("address", txtAddress.getText().toString());
                startActivity(intent);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
                    intent.putExtra("address", txtAddress.getText().toString());
                    startActivity(intent);
            }
        });

        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new GeocodingTask().execute(txtShopAddress.getText().toString(), txtAddress.getText().toString());
            }
        });

    }
    private void showOptionTransport() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_chose_transport_order);

        btnMotorbike = dialog.findViewById(R.id.btnMotoBike);
        btnTricycles = dialog.findViewById(R.id.btnTricycles);

        btnMotorbike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenTransport="bike";
                updateTransportButtonAppearance();
                dialog.dismiss();
            }
        });

        btnTricycles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenTransport="tricycle";
                updateTransportButtonAppearance();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showOptionService() {
        Dialog dialog= new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_chose_service_order);

        btnFastService = dialog.findViewById(R.id.btnFastService);
        btnCheapService = dialog.findViewById(R.id.btnCheapService);

        btnCheapService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenService = "cheap";
                updateServiceButtonAppearance();
                new GeocodingTask().execute(txtShopAddress.getText().toString(), txtAddress.getText().toString());
                dialog.dismiss();
            }

        });

        btnFastService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenService="fast";
                updateServiceButtonAppearance();
                new GeocodingTask().execute(txtShopAddress.getText().toString(), txtAddress.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateServiceButtonAppearance() {
        if (chosenService.equals("cheap")) {
            spaceChoseService.setIcon(getResources().getDrawable(R.drawable.product));
            spaceChoseService.setText("Tiết kiệm: 30.000 VND");
        } else if (chosenService.equals("fast")) {
            spaceChoseService.setIcon(getResources().getDrawable(R.drawable.fast_product));
            spaceChoseService.setText("Siêu tốc: 100.000 VND");
        }
    }

    private void updateTransportButtonAppearance() {
        if (chosenTransport.equals("bike")) {
            spaceChoseTransport.setIcon(getResources().getDrawable(R.drawable.fast_delivery));
            spaceChoseTransport.setText("Xe máy: 30.000 VND");
        } else if (chosenTransport.equals("tricycle")) {
            spaceChoseTransport.setIcon(getResources().getDrawable(R.drawable.tricycle));
            spaceChoseTransport.setText("Xe ba gác: 300.000 VND");
        }
    }

    //Move calculate to background to reduce lag
    private class GeocodingTask extends AsyncTask<String, Void, Float> {
        @Override
        protected Float doInBackground(String... addresses) {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
                List<Address> addresses1 = geocoder.getFromLocationName(addresses[0], 1);
                List<Address> addresses2 = geocoder.getFromLocationName(addresses[1], 1);

                if (addresses1.size() > 0 && addresses2.size() > 0) {
                    Address location1 = addresses1.get(0);
                    Address location2 = addresses2.get(0);

                    float[] results = new float[1];
                    Location.distanceBetween(location1.getLatitude(), location1.getLongitude(),
                            location2.getLatitude(), location2.getLongitude(), results);
                    return results[0];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Float distanceInMeters) {
            if (distanceInMeters != null) {

                float speed = 10 * 1000f / 3600f;
                //Speed base on service
                if (chosenService.equals("cheap")) {
                        speed = 10 * 1000f / 3600f;
                } else {
                        speed = 30 * 1000f / 3600f;
                }


                float timeInSeconds = distanceInMeters / speed;

                //Convert time to days, hours, and minutes
                int days = (int) (timeInSeconds / 86400);
                int hours = (int) ((timeInSeconds % 86400) / 3600);
                int minutes = (int) ((timeInSeconds % 3600) / 60);

                if (days == 0 && hours == 0 && minutes == 0) {
                    txtTime.setText("Vị trí người nhận không phải vị trí shop");
                } else if (days == 0 && hours == 0) {
                    txtTime.setText(minutes + " phút");
                } else if (days == 0 && minutes == 0) {
                    txtTime.setText(hours + " giờ");
                } else if (hours == 0 && minutes == 0) {
                    txtTime.setText(days + " ngày");
                } else if (days == 0) {
                    txtTime.setText(hours + " giờ " + minutes + " phút");
                } else if (hours == 0) {
                    txtTime.setText(days + " ngày " + minutes + " phút");
                } else if (minutes == 0) {
                    txtTime.setText(days + " ngày " + hours + " giờ");
                } else {
                    txtTime.setText(days + " ngày " + hours + " giờ " + minutes + " phút");
                }
            }
        }
    }

    private void initUI() {
        spaceChoseService = mView.findViewById(R.id.space_choseService);
        spaceChoseTransport = mView.findViewById(R.id.space_choseTransport);
        btnNextToDesProduct = mView.findViewById(R.id.btnNextToDesProduct);
        btnMap = mView.findViewById(R.id.btnMap2);
        txtAddress = mView.findViewById(R.id.txtAddress);
        txtShopAddress = mView.findViewById(R.id.txtShopAddress);
        txtTime = mView.findViewById(R.id.txtTime);
    }

}
