package com.example.jstyle;

//class JsonTask extends AsyncTask<String, String, String> {
//
//    protected void onPreExecute() {
//        super.onPreExecute();
//
//        pd = new ProgressDialog(MainActivity.this);
//        pd.setMessage("Please wait");
//        pd.setCancelable(false);
//        pd.show();
//    }
//
//    protected String doInBackground(String... params) {
//
//
//        HttpURLConnection connection = null;
//        BufferedReader reader = null;
//
//        try {
//            URL url = new URL(params[0]);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//
//
//            InputStream stream = connection.getInputStream();
//
//            reader = new BufferedReader(new InputStreamReader(stream));
//
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line+"\n");
//                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//            }
//
//            return buffer.toString();
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        if (pd.isShowing()){
//            pd.dismiss();
//        }
//        txtJson.setText(result);
//    }
//}
