package com.example.dioum2touba.dtsmediacom;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentPicture extends Fragment {

    View v;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    CardView item_Layout;
    Dialog dialog;
    TextView dialog_Title;
    TextView dialog_Description;
    int count = 0;
    ImageView dialog_img_view;
    SweetAlertDialog pDialog;
    private LinearLayoutManager mLayoutManager;

    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public FragmentPicture() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.picture_fragment, container, false);

        // RecyclerView
        mRecyclerView = v.findViewById(R.id.picture_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // Set layout as LinearLayout
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Data");
        mRef.keepSynced(true);
        mRef.orderByValue().limitToLast(200).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                System.out.println("The " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Dialog for laoding the data
        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Chargement en cours");
        pDialog.show();


        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pDialog.setCancelable(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Load data into recycler view onStart
    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {

                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setdetails(getContext(), model.getTitle(), model.getDescription(), model.getImage());
                    }

                    @Override
                    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
                        super.onBindViewHolder(holder, position, payloads);
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        final ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.activity_post_detail);
                         dialog_Title = (TextView) dialog.findViewById(R.id.dialog_title);
                         dialog_Description = (TextView) dialog.findViewById(R.id.dialog_description);
                         dialog_img_view = (ImageView) dialog.findViewById(R.id.dialog_img_view);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {
                                // Views
                                item_Layout = view.findViewById(R.id.picture_item);
                                TextView mTitleTv = view.findViewById(R.id.rTitleTV);
                                TextView mDescTv =  view.findViewById(R.id.rDescription);
                                ImageView mImageView = view.findViewById(R.id.rImageView);

                                //get data from views
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap;

                                dialog_Title.setText(mTitle);
                                dialog_Description.setText(mDesc);
                                dialog_img_view.setImageDrawable(mDrawable);

                                if (mDrawable == null){
                                    SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Patientez s'il vous plait!");
                                    pDialog.show();


                                }
                                else
                                {
                                    mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                    dialog.show();
                                }
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                // TODO do your own implementation on long item cick

                            }

                        });

                        View v;
                        v = LayoutInflater.from(getContext()).inflate(R.layout.activity_post_detail, parent, false);
                        final ViewHolder holder = new ViewHolder(v);

                        holder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }
                };

        // Set Adapter to recycler view
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    //Seach data
    private void fireBaseSearch(String searchText){
        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        firebaseSearchQuery
                ){
                    @Override
                    protected void populateViewHolder(ViewHolder  viewHolder, Model model, int position){
                        viewHolder.setdetails(getContext(), model.getTitle(), model.getDescription(), model.getImage());
                    }


                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                // Views
                                TextView mTitleTv = view.findViewById(R.id.rTitleTV);
                                TextView mDescTv =  view.findViewById(R.id.rDescription);
                                ImageView mImageView = view.findViewById(R.id.rImageView);

                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                // TODO do your own implementation on long item cick

                            }

                        });
                        return viewHolder;
                    }

                };

        // Set Adapter to recycler view
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}