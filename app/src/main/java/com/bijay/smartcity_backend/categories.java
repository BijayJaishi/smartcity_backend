package com.bijay.smartcity_backend;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bijay.smartcity_backend.firebase_models.Modeladdcompo;
import com.bijay.smartcity_backend.firebase_models.Modeladdelement;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class categories extends AppCompatActivity {

    private static final String SECTOR_KEY ="sector";
    private static final String TAG ="12" ;
    private static final int GALLERY_INTENT = 101;

    Uri uri;
    StorageReference profileref;
    String imageUrl;
   ImageButton melepic;

    private StorageTask muploading;

    Button maddnew;
    Button maddnewsec,maddnewele;
    DatabaseReference databaseReference,mdatabaseReference2,mdatabaseReference3;
    //DocumentReference db = FirebaseFirestore.getInstance().document("Smartcity/Sectors");

    RecyclerView recyclerView,mrecyclerView2,mrecyclerView3;
    Myadapter myadapter;
    Myadapter2 myadapter2;
    Myadapter3 myadapter3;
    List<Modeladdcate>listdata;
    List<Modeladdcompo>listdata2;
    List<Modeladdelement>listdata3;
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
        //for component
        maddnewsec=findViewById(R.id.addnewsec);

        //for element
        maddnewele=findViewById(R.id.addnewele);



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


        ///for element

        mrecyclerView3 = findViewById(R.id.recyclerView3);
        mrecyclerView3.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView3.setLayoutManager(layoutManager);
        mrecyclerView3.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView3.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        listdata3=new ArrayList<>();
        myadapter3=new Myadapter3(listdata3);
        FDB3=FirebaseDatabase.getInstance();





        //for element
        mdatabaseReference3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://work1-1da82.firebaseio.com/Element");
        //for component
        mdatabaseReference2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://work1-1da82.firebaseio.com/Component");
        //for category
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


        maddnewele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addele();
            }
        });




        //getcate();
        getdatafirebase();
        getdatafirebase2();
        getdatafirebase3();



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


        //for component

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


        ///for element

        btn3 = (ToggleButton) findViewById(R.id.radioButton3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// progress dialog
//                progressDialog = new ProgressDialog(categories.this);
//                progressDialog.setMessage("Loading ....");
//                progressDialog.setCancelable(true);
//                progressDialog.show();
                expandableRelativeLayout3 = (ExpandableRelativeLayout)findViewById(R.id.btnexpand3);
                expandableRelativeLayout3.toggle();

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

                Modeladdcompo data2=dataSnapshot.getValue(Modeladdcompo.class);
                listdata2.add(data2);
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

    //for element

    void getdatafirebase3(){


        mdatabaseReference3=FDB3.getReferenceFromUrl("https://work1-1da82.firebaseio.com/Element");
        //databaseReference.orderByChild("CategoryId").equalTo(Catid);
        mdatabaseReference3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Modeladdelement data3=dataSnapshot.getValue(Modeladdelement.class);
                listdata3.add(data3);
                mrecyclerView3.setAdapter(myadapter3);
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




    //for element


    public class Myadapter3 extends RecyclerView.Adapter<Myadapter3.MyViewHolder>{
        //Context context;
        List<Modeladdelement>listarray3;

        public Myadapter3(List<Modeladdelement> listarray3) {
            this.listarray3 = listarray3;
        }

        @NonNull
        @Override
        public Myadapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_element,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


            final Modeladdelement data3=listarray3.get(position);


            holder.txt_item_name3.setText(data3.getElement_name());
            holder.txt_item_desc3.setText(data3.getElement_desc());
            holder.txt_item_address3.setText(data3.getElement_address());
            holder.txt_item_open3.setText(data3.getElement_open());
            holder.txt_item_close3.setText(data3.getElement_close());
            holder.txt_item_phone3.setText(data3.getElement_phone());
            holder.txt_item_id3.setText(data3.getElement_id());
            String mimage=data3.getElement_photo();

            Picasso.with(categories.this )
                    .load(mimage)
                    .fit()
                    .centerCrop()
                    .into(holder.eleimage);


            holder.deleteitem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(categories.this,"Recently not in use",Toast.LENGTH_LONG).show();

                }
            });

            holder.edititem3.setOnClickListener(new View.OnClickListener() {
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
            return listarray3.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt_item_desc3,txt_item_name3,txt_item_address3,txt_item_phone3,txt_item_open3,txt_item_close3,txt_item_id3;
            Button deleteitem3,edititem3;
            ImageView eleimage;

            public MyViewHolder(View itemView) {
                super(itemView);

                txt_item_desc3 = itemView.findViewById(R.id.eleitem_desc);
                txt_item_name3 = itemView.findViewById(R.id.eleitem_name);
                txt_item_address3 =itemView.findViewById(R.id.eleitem_address);
                txt_item_open3 = itemView.findViewById(R.id.eleitem_open);
                txt_item_close3 = itemView.findViewById(R.id.eleitem_close);
                txt_item_phone3 = itemView.findViewById(R.id.eleitem_phone);
                txt_item_id3= itemView.findViewById(R.id.eleitem_id);
                deleteitem3=itemView.findViewById(R.id.eledelete);
                edititem3=itemView.findViewById(R.id.eleedit);
                eleimage=itemView.findViewById(R.id.eleitem_pic);
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
        dialog.setView(maddcompo);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }



    public void addele() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add Element");
        LayoutInflater inflater = LayoutInflater.from(this);
        View maddele = inflater.inflate(R.layout.addelement_form, null);

        final String image=imageUrl;

        final MaterialEditText melename=maddele.findViewById(R.id.element_name);
        final MaterialEditText meleaddress=maddele.findViewById(R.id.element_address);
        final MaterialEditText meleopen=maddele.findViewById(R.id.element_ophour);
        final MaterialEditText meleclose=maddele.findViewById(R.id.element_clohour);
        final MaterialEditText melephone=maddele.findViewById(R.id.element_phnumber);
        final MaterialEditText meledesc=maddele.findViewById(R.id.element_desc);
        final MaterialEditText meleid=maddele.findViewById(R.id.element_id);
        melepic=maddele.findViewById(R.id.element_pic);
        final TextView merrormsg=maddele.findViewById(R.id.errormsg);
        final RadioButton mclick =maddele.findViewById(R.id.ele_radioButton);
        final Button msubmit3=maddele.findViewById(R.id.submitelem);
        final MaterialEditText mcate_name=maddele.findViewById(R.id.cate_name);
        //final Button mcancel=maddcate.findViewById(R.id.cancel);

        mclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uri !=null) {
                    melepic.setEnabled(false);
                    mclick.setEnabled(false);
                    if (uri != null) {
                        profileref= FirebaseStorage.getInstance().getReference("ElementPhotos/" + System.currentTimeMillis()+"."+getFileExtension(uri) );//.child(uri.getLastPathSegment());//+ ".jpg");
                        muploading=profileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                imageUrl = taskSnapshot.getDownloadUrl().toString();


                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        //waitingdialog.dismiss();
                                        Toast.makeText(categories.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                    //galleryAddPic();

                }

            }
        });

        melepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent();
                intent1.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                intent1.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent1, "Select picture"), GALLERY_INTENT );


            }
        });




        msubmit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (muploading != null && muploading.isInProgress()) {

                    merrormsg.setText("* Image Upload In Progress !!");
                    merrormsg.requestFocus();

                } else {
                    merrormsg.setText("* Image Upload successful !!");
                    merrormsg.requestFocus();


                    if (uri != null) {
                        //mprogress.dismiss();
                        // msignup.setError("Profile picture Required");
                        //msignup.requestFocus();
                        //return;
                        //}
                        if (!mclick.isEnabled()) {

                            if (mcate_name.getText().toString().trim().isEmpty()) {
                                mcate_name.setError("Category name required !!");
                                mcate_name.requestFocus();
                                return;
                            }


                            if (melename.getText().toString().trim().isEmpty()) {
                                melename.setError("Element name required !!");
                                melename.requestFocus();
                                return;
                            }



                            if (meleaddress.getText().toString().trim().isEmpty()) {
                                meleaddress.setError("Address required");
                                meleaddress.requestFocus();
                                return;
                            }

                            if (meleopen.getText().toString().trim().isEmpty()) {
                                meleopen.setError("Opening hour required");
                                meleopen.requestFocus();
                                return;
                            }

                            if (meleclose.getText().toString().trim().isEmpty()) {
                                meleclose.setError("Closing hour required");
                                meleclose.requestFocus();
                                return;
                            }

                            if (melephone.getText().toString().trim().isEmpty()) {
                                melephone.setError("Phone Number required");
                                melephone.requestFocus();
                                return;
                            }

                            if (meledesc.getText().toString().trim().isEmpty()) {
                                meledesc.setError("Description required");
                                meledesc.requestFocus();
                                return;
                            }
                            if (meleid.getText().toString().trim().isEmpty()) {
                                meleid.setError("ID required");
                                meleid.requestFocus();
                                return;
                            } else {


                                Modeladdelement modeladdelement = new Modeladdelement();

                                modeladdelement.setCatename(mcate_name.getText().toString());
                                modeladdelement.setElement_name(melename.getText().toString());
                                modeladdelement.setElement_address(meleaddress.getText().toString());
                                modeladdelement.setElement_open(meleopen.getText().toString());
                                modeladdelement.setElement_close(meleclose.getText().toString());
                                modeladdelement.setElement_phone(melephone.getText().toString());
                                modeladdelement.setElement_desc(meledesc.getText().toString());
                                modeladdelement.setElement_id(meleid.getText().toString());
                                modeladdelement.setElement_photo(image);
                                String catename=modeladdelement.getCatename();
                                String elenamee=modeladdelement.getElement_name();
                                String eleid = modeladdelement.getElement_id();
                                mdatabaseReference3.child(catename).child(elenamee).setValue(modeladdelement);

                                Toast.makeText(categories.this, "Element Created", Toast.LENGTH_SHORT).show();

                                Intent inten = new Intent(categories.this, categories.class);
                                startActivity(inten);

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
                        }else {

                            merrormsg.setText("* Confirm by clicking (DONE) !!");
                            merrormsg.requestFocus();

                        }
                    }else {

                        merrormsg.setText("*  picture Required !!");
                        merrormsg.requestFocus();
                        //return;

                    }
                }
            }
        });
        dialog.setView(maddele);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            //setPic();

            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mpropic.setImageBitmap(imageBitmap);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                melepic.setImageBitmap(bitmap);
                //firebaseimageupload();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }//else if (requestCode ==CAMERA_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
        //uri = data.getData();

        //Picasso.with(signup.this).load(uri).into(mpropic);
        //}
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mine = MimeTypeMap.getSingleton();
        return mine.getExtensionFromMimeType(cR.getType(uri));
    }



}
