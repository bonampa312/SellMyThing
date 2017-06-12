/*
 * Copyright (c) 2014 Rex St. John on behalf of AirPair.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package co.com.romero.sellmything.sellmything.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.utilities.MyConstants;
import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.activities.CameraActivity;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassifyPostManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.recognition.ClassifyPost;
import co.com.romero.sellmything.sellmything.utilities.rest.APIClient;
import co.com.romero.sellmything.sellmything.utilities.rest.APIInterface;
import co.com.romero.sellmything.sellmything.utilities.rest.VisualRecognitionManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraFragment extends BaseFragment implements Button.OnClickListener {

    public static final int ID = MyConstants.CAMERA_FRAGMENT;

    // Activity result key for camera
    static final int REQUEST_TAKE_PHOTO = 11111;

    // Image view for showing our image.
    private ImageView mImageView;
    private Button takePictureButton;
    private Button takePictureAgainButton;
    private Button sendPictureButton;
    private LinearLayout pictureTakenLinearLayout;
    private RelativeLayout rlLoading;
    private LinearLayout llTakePicture;

    /**
     * Default empty constructor.
     */
    public CameraFragment() {
        super();
    }

    /**
     * Static factory method
     *
     * @return
     */
    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    /**
     * OnCreateView fragment override
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_camera, container, false);

        // Set the image view
        mImageView = (ImageView) view.findViewById(R.id.imageViewFullSized);
        takePictureButton = (Button) view.findViewById(R.id.btn_take_photo);
        takePictureAgainButton = (Button) view.findViewById(R.id.btn_take_photo_again);
        sendPictureButton = (Button) view.findViewById(R.id.btn_send_photo);
        pictureTakenLinearLayout = (LinearLayout) view.findViewById(R.id.linlay_btns_photo_taken);
        llTakePicture = (LinearLayout) view.findViewById(R.id.ll_take_picture);
        rlLoading = (RelativeLayout) view.findViewById(R.id.rl_loading);

        takePictureButton.setVisibility(View.VISIBLE);
        pictureTakenLinearLayout.setVisibility(View.INVISIBLE);
        // Set OnItemClickListener so we can be notified on button clicks
        takePictureAgainButton.setOnClickListener(this);
        takePictureButton.setOnClickListener(this);
        sendPictureButton.setOnClickListener(this);

        return view;
    }

    /**
     * Start the camera by dispatching a camera intent.
     */
    protected void dispatchTakePictureIntent() {

        // Check if there is a camera.
        Context context = getActivity();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
            Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // Camera exists? Then proceed...
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        CameraActivity activity = (CameraActivity) getActivity();
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go.
            // If you don't do this, you may get a crash in some devices.
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("@@@ DEBUG", "dispatchTakePictureIntent: error: ", ex);
                Toast toast = Toast.makeText(activity, "There was a problem saving the photo...", Toast.LENGTH_SHORT);
                toast.show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri fileUri = Uri.fromFile(photoFile);
                activity.setCapturedImageURI(fileUri);
                activity.setCurrentPhotoPath(fileUri.getPath());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        activity.getCapturedImageURI());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * The activity returns with the photo.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {

            addPhotoToGallery();
            CameraActivity activity = (CameraActivity) getActivity();

            // Show the full sized image.
            setFullImageFromFilePath(activity.getCurrentPhotoPath(), mImageView);
        } else {
            Toast.makeText(getActivity(), "Image Capture Failed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Creates the image file to which the image must be saved.
     *
     * @return
     * @throws IOException
     */
    protected File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
        String imageFileName = "SellMyThing_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        CameraActivity activity = (CameraActivity) getActivity();
        activity.setCurrentPhotoPath("file:" + image.getAbsolutePath());
        return image;
    }

    /**
     * Add the picture to the photo gallery.
     * Must be called on all camera images or they will
     * disappear once taken.
     */
    protected void addPhotoToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        CameraActivity activity = (CameraActivity) getActivity();
        File f = new File(activity.getCurrentPhotoPath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.getActivity().sendBroadcast(mediaScanIntent);
    }

    /**
     * Deal with button clicks.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        CameraActivity camActivity = (CameraActivity) getActivity();
        switch (v.getId()) {
            case (R.id.btn_take_photo):
                takePictureButton.setVisibility(View.INVISIBLE);
                pictureTakenLinearLayout.setVisibility(View.VISIBLE);
                dispatchTakePictureIntent();
                break;
            case (R.id.btn_take_photo_again):
                if (deletePhoto(camActivity.getCurrentPhotoPath())) {
                    Toast.makeText(this.getActivity(), "Photo deleted", Toast.LENGTH_SHORT).show();
                }
                dispatchTakePictureIntent();
                break;
            case (R.id.btn_send_photo):

                //VisualRecognitionManager.getInstance().recognizeImage(camActivity.getCurrentPhotoPath());
                APIInterface apiInterface = APIClient.getClientWatson().create(APIInterface.class);
                rlLoading.setVisibility(View.VISIBLE);
                setClickable(llTakePicture, false);
                File file = new File(camActivity.getCurrentPhotoPath());
                RequestBody image = RequestBody.create(MediaType.parse("image/*"), file);
                Call<ClassifyPost> call = apiInterface.classifyImage(MyConstants.WATSON_API_KEY, MyConstants.WATSON_VERSION, image);
                call.enqueue(new Callback<ClassifyPost>() {
                    @Override
                    public void onResponse(Call<ClassifyPost> call, Response<ClassifyPost> response) {
                        ClassifyPostManager.getInstance().saveClassifyPostLocal(response.body());
                        // Toast.makeText(SellMyThing.getContext(), "Ready to classify :D", Toast.LENGTH_SHORT).show();
                        changeFragment();
                    }

                    @Override
                    public void onFailure(Call<ClassifyPost> call, Throwable t) {
                        Log.d("@@@ DEBUG", "onResponse: FAILURE ON CALL", t);
                        Toast.makeText(SellMyThing.getContext(), "Failed, try again :c", Toast.LENGTH_SHORT).show();
                        rlLoading.setVisibility(View.INVISIBLE);
                        setClickable(llTakePicture, true);
                    }
                });
                break;
        }

    }

    /**
     * Scale the photo down and fit it to our image views.
     * <p>
     * "Drastically increases performance" to set images using this technique.
     * Read more:http://developer.android.com/training/camera/photobasics.html
     */
    private void setFullImageFromFilePath(String imagePath, ImageView imageView) {
        // Get the dimensions of the View
        int targetW = 780;
        int targetH = 1040;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    private boolean deletePhoto(String filepath) {
        File file = new File(filepath);
        boolean deleted = file.delete();
        return deleted;
    }

    private void changeFragment(){
        new Handler().post(new Runnable() {
            public void run() {
                FragmentManager fragmentManager = getFragmentManager();
                BaseFragment targetFragment = ListClassifiersFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, targetFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    public void setClickable(View view, boolean flag) {
        if (view != null) {
            view.setClickable(flag);
            if (view instanceof ViewGroup) {
                ViewGroup vg = ((ViewGroup) view);
                for (int i = 0; i < vg.getChildCount(); i++) {
                    setClickable(vg.getChildAt(i), flag);
                }
            }
        }
    }
}
