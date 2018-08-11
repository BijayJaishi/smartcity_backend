package com.bijay.smartcity_backend;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class categories extends AppCompatActivity {

    private static final String SECTOR_KEY ="sector";
    private static final String TAG ="12" ;
    Button maddnew;
    DatabaseReference databaseReference;
    //DocumentReference db = FirebaseFirestore.getInstance().document("Smartcity/Sectors");

    RecyclerView recyclerView;
    Myadapter myadapter;
    List<Modeladdcate>listdata;
    FirebaseDatabase FDB;
    //DatabaseReference DBR;
    ProgressDialog progressDialog;
    //List<Modeladdcate>modeladdcateList;

    ToggleButton btn;
    ExpandableRelativeLayout expandableRelativeLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        maddnew=findViewById(R.id.addnew);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        listdata=new ArrayList<>();
        myadapter=new Myadapter(listdata);
        FDB=FirebaseDatabase.getInstance();

        //// progress dialog




        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://work1-1da82.firebaseio.com/Categories");

        maddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcate();
            }
        });

        //getcate();
        getdatafirebase();


        btn = (ToggleButton) findViewById(R.id.radioButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(categories.this);
                progressDialog.setMessage("Loading ....");
                progressDialog.setCancelable(true);
                progressDialog.show();
                expandableRelativeLayout = (ExpandableRelativeLayout)findViewById(R.id.btnexpand);
                expandableRelativeLayout.toggle();

            }
        });



    }


    void getdatafirebase(){

        databaseReference=FDB.getReferenceFromUrl("https://work1-1da82.firebaseio.com/Categories");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Modeladdcate data=dataSnapshot.getValue(Modeladdcate.class);
                listdata.add(data);
                recyclerView.setAdapter(myadapter);
//                 if (progressDialog.isShowing()){
//                    progressDialog.dismiss();
//                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>{
    //Context context;
    List<Modeladdcate>listarray;

        public Myadapter(List<Modeladdcate> listarray) {
            this.listarray = listarray;
        }

        @NonNull
        @Override
        public Myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reclycer_view_item,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

            final Modeladdcate data=listarray.get(position);


            holder.txt_item_name.setText(data.getName());
            holder.txt_item_desc.setText(data.getId());

            holder.deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(categories.this,"Recently not in use",Toast.LENGTH_LONG).show();

                }
            });

            holder.edititem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(categories.this,"Recently not in use",Toast.LENGTH_LONG).show();
                }
            });

            holder.txt_item_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Catid", data.getId());

                    Intent intent=new Intent(categories.this,component.class);
                    intent.putExtra("Catid", data.getId());

                    startActivity(intent);



                }
            });

        }

        @Override
        public int getItemCount() {
            return listarray.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt_item_desc,txt_item_name;
            Button deleteitem,edititem;

            public MyViewHolder(View itemView) {
                super(itemView);

                txt_item_desc = itemView.findViewById(R.id.item_desc);
                txt_item_name = itemView.findViewById(R.id.item_name);
                deleteitem=itemView.findViewById(R.id.delete);
                edititem=itemView.findViewById(R.id.edit);
            }
        }
    }

    /*private void getcate() {

        Recyclerviewadapter_1 recyclerviewAdapter1 = new Recyclerviewadapter_1(categories.this,modeladdcateList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(categories.this);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewAdapter1);
        recyclerviewAdapter1.notifyDataSetChanged();

        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }*/

    private void addcate() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add Sector");
        LayoutInflater inflater = LayoutInflater.from(this);
        View maddcate = inflater.inflate(R.layout.addcate_form, null);

        final MaterialEditText msector=maddcate.findViewById(R.id.sector);
        final MaterialEditText mcategoryid=maddcate.findViewById(R.id.caterory_id);
        final Button msubmit=maddcate.findViewById(R.id.submit);
        //final Button mcancel=maddcate.findViewById(R.id.cancel);

       msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msector.getText().toString().trim().isEmpty()) {
                    msector.setError("Sector name required !!");
                    msector.requestFocus();
                    return;
                }
                if (mcategoryid.getText().toString().trim().isEmpty()) {
                    mcategoryid.setError("Id is required !!");
                    mcategoryid.requestFocus();
                    return;
                }




                Modeladdcate modeladdcate=new Modeladdcate();
                modeladdcate.setName(msector.getText().toString());
                modeladdcate.setId(mcategoryid.getText().toString());
                String category_id=modeladdcate.getId();
                databaseReference.child(category_id).setValue(modeladdcate);

                Toast.makeText(categories.this,"Sector Created",Toast.LENGTH_SHORT).show();

                Intent inte=new Intent(categories.this,categories.class);
                startActivity(inte);

                /*Map<String,Object> dataTosave=new HashMap<String, Object>();
                dataTosave.put(SECTOR_KEY,sectorname);
                db.set(dataTosave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG,"Document has been saved");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Document was not saved!",e);
                    }
                });*/



            }
        });
        dialog.setView(maddcate);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }



}
