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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bijay.smartcity_backend.firebase_models.Modeladdcompo;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class categories extends AppCompatActivity {

    private static final String SECTOR_KEY ="sector";
    private static final String TAG ="12" ;
    Button maddnew;
    Button maddnewsec,maddnewele;
    DatabaseReference databaseReference,mdatabaseReference2,mdatabaseReference3;
    //DocumentReference db = FirebaseFirestore.getInstance().document("Smartcity/Sectors");

    RecyclerView recyclerView,mrecyclerView2,mrecyclerView3;
    Myadapter myadapter;
    Myadapter2 myadapter2;
    Myadapter myadapter3;
    List<Modeladdcate>listdata;
    List<Modeladdcompo>listdata2;
    FirebaseDatabase FDB,FDB2,FDB3;
    //DatabaseReference DBR;
    ProgressDialog progressDialog;
    //List<Modeladdcate>modeladdcateList;

    ToggleButton btn,btn2,btn3;
    ExpandableRelativeLayout expandableRelativeLayout,expandableRelativeLayout2,expandableRelativeLayout3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        maddnew=findViewById(R.id.addnew);
        maddnewsec=findViewById(R.id.addnewsec);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        listdata=new ArrayList<>();
        myadapter=new Myadapter(listdata);
        FDB=FirebaseDatabase.getInstance();


        ///for component

        mrecyclerView2 = findViewById(R.id.recyclerView2);
        mrecyclerView2.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView2.setLayoutManager(layoutManager);
        mrecyclerView2.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView2.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        listdata2=new ArrayList<>();
        myadapter2=new Myadapter2(listdata2);
        FDB2=FirebaseDatabase.getInstance();




        mdatabaseReference2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://work1-1da82.firebaseio.com/Component");
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://work1-1da82.firebaseio.com/Categories");

        maddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcate();
            }
        });

        maddnewsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcompo();
            }
        });

        //getcate();
        getdatafirebase();
        getdatafirebase2();



        btn = (ToggleButton) findViewById(R.id.radioButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// progress dialog
//                progressDialog = new ProgressDialog(categories.this);
//                progressDialog.setMessage("Loading ....");
//                progressDialog.setCancelable(true);
//                progressDialog.show();
                expandableRelativeLayout = (ExpandableRelativeLayout)findViewById(R.id.btnexpand);
                expandableRelativeLayout.toggle();

            }
        });

        btn2 = (ToggleButton) findViewById(R.id.radioButton2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// progress dialog
//                progressDialog = new ProgressDialog(categories.this);
//                progressDialog.setMessage("Loading ....");
//                progressDialog.setCancelable(true);
//                progressDialog.show();
                expandableRelativeLayout2 = (ExpandableRelativeLayout)findViewById(R.id.btnexpand2);
                expandableRelativeLayout2.toggle();

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


    //for component

    void getdatafirebase2(){


        mdatabaseReference2=FDB2.getReferenceFromUrl("https://work1-1da82.firebaseio.com/Component");
        //databaseReference.orderByChild("CategoryId").equalTo(Catid);
        mdatabaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Modeladdcompo data=dataSnapshot.getValue(Modeladdcompo.class);
                listdata2.add(data);
                mrecyclerView2.setAdapter(myadapter2);
//                if (progressDialog.isShowing()){
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


    //for component


    public class Myadapter2 extends RecyclerView.Adapter<Myadapter2.MyViewHolder>{
        //Context context;
        List<Modeladdcompo>listarray2;

        public Myadapter2(List<Modeladdcompo> listarray2) {
            this.listarray2 = listarray2;
        }

        @NonNull
        @Override
        public Myadapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_components,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


            final Modeladdcompo data2=listarray2.get(position);


            holder.txt_item_name2.setText(data2.getSector_name());
            holder.txt_item_desc2.setText(data2.getSector_id());

            holder.deleteitem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(categories.this,"Recently not in use",Toast.LENGTH_LONG).show();

                }
            });

            holder.edititem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(categories.this,"Recently not in use",Toast.LENGTH_LONG).show();
                }
            });

////            holder.txt_item_name2.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////
////                    Intent intent=new Intent(categories.this,categories.class);
////
////                    startActivity(intent);
//
//
//
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return listarray2.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt_item_desc2,txt_item_name2;
            Button deleteitem2,edititem2;

            public MyViewHolder(View itemView) {
                super(itemView);

                txt_item_desc2 = itemView.findViewById(R.id.compoitem_desc);
                txt_item_name2 = itemView.findViewById(R.id.compoitem_name);
                deleteitem2=itemView.findViewById(R.id.compodelete);
                edititem2=itemView.findViewById(R.id.compoedit);
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

    private void addcompo() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add Component");
        LayoutInflater inflater = LayoutInflater.from(this);
        View maddcompo = inflater.inflate(R.layout.addcompoform, null);

        final MaterialEditText msector2=maddcompo.findViewById(R.id.newcompo);
        final MaterialEditText msectorid2=maddcompo.findViewById(R.id.newcompoid);
        final Button msubmit2=maddcompo.findViewById(R.id.submitcompo);
        //final Button mcancel=maddcate.findViewById(R.id.cancel);

        msubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msector2.getText().toString().trim().isEmpty()) {
                    msector2.setError("Component name required !!");
                    msector2.requestFocus();
                    return;
                }

                if(msectorid2.getText().toString().trim().isEmpty()){
                    msectorid2.setError("Id required");
                    msectorid2.requestFocus();
                    return;
                }




                Modeladdcompo modeladdcompo=new Modeladdcompo();
                Modeladdcate modeladdcate=new Modeladdcate();
                String cateid=modeladdcate.getId();
                modeladdcompo.setCategoryid(cateid);


                modeladdcompo.setSector_name(msector2.getText().toString());
                modeladdcompo.setSector_id(msectorid2.getText().toString());
                String id=modeladdcompo.getSector_id();
                mdatabaseReference2.child(id).setValue(modeladdcompo);

                Toast.makeText(categories.this,"Component Created",Toast.LENGTH_SHORT).show();

                Intent inte=new Intent(categories.this,component.class);
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
