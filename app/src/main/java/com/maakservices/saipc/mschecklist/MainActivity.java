package com.maakservices.saipc.mschecklist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.maakservices.saipc.mschecklist.Activities.UponArrivalCheckList;
import com.maakservices.saipc.mschecklist.Activities.PreDepartureCheckList;
import com.maakservices.saipc.mschecklist.Activities.Preferences;
import com.maakservices.saipc.mschecklist.Controllers.ItemListData;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private RelativeLayout bottomSelectionRL;
    private ImageView splashIconImageView;
    private LinearLayout main_layout;
    //    private AdView mAdview;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(getApplicationContext());
        try {
            if (sessionManager.getSpecificStringValue(SessionManager.CURRENT_PAGE).equals("Post")) {
                intent = new Intent(getApplicationContext(), UponArrivalCheckList.class);
                startActivity(intent);
            } else if (sessionManager.getSpecificStringValue(SessionManager.CURRENT_PAGE).equals("Pre")) {
                intent = new Intent(getApplicationContext(), PreDepartureCheckList.class);
                startActivity(intent);
            }
        } catch (NullPointerException e) {
            System.out.println(" null pointer " + e);
        }
        setContentView(R.layout.activity_main);

        ImageView preDepartureImageView = (ImageView) findViewById(R.id.preDepartureImageView);
        ImageView postDepartureImageView = (ImageView) findViewById(R.id.postDepartureImageView);

        bottomSelectionRL = (RelativeLayout) findViewById(R.id.bottomSelectionRelativeLayout);
        main_layout = (LinearLayout) findViewById(R.id.main_activity_layout);

//        bottomSelectionRL.setVisibility(View.INVISIBLE);
        TextView preferencesTextView = (TextView) findViewById(R.id.preferencesTextView);


        System.out.println("SessionManager.GLOBAL_DATA_INSERTED : " + sessionManager.getSpecificBooleanValue(SessionManager.GLOBAL_DATA_INSERTED));
        if (!sessionManager.getSpecificBooleanValue(SessionManager.GLOBAL_DATA_INSERTED)) {
            ProgressDialog startUpProgressDialog = new ProgressDialog(MainActivity.this);
            startUpProgressDialog.setMessage("Preparing for startup");
            startUpProgressDialog.show();
            insertGlobalDataIntoDatabase();
            startUpProgressDialog.dismiss();
        }

