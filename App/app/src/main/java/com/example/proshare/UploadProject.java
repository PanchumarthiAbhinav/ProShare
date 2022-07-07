package com.example.proshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.UUID;

public class UploadProject extends AppCompatActivity {

    ImageView img;
    Button upload;
    Uri filepath;
    Bitmap bitmap;
    TextView icontext;
    TextInputLayout title, description, category;
    DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_project);
        img = (ImageView) findViewById(R.id.LogoForProject);
        upload = (Button) findViewById(R.id.Upload);
        icontext = (TextView) findViewById(R.id.icontext);
        title = (TextInputLayout) findViewById(R.id.TitleTextLayout);
        description = (TextInputLayout) findViewById(R.id.DescriptionTextLayout);
        category = (TextInputLayout) findViewById(R.id.CategoryTextLayout);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(UploadProject.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select the Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

                icontext.setText("Change Icon");
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(UploadProject.this);
                dialog.setTitle("File Uploader");
                dialog.show();

                String Title = title.getEditText().getText().toString();
                String Description = description.getEditText().getText().toString();
                String Category = category.getEditText().getText().toString();

                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                StorageReference uploader = firebaseStorage.getReference().child("images/" + UUID.randomUUID().toString());
                uploader.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"File Uploaded", Toast.LENGTH_LONG).show();
                                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        userdatabase users = new userdatabase(Category, Description, uri.toString()+".jpeg", Title);
                                        root = FirebaseDatabase.getInstance().getReference("Projects");
                                        String userdatabaseId = root.push().getKey();
                                        root.child(userdatabaseId).setValue(users);
                                        Log.i("URL: ",uri.toString());
                                        finish();
                                    }
                                });
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                float percent = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                                dialog.setMessage("Uploaded: "+(int)percent+" %");
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1&&resultCode==RESULT_OK){
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}