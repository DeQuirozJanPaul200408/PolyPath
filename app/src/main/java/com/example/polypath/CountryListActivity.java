package com.example.polypath;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CountryListActivity extends AppCompatActivity {

    ListView languageListView;
    String[] languages = {"China (Mandarin)", " Italy (Italian)", "Japan (Japanese)", "Korea (Korean)", "Spain (Spanish)"};
    int[] flags = {
            R.drawable.china,
            R.drawable.italy,
            R.drawable.japan,
            R.drawable.korea,
            R.drawable.sspian,
    };

    LanguageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        languageListView = findViewById(R.id.languageListView);
        adapter = new LanguageAdapter(this, languages, flags);
        languageListView.setAdapter(adapter);
    }

    class LanguageAdapter extends BaseAdapter {

        Context context;
        String[] languages;
        int[] flags;
        LayoutInflater inflater;
        int selectedPos = -1;

        LanguageAdapter(Context context, String[] languages, int[] flags) {
            this.context = context;
            this.languages = languages;
            this.flags = flags;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return languages.length;
        }

        @Override
        public Object getItem(int i) {
            return languages[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null)
                view = inflater.inflate(R.layout.list_item_language, viewGroup, false);

            ImageView imgFlag = view.findViewById(R.id.imgFlag);
            TextView tvLanguage = view.findViewById(R.id.tvLanguage);
            Button btnSelect = view.findViewById(R.id.btnSelect);

            imgFlag.setImageResource(flags[i]);
            tvLanguage.setText(languages[i]);


            if (selectedPos == i) {
                btnSelect.setText("Selected");
                btnSelect.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));
            } else {
                btnSelect.setText("Select");
                btnSelect.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
            }

            btnSelect.setOnClickListener(v -> {
                if (selectedPos != i) {
                    selectedPos = i;
                    notifyDataSetChanged();

                    Intent intent;
                    switch (i) {
                        case 0:
                            intent = new Intent(context, chinalanguage.class);
                            break;
                        case 1:
                            intent = new Intent(context, italylanguange.class);
                            break;
                        case 2:
                            intent = new Intent(context, japanlanguage.class);
                            break;
                        case 3:
                            intent = new Intent(context, koreanlanguage.class);
                            break;
                        case 4:
                            intent = new Intent(context, spainlanguage.class);
                            break;
                        default:
                            return;
                    }
                    context.startActivity(intent);
                }
            });

            return view;
        }
    }
}