//        StartFirstAnimation();

        preDepartureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), PreDepartureCheckList.class);
                startActivity(intent);
            }
        });
        postDepartureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), UponArrivalCheckList.class);
                startActivity(intent);
            }
        });
        preferencesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Preferences.class);
                startActivity(intent);
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            // Using handler with postDelayed called runnable run method
//            @Override
//            public void run() {
//                startSecondAnimation();
//            }
//        }, 2800); // wait for n seconds
    }

    private void insertGlobalDataIntoDatabase() {

        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());

        //DOCUMENTS data entry
        databaseHandler.addCheckListItem(new ItemListData("doc-1", "Form I-20", "Possibly in the envelope in which it was received.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-2", "Passport", "Visa with passport.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-4", "SEVI fee receipt", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-5", "Immunization form", "Most of the universities will ask you to fill one.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-6", "Health insurance", "Its better to go with the health insurance provided by your university. Its easy for reimbursement but expensive when compared to indian insurance. Indian overseas insurance work in US and are very cheap but reimbursement may be difficult. Take help from seniors and people living in US.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-7", "Vaccination certificates", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-8", "Air ticket", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-9", "Original transcripts", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-10", "Original mark sheets", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-11", "University letters", "Acceptance email and other correspondence.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-12", "GRE/TOEFL/IELTS reports ", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-13", "Final year degree", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-15", "Diary/note of imp contacts & emails", "In case if you loose your phone, u still have imp contacts.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-16", "School/college leaving certificates", null, "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-18", "Passport photos", "Get enough copies of passport photos for both US and Indian sizes.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-19", "Work experience letters", "Experience letters, relieving letter, appraisals or increments, salary & tax slips.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-20", "Cash in USD & INR", "Check for cash requirements according to your town/state in US. A few hundred dollars and 10,000 rupees would be perfect.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-21", "Scan all docs", "Scan all documents you have and backup them on cloud and your laptop as well.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-22", "Leave copies in India", "What ever documents you take along with you, leave at least a set of photocopies of them at your home.", "M", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-14", "Final year project/thesis", null, "O", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-3", "International Driving Permit", "Better to have one for driving in the US. Also you can use it as an identity. You won't be carrying your passport always with you.", "O", "Documents", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("doc-17", "Birth certificate", null, "O", "Documents", 0, 0, 0, "Pre", 0, null));

        //HEALTH data entry
        databaseHandler.addCheckListItem(new ItemListData("med-1", "Immunization", "Check what all vaccines are made mandatory by your university and get them administered.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-2", "Full body checkup", "Specially dental and eye. Its very expensive out there in US and not always covered by health insurance.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-3", "All previous medical prescriptions", "It will help you find your medicines in US.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-4", "Preferred medicines ", "Get all your preferred/current medicines with longer expiry date.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-5", "Dressng kit", "Cotton, ointments, bandage, guaze etc.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-6", "Antiseptic liquid", null, "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-7", "Crepe bandage", "For sprains.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-8", "Soframycin/betnovate", "For cuts and wounds.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-9", "Gelusil/pudinhara/Zinetac", "For acidity & indigestion.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-10", "Balms", "Iodex, Amrutanjan, Sloan balm, zandu balm which ever you prefer.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-11", "Vicks", "For cold.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-12", "Pain reliever spray", "Volini, Rely spray etc.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-13", "Combiflam/ibuprofen/crocin", "Painkillers", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-14", "Erythromycin", "Throat infection.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-15", "Paracetamol", "Fever & pain.(Known as Tylenol in US).", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-16", "Multivitamins/Calcium tabs", "Any specific suppliments if you take.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-17", "Electrolytes/Ors", "Electral powder, glucose or energizing drinks etc.", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-18", "Cough syrup", "Adulsa, Ascoril, Benadryl or Torex etc", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-19", "Itch reliever", "Creams, powder or gels, whatever is preffered", "M", "Medicines", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("med-20", "Homeopathic medicine", "If needed", "M", "Medicines", 0, 0, 0, "Pre", 0, null));

        //CLOTHES data entry
        databaseHandler.addCheckListItem(new ItemListData("clo-1", "Inner wear", "Get at least 10 sets of them. Laundry is not an everyday thing there.", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-2", "Jeans (Blue/black)", "get 4-5 jeans from India. You need to buy more in US especially if you are going to cold states.", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-3", "T-shirts", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-4", "Formal shirts/trousers", "2 sets would be enough. You may need them for orientations, meetings and interviews", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-5", "Casual shirts/trousers", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-6", "Blazer", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-7", "Towel", "couple of large and hand towels for everyday use", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-8", "Napkins 2-3", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-9", "Handkerchief 4-7", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-10", "Track suits", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-11", "Jackets and sweaters", "Decide according to your place of stay in US.(Min 2-3). Also spring sessions are pretty cold, you may come across chilling cold weather right after stepping into US. Remember, wearing two thin jackets are better than one thick. Plan accordingly", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-12", "Scarf", "Thick scarf to cover your neck area during winters and a regular scarf for summers", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-13", "Ethnic wear", "For parties and festivals.", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-14", "(Boys) Kurta", "Anything like bangali, sherwani or jhabba.", "O", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-15", "(Girls) Sarees", null, "O", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-16", "(Girls) Salwar suits", null, "O", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-17", "(Girls) Skirts", null, "O", "Clothes", 0, 0, 0, "Pre", 0, null));

        databaseHandler.addCheckListItem(new ItemListData("clo-18", "Socks 4-7", "Short & long.", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-19", "Hand gloves", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-20", "Woolen cap", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-21", "Casual cap", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-22", "Shorts", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-23", "Bedsheets 2", null, "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-24", "Blanket/Rajai/Rug", "Make sure they are not heavy", "M", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-25", "Pillow", "If you have your special one or if its very light in weight.", "O", "Clothes", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("clo-26", "Pillow cover", null, "O", "Clothes", 0, 0, 0, "Pre", 0, null));

        //UTENSILS data entry
        databaseHandler.addCheckListItem(new ItemListData("ute-1", "Pressure cooker 3-7 ltrs", "Talk with your companions and save your luggage weight by sharing pressure cooker with others.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-2", "Frying pans", "Keep in mind, along with curries you may also want to cook rotis. Purchase deep and shallow pans accordingly. Also look for their weight.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-3", "Knives", "1-2 knives in luggage are enough. Don't carry more. Airlines may have issues with it.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-4", "Spoons & Forks 3-4", null, "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-5", "Chopping board", "Share with your companions and save weight.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-6", "Cups & bowls 2-3", "Buy more in US, you need microwave safe cups.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-7", "Plates small & large 1-2", "Buy more in US, you need microwave safe plates.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-8", "Peeler", null, "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-9", "Water bottles 1-2", null, "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-10", "Containers & boxes", null, "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-11", "Roti making board & roller", "Share with your companions and save weight.", "M", "Utensils", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ute-12", "Tea strainer", "Share with your companions", "M", "Utensils", 0, 0, 0, "Pre", 0, null));

        //GIRLS/BOYS data entry
        databaseHandler.addCheckListItem(new ItemListData("gb-1", "(Boys) Shaving kit", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-2", "(Boys) After shave", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-3", "(Boys) Trimmer/shaver", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-4", "(Boys) Neck ties", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-5", "(Boys) Wallets", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));

        databaseHandler.addCheckListItem(new ItemListData("gb-6", "(Girls) Makeup kit", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-7", "(Girls) Hair bands/clips", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-8", "(Girls) Nail polish & remover", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-9", "(Girls) Jewelry", "Do not carry gold in large amounts. Imitation jewelry is a good option", "O", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-10", "(Girls) Purse/Hand bags", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-11", "(Girls) Wedges/heels", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));

        databaseHandler.addCheckListItem(new ItemListData("gb-12", "Passport bag", "A small passport bag with strap will be very useful to keep passport handy.", "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-13", "Belts (Min 2)", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-14", "Glasses/Eye lenses", "As many you want. Very expensive out there", "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-15", "Sunglasses", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-16", "Wrist watches", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-17", "Deodorant/cologne", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-18", "Chappals/Sandals", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-19", "Formal shoes", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-20", "Casual shoes/sneakers", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-21", "Safety pins", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-22", "Sewing kit", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-23", "Umbrella", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-24", "Backpack", null, "M", "Girls/Boys", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("gb-25", "Mirror", "Apartments usually have them pre-installed", "O", "Girls/Boys", 0, 0, 0, "Pre", 0, null));

        //COOKING data entry
        databaseHandler.addCheckListItem(new ItemListData("cook-1", "All kind of masala", "Your mom knows well. Get her help", "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-2", "Pickles (dry)", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-3", "few soup packets", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-4", "Tea powder/bags", null, "O", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-5", "Few coffee sachets", null, "O", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-6", "Milk powder", null, "O", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-7", "Few sweets (dry)", null, "O", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-8", "Ready to eat foods", "For initial days", "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-9", "Few Instant mix packets", "Preparations like upma, gulab jamun, sambhar etc as per need.", "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-10", "Papad packet", null, "O", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-11", "Nutmeg", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-12", "Cinnamon", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-13", "Bay Leaf", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-14", "Cardamon", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-15", "Cumin seeds", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-16", "Turmeric powder", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-17", "Chilli powder", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-18", "Asafoetida", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("cook-19", "Clove", null, "M", "Cooking", 0, 0, 0, "Pre", 0, null));

        //ELECTRONICS data entry
        databaseHandler.addCheckListItem(new ItemListData("elec-1", "Pen drive 1-2", null, "M", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-2", "External hard drive", null, "O", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-3", "Laptop", "Load your laptop with useful stuff like free books and movies. Stay away from piracy, US has strict laws for it", "M", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-4", "Laptop and mobile charger", null, "M", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-5", "Headphones with mic", null, "M", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-6", "Scientific calculator", "Some majors need calculators, others do not. Check your course catalog for better idea", "M", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-7", "Plug/pin converters", null, "M", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-8", "Camera", null, "O", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-9", "Keyboard & mouse", null, "O", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-10", "Thermometer", null, "O", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-11", "Screwdriver set", "For minor repairs", "O", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-12", "Hair dryer", null, "O", "Electronics", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("elec-13", "Straightner", null, "O", "Electronics", 0, 0, 0, "Pre", 0, null));

        //TOILETRIES data entry
        databaseHandler.addCheckListItem(new ItemListData("toi-1", "Tooth brush", "Toiletries are cheap there, carry minimal of these for initial days", "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-2", "Tooth paste", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-3", "Shampoo & conditioner", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-4", "Soap/body wash", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-5", "Hair oil", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-6", "Moisturizer", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-7", "Lip balm", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-8", "Preferred creams", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-9", "Nail cutter", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-10", "Hair brush/comb", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-11", "Ear buds", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-12", "Detergent", "Liquid preferred over powder, but in less amounts", "O", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-13", "Few tissue", null, "M", "Toiletries", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("toi-14", "Face wipes", null, "O", "Toiletries", 0, 0, 0, "Pre", 0, null));

        //STATIONERIES data entry
        databaseHandler.addCheckListItem(new ItemListData("sta-1", "Pencils 2-3", "Wooden & mechanical.", "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-2", "Eraser 1-2", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-3", "Stapler with pins", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-4", "Tapes 1-2", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-5", "Indian calendar", "Will help you find Indian festivals and dates.", "O", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-6", "Marker & highlighter", null, "O", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-7", "Pens", "Few ball pens, gel pens, colored pens.", "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-8", "Glue", "Feviquick, fevistick, fevicol.", "O", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-9", "Keychains 2-3", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-10", "U clips & paper clamps", null, "O", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-11", "Scissors", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-12", "Ruler", null, "O", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-13", "Few rough papers", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-14", "Long book", "Plain and ruled", "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-15", "A4 size papers", "Few of them, bit expensive out there", "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-16", "Files & folders", null, "M", "Stationery", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("sta-17", "Pad locks(Small)", "For lockers at sports zone or library", "M", "Stationery", 0, 0, 0, "Pre", 0, null));

        //EXTRA data entry
        databaseHandler.addCheckListItem(new ItemListData("ext-1", "Weighing machine/scale", "For measuring baggage weight before departure.", "M", "Extras", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ext-2", "Arrange a vehicle for airport", "Arrange a vehicle before hand for your ride till the airport. You may need a bigger one considering your luggage", "M", "Extras", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ext-3", "Meet relatives and friends", "You might possibly meet them again after an year or more. Spend enough time with them.", "M", "Extras", 0, 0, 0, "Pre", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("ext-4", "Set up video call facility", "This must be your first international journey, and you might want to see your loved ones. Try to arrange video call facility for them before you leave", "M", "Extras", 0, 0, 0, "Pre", 0, null));


        //-----------------------------UPON ARRIVAL data entry-----------------------------------------


        databaseHandler.addCheckListItem(new ItemListData("per-1", "Call and inform parents", "This is the first thing you should do after stepping in US", "M", "Personal", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("per-2", "Finalize accommodation", null, "M", "Personal", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("per-3", "Arrange utilities & internet", "Check with your accommodation and apply for utilities if its not included", "M", "Personal", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("per-4", "Set up bank account", null, "M", "Personal", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("per-5", "Get a phone number", null, "M", "Personal", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("per-6", "Complete your form I-94", null, "M", "Personal", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("per-7", "Money transfer", "Try to complete all monetary procedures as soon as possible.", "M", "Personal", 0, 0, 0, "Post", 0, null));


        databaseHandler.addCheckListItem(new ItemListData("coll-1", "Report to office of int'l admissions", null, "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-2", "Attend orientations", "There may be one or more orientations at your college. Try to attend all of them, you will meet new individuals there.", "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-3", "Register for courses", null, "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-4", "Pay college fees as scheduled", null, "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-5", "Health insurance", "Check for health insurance if you are not enrolled with the university's health insurance", "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-6", "Meet your adviser", "You may need to take appointment. Plan accordingly.", "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-7", "Get student ID", null, "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-8", "Apply for on-campus jobs", "There are job portals or application desks where you can apply for on-campus jobs. Get help from seniors", "M", "College", 0, 0, 0, "Post", 0, null));
        databaseHandler.addCheckListItem(new ItemListData("coll-9", "Check with assistantships", null, "M", "College", 0, 0, 0, "Post", 0, null));

        System.out.println("GLOBAL INSERTION LAST LINE");

        sessionManager.setSpecificBooleanDetail(SessionManager.GLOBAL_DATA_INSERTED, true);

    }

    private void StartFirstAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha_slow);
        anim.reset();
        main_layout.clearAnimation();
        main_layout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.appIconSplashLayout);
        rl.clearAnimation();
        rl.startAnimation(anim);
    }

    private void startSecondAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        bottomSelectionRL.clearAnimation();
        bottomSelectionRL.startAnimation(anim);
        bottomSelectionRL.setVisibility(View.VISIBLE);
    }
}
