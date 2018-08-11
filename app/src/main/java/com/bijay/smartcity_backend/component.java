package com.bijay.smartcity_backend;

import android.app.ProgressDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class component extends AppCompatActivity {
   TextView msecname;
   Button maddnewsec;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    component.Myadapter myadapter;
    List<Modeladdcompo> listdata;
    FirebaseDatabase FDB;
    //DatabaseReference DBR;
    ProgressDialog progressDialog;
    //List<Modeladdcate>modeladdcateList;
    String Catid ="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        maddnewsec=findViewById(R.id.addnewsec);
        msecname=findViewById(R.id.scename);



        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        listdata=new ArrayList<>();
        myadapter=new Myadapter(listdata);
        FDB=FirebaseDatabase.getInstance();

        //// progress dialog

        progressDialog = new ProgressDialog(component.this);
        progressDialog.setMessage("Loading ....");
        progressDialog.setCancelable(true);
        progressDialog.show();


        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://work1-1da82.firebaseio.com/Component");


        maddnewsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcompo();
            }
        });
        //add key
//        if (getIntent() != null)
//            Catid = getIntent().getStringExtra(Catid);
//
//        if (Catid!= null && Catid.isEmpty()){
            getdatafirebase();
        }





    void getdatafirebase(){


        databaseReference=FDB.getReferenceFromUrl("https://work1-1da82.firebaseio.com/Component");
        //databaseReference.orderByChild("CategoryId").equalTo(Catid);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Modeladdcompo data=dataSnapshot.getValue(Modeladdcompo.class);
                listdata.add(data);
                recyclerView.setAdapter(myadapter);
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

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

    public class Myadapter extends RecyclerView.Adapter<component.Myadapter.MyViewHolder>{
        //Context context;
        List<Modeladdcompo>listarray;

        public Myadapter(List<Modeladdcompo> listarray) {
            this.listarray = listarray;
        }

        @NonNull
        @Override
        public component.Myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_components,parent,false);
            return new component.Myadapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


            final Modeladdcompo data=listarray.get(position);


            holder.txt_item_name.setText(data.getSector_name());
            holder.txt_item_desc.setText(data.getSector_id());

            holder.deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(component.this,"Recently not in use",Toast.LENGTH_LONG).show();

                }
            });

            holder.edititem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(component.this,"Recently not in use",Toast.LENGTH_LONG).show();
                }
            });

            holder.txt_item_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(component.this,component.class);

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

                txt_item_desc = itemView.findViewById(R.id.compoitem_desc);
                txt_item_name = itemView.findViewById(R.id.compoitem_name);
                deleteitem=itemView.findViewById(R.id.compodelete);
                edititem=itemView.findViewById(R.id.compoedit);
            }
        }

        }

    private void addcompo() {

    final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add Component");
    LayoutInflater inflater = LayoutInflater.from(this);
    View maddcompo = inflater.inflate(R.layout.addcompoform, null);

    final MaterialEditText msector=maddcompo.findViewById(R.id.newcompo);
    final MaterialEditText msectorid=maddcompo.findViewById(R.id.newcompoid);
    final Button msubmit=maddcompo.findViewById(R.id.submitcompo);
    //final Button mcancel=maddcate.findViewById(R.id.cancel);

       msubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (msector.getText().toString().trim().isEmpty()) {
                msector.setError("Component name required !!");
                msector.requestFocus();
                return;
            }

            if(msectorid.getText().toString().trim().isEmpty()){
                msectorid.setError("Id required");
                msectorid.requestFocus();
                return;
            }




            Modeladdcompo modeladdcompo=new Modeladdcompo();
            Modeladdcate modeladdcate=new Modeladdcate();
            String cateid=modeladdcate.getId();
            modeladdcompo.setCategoryid(cateid);


            modeladdcompo.setSector_name(msector.getText().toString());
            modeladdcompo.setSector_id(msectorid.getText().toString());
            String id=modeladdcompo.getSector_id();
            databaseReference.child(id).setValue(modeladdcompo);

            Toast.makeText(component.this,"Component Created",Toast.LENGTH_SHORT).show();

            Intent inte=new Intent(component.this,component.class);
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
        dialog.setView(maddcompo);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    });

        dialog.show();

}

}
