package com.example.moneymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChatsActivity extends AppCompatActivity {
    private static final String TAG="ChatsActivity";
    private RecyclerView recyclerView;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    //creating a variable for array list and adapter class.
    private ArrayList<ChatsModel> chatsModalArrayList;
    private MessageRVAdapter messageRVAdapter;
    public static Intent newIntent(Context context){
        Intent intent=new Intent(context,ChatsActivity.class);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats_layout);
        recyclerView=findViewById(R.id.chats_recView);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        //below line is to initialize our request queue.

        //creating a new array list
        chatsModalArrayList = new ArrayList<>();
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking if the message entered by user is empty or not.
                if (userMsgEdt.getText().toString().isEmpty()) {
                    //if the edit text is empty display a toast message.
                    Toast.makeText(ChatsActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to send message to our bot to get response.
                getResponse(userMsgEdt.getText().toString());
                //below line we are setting text in our edit text as empty
                userMsgEdt.setText("");

            }
        });
        messageRVAdapter = new MessageRVAdapter(chatsModalArrayList, this);
        //below line we are creating a variable for our linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //below line is to set layout manager to our recycler view.
        recyclerView.setLayoutManager(linearLayoutManager);
        //below line we are setting adapter to our recycler view.
        recyclerView.setAdapter(messageRVAdapter);
    }
//    private void sendMessage(String userMsg) {
//        //below line is to pass message to our array list which is entered by the user.
//        chatsModalArrayList.add(new MsgModel(userMsg, USER_KEY));
//        messageRVAdapter.notifyDataSetChanged();
//        //url for our brain
//        //make sure to add mshape for uid.
//        String url = "http://api.brainshop.ai/get?bid=167449&key=yptOoOxb9tzwblk5&uid=[uid]&msg=" + userMsg;
//        //creating a variable for our request queue.
//        RequestQueue queue = Volley.newRequestQueue(ChatsActivity.this);
//        Log.d(TAG,"....>>"+queue);
//        //on below line we are making a json object request for a get request and passing our url .
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    //in on response method we are extracting data from json response and adding this response to our array list.
//                    String botResponse = response.getString("cnt");
//                    Log.d(TAG,"....>>"+botResponse);
//                    chatsModalArrayList.add(new MsgModel(botResponse, BOT_KEY));
//                    //notifying our adapter as data changed.
//                    messageRVAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    //handling error response from bot.
//                    chatsModalArrayList.add(new MsgModel("No response", BOT_KEY));
//                    messageRVAdapter.notifyDataSetChanged();
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //error handling.
//                chatsModalArrayList.add(new MsgModel("Sorry no response found", BOT_KEY));
//                Toast.makeText(ChatsActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
//            }
//        });
//        //at last adding json object request to our queue.
//        queue.add(jsonObjectRequest);
//
//
//    }
private void getResponse(String message){
    chatsModalArrayList.add(new ChatsModel(message,USER_KEY));
    messageRVAdapter.notifyDataSetChanged();
    String url="http://api.brainshop.ai/get?bid=167449&key=yptOoOxb9tzwblk5&uid=[uid]&msg="+message;
    String BASE_URL="http://api.brainshop.ai";
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
    Call<MsgModel> call=retrofitApi.getMessage(url);
    Log.d(TAG,">>"+call);
    call.enqueue(new Callback<MsgModel>() {
        @Override
        public void onResponse(Call<MsgModel> call, retrofit2.Response<MsgModel> response) {
            Log.d(TAG,">>"+response);
            if(response.isSuccessful()){
                MsgModel msgModel=response.body();
                chatsModalArrayList.add(new ChatsModel(msgModel.getCnt(),BOT_KEY));
                messageRVAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatsModalArrayList.size());
            }
        }

        @Override
        public void onFailure(Call<MsgModel> call, Throwable t) {
            chatsModalArrayList.add(new ChatsModel("no response",BOT_KEY));
            messageRVAdapter.notifyDataSetChanged();
        }
    });

}
}
