//Sample retrofit code

/*String photo_url = "https://maps.googleapis.com/maps/api/place/photo?photo_reference="

List<PlaceModel> suggestions = new ArrayList<>();

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl(API.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build();
API api = retrofit.create(API.class);

Call<JSONObject> call = api.getAutoComplete(string, key);
call.enqueue(new Callback<JSONObject>() {
    @Override
    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
        try {
            JSONObject res = response.body();
            JSONArray results = res.get("results");

            for (int i = 0; i < results.size(), i++) {
                JSONObject obj = results.get(i);
                if (obj.get("photos").size() > 0) {
                    PlaceModel model = new PlaceModel(obj.get("name"), photo_uri + obj.get("photos").get(0).get("photo_reference") + "&key=" + key, 
                    obj.get("geometry").get("location").get("lat"), obj.get("geometry").get("location").get("lng"));
                    suggestions.add(model);
                } else {
                    PlaceModel model = new PlaceModel(obj.get("name"), null,
                    obj.get("geometry").get("location").get("lat"), obj.get("geometry").get("location").get("lng"));
                    suggestions.add(model);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }     
    }

    @Override
    public void onFailure(Call<JSONObject> call, Throwable t) {

    }
});

List<PlaceModel> places = new ArrayList<>();

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl(API.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build();
API api = retrofit.create(API.class);

Call<JSONObject> call = api.getNearby(lat + "," + lng, type, key);
call.enqueue(new Callback<JSONObject>() {
    @Override
    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
        try {
            JSONObject res = response.body();
            JSONArray results = res.get("results");

            for (int i = 0; i < results.size(), i++) {
                JSONObject obj = results.get(i);
                if (obj.get("photos").size() > 0) {
                    PlaceModel model = new PlaceModel(obj.get("name"), photo_uri + obj.get("photos").get(0).get("photo_reference") + "&key=" + key, 
                    obj.get("geometry").get("location").get("lat"), obj.get("geometry").get("location").get("lng"));
                    suggestions.add(model);
                } else {
                    PlaceModel model = new PlaceModel(obj.get("name"), null,
                    obj.get("geometry").get("location").get("lat"), obj.get("geometry").get("location").get("lng"));
                    suggestions.add(model);
                } 
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }     
    }

    @Override
    public void onFailure(Call<JSONObject> call, Throwable t) {

    }
});*/