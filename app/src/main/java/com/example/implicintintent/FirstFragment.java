package com.example.implicintintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.implicintintent.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    Button button,button2;
    EditText url;
    private FragmentFirstBinding binding;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        button = view.findViewById(R.id.button);
        button2 = view.findViewById(R.id.button2);
        url = view.findViewById(R.id.url);
        imageView = view.findViewById(R.id.imageView);





        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urltext = url.getText().toString();

                Uri webpage = Uri.parse(urltext);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
                Toast.makeText(getActivity(), urltext, Toast.LENGTH_SHORT).show();

                //camera
                //Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA); //video
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Audio
//                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
//                }




            }
        });


    button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String urltext = url.getText().toString();
//
//                Uri webpage = Uri.parse(urltext);
//                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//                Toast.makeText(getActivity(), urltext, Toast.LENGTH_SHORT).show();

                //camera
                //Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA); //video
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Camera
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }




            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            // Do other work with full size photo saved in locationForPhotos
            imageView.setImageBitmap(thumbnail);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}