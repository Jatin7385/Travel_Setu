//Sample retrofit code

/*Retrofit retrofit = new Retrofit.Builder()
    .baseUrl(API.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build();
API api = retrofit.create(API.class);

Call<JSONObject> call = api.getAutoComplete();
call.enqueue(new Callback<JSONObject>() {
    @Override
    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
        JSONObject result = response.body();
    }

    @Override
    public void onFailure(Call<JSONObject> call, Throwable t) {

    }
});*/