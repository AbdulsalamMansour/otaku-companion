package com.example.abdulsalam.otakucompanion.OtakuWidget;


import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.abdulsalam.otakucompanion.Model.SharedClasses.AnimeDetailed;

public class OtakuWidgetService extends IntentService {

    public static final String ACTION_UPDATE = "com.example.abdulsalam.oatkucompanion.action.update_widget";
    private static String synopsis;
    private static AnimeDetailed animeDetailed;

    public OtakuWidgetService( ) {
        super("OtakuWidgetService");
    }

    public static void startActionUpdateWidgets(Context context, String mSynopsis, AnimeDetailed mAnimeDetailed) {

        Intent intent = new Intent(context, OtakuWidgetService.class);
        intent.setAction(ACTION_UPDATE);
        synopsis=mSynopsis;
        animeDetailed = mAnimeDetailed;
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE.equals(action)) {

                handleActionUpdateWidgets(this );

            }

        }


    }

    private void handleActionUpdateWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, OtakuWidget.class));
        OtakuWidget.updateAnimeSynopsis(context, appWidgetManager,synopsis, animeDetailed,appWidgetIds);

    }


}


