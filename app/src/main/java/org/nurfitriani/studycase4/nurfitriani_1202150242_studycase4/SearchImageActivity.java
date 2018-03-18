package org.nurfitriani.studycase4.nurfitriani_1202150242_studycase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class SearchImageActivity extends AppCompatActivity {

    private ImageView imageResult;
    private EditText editURL;
    private Button generateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_image);

        imageResult = (ImageView)findViewById(R.id.iv_imageresult); //initial the Image
        editURL = (EditText)findViewById(R.id.et_urlgambar); //initial the edit text
        generateImage = (Button)findViewById(R.id.b_clickGambar); //initial the button

        generateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when button clicked
                String urlgambar = editURL.getText().toString(); //take the edit text value from edit text (using getText())
                loadImageFromURL(urlgambar); //load the method underneath
            }
        });
    }

    private void loadImageFromURL(String url){ //method to change URL to actual Image.
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher) //placeholder is for the image doen't load anything
                .error(R.mipmap.ic_launcher_round) //if image error load this image
                .into(imageResult, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }


}
