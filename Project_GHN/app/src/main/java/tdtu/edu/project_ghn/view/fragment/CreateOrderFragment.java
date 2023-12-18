package tdtu.edu.project_ghn.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import tdtu.edu.project_ghn.MainActivity;
import tdtu.edu.project_ghn.R;

public class CreateOrderFragment extends Fragment {
    private View mView;
    private Button spaceChoseService, spaceChoseTransport, btnCheapService, btnFastService, btnMotorbike, btnTricycles;
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
                dialog.dismiss();
            }
        });

        btnTricycles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Toast.makeText(getActivity(), "Cheap service", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });

        btnFastService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Faster service", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initUI() {
        spaceChoseService = mView.findViewById(R.id.space_choseService);
        spaceChoseTransport = mView.findViewById(R.id.space_choseTransport);
    }

}